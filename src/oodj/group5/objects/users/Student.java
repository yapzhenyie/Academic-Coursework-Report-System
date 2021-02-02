package oodj.group5.objects.users;

import java.util.ArrayList;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.objects.Intake;
import oodj.group5.objects.Module;
import oodj.group5.utils.UserType;

public class Student extends User {

    private String tpNumber;
    private String icNumber;
    private Intake intake;
    private ArrayList<Module> modulesEnroll;

    public Student(String username, String password, String name) {
        super(username, password, name, UserType.STUDENT);
    }

    public Student(String username, String password, String name, String tpNumber, String icNumber, Intake intake, ArrayList<Module> modulesEnroll) {
        super(username, password, name, UserType.STUDENT);
        this.tpNumber = tpNumber;
        this.icNumber = icNumber;
        this.intake = intake;
        this.modulesEnroll = modulesEnroll;
    }

    public String getTPNumber() {
        return tpNumber;
    }

    public void setTPNumber(String tpNumber) {
        this.tpNumber = tpNumber;
    }

    public String getICNumber() {
        return icNumber;
    }

    public void setICNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public Intake getIntake() {
        return intake;
    }

    public void setIntake(Intake intake) {
        this.intake = intake;
    }

    public ArrayList<Module> getModulesEnroll() {
        if (this.modulesEnroll == null) {
            return this.modulesEnroll = new ArrayList<Module>();
        }
        return this.modulesEnroll;
    }

    public void setModulesEnroll(ArrayList<Module> modulesEnroll) {
        this.modulesEnroll = modulesEnroll;
    }

    public void addModuleEnroll(Module moduleEnroll) {
        if (this.modulesEnroll == null) {
            this.modulesEnroll = new ArrayList<Module>();
        }
        this.modulesEnroll.add(moduleEnroll);
    }

    public void removeModuleEnroll(Module moduleEnroll) {
        if (this.modulesEnroll == null) {
            this.modulesEnroll = new ArrayList<Module>();
            return;
        }
        this.modulesEnroll.remove(moduleEnroll);
    }

    public static ArrayList<Student> getStudentsByTPNumber(String tpNumber) {
        ArrayList<Student> results = new ArrayList<>();
        for (User user : AcademicCourseworkReportSystem.USERS) {
            if (user instanceof Student) {
                Student student = (Student) user;
                if (student.getTPNumber().startsWith(tpNumber) || student.getTPNumber().contains(tpNumber)) {
                    results.add(student);
                }
            }
        }
        return results;
    }

    public static ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        for (User user : AcademicCourseworkReportSystem.USERS) {
            if (user instanceof Student) {
                students.add((Student) user);
            }
        }
        return students;
    }

    public static User valueOf(String username) {
        for (User user : AcademicCourseworkReportSystem.USERS) {
            if (user instanceof Student && user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
