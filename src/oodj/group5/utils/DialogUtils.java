package oodj.group5.utils;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DialogUtils {

    /**
     * Following source code referenced from (bonney 2015)
     * 
     * Shows a titled error message box. Allow multiple line messages.
     */
    public static void showErrorMessageDialog(Component component, String message) {
        JOptionPane.showMessageDialog(component, message, "Error Message", JOptionPane.ERROR_MESSAGE);
    }

    public static void showColoredErrorMessageDialog(Component component, String message, Color color) {
        showColoredMessageDialog(component, message, color, "Error Message", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows a colored error message. Only allow single line message.
     */
    public static void showColoredMessageDialog(Component component, String message, Color color, String title, int option) {
        JLabel label = new JLabel(message);
        label.setForeground(color);
        JOptionPane.showMessageDialog(component, label, title, option);
    }

    /**
     *
     * @param component The component.
     * @param message The message inside the dialog.
     * @param title The title of the dialog.
     * @param options The options available.
     * @param defaultOption The default focused option.
     *
     * @return option selected.
     */
    public static int showOptionDialog(Component component, String message, String title, Object[] options, Object defaultOption) {
        return JOptionPane.showOptionDialog(null, message, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, defaultOption);
    }
}
