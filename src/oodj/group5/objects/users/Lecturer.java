package oodj.group5.objects.users;

import java.util.ArrayList;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.utils.UserType;

public class Lecturer extends User {

    public Lecturer(String username, String password, String name) {
        super(username, password, name, UserType.LECTURER);
    }

    public static ArrayList<Lecturer> getLecturers() {
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        for (User user : AcademicCourseworkReportSystem.USERS) {
            if (user instanceof Lecturer) {
                lecturers.add((Lecturer) user);
            }
        }
        return lecturers;
    }

    public static User valueOf(String username) {
        for (User user : AcademicCourseworkReportSystem.USERS) {
            if (user instanceof Lecturer && user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
