package oodj.group5.utils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Demonstrate a label as a button by using animation.
 *
 * @author Yap Zhen Yie
 */
public class ButtonAnimation {

    /*
     * Add hover animation.
     */
    public static void addAnimation(JLabel label) {
        label.setBackground(new Color(255, 51, 0));
        label.setForeground(new Color(255, 255, 255));
        label.setOpaque(true);
        label.setFont(new java.awt.Font("Times New Roman", 1, 20));
    }

    public static void addBold(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new java.awt.Font("Times New Roman", 1, 20));
    }

    /*
     * Remove hover animation.
     */
    public static void restore(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setBackground(new java.awt.Color(255, 102, 102));
        label.setFont(new java.awt.Font("Times New Roman", 1, 18));
    }

    /*
     * Add click animation for logout button.
     */
    public static void addClickedEffect(JPanel panel) {
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
    }

    /*
     * Remove click animation.
     */
    public static void removeClickedEffect(JPanel panel) {
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }
}
