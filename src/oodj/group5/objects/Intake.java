package oodj.group5.objects;

import oodj.group5.AcademicCourseworkReportSystem;

public class Intake {
    
    private String intakeCode;
    
    public Intake(String intakeCode) {
        this.intakeCode = intakeCode;
    }

    public String getIntakeCode() {
        return intakeCode;
    }
    
    @Override
    public String toString() {
        return this.intakeCode;
    }
    
    public static Intake valueOf(String intakeCode) {
        for(Intake intake : AcademicCourseworkReportSystem.INTAKES) {
            if(intake.getIntakeCode().equals(intakeCode)) {
                return intake;
            }
        }
        return null;
    }
}
