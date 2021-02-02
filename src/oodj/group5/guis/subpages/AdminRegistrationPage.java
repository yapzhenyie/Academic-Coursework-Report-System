package oodj.group5.guis.subpages;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.objects.Intake;
import oodj.group5.objects.users.Admin;
import oodj.group5.objects.users.Lecturer;
import oodj.group5.objects.users.Student;
import oodj.group5.objects.users.User;
import oodj.group5.utils.DialogUtils;

public class AdminRegistrationPage extends ISubPage {

    private ArrayList<Component> Components = new ArrayList<>();

    private JLabel labelTitle;

    private JLabel labelUser;
    private JLabel labelName;
    private JLabel labelUsername;
    private JLabel labelPassword;
    private JLabel labelConfirmPassword;

    private JComboBox<String> comboBoxUser;
    private JTextField fieldName;
    private JTextField fieldUsername;
    private JPasswordField fieldPassword;
    private JPasswordField fieldConfirmPassword;

    // Student information.
    private JLabel labelTPNumber;
    private JLabel labelICNumber;
    private JLabel labelIntake;

    private JTextField fieldTPNumber;
    private JTextField fieldICNumber;
    private JComboBox<String> comboBoxIntake;

    private JButton buttonRegister;

    private boolean initialized = false;

    // Used for update username based on the name of the user.
    private String oldNameData = "";

    @Override
    protected void initComponents() {
        if (initialized) {
            return;
        }
        labelTitle = new JLabel();
        labelTitle.setBounds(10, 0, 135, 40);
        labelTitle.setFont(new java.awt.Font("Times New Roman", 1, 24));
        labelTitle.setText("Registration");
        Components.add(labelTitle);

        labelUser = new JLabel();
        labelUser.setBounds(90, 40, 60, 40);
        labelUser.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelUser.setText("User :");
        Components.add(labelUser);

        labelName = new JLabel();
        labelName.setBounds(83, 80, 60, 40);
        labelName.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelName.setText("Name :");
        Components.add(labelName);

        labelUsername = new JLabel();
        labelUsername.setBounds(58, 120, 100, 40);
        labelUsername.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelUsername.setText("Username :");
        Components.add(labelUsername);

        labelPassword = new JLabel();
        labelPassword.setBounds(61, 160, 100, 40);
        labelPassword.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelPassword.setText("Password :");
        Components.add(labelPassword);

        labelConfirmPassword = new JLabel();
        labelConfirmPassword.setBounds(9, 200, 130, 40);
        labelConfirmPassword.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelConfirmPassword.setText("Confirm Password :");
        Components.add(labelConfirmPassword);

        comboBoxUser = new JComboBox();
        comboBoxUser.setBounds(150, 50, 90, 22);
        comboBoxUser.setFont(new java.awt.Font("Times New Roman", 0, 14));
        comboBoxUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Student", "Lecturer", "Admin"}));
        comboBoxUser.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxUserItemStateChanged(evt);
            }
        });
        Components.add(comboBoxUser);

        fieldName = new JTextField();
        fieldName.setBounds(150, 90, 130, 25);
        fieldName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateField();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateField();
            }

            public void updateField() {
                if (!fieldUsername.getText().equals("")
                        && !oldNameData.equals(fieldUsername.getText())) {
                    return;
                }
                fieldUsername.setText(fieldName.getText().toLowerCase().replace(" ", ""));
                oldNameData = fieldName.getText().toLowerCase().replace(" ", "");
            }
        });
        Components.add(fieldName);

        fieldUsername = new JTextField();
        fieldUsername.setBounds(150, 130, 130, 25);
        Components.add(fieldUsername);

        fieldPassword = new JPasswordField();
        fieldPassword.setBounds(150, 170, 130, 25);
        Components.add(fieldPassword);

        fieldConfirmPassword = new JPasswordField();
        fieldConfirmPassword.setBounds(150, 210, 130, 25);
        fieldConfirmPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    registerAccount();
                }
            }

            @Override
            public void keyTyped(KeyEvent paramKeyEvent) {
            }

            @Override
            public void keyReleased(KeyEvent paramKeyEvent) {
            }
        });
        Components.add(fieldConfirmPassword);

        /**
         * Student Information section.
         */
        labelTPNumber = new JLabel();
        labelTPNumber.setBounds(320, 40, 100, 40);
        labelTPNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelTPNumber.setText("TP Number :");
        Components.add(labelTPNumber);

        labelICNumber = new JLabel();
        labelICNumber.setBounds(323, 80, 100, 40);
        labelICNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelICNumber.setText("IC Number :");
        Components.add(labelICNumber);

        labelIntake = new JLabel();
        labelIntake.setBounds(353, 120, 100, 40);
        labelIntake.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelIntake.setText("Intake :");
        Components.add(labelIntake);

        fieldTPNumber = new JTextField();
        fieldTPNumber.setBounds(415, 50, 130, 25);
        Components.add(fieldTPNumber);

        fieldICNumber = new JTextField();
        fieldICNumber.setBounds(415, 90, 130, 25);
        Components.add(fieldICNumber);

        comboBoxIntake = new JComboBox();
        comboBoxIntake.setBounds(415, 130, 140, 22);
        comboBoxIntake.setFont(new java.awt.Font("Times New Roman", 0, 14));
        String[] intakes = new String[AcademicCourseworkReportSystem.INTAKES.size()];
        for (int i = 0; i < AcademicCourseworkReportSystem.INTAKES.size(); i++) {
            intakes[i] = AcademicCourseworkReportSystem.INTAKES.get(i).toString();
        }
        comboBoxIntake.setModel(new javax.swing.DefaultComboBoxModel<>(intakes));
        Components.add(comboBoxIntake);

        buttonRegister = new JButton();
        buttonRegister.setBounds(230, 300, 110, 35);
        buttonRegister.setFont(new java.awt.Font("Times New Roman", 1, 18));
        buttonRegister.setText("Register");
        buttonRegister.addActionListener(new ButtonRegisterListener());
        Components.add(buttonRegister);

        initialized = true;
    }

    @Override
    public void showAllComponents() {
        if (!initialized) {
            initComponents();
            for (Component component : Components) {
                AcademicCourseworkReportSystem.getAdminPage().addWorkingPanelComponent(component);
            }
        }
        for (Component component : Components) {
            component.setVisible(true);
        }
        updateInfo();
    }

    @Override
    public void hideAllComponents() {
        for (Component component : Components) {
            component.setVisible(false);
        }
    }

    @Override
    public void hideSomeComponents() {

    }

    @Override
    public void updateInfo() {
        if (!initialized) {
            return;
        }
        String[] intakes = new String[AcademicCourseworkReportSystem.INTAKES.size()];
        for (int i = 0; i < AcademicCourseworkReportSystem.INTAKES.size(); i++) {
            intakes[i] = AcademicCourseworkReportSystem.INTAKES.get(i).toString();
        }
        comboBoxIntake.setModel(new javax.swing.DefaultComboBoxModel<>(intakes));
    }

    @Override
    public void clearInfo() {
        if (!initialized) {
            return;
        }
        comboBoxUser.requestFocus();
        comboBoxUser.setSelectedIndex(0);
        fieldName.setText("");
        fieldUsername.setText("");
        fieldPassword.setText("");
        fieldConfirmPassword.setText("");
        fieldTPNumber.setText("");
        fieldICNumber.setText("");
        if (comboBoxIntake.getItemCount() > 0) {
            comboBoxIntake.setSelectedIndex(0);
        }
    }

    private void comboBoxUserItemStateChanged(java.awt.event.ItemEvent evt) {
        // New selection not equal Student.
        if (!evt.getItem().equals("Student")) {
            labelTPNumber.setVisible(false);
            labelICNumber.setVisible(false);
            labelIntake.setVisible(false);
            fieldTPNumber.setVisible(false);
            fieldICNumber.setVisible(false);
            comboBoxIntake.setVisible(false);
        } else {
            labelTPNumber.setVisible(true);
            labelICNumber.setVisible(true);
            labelIntake.setVisible(true);
            fieldTPNumber.setVisible(true);
            fieldTPNumber.setText("");
            fieldICNumber.setVisible(true);
            fieldICNumber.setText("");
            comboBoxIntake.setVisible(true);
            comboBoxIntake.setSelectedIndex(0);
        }
        fieldName.setText("");
        fieldUsername.setText("");
        fieldPassword.setText("");
        fieldConfirmPassword.setText("");
    }

    private class ButtonRegisterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == buttonRegister) {
                registerAccount();
            }
        }
    }

    private void registerAccount() {
        if (fieldUsername.getText().equals("")) {
            DialogUtils.showErrorMessageDialog(null, "Username cannot be empty!");
            return;
        }
        for (User user : AcademicCourseworkReportSystem.USERS) {
            if (user.getUsername().equals(fieldUsername.getText())) {
                DialogUtils.showErrorMessageDialog(null, "This username has been taken.\nPlease use other username.");
                return;
            }
        }
        if (fieldPassword.getText().equals("")) {
            DialogUtils.showErrorMessageDialog(null, "Password cannot be empty!");
            return;
        }
        if (fieldConfirmPassword.getText().equals("")) {
            DialogUtils.showErrorMessageDialog(null, "Confirm Password cannot be empty!");
            return;
        }
        if (!fieldConfirmPassword.getText().equals(fieldPassword.getText())) {
            DialogUtils.showErrorMessageDialog(null, "Password does not match!");
            return;
        }
        if (comboBoxUser.getSelectedIndex() == 0) {
            if (fieldTPNumber.getText().equals("")) {
                DialogUtils.showErrorMessageDialog(null, "TP Number cannot be empty!");
                return;
            }
            try {
                Integer.parseInt(fieldTPNumber.getText());
            } catch (NumberFormatException e) {
                DialogUtils.showErrorMessageDialog(null, "TP Number can only contains number!");
                return;
            }
            for (Student student : Student.getStudents()) {
                if (student.getTPNumber().equals(fieldTPNumber.getText())) {
                    DialogUtils.showErrorMessageDialog(null, "This TP number has been taken!\nPlease use another TP Number.");
                    return;
                }
            }

            if (fieldICNumber.getText().equals("")) {
                DialogUtils.showErrorMessageDialog(null, "IC Number cannot be empty!");
                return;
            }
            String icNumber = fieldICNumber.getText();
            if (icNumber.length() != 12) {
                DialogUtils.showErrorMessageDialog(null, "IC Number must be 12 numbers format!");
                return;
            }
            try {
                Long.parseLong(icNumber);
            } catch (NumberFormatException e) {
                DialogUtils.showErrorMessageDialog(null, "IC Number can only contains number!");
                return;
            }
            if (Integer.parseInt(icNumber.substring(2, 4)) > 12 || Integer.parseInt(icNumber.substring(4, 6)) > 31) {
                DialogUtils.showErrorMessageDialog(null, "The IC Number is wrong format!");
                return;
            }
        }
        switch (comboBoxUser.getSelectedIndex()) {
            case 0: // Student
                Student student = new Student(fieldUsername.getText(), fieldPassword.getText(),
                        fieldName.getText(), fieldTPNumber.getText(), fieldICNumber.getText(),
                        Intake.valueOf(comboBoxIntake.getSelectedItem().toString()), null);
                AcademicCourseworkReportSystem.USERS.add(student);
                JOptionPane.showMessageDialog(null, "Successful register a student account!");
                break;
            case 1: // Lecturer
                Lecturer lecturer = new Lecturer(fieldUsername.getText(), fieldPassword.getText(),
                        fieldName.getText());
                AcademicCourseworkReportSystem.USERS.add(lecturer);
                JOptionPane.showMessageDialog(null, "Successful register a lecturer account!");
                break;
            case 2: // Admin
                Admin admin = new Admin(fieldUsername.getText(), fieldPassword.getText(),
                        fieldName.getText());
                AcademicCourseworkReportSystem.USERS.add(admin);
                JOptionPane.showMessageDialog(null, "Successful register an admin account!");
                break;
        }
        clearInfo();
    }
}
