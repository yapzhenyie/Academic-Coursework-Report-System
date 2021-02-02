package oodj.group5.objects.users;

import java.util.ArrayList;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.utils.UserType;

public class User {

    private String username;
    private String password;
    private String name;
    private UserType type;

    public User(String username, String password, String name, UserType type) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserType() {
        return type;
    }

    public static ArrayList<User> getUsers(String username) {
        ArrayList<User> results = new ArrayList<>();
        for (User user : AcademicCourseworkReportSystem.USERS) {
            if (user.getUsername().startsWith(username) || user.getUsername().contains(username)) {
                results.add(user);
            }
        }
        return results;
    }

    public static User valueOf(String username) {
        for (User user : AcademicCourseworkReportSystem.USERS) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
