package oodj.group5.guis;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.guis.subpages.AdminLogActivityPage;
import oodj.group5.guis.subpages.AdminModuleEnrollmentPage;
import oodj.group5.guis.subpages.AdminModulesPage;
import oodj.group5.guis.subpages.AdminRegistrationPage;
import oodj.group5.guis.subpages.AdminReportsPage;
import oodj.group5.guis.subpages.AdminUserProfilePage;
import oodj.group5.utils.ButtonAnimation;
import oodj.group5.utils.DialogUtils;

public class AdminPage extends JFrame implements IPage {

    private JPanel panelTop;
    private JLabel labelLogo;
    private JLabel labelWelcome;

    private JPanel panelLogOut;
    private JLabel labelLogOutLogo;
    private JLabel labelLogOut;

    private JPanel panelLeft;
    private JLabel labelRegistration;
    private JLabel labelModuleEnrollment;
    private JLabel labelModules;
    private JLabel labelUserProfile;
    private JLabel labelReports;
    private JLabel labelLogActivity;

    private JPanel panelWorking;

    private JLabel currentSelectedLabel;

    public AdminRegistrationPage registrationPage;
    public AdminModuleEnrollmentPage moduleEnrollmentPage;
    public AdminModulesPage modulesPage;
    public AdminUserProfilePage userProfilePage;
    public AdminReportsPage reportsPage;
    public AdminLogActivityPage logActivityPage;

    public AdminPage() {
        super.setSize(770, 480);
        /**
         * Following source code obtained from (Jack, 2010)
         */
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        super.setTitle("Academic Coursework Report System");
        super.setIconImage(new ImageIcon(getClass().getResource("/oodj/resources/logo.png")).getImage());
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelTop = new JPanel();
        panelTop.setBounds(0, 0, 760, 60);
        panelTop.setBackground(new java.awt.Color(255, 153, 0));
        panelTop.setLayout(null);

        labelLogo = new JLabel();
        labelLogo.setBounds(5, 5, 50, 50);
        labelLogo.setIcon(new ImageIcon(getClass().getResource("/oodj/resources/small-logo.png")));

        labelWelcome = new JLabel();
        labelWelcome.setBounds(70, 25, 400, 30);
        labelWelcome.setFont(new java.awt.Font("Times New Roman", 1, 18));
        labelWelcome.setForeground(Color.WHITE);
        labelWelcome.setText("Welcome " + (AcademicCourseworkReportSystem.currentLoginUser == null ? "User" : AcademicCourseworkReportSystem.currentLoginUser.getName()));

        panelLogOut = new JPanel();
        panelLogOut.setBounds(690, 0, 50, 60);
        panelLogOut.setOpaque(false);
        panelLogOut.setLayout(null);
        panelLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {
                panelLogOutMouseClicked(event);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent event) {
                panelLogOutMouseEntered(event);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent event) {
                panelLogOutMouseExited(event);
            }
        });

        labelLogOutLogo = new JLabel();
        labelLogOutLogo.setBounds(5, 4, 40, 40);
        labelLogOutLogo.setIcon(new ImageIcon(getClass().getResource("/oodj/resources/logout-small.png")));

        labelLogOut = new JLabel();
        labelLogOut.setBounds(4, 35, 43, 30);
        labelLogOut.setFont(new java.awt.Font("Times New Roman", 1, 12));
        labelLogOut.setForeground(Color.WHITE);
        labelLogOut.setHorizontalAlignment(SwingConstants.CENTER);
        labelLogOut.setText("Log Out");

        panelLogOut.add(labelLogOutLogo);
        panelLogOut.add(labelLogOut);

        panelTop.add(labelLogo);
        panelTop.add(labelWelcome);
        panelTop.add(panelLogOut);

        panelLeft = new JPanel();
        panelLeft.setBounds(0, 60, 180, 390);
        panelLeft.setBackground(new java.awt.Color(153, 153, 255));
        panelLeft.setLayout(null);

        labelRegistration = new JLabel();
        labelRegistration.setBounds(0, 0, panelLeft.getSize().width, 40);
        labelRegistration.setText("Registration");
        labelRegistration.setFont(new java.awt.Font("Times New Roman", 1, 18));
        labelRegistration.setForeground(Color.WHITE);
        labelRegistration.setBackground(new java.awt.Color(255, 102, 102));
        labelRegistration.setOpaque(true);
        labelRegistration.setHorizontalAlignment(SwingConstants.CENTER);
        labelRegistration.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
        labelRegistration.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {
                labelRegistrationMouseClicked(event);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent event) {
                labelRegistrationMouseEntered(event);
            }

            @Override
            public void mouseMoved(java.awt.event.MouseEvent event) {
                labelRegistrationMouseMoved(event);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent event) {
                labelRegistrationMouseExited(event);
            }
        });

        labelModuleEnrollment = new JLabel();
        labelModuleEnrollment.setBounds(0, 39, panelLeft.getSize().width, 40);
        labelModuleEnrollment.setText("Module Enrollment");
        labelModuleEnrollment.setFont(new java.awt.Font("Times New Roman", 1, 18));
        labelModuleEnrollment.setForeground(Color.WHITE);
        labelModuleEnrollment.setBackground(new Color(255, 102, 102));
        labelModuleEnrollment.setOpaque(true);
        labelModuleEnrollment.setHorizontalAlignment(SwingConstants.CENTER);
        labelModuleEnrollment.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
        labelModuleEnrollment.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {
                labelModuleEnrollmentMouseClicked(event);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent event) {
                labelModuleEnrollmentMouseEntered(event);
            }

            @Override
            public void mouseMoved(java.awt.event.MouseEvent event) {
                labelModuleEnrollmentMouseMoved(event);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent event) {
                labelModuleEnrollmentMouseExited(event);
            }
        });

        labelModules = new JLabel();
        labelModules.setBounds(0, 78, panelLeft.getSize().width, 40);
        labelModules.setText("Modules");
        labelModules.setFont(new java.awt.Font("Times New Roman", 1, 18));
        labelModules.setForeground(Color.WHITE);
        labelModules.setBackground(new Color(255, 102, 102));
        labelModules.setOpaque(true);
        labelModules.setHorizontalAlignment(SwingConstants.CENTER);
        labelModules.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
        labelModules.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {
                labelModulesMouseClicked(event);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent event) {
                labelModulesMouseEntered(event);
            }

            @Override
            public void mouseMoved(java.awt.event.MouseEvent event) {
                labelModulesMouseMoved(event);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent event) {
                labelModulesMouseExited(event);
            }
        });

        labelUserProfile = new JLabel();
        labelUserProfile.setBounds(0, 116, panelLeft.getSize().width, 40);
        labelUserProfile.setText("User Profile");
        labelUserProfile.setFont(new java.awt.Font("Times New Roman", 1, 18));
        labelUserProfile.setForeground(Color.WHITE);
        labelUserProfile.setBackground(new Color(255, 102, 102));
        labelUserProfile.setOpaque(true);
        labelUserProfile.setHorizontalAlignment(SwingConstants.CENTER);
        labelUserProfile.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
        labelUserProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {
                labelUserProfileMouseClicked(event);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent event) {
                labelUserProfileMouseEntered(event);
            }

            @Override
            public void mouseMoved(java.awt.event.MouseEvent event) {
                labelUserProfileMouseMoved(event);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent event) {
                labelUserProfileMouseExited(event);
            }
        });

        labelReports = new JLabel();
        labelReports.setBounds(0, 155, panelLeft.getSize().width, 40);
        labelReports.setText("Reports");
        labelReports.setFont(new java.awt.Font("Times New Roman", 1, 18));
        labelReports.setForeground(Color.WHITE);
        labelReports.setBackground(new Color(255, 102, 102));
        labelReports.setOpaque(true);
        labelReports.setHorizontalAlignment(SwingConstants.CENTER);
        labelReports.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
        labelReports.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {
                labelReportsMouseClicked(event);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent event) {
                labelReportsMouseEntered(event);
            }

            @Override
            public void mouseMoved(java.awt.event.MouseEvent event) {
                labelReportsMouseMoved(event);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent event) {
                labelReportsMouseExited(event);
            }
        });

        labelLogActivity = new JLabel();
        labelLogActivity.setBounds(0, 194, panelLeft.getSize().width, 40);
        labelLogActivity.setText("Log Activity");
        labelLogActivity.setFont(new java.awt.Font("Times New Roman", 1, 18));
        labelLogActivity.setForeground(Color.WHITE);
        labelLogActivity.setBackground(new Color(255, 102, 102));
        labelLogActivity.setOpaque(true);
        labelLogActivity.setHorizontalAlignment(SwingConstants.CENTER);
        labelLogActivity.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
        labelLogActivity.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {
                labelLogActivityMouseClicked(event);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent event) {
                labelLogActivityMouseEntered(event);
            }

            @Override
            public void mouseMoved(java.awt.event.MouseEvent event) {
                labelLogActivityMouseMoved(event);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent event) {
                labelLogActivityMouseExited(event);
            }
        });

        panelLeft.add(labelRegistration);
        panelLeft.add(labelModuleEnrollment);
        panelLeft.add(labelModules);
        panelLeft.add(labelUserProfile);
        panelLeft.add(labelReports);
        panelLeft.add(labelLogActivity);

        panelWorking = new JPanel();
        panelWorking.setBounds(180, 60, 580, 420);
        panelWorking.setOpaque(false);
        panelWorking.setLayout(null);

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
        super.add(panelTop);
        super.add(panelLeft);
        super.add(panelWorking);
        super.setVisible(true);

        this.registrationPage = new AdminRegistrationPage();
        this.moduleEnrollmentPage = new AdminModuleEnrollmentPage();
        this.modulesPage = new AdminModulesPage();
        this.userProfilePage = new AdminUserProfilePage();
        this.reportsPage = new AdminReportsPage();
        this.logActivityPage = new AdminLogActivityPage();
    }

    @Override
    public void setPageVisible(boolean flag) {
        super.setVisible(flag);
    }

    @Override
    public void updateWelcomeMessage() {
        labelWelcome.setText("Welcome " + (AcademicCourseworkReportSystem.currentLoginUser == null ? "User"
                : AcademicCourseworkReportSystem.currentLoginUser.getName()));
    }

    @Override
    public void addWorkingPanelComponent(Component component) {
        panelWorking.add(component);
        panelWorking.updateUI();
    }

    /**
     * Log Out Panel events
     *
     * @param event
     */
    private void panelLogOutMouseClicked(java.awt.event.MouseEvent event) {
        Object[] options = {"Log out", "Cancel"};
        int selection = DialogUtils.showOptionDialog(null, "Are you sure you want to log out?",
                "Log out", options, options[0]);
        if (selection == 0) {
            logout();
        }
    }

    private void panelLogOutMouseEntered(java.awt.event.MouseEvent event) {
        ButtonAnimation.addClickedEffect(panelLogOut);
    }

    private void panelLogOutMouseExited(java.awt.event.MouseEvent event) {
        ButtonAnimation.removeClickedEffect(panelLogOut);
    }

    /**
     * Registration Label events
     *
     * @param event
     */
    private void labelRegistrationMouseClicked(java.awt.event.MouseEvent event) {
        if (currentSelectedLabel == this.labelRegistration) {
            return;
        }
        this.labelRegistration.requestFocus();
        restoreAndAddAnimation(this.labelRegistration);
        this.currentSelectedLabel = this.labelRegistration;

        this.moduleEnrollmentPage.clearInfo();
        this.moduleEnrollmentPage.hideAllComponents();

        this.modulesPage.clearInfo();
        this.modulesPage.hideAllComponents();

        this.userProfilePage.clearInfo();
        this.userProfilePage.hideAllComponents();

        this.reportsPage.clearInfo();
        this.reportsPage.hideAllComponents();

        this.logActivityPage.clearInfo();
        this.logActivityPage.hideAllComponents();

        this.registrationPage.showAllComponents();
    }

    private void labelRegistrationMouseEntered(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelRegistration);
    }

    private void labelRegistrationMouseMoved(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelRegistration);
    }

    private void labelRegistrationMouseExited(java.awt.event.MouseEvent event) {
        restoreAnimation(this.labelRegistration);
    }

    /**
     * Module Enrollment Label events
     *
     * @param event
     */
    private void labelModuleEnrollmentMouseClicked(java.awt.event.MouseEvent event) {
        if (currentSelectedLabel == this.labelModuleEnrollment) {
            return;
        }

        this.labelModuleEnrollment.requestFocus();
        restoreAndAddAnimation(this.labelModuleEnrollment);
        this.currentSelectedLabel = this.labelModuleEnrollment;

        this.registrationPage.clearInfo();
        this.registrationPage.hideAllComponents();

        this.modulesPage.clearInfo();
        this.modulesPage.hideAllComponents();

        this.userProfilePage.clearInfo();
        this.userProfilePage.hideAllComponents();

        this.reportsPage.clearInfo();
        this.reportsPage.hideAllComponents();

        this.logActivityPage.clearInfo();
        this.logActivityPage.hideAllComponents();

        this.moduleEnrollmentPage.showAllComponents();
        this.moduleEnrollmentPage.hideSomeComponents();
    }

    private void labelModuleEnrollmentMouseEntered(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelModuleEnrollment);
    }

    private void labelModuleEnrollmentMouseMoved(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelModuleEnrollment);
    }

    private void labelModuleEnrollmentMouseExited(java.awt.event.MouseEvent event) {
        restoreAnimation(this.labelModuleEnrollment);
    }

    /**
     * Modules Label events
     *
     * @param event
     */
    private void labelModulesMouseClicked(java.awt.event.MouseEvent event) {
        if (currentSelectedLabel == this.labelModules) {
            return;
        }

        this.labelModules.requestFocus();
        restoreAndAddAnimation(this.labelModules);
        this.currentSelectedLabel = this.labelModules;

        this.registrationPage.clearInfo();
        this.registrationPage.hideAllComponents();

        this.moduleEnrollmentPage.clearInfo();
        this.moduleEnrollmentPage.hideAllComponents();

        this.userProfilePage.clearInfo();
        this.userProfilePage.hideAllComponents();

        this.reportsPage.clearInfo();
        this.reportsPage.hideAllComponents();

        this.logActivityPage.clearInfo();
        this.logActivityPage.hideAllComponents();

        this.modulesPage.showAllComponents();
    }

    private void labelModulesMouseEntered(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelModules);
    }

    private void labelModulesMouseMoved(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelModules);
    }

    private void labelModulesMouseExited(java.awt.event.MouseEvent event) {
        restoreAnimation(this.labelModules);
    }

    /**
     * User Profile Label events
     *
     * @param event
     */
    private void labelUserProfileMouseClicked(java.awt.event.MouseEvent event) {
        if (currentSelectedLabel == this.labelUserProfile) {
            return;
        }

        this.labelUserProfile.requestFocus();
        restoreAndAddAnimation(this.labelUserProfile);
        this.currentSelectedLabel = this.labelUserProfile;

        this.registrationPage.clearInfo();
        this.registrationPage.hideAllComponents();

        this.moduleEnrollmentPage.clearInfo();
        this.moduleEnrollmentPage.hideAllComponents();

        this.modulesPage.clearInfo();
        this.modulesPage.hideAllComponents();

        this.reportsPage.clearInfo();
        this.reportsPage.hideAllComponents();

        this.logActivityPage.clearInfo();
        this.logActivityPage.hideAllComponents();

        this.userProfilePage.showAllComponents();
        this.userProfilePage.hideSomeComponents();
    }

    private void labelUserProfileMouseEntered(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelUserProfile);
    }

    private void labelUserProfileMouseMoved(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelUserProfile);
    }

    private void labelUserProfileMouseExited(java.awt.event.MouseEvent event) {
        restoreAnimation(this.labelUserProfile);
    }

    /**
     * Reports Label events
     *
     * @param event
     */
    private void labelReportsMouseClicked(java.awt.event.MouseEvent event) {
        if (currentSelectedLabel == this.labelReports) {
            return;
        }

        this.labelReports.requestFocus();
        restoreAndAddAnimation(this.labelReports);
        this.currentSelectedLabel = this.labelReports;

        this.registrationPage.clearInfo();
        this.registrationPage.hideAllComponents();

        this.moduleEnrollmentPage.clearInfo();
        this.moduleEnrollmentPage.hideAllComponents();

        this.modulesPage.clearInfo();
        this.modulesPage.hideAllComponents();

        this.userProfilePage.clearInfo();
        this.userProfilePage.hideAllComponents();

        this.logActivityPage.clearInfo();
        this.logActivityPage.hideAllComponents();

        this.reportsPage.showAllComponents();
    }

    private void labelReportsMouseEntered(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelReports);
    }

    private void labelReportsMouseMoved(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelReports);
    }

    private void labelReportsMouseExited(java.awt.event.MouseEvent event) {
        restoreAnimation(this.labelReports);
    }

    /**
     * Reports Label events
     *
     * @param event
     */
    private void labelLogActivityMouseClicked(java.awt.event.MouseEvent event) {
        if (currentSelectedLabel == this.labelLogActivity) {
            return;
        }

        this.labelLogActivity.requestFocus();
        restoreAndAddAnimation(this.labelLogActivity);
        this.currentSelectedLabel = this.labelLogActivity;

        this.registrationPage.clearInfo();
        this.registrationPage.hideAllComponents();

        this.moduleEnrollmentPage.clearInfo();
        this.moduleEnrollmentPage.hideAllComponents();

        this.modulesPage.clearInfo();
        this.modulesPage.hideAllComponents();

        this.userProfilePage.clearInfo();
        this.userProfilePage.hideAllComponents();

        this.reportsPage.clearInfo();
        this.reportsPage.hideAllComponents();

        this.logActivityPage.showAllComponents();
        this.logActivityPage.hideSomeComponents();
    }

    private void labelLogActivityMouseEntered(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelLogActivity);
    }

    private void labelLogActivityMouseMoved(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelLogActivity);
    }

    private void labelLogActivityMouseExited(java.awt.event.MouseEvent event) {
        restoreAnimation(this.labelLogActivity);
    }

    private void restoreAndAddAnimation(JLabel label) {
        if (currentSelectedLabel != null && currentSelectedLabel != label) {
            ButtonAnimation.restore(currentSelectedLabel);
        }
        ButtonAnimation.addAnimation(label);
    }

    private void restoreAnimation(JLabel label) {
        if (currentSelectedLabel == null || currentSelectedLabel != label) {
            ButtonAnimation.restore(label);
        }
    }

    @Override
    public void setCurrentSelectedLabel(int index) {
        JLabel newLabel = null;
        switch (index) {
            default:
            case 1:
                newLabel = this.labelRegistration;
                break;
            case 2:
                newLabel = this.labelModuleEnrollment;
                break;
            case 3:
                newLabel = this.labelModules;
                break;
            case 4:
                newLabel = this.labelUserProfile;
                break;
            case 5:
                newLabel = this.labelReports;
                break;
            case 6:
                newLabel = this.labelLogActivity;
                break;
        }
        if (newLabel == null) {
            return;
        }
        restoreAndAddAnimation(newLabel);
        currentSelectedLabel = newLabel;
    }

    private void logout() {
        this.panelLogOut.requestFocus();

        if (AcademicCourseworkReportSystem.currentLoginUser != null) {
            AcademicCourseworkReportSystem.getLoggerManager().addLogoutMessage(AcademicCourseworkReportSystem.currentLoginUser.getName()
                    + "(" + AcademicCourseworkReportSystem.currentLoginUser.getUsername() + ") logged out.");
        }
        /**
         * Dispose All sub page components.
         */
        this.registrationPage.clearInfo();
        this.registrationPage.hideAllComponents();

        this.moduleEnrollmentPage.clearInfo();
        this.moduleEnrollmentPage.hideAllComponents();

        this.modulesPage.clearInfo();
        this.modulesPage.hideAllComponents();

        this.userProfilePage.clearInfo();
        this.userProfilePage.hideAllComponents();

        this.reportsPage.clearInfo();
        this.reportsPage.hideAllComponents();

        this.logActivityPage.clearInfo();
        this.logActivityPage.hideAllComponents();

        /**
         * Reset labels.
         */
        this.currentSelectedLabel = null;
        restoreAnimation(this.labelRegistration);
        restoreAnimation(this.labelModuleEnrollment);
        restoreAnimation(this.labelModules);
        restoreAnimation(this.labelUserProfile);
        restoreAnimation(this.labelReports);
        restoreAnimation(this.labelLogActivity);

        setPageVisible(false);
        AcademicCourseworkReportSystem.getLoginPage().clearInfo();
        AcademicCourseworkReportSystem.getLoginPage().setPageVisible(true);
        AcademicCourseworkReportSystem.currentLoginUser = null;
    }
}
