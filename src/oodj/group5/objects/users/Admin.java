package oodj.group5.objects.users;

import java.util.ArrayList;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.utils.UserType;

public class Admin extends User {

    public Admin(String username, String password, String name) {
        super(username, password, name, UserType.ADMIN);
    }

    public static ArrayList<Admin> getAdmins() {
        ArrayList<Admin> admins = new ArrayList<>();
        for (User user : AcademicCourseworkReportSystem.USERS) {
            if (user instanceof Admin) {
                admins.add((Admin) user);
            }
        }
        return admins;
    }

    public static User valueOf(String username) {
        for (User user : AcademicCourseworkReportSystem.USERS) {
            if (user instanceof Admin && user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
