package oodj.group5.utils;

import java.util.Random;

public class PasswordUtils {

    private final static String charsCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String chars = "abcdefghijklmnopqrstuvwxyz";
    private final static String nums = "0123456789";

    /**
     * Following source code referenced from (devdeep, 2020)
     * https://code.sololearn.com/cYbJ69AmaS8S/
     * 
     * @param length
     * @return a random string password.
     */
    public static String generatePassword(int length) {
        String passSymbols = charsCaps + chars + nums;// + symbols;
        Random random = new Random();

        String password = new String();
        for (int i = 0; i < length; i++) {
            password += passSymbols.charAt(random.nextInt(passSymbols.length()));

        }
        return password;
    }
}
