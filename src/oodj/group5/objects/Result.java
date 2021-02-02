package oodj.group5.objects;

import java.util.ArrayList;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.utils.Grade;

public class Result {

    private String username;
    private Intake intake;
    private Module module;
    private int[] marks;
    private String comment;

    public Result(String username, Intake intake, Module module, int[] marks, String comment) {
        this.username = username;
        this.intake = intake;
        this.module = module;
        this.marks = marks;
        this.comment = comment;
    }

    public String getStudentUsername() {
        return username;
    }

    public Intake getIntake() {
        return intake;
    }

    public Module getModule() {
        return module;
    }

    public int[] getMarks() {
        return marks;
    }

    public void setMarks(int[] marksAllocated) {
        this.marks = marksAllocated;
    }

    public Grade getGrade() {
        int totalMarks = 0;
        for (int mark : this.marks) {
            totalMarks += mark;
        }
        if (totalMarks < 0) {
            totalMarks = 0;
        }
        if (totalMarks > 100) {
            totalMarks = 100;
        }
        return Grade.getGrade(totalMarks);
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static ArrayList<Intake> getIntakesAvailable(String username) {
        ArrayList<Intake> intakes = new ArrayList<>();
        for (Result result : AcademicCourseworkReportSystem.RESULTS) {
            if (result.getStudentUsername().equals(username) && !intakes.contains(result.getIntake())) {
                intakes.add(result.getIntake());
            }
        }
        return intakes;
    }

    public static ArrayList<Result> getResultsByIntake(String username, Intake intake) {
        if (username == null || intake == null) {
            return null;
        }
        ArrayList<Result> results = new ArrayList<>();
        for (Result result : AcademicCourseworkReportSystem.RESULTS) {
            if (result.getStudentUsername().equals(username) && result.getIntake() == intake) {
                results.add(result);
            }
        }
        return results;
    }

    public static ArrayList<Result> getResultsByModule(Module module) {
        if (module == null) {
            return null;
        }
        ArrayList<Result> results = new ArrayList<>();
        for (Result result : AcademicCourseworkReportSystem.RESULTS) {
            if (result.getModule() == module) {
                results.add(result);
            }
        }
        return results;
    }

    public static Result getResult(String username, Module module) {
        if (username == null || module == null) {
            return null;
        }
        for (Result result : AcademicCourseworkReportSystem.RESULTS) {
            if (result.getStudentUsername().equals(username) && result.getModule() == module) {
                return result;
            }
        }
        return null;
    }
}
