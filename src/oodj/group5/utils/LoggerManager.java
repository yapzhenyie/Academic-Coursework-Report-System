package oodj.group5.utils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Manage the log file messages.
 *
 * @author Yap Zhen Yie
 */
public class LoggerManager {

    private static PrintWriter logsFile;

    /**
     * Following source code obtained from (Jin, Q., 2012)
     */
    public LoggerManager() {
        try {
            logsFile = new PrintWriter(new FileWriter("logs.txt", true));
        } catch (Exception e) {
            DialogUtils.showErrorMessageDialog(null, "Failed to load log.txt file!\nLog activity will not be recorded.");
            e.printStackTrace();
        }
    }

    public void addLoginMessage(String message) {
        if (logsFile == null) {
            return;
        }
        logsFile.println(getDateFormat() + " [Login]: " + message);
        logsFile.flush();
    }

    public void addLogoutMessage(String message) {
        if (logsFile == null) {
            return;
        }
        logsFile.println(getDateFormat() + " [Logout]: " + message);
        logsFile.flush();
    }

    public void addLogMessage(String message) {
        if (logsFile == null) {
            return;
        }
        logsFile.println(getDateFormat() + " [Log]: " + message);
        logsFile.flush();
    }

    public void addLogMessage(String[] messages) {
        if (logsFile == null) {
            return;
        }
        for (String msg : messages) {
            logsFile.println(getDateFormat() + " [Log]: " + msg);
            logsFile.flush();
        }
    }

    public void addErrorMessage(String message) {
        if (logsFile == null) {
            return;
        }
        logsFile.println(getDateFormat() + " [Error]: " + message);
        logsFile.flush();
    }

    public void addMessage(String prefix, String message) {
        if (logsFile == null) {
            return;
        }
        logsFile.println(getDateFormat() + " " + prefix + ": " + message);
        logsFile.flush();
    }

    public void saveLogFile() {
        if (logsFile == null) {
            return;
        }
        logsFile.close();
    }

    /**
     * Get the current date time for creating log message.
     *
     * @return date time "[MM/dd/yyyy HH:mm:ss]"
     */
    public static String getDateFormat() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // S is the millisecond
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return "[" + simpleDateFormat.format(timestamp) + "]";
    }
}
