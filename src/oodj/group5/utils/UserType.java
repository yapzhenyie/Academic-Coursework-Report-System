package oodj.group5.utils;

public enum UserType {
   STUDENT("Student"), LECTURER("Lecturer"), ADMIN("Admin"); 
   
   private String name;
   
   private UserType(String name) {
       this.name = name;
   }
   
   public String getName() {
       return this.name;
   }
}
