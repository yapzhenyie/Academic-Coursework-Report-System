package oodj.group5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import oodj.group5.guis.AdminPage;
import oodj.group5.guis.LecturerPage;
import oodj.group5.guis.LoginPage;
import oodj.group5.guis.StudentPage;
import oodj.group5.objects.Result;
import oodj.group5.objects.Intake;
import oodj.group5.objects.Module;
import oodj.group5.objects.users.Admin;
import oodj.group5.objects.users.Lecturer;
import oodj.group5.objects.users.Student;
import oodj.group5.objects.users.User;
import oodj.group5.utils.DialogUtils;
import oodj.group5.utils.LoggerManager;
import oodj.group5.utils.UserType;

public class AcademicCourseworkReportSystem {

    private static LoginPage loginPage;
    private static AdminPage adminPage;
    private static LecturerPage lecturerPage;
    private static StudentPage studentPage;

    private static LoggerManager loggerManager;

    public static ArrayList<User> USERS = new ArrayList<>();
    public static ArrayList<Intake> INTAKES = new ArrayList<>();
    public static ArrayList<Module> MODULES = new ArrayList<>();
    public static ArrayList<Result> RESULTS = new ArrayList<>();

    public static User currentLoginUser;

    public static void main(String[] args) {
        startProgram();
    }

    /**
     * Initialize the program.
     */
    public static void startProgram() {
        // Loading logs file to enable log activity.
        loggerManager = new LoggerManager();
        loggerManager.addLogMessage("Loading the program...");

        initializeFiles();

        loginPage = new LoginPage();
    }

    /**
     * Stop the program.
     */
    public static void stopProgram() {
        if (loggerManager == null) {
            DialogUtils.showErrorMessageDialog(null, "Unexpected error occur!");
            return;
        }
        if (AcademicCourseworkReportSystem.currentLoginUser != null) {
            AcademicCourseworkReportSystem.getLoggerManager().addLogoutMessage(AcademicCourseworkReportSystem.currentLoginUser.getName()
                    + "(" + AcademicCourseworkReportSystem.currentLoginUser.getUsername() + ") logged out.");
        }
        saveFiles();
        loggerManager.addLogMessage("Save the files and stop the program.");
        loggerManager.saveLogFile();
    }

    public static void initializeFiles() {
        try {
            File usersFile = new File("users.txt");

            Scanner s = new Scanner(usersFile);

            while (s.hasNext()) {
                String username = s.nextLine(); // must be unique.
                String password = s.nextLine();
                String name = s.nextLine();
                UserType userType = null;
                try {
                    userType = UserType.valueOf(s.nextLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    loggerManager.addLogMessage("Failed to identify user: " + username + "'s user type.");
                }
                s.nextLine(); // Empty line
                if (userType != null) {
                    User user = null;
                    switch (userType) {
                        case ADMIN:
                            user = new Admin(username, password, name);
                            break;
                        case LECTURER:
                            user = new Lecturer(username, password, name);
                            break;
                        case STUDENT:
                            user = new Student(username, password, name);
                            break;
                    }
                    USERS.add(user);
                }
            }
            s.close();
            loggerManager.addLogMessage(USERS.size() + " users have been loaded.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            File intakesFile = new File("intakes.txt");

            Scanner s = new Scanner(intakesFile);
            while (s.hasNext()) {
                String intakeCode = s.nextLine();
                Intake intake = new Intake(intakeCode);
                INTAKES.add(intake);
            }
            s.close();
            loggerManager.addLogMessage(INTAKES.size() + " intakes have been loaded.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            File modulesFile = new File("modules.txt");

            Scanner s = new Scanner(modulesFile);
            int invalidModules = 0;
            while (s.hasNext()) {
                String moduleCode = s.nextLine();
                String moduleName = s.nextLine();
                User assignedLecturer = Lecturer.valueOf(s.nextLine());
                if (assignedLecturer == null) {
                    System.out.println(moduleCode + " lecturer not found.");
                }
                String[] assessments = s.nextLine().split("\\;");
                String[] str = s.nextLine().split("\\;");
                if (str.length == 1 && str[0].equals("")) {
                    str = new String[]{};
                }
                int[] marksAllocated = new int[str.length];
                for (int i = 0; i < str.length; i++) {
                    try {
                        marksAllocated[i] = Integer.parseInt(str[i]);
                    } catch (NumberFormatException e) {
                        invalidModules++;
                        System.out.println(moduleCode + " marks allocated format error.");
                        e.printStackTrace();
                    }
                }
                s.nextLine();

                Module module = new Module(moduleCode, moduleName, assignedLecturer, assessments, marksAllocated);
                MODULES.add(module);
            }
            s.close();
            loggerManager.addLogMessage(MODULES.size() + " modules have been loaded.");
            if (invalidModules > 0) {
                loggerManager.addErrorMessage("Found " + invalidModules + " modules have incorrect format and have been deleted.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            File studentsFile = new File("students.txt");

            Scanner s = new Scanner(studentsFile);
            int students = 0;
            while (s.hasNext()) {
                String currentLine; // caching
                String username = s.nextLine();
                String tpNumber = s.nextLine();
                String icNumber = s.nextLine();
                Intake intake = Intake.valueOf(currentLine = s.nextLine());
                if (intake == null) {
                    intake = new Intake(currentLine);
                    INTAKES.add(intake);
                }
                String[] modulesEnroll = s.nextLine().split("\\;");
                s.nextLine();

                User user = Student.valueOf(username);
                if (user != null) {
                    Student student = (Student) user;
                    student.setTPNumber(tpNumber);
                    student.setICNumber(icNumber);
                    student.setIntake(intake);
                    for (String moduleEnroll : modulesEnroll) {
                        Module module = Module.valueOf(moduleEnroll);
                        if (module != null) {
                            student.addModuleEnroll(module);
                        }
                    }
                    students++;
                }
            }
            s.close();
            int invalidStudents = 0;
            for (Student invalidStudent : Student.getStudents()) {
                if (invalidStudent.getTPNumber() == null || invalidStudent.getICNumber() == null || invalidStudent.getIntake() == null) {
                    USERS.remove(invalidStudent);
                    invalidStudents++;
                }
            }
            loggerManager.addLogMessage(students + " students have been loaded.");
            if (invalidStudents > 0) {
                loggerManager.addErrorMessage("Found " + invalidStudents + " students have incomplete information and have been deleted.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            File resultsFile = new File("results.txt");

            Scanner s = new Scanner(resultsFile);
            int invalidData = 0;
            while (s.hasNext()) {
                String currentLine; // caching
                String username = s.nextLine();
                Intake intake = Intake.valueOf(currentLine = s.nextLine());
                if (intake == null) {
                    intake = new Intake(currentLine);
                    INTAKES.add(intake);
                }
                Module module = Module.valueOf(s.nextLine());
                if (module == null) {
                    invalidData++;
                    continue;
                }
                String[] marks = s.nextLine().split("\\;");
                String comment = s.nextLine();
                s.nextLine();

                int[] marksAllocated = new int[marks.length];
                for (int i = 0; i < marks.length; i++) {
                    try {
                        marksAllocated[i] = Integer.parseInt(marks[i]);
                    } catch (NumberFormatException e) {
                        invalidData++;
                        System.out.println("Username: " + username + ", Module: " + intake.getIntakeCode() + ", marks allocated format is incorrect.");
                        e.printStackTrace();
                    }
                }
                Result assessment = new Result(username, intake, module, marksAllocated, comment);
                RESULTS.add(assessment);
            }
            s.close();
            loggerManager.addLogMessage(RESULTS.size() + " module results have been loaded.");
            if (invalidData > 0) {
                loggerManager.addErrorMessage("Found " + invalidData + " module results failed to load and will be removed.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveFiles() {
        try {
            PrintWriter usersFile = new PrintWriter("users.txt");
            for (User user : USERS) {
                usersFile.println(user.getUsername());
                usersFile.println(user.getPassword());
                usersFile.println(user.getName());
                usersFile.println(user.getUserType().toString());
                usersFile.println();
            }
            usersFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            PrintWriter studentsFile = new PrintWriter("students.txt");
            for (Student student : Student.getStudents()) {
                studentsFile.println(student.getUsername());
                studentsFile.println(student.getTPNumber());
                studentsFile.println(student.getICNumber());
                studentsFile.println(student.getIntake().toString());
                StringBuilder sb1 = new StringBuilder();
                for (Module module : student.getModulesEnroll()) {
                    sb1.append(module.getModuleCode());
                    sb1.append(";");
                }
                if (sb1.toString().endsWith(";")) {
                    sb1.deleteCharAt(sb1.length() - 1);
                }
                studentsFile.println(sb1.toString());
                studentsFile.println();
            }
            studentsFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            PrintWriter intakesFile = new PrintWriter("intakes.txt");
            for (Intake intake : INTAKES) {
                intakesFile.println(intake.getIntakeCode());
            }
            intakesFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            PrintWriter intakesFile = new PrintWriter("modules.txt");
            for (Module module : MODULES) {
                intakesFile.println(module.getModuleCode());
                intakesFile.println(module.getModuleName());
                intakesFile.println(module.getAssignedLecturer().getUsername());
                StringBuilder sb1 = new StringBuilder();
                for (String assessment : module.getAssessments()) {
                    sb1.append(assessment);
                    sb1.append(";");
                }
                if (sb1.toString().endsWith(";")) {
                    sb1.deleteCharAt(sb1.length() - 1);
                }
                intakesFile.println(sb1.toString());
                StringBuilder sb2 = new StringBuilder();
                for (int mark : module.getMarksAllocated()) {
                    sb2.append(mark);
                    sb2.append(";");
                }
                if (sb2.toString().endsWith(";")) {
                    sb2.deleteCharAt(sb2.length() - 1);
                }
                intakesFile.println(sb2.toString());
                intakesFile.println();
            }
            intakesFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            PrintWriter resultsFile = new PrintWriter("results.txt");
            for (Result assessment : RESULTS) {
                resultsFile.println(assessment.getStudentUsername());
                resultsFile.println(assessment.getIntake().getIntakeCode());
                resultsFile.println(assessment.getModule().getModuleCode());
                StringBuilder sb1 = new StringBuilder();
                for (int mark : assessment.getMarks()) {
                    sb1.append(mark);
                    sb1.append(";");
                }
                if (sb1.toString().endsWith(";")) {
                    sb1.deleteCharAt(sb1.length() - 1);
                }
                resultsFile.println(sb1.toString());
                resultsFile.println(assessment.getComment());
                resultsFile.println();
            }
            resultsFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static LoginPage getLoginPage() {
        if (loginPage == null) {
            return loginPage = new LoginPage();
        }
        return loginPage;
    }

    public static AdminPage initAdminPage() {
        return adminPage = new AdminPage();
    }

    public static AdminPage getAdminPage() {
        return adminPage;
    }

    public static LecturerPage initLecturerPage() {
        return lecturerPage = new LecturerPage();
    }

    public static LecturerPage getLecturerPage() {
        return lecturerPage;
    }

    public static StudentPage initStudentPage() {
        return studentPage = new StudentPage();
    }

    public static StudentPage getStudentPage() {
        return studentPage;
    }

    public static LoggerManager getLoggerManager() {
        return loggerManager;
    }
}
