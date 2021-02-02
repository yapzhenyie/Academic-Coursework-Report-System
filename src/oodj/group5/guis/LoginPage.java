package oodj.group5.guis;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.objects.users.User;
import oodj.group5.utils.DialogUtils;

public class LoginPage extends JFrame implements ActionListener, IPage {

    private JPanel panelLeft;
    private JLabel labelLogo;
    private JLabel labelSystemName1;
    private JLabel labelSystemName2;
    private JLabel labelSignIn;
    private JLabel labelUsername;
    private JLabel labelPassword;
    private JTextField fieldUsername;
    private JPasswordField fieldPassword;
    private JButton buttonLogin;

    public LoginPage() {
        super.setSize(760, 480);
        /**
         * Following source code obtained from (Jack, 2010)
         */
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        super.setTitle("Academic Coursework Report System");
        super.setIconImage(new ImageIcon(getClass().getResource("/oodj/resources/logo.png")).getImage());
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelLeft = new JPanel();
        panelLeft.setBounds(0, 0, 360, this.getSize().height);
        panelLeft.setBackground(new java.awt.Color(102, 102, 255));

        labelLogo = new JLabel();
        labelLogo.setBounds(79, 40, 210, 210);
        labelLogo.setIcon(new ImageIcon(getClass().getResource("/oodj/resources/logo.png")));

        labelSystemName1 = new JLabel();
        labelSystemName1.setBounds(45, 270, 280, 40);
        labelSystemName1.setFont(new java.awt.Font("Times New Roman", 1, 28));
        labelSystemName1.setForeground(new java.awt.Color(255, 255, 255));
        labelSystemName1.setText("Academic Coursework");

        labelSystemName2 = new JLabel();
        labelSystemName2.setBounds(90, 310, 180, 40);
        labelSystemName2.setFont(new java.awt.Font("Times New Roman", 1, 28));
        labelSystemName2.setForeground(new java.awt.Color(255, 255, 255));
        labelSystemName2.setText("Report System");

        panelLeft.setLayout(null);
        panelLeft.add(labelLogo);
        panelLeft.add(labelSystemName1);
        panelLeft.add(labelSystemName2);

        labelSignIn = new JLabel();
        labelSignIn.setBounds(500, 60, 115, 49);
        labelSignIn.setFont(new Font("Times New Roman", 1, 36));
        labelSignIn.setText("Sign In");

        labelUsername = new JLabel();
        labelUsername.setBounds(470, 135, 63, 24);
        labelUsername.setFont(new Font("Times New Roman", 0, 14));
        labelUsername.setText("Username");

        labelPassword = new JLabel();
        labelPassword.setBounds(470, 205, 63, 24);
        labelPassword.setFont(new Font("Times New Roman", 0, 14));
        labelPassword.setText("Password");

        fieldUsername = new JTextField();
        fieldUsername.setBounds(470, 165, 165, 30);
        fieldUsername.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (fieldUsername.getText().equals("")) {
                        DialogUtils.showErrorMessageDialog(fieldUsername, "Username cannot be empty!");
                        return;
                    }
                    fieldPassword.requestFocus();
                }
            }

            @Override
            public void keyTyped(KeyEvent paramKeyEvent) {
            }

            @Override
            public void keyReleased(KeyEvent paramKeyEvent) {
            }
        });

        fieldPassword = new JPasswordField();
        fieldPassword.setBounds(470, 235, 165, 30);
        fieldPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (fieldPassword.getText().equals("")) {
                        DialogUtils.showErrorMessageDialog(fieldPassword, "Password cannot be empty!");
                        return;
                    }
                    performLogin();
                }
            }

            @Override
            public void keyTyped(KeyEvent paramKeyEvent) {
            }

            @Override
            public void keyReleased(KeyEvent paramKeyEvent) {
            }
        });

        buttonLogin = new JButton();
        buttonLogin.setBounds(505, 300, 90, 34);
        buttonLogin.setText("Login");
        buttonLogin.setFont(new Font("Times New Roman", 1, 14));
        buttonLogin.addActionListener(this);

        /**
         * Following source code obtained from (Saryada, W., 2020)
         */
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AcademicCourseworkReportSystem.stopProgram();
                System.exit(0);
            }
        });

        super.setLayout(null);
        super.add(panelLeft);
        super.add(labelSignIn);
        super.add(labelUsername);
        super.add(labelPassword);
        super.add(fieldUsername);
        super.add(fieldPassword);
        super.add(buttonLogin);
        super.setVisible(true);
    }

    public void clearInfo() {
        this.fieldUsername.requestFocus();
        this.fieldUsername.setText("");
        this.fieldPassword.setText("");
    }

    @Override
    public void setPageVisible(boolean flag) {
        super.setVisible(flag);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.buttonLogin) {
            performLogin();
        }
    }

    private void performLogin() {
        if (fieldUsername.getText().equals("")) {
            DialogUtils.showErrorMessageDialog(fieldUsername, "Username cannot be empty!");
            return;
        }
        if (fieldPassword.getText().equals("")) {
            DialogUtils.showErrorMessageDialog(fieldPassword, "Password cannot be empty!");
            return;
        }
        boolean found = false;
        for (User user : AcademicCourseworkReportSystem.USERS) {
            if (user.getUsername().equals(fieldUsername.getText())) {
                if (user.getPassword().equals(fieldPassword.getText())) {
                    found = true;
                    AcademicCourseworkReportSystem.currentLoginUser = user;
                    AcademicCourseworkReportSystem.getLoggerManager().addLoginMessage(user.getName() + "(" + user.getUsername() + ") logged in.");
                    setPageVisible(false);
                    clearInfo();
                    switch (user.getUserType()) {
                        case ADMIN:
                            if (AcademicCourseworkReportSystem.getAdminPage() != null) {
                                AcademicCourseworkReportSystem.getAdminPage().setPageVisible(true);
                            } else {
                                AcademicCourseworkReportSystem.initAdminPage();
                            }
                            AcademicCourseworkReportSystem.getAdminPage().setCurrentSelectedLabel(1);
                            AcademicCourseworkReportSystem.getAdminPage().updateWelcomeMessage();
                            AcademicCourseworkReportSystem.getAdminPage().registrationPage.showAllComponents();
                            break;
                        case LECTURER:
                            if (AcademicCourseworkReportSystem.getLecturerPage() != null) {
                                AcademicCourseworkReportSystem.getLecturerPage().setPageVisible(true);
                            } else {
                                AcademicCourseworkReportSystem.initLecturerPage();
                            }
                            AcademicCourseworkReportSystem.getLecturerPage().setCurrentSelectedLabel(1);
                            AcademicCourseworkReportSystem.getLecturerPage().updateWelcomeMessage();
                            AcademicCourseworkReportSystem.getLecturerPage().profilePage.showAllComponents();
                            AcademicCourseworkReportSystem.getLecturerPage().profilePage.updateInfo();
                            AcademicCourseworkReportSystem.getLecturerPage().profilePage.hideSomeComponents();
                            break;
                        case STUDENT:
                            if (AcademicCourseworkReportSystem.getStudentPage() != null) {
                                AcademicCourseworkReportSystem.getStudentPage().setPageVisible(true);
                            } else {
                                AcademicCourseworkReportSystem.initStudentPage();
                            }
                            AcademicCourseworkReportSystem.getStudentPage().setCurrentSelectedLabel(1);
                            AcademicCourseworkReportSystem.getStudentPage().updateWelcomeMessage();
                            AcademicCourseworkReportSystem.getStudentPage().profilePage.showAllComponents();
                            AcademicCourseworkReportSystem.getStudentPage().profilePage.hideSomeComponents();
                            AcademicCourseworkReportSystem.getStudentPage().profilePage.updateInfo();
                            break;
                    }
                    break;
                } else {
                    DialogUtils.showErrorMessageDialog(null, "Wrong username or password!");
                    AcademicCourseworkReportSystem.getLoggerManager().addLoginMessage(user.getName() + "(" + user.getUsername() + ") is attempting to login.");
                    return;
                }
            }
        }
        if (!found) {
            DialogUtils.showErrorMessageDialog(null, "Wrong username or password!");
        }
    }
}
