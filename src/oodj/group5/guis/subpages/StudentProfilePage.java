package oodj.group5.guis.subpages;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.objects.users.Student;
import oodj.group5.utils.DialogUtils;

public class StudentProfilePage extends ISubPage {

    private ArrayList<Component> Components = new ArrayList<>();

    private JLabel labelTitle;

    private JLabel labelUsername;
    private JLabel labelName;
    private JLabel labelTPNumber;
    private JLabel labelICNumber;
    private JLabel labelIntake;

    private JLabel labelResultUsername;
    private JLabel labelResultName;
    private JLabel labelResultTPNumber;
    private JLabel labelResultICNumber;
    private JLabel labelResultIntake;

    /**
     * Components when editing profile.
     */
    private JLabel labelOldPassword;
    private JLabel labelNewPassword;
    private JLabel labelConfirmPassword;

    private JPasswordField fieldOldPassword;
    private JPasswordField fieldNewPassword;
    private JPasswordField fieldConfirmPassword;

    private JButton buttonEdit;
    private static JButton buttonSave;
    private static JButton buttonCancel;

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
        labelName.setBounds(63, 50, 80, 40);
        labelName.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelName.setText("Name :");
        Components.add(labelName);

        labelUsername = new JLabel();
        labelUsername.setBounds(38, 85, 80, 40);
        labelUsername.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelUsername.setText("Username :");
        Components.add(labelUsername);

        labelTPNumber = new JLabel();
        labelTPNumber.setBounds(29, 120, 80, 40);
        labelTPNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelTPNumber.setText("TP Number :");
        Components.add(labelTPNumber);

        labelICNumber = new JLabel();
        labelICNumber.setBounds(32, 155, 80, 40);
        labelICNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelICNumber.setText("IC Number :");
        Components.add(labelICNumber);

        labelIntake = new JLabel();
        labelIntake.setBounds(62, 190, 80, 40);
        labelIntake.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelIntake.setText("Intake :");
        Components.add(labelIntake);

        Student student = AcademicCourseworkReportSystem.currentLoginUser == null ? null : (Student) AcademicCourseworkReportSystem.currentLoginUser;

        labelResultName = new JLabel();
        labelResultName.setBounds(120, 50, 300, 40);
        labelResultName.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultName.setText(student == null ? "Invalid" : student.getName());
        Components.add(labelResultName);

        labelResultUsername = new JLabel();
        labelResultUsername.setBounds(120, 85, 300, 40);
        labelResultUsername.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultUsername.setText(student == null ? "Invalid" : student.getUsername());
        Components.add(labelResultUsername);

        labelResultTPNumber = new JLabel();
        labelResultTPNumber.setBounds(120, 120, 150, 40);
        labelResultTPNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultTPNumber.setText(student == null ? "Invalid" : student.getTPNumber());
        Components.add(labelResultTPNumber);

        labelResultICNumber = new JLabel();
        labelResultICNumber.setBounds(120, 155, 150, 40);
        labelResultICNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultICNumber.setText(student == null ? "Invalid" : student.getICNumber());
        Components.add(labelResultICNumber);

        labelResultIntake = new JLabel();
        labelResultIntake.setBounds(120, 190, 200, 40);
        labelResultIntake.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultIntake.setText(student == null ? "Invalid" : student.getIntake().getIntakeCode());
        Components.add(labelResultIntake);

        labelOldPassword = new JLabel();
        labelOldPassword.setBounds(300, 50, 100, 40);
        labelOldPassword.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelOldPassword.setText("Old Password :");
        Components.add(labelOldPassword);

        labelNewPassword = new JLabel();
        labelNewPassword.setBounds(292, 85, 120, 40);
        labelNewPassword.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelNewPassword.setText("New Password :");
        Components.add(labelNewPassword);

        labelConfirmPassword = new JLabel();
        labelConfirmPassword.setBounds(273, 120, 120, 40);
        labelConfirmPassword.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelConfirmPassword.setText("Confirm Password :");
        Components.add(labelConfirmPassword);

        fieldOldPassword = new JPasswordField();
        fieldOldPassword.setBounds(405, 60, 150, 25);
        fieldOldPassword.setText("");
        Components.add(fieldOldPassword);

        fieldNewPassword = new JPasswordField();
        fieldNewPassword.setBounds(405, 95, 150, 25);
        fieldNewPassword.setText("");
        Components.add(fieldNewPassword);

        fieldConfirmPassword = new JPasswordField();
        fieldConfirmPassword.setBounds(405, 130, 150, 25);
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
                AcademicCourseworkReportSystem.getStudentPage().addWorkingPanelComponent(component);
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
        labelResultTPNumber.setVisible(true);
        labelResultICNumber.setVisible(true);
        labelResultIntake.setVisible(true);
        Student student = AcademicCourseworkReportSystem.currentLoginUser == null ? null : (Student) AcademicCourseworkReportSystem.currentLoginUser;
        labelResultName.setText(student == null ? "Invalid" : student.getName());
        labelResultUsername.setText(student == null ? "Invalid" : student.getUsername());
        labelResultTPNumber.setText(student == null ? "Invalid" : student.getTPNumber());
        labelResultICNumber.setText(student == null ? "Invalid" : student.getICNumber());
        labelResultIntake.setText(student == null ? "Invalid" : student.getIntake().getIntakeCode());
    }

    @Override
    public void clearInfo() {
        if (initialized) {
            labelResultUsername.setText("");
            labelResultName.setText("");
            labelResultTPNumber.setText("");
            labelResultICNumber.setText("");
            labelResultIntake.setText("");
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

        labelOldPassword.setVisible(true);
        labelNewPassword.setVisible(true);
        labelConfirmPassword.setVisible(true);
        fieldOldPassword.setVisible(true);
        fieldNewPassword.setVisible(true);
        fieldConfirmPassword.setVisible(true);
        buttonSave.setVisible(true);
        buttonCancel.setVisible(true);

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
                Student user = (Student) AcademicCourseworkReportSystem.currentLoginUser;
                if (user == null) {
                    DialogUtils.showErrorMessageDialog(null, "An error has occured while doing this process,\nplease log out and try again!");
                    return;
                }
                if (fieldOldPassword.getText().equals("") || fieldNewPassword.getText().equals("")
                        || fieldConfirmPassword.getText().equals("")) {
                    DialogUtils.showErrorMessageDialog(null, "You must fill up your old password and new password\nin order to change your password!");
                    return;
                }
                if (!fieldOldPassword.getText().equals(user.getPassword())) {
                    DialogUtils.showErrorMessageDialog(null, "Your old password is wrong!");
                    fieldOldPassword.setText("");
                    return;
                }
                if (!fieldNewPassword.getText().equals(fieldConfirmPassword.getText())) {
                    DialogUtils.showErrorMessageDialog(null, "Your new password and confirm password\ndo not matched!");
                    return;
                }
                Object[] options = {"Confirm", "Cancel"};
                int selection = DialogUtils.showOptionDialog(null, "Are you sure you to change password?",
                        "Change Password", options, options[0]);
                if (selection == 0) {
                    user.setPassword(fieldConfirmPassword.getText());
                    quitEditMode();
                }
            } else if (event.getSource() == buttonCancel) {
                quitEditMode();
            }
        }
    }
}
