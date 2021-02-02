package oodj.group5.guis.subpages;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.utils.DialogUtils;

public class LecturerProfilePage extends ISubPage {

    private static ArrayList<Component> Components = new ArrayList<>();

    private static JLabel labelTitle;

    private static JLabel labelUsername;
    private static JLabel labelName;

    private static JLabel labelResultUsername;
    private static JLabel labelResultName;

    /**
     * Components when editing profile.
     */
    private JLabel labelOldPassword;
    private JLabel labelNewPassword;
    private JLabel labelConfirmPassword;

    private JTextField fieldName;
    private JPasswordField fieldOldPassword;
    private JPasswordField fieldNewPassword;
    private JPasswordField fieldConfirmPassword;

    private JButton buttonEdit;
    private JButton buttonSave;
    private JButton buttonCancel;

    private boolean initialized = false;

    @Override
    protected void initComponents() {
        if (initialized) {
            return;
        }

        labelTitle = new JLabel();
        labelTitle.setBounds(10, 0, 80, 40);
        labelTitle.setFont(new java.awt.Font("Times New Roman", 1, 24));
        labelTitle.setText("Profile");
        Components.add(labelTitle);

        labelName = new JLabel();
        labelName.setBounds(93, 50, 80, 40);
        labelName.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelName.setText("Name :");
        Components.add(labelName);

        labelUsername = new JLabel();
        labelUsername.setBounds(68, 85, 80, 40);
        labelUsername.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelUsername.setText("Username :");
        Components.add(labelUsername);

        labelResultName = new JLabel();
        labelResultName.setBounds(155, 50, 300, 40);
        labelResultName.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultName.setText(AcademicCourseworkReportSystem.currentLoginUser == null ? "Invalid" : AcademicCourseworkReportSystem.currentLoginUser.getName());
        Components.add(labelResultName);

        labelResultUsername = new JLabel();
        labelResultUsername.setBounds(155, 85, 300, 40);
        labelResultUsername.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultUsername.setText(AcademicCourseworkReportSystem.currentLoginUser == null ? "Invalid" : AcademicCourseworkReportSystem.currentLoginUser.getUsername());
        Components.add(labelResultUsername);

        labelOldPassword = new JLabel();
        labelOldPassword.setBounds(46, 120, 100, 40);
        labelOldPassword.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelOldPassword.setText("Old Password :");
        Components.add(labelOldPassword);

        labelNewPassword = new JLabel();
        labelNewPassword.setBounds(38, 155, 120, 40);
        labelNewPassword.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelNewPassword.setText("New Password :");
        Components.add(labelNewPassword);

        labelConfirmPassword = new JLabel();
        labelConfirmPassword.setBounds(19, 190, 120, 40);
        labelConfirmPassword.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelConfirmPassword.setText("Confirm Password :");
        Components.add(labelConfirmPassword);

        fieldName = new JTextField();
        fieldName.setBounds(155, 60, 150, 25);
        fieldName.setFont(new java.awt.Font("Times New Roman", 0, 15));
        fieldName.setText(AcademicCourseworkReportSystem.currentLoginUser == null ? "Invalid" : AcademicCourseworkReportSystem.currentLoginUser.getName());
        fieldName.setCaretPosition(0);
        Components.add(fieldName);

        fieldOldPassword = new JPasswordField();
        fieldOldPassword.setBounds(155, 130, 150, 25);
        fieldOldPassword.setText("");
        Components.add(fieldOldPassword);

        fieldNewPassword = new JPasswordField();
        fieldNewPassword.setBounds(155, 165, 150, 25);
        fieldNewPassword.setText("");
        Components.add(fieldNewPassword);

        fieldConfirmPassword = new JPasswordField();
        fieldConfirmPassword.setBounds(155, 200, 150, 25);
        fieldConfirmPassword.setText("");
        Components.add(fieldConfirmPassword);

        buttonEdit = new JButton();
        buttonEdit.setBounds(380, 35, 70, 25);
        buttonEdit.setFont(new java.awt.Font("Times New Roman", 0, 16));
        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(new ButtonListener());
        Components.add(buttonEdit);

        buttonSave = new JButton();
        buttonSave.setBounds(150, 300, 110, 35);
        buttonSave.setFont(new java.awt.Font("Times New Roman", 1, 18));
        buttonSave.setText("Save");
        buttonSave.addActionListener(new ButtonListener());
        Components.add(buttonSave);

        buttonCancel = new JButton();
        buttonCancel.setBounds(280, 300, 110, 35);
        buttonCancel.setFont(new java.awt.Font("Times New Roman", 1, 18));
        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new ButtonListener());
        Components.add(buttonCancel);

        initialized = true;
    }

    @Override
    public void showAllComponents() {
        if (!initialized) {
            initComponents();
            for (Component component : Components) {
                AcademicCourseworkReportSystem.getLecturerPage().addWorkingPanelComponent(component);
            }
        }
        for (Component component : Components) {
            component.setVisible(true);
        }
    }

    @Override
    public void hideAllComponents() {
        for (Component component : Components) {
            component.setVisible(false);
        }
    }

    @Override
    public void hideSomeComponents() {
        labelOldPassword.setVisible(false);
        labelNewPassword.setVisible(false);
        labelConfirmPassword.setVisible(false);
        fieldName.setVisible(false);
        fieldOldPassword.setVisible(false);
        fieldNewPassword.setVisible(false);
        fieldConfirmPassword.setVisible(false);
        buttonSave.setVisible(false);
        buttonCancel.setVisible(false);
    }

    @Override
    public void updateInfo() {
        labelResultName.setVisible(true);
        labelResultUsername.setVisible(true);
        labelResultName.setText(AcademicCourseworkReportSystem.currentLoginUser == null ? "Invalid"
                : AcademicCourseworkReportSystem.currentLoginUser.getName());
        labelResultUsername.setText(AcademicCourseworkReportSystem.currentLoginUser == null ? "Invalid"
                : AcademicCourseworkReportSystem.currentLoginUser.getUsername());
    }

    @Override
    public void clearInfo() {
        if (initialized) {
            labelResultUsername.setText("");
            labelResultName.setText("");
            fieldName.setText("");
            fieldOldPassword.setText("");
            fieldNewPassword.setText("");
            fieldConfirmPassword.setText("");
            hideSomeComponents();
            return;
        }
    }

    /**
     * Enable profile edit mode.
     */
    private void enterEditMode() {
        buttonEdit.setVisible(false);
        labelResultName.setVisible(false);

        labelOldPassword.setVisible(true);
        labelNewPassword.setVisible(true);
        labelConfirmPassword.setVisible(true);
        fieldName.setVisible(true);
        fieldOldPassword.setVisible(true);
        fieldNewPassword.setVisible(true);
        fieldConfirmPassword.setVisible(true);
        buttonSave.setVisible(true);
        buttonCancel.setVisible(true);

        fieldName.setText(AcademicCourseworkReportSystem.currentLoginUser == null ? "Invalid" : AcademicCourseworkReportSystem.currentLoginUser.getName());
        fieldName.setCaretPosition(0);
        fieldOldPassword.setText("");
        fieldNewPassword.setText("");
        fieldConfirmPassword.setText("");
    }

    /**
     * Quit profile edit mode.
     */
    private void quitEditMode() {
        buttonEdit.setVisible(true);
        updateInfo();
        hideSomeComponents();
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == buttonEdit) {
                enterEditMode();
            } else if (event.getSource() == buttonSave) {
                if (AcademicCourseworkReportSystem.currentLoginUser == null) {
                    DialogUtils.showErrorMessageDialog(null, "An error has occured while doing this process,\nplease log out and try again!");
                    return;
                }
                if (fieldName.getText().equals("")) {
                    DialogUtils.showErrorMessageDialog(null, "Your name cannot be empty!");
                    return;
                }
                if (!fieldOldPassword.getText().equals("") || !fieldNewPassword.getText().equals("")
                        || !fieldConfirmPassword.getText().equals("")) {
                    if (fieldOldPassword.getText().equals("") || fieldNewPassword.getText().equals("")
                            || fieldConfirmPassword.getText().equals("")) {
                        DialogUtils.showErrorMessageDialog(null, "You must fill up your old password and new password\nin order to change your password!");
                        return;
                    }
                    if (!fieldOldPassword.getText().equals(AcademicCourseworkReportSystem.currentLoginUser.getPassword())) {
                        DialogUtils.showErrorMessageDialog(null, "Your old password is wrong!");
                        fieldOldPassword.setText("");
                        return;
                    }
                    if (!fieldNewPassword.getText().equals(fieldConfirmPassword.getText())) {
                        DialogUtils.showErrorMessageDialog(null, "Your new password and confirm password\ndo not matched!");
                        return;
                    }
                }
                Object[] options = {"Confirm", "Cancel"};
                int selection = DialogUtils.showOptionDialog(null, "Are you sure you to save profile changes?",
                        "Save Profile", options, options[0]);
                if (selection == 0) {
                    AcademicCourseworkReportSystem.currentLoginUser.setName(fieldName.getText());
                    if (!fieldNewPassword.getText().equals("")
                            && !fieldConfirmPassword.getText().equals("")) {
                        AcademicCourseworkReportSystem.currentLoginUser.setPassword(fieldConfirmPassword.getText());
                    }
                    quitEditMode();
                }
            } else if (event.getSource() == buttonCancel) {
                quitEditMode();
            }
        }
    }
}
