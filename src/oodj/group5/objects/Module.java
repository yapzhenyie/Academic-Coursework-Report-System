package oodj.group5.objects;

import java.util.ArrayList;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.objects.users.Lecturer;
import oodj.group5.objects.users.User;

public class Module {
    
    private String moduleCode;
    private String moduleName;
    private User assignedLecturer;
    private String[] assessments;
    private int[] marksAllocated;
    
    public Module(String moduleCode, String moduleName, User assignedLecturer, String[] assessments, int[] marksAllocated) {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.assignedLecturer = assignedLecturer;
        this.assessments = assessments;
        this.marksAllocated = marksAllocated;
    }
    
    public String getModuleCode() {
        return moduleCode;
    }
    
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
    
    public String getModuleName() {
        return moduleName;
    }
    
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    
    public User getAssignedLecturer() {
        return assignedLecturer;
    }
    
    public void setAssignedLecturer(User assignedLecturer) {
        this.assignedLecturer = assignedLecturer;
    }
    
    public String[] getAssessments() {
        return assessments;
    }
    
    public void setAssessments(String[] assessments) {
        this.assessments = assessments;
    }
    
    public int[] getMarksAllocated() {
        return marksAllocated;
    }
    
    public void setMarksAllocated(int[] marksAllocated) {
        this.marksAllocated = marksAllocated;
    }
    
    @Override
    public String toString() {
        return this.moduleCode;
    }
    
    public static ArrayList<Module> getModulesTeach(Lecturer lecturer) {
        if(lecturer == null) {
            return null;
        }
        ArrayList<Module> modules = new ArrayList<>();
        for (Module module : AcademicCourseworkReportSystem.MODULES) {
            if (module.getAssignedLecturer() == lecturer && !modules.contains(module)) {
                modules.add(module);
            }
        }
        return modules;
    }
    
    public static Module valueOf(String moduleCode) {
        for (Module module : AcademicCourseworkReportSystem.MODULES) {
            if (module.getModuleCode().equals(moduleCode)) {
                return module;
            }
        }
        return null;
    }
}
