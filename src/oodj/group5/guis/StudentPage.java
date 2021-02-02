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
import oodj.group5.guis.subpages.StudentModuleEnrolledPage;
import oodj.group5.guis.subpages.StudentProfilePage;
import oodj.group5.guis.subpages.StudentResultsPage;
import oodj.group5.utils.ButtonAnimation;
import oodj.group5.utils.DialogUtils;

public class StudentPage extends JFrame implements IPage {

    private JPanel panelTop;
    private JLabel labelLogo;
    private JLabel labelWelcome;

    private JPanel panelLogOut;
    private JLabel labelLogOutLogo;
    private JLabel labelLogOut;

    private JPanel panelLeft;
    private JLabel labelProfile;
    private JLabel labelModuleEnrolled;
    private JLabel labelResults;

    private JPanel panelWorking;

    private JLabel currentSelectedLabel;

    public StudentProfilePage profilePage;
    public StudentModuleEnrolledPage moduleEnrolledPage;
    public StudentResultsPage resultsPage;

    public StudentPage() {
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

        labelProfile = new JLabel();
        labelProfile.setBounds(0, 0, panelLeft.getSize().width, 40);
        labelProfile.setText("Profile");
        labelProfile.setFont(new java.awt.Font("Times New Roman", 1, 18));
        labelProfile.setForeground(Color.WHITE);
        labelProfile.setBackground(new java.awt.Color(255, 102, 102));
        labelProfile.setOpaque(true);
        labelProfile.setHorizontalAlignment(SwingConstants.CENTER);
        labelProfile.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
        labelProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {
                labelProfileMouseClicked(event);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent event) {
                labelProfileMouseEntered(event);
            }

            @Override
            public void mouseMoved(java.awt.event.MouseEvent event) {
                labelProfileMouseMoved(event);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent event) {
                labelProfileMouseExited(event);
            }
        });

        labelModuleEnrolled = new JLabel();
        labelModuleEnrolled.setBounds(0, 39, panelLeft.getSize().width, 40);
        labelModuleEnrolled.setText("Module Enrolled");
        labelModuleEnrolled.setFont(new java.awt.Font("Times New Roman", 1, 18));
        labelModuleEnrolled.setForeground(Color.WHITE);
        labelModuleEnrolled.setBackground(new java.awt.Color(255, 102, 102));
        labelModuleEnrolled.setOpaque(true);
        labelModuleEnrolled.setHorizontalAlignment(SwingConstants.CENTER);
        labelModuleEnrolled.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
        labelModuleEnrolled.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {
                labelModuleEnrolledMouseClicked(event);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent event) {
                labelModuleEnrolledMouseEntered(event);
            }

            @Override
            public void mouseMoved(java.awt.event.MouseEvent event) {
                labelModuleEnrolledMouseMoved(event);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent event) {
                labelModuleEnrolledMouseExited(event);
            }
        });

        labelResults = new JLabel();
        labelResults.setBounds(0, 78, panelLeft.getSize().width, 40);
        labelResults.setText("Results");
        labelResults.setFont(new java.awt.Font("Times New Roman", 1, 18));
        labelResults.setForeground(Color.WHITE);
        labelResults.setBackground(new Color(255, 102, 102));
        labelResults.setOpaque(true);
        labelResults.setHorizontalAlignment(SwingConstants.CENTER);
        labelResults.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
        labelResults.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event) {
                labelResultsMouseClicked(event);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent event) {
                labelResultsMouseEntered(event);
            }

            @Override
            public void mouseMoved(java.awt.event.MouseEvent event) {
                labelResultsMouseMoved(event);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent event) {
                labelResultsMouseExited(event);
            }
        });

        panelLeft.add(labelProfile);
        panelLeft.add(labelModuleEnrolled);
        panelLeft.add(labelResults);

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

        this.profilePage = new StudentProfilePage();
        this.moduleEnrolledPage = new StudentModuleEnrolledPage();
        this.resultsPage = new StudentResultsPage();
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
     * Profile Label events
     *
     * @param event
     */
    private void labelProfileMouseClicked(java.awt.event.MouseEvent event) {
        if (currentSelectedLabel == this.labelProfile) {
            return;
        }
        this.labelProfile.requestFocus();
        restoreAndAddAnimation(this.labelProfile);
        this.currentSelectedLabel = this.labelProfile;

        this.resultsPage.clearInfo();
        this.resultsPage.hideAllComponents();

        this.moduleEnrolledPage.clearInfo();
        this.moduleEnrolledPage.hideAllComponents();

        this.profilePage.showAllComponents();
        this.profilePage.hideSomeComponents();
        this.profilePage.updateInfo();

    }

    private void labelProfileMouseEntered(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelProfile);
    }

    private void labelProfileMouseMoved(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelProfile);
    }

    private void labelProfileMouseExited(java.awt.event.MouseEvent event) {
        restoreAnimation(this.labelProfile);
    }
    
    /**
     * Module Enrolled Label events
     *
     * @param event
     */
    private void labelModuleEnrolledMouseClicked(java.awt.event.MouseEvent event) {
        if (currentSelectedLabel == this.labelModuleEnrolled) {
            return;
        }
        this.labelModuleEnrolled.requestFocus();
        restoreAndAddAnimation(this.labelModuleEnrolled);
        this.currentSelectedLabel = this.labelModuleEnrolled;

        this.profilePage.clearInfo();
        this.profilePage.hideAllComponents();
        
        this.resultsPage.clearInfo();
        this.resultsPage.hideAllComponents();

        this.moduleEnrolledPage.showAllComponents();
        this.moduleEnrolledPage.updateInfo();

    }

    private void labelModuleEnrolledMouseEntered(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelModuleEnrolled);
    }

    private void labelModuleEnrolledMouseMoved(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelModuleEnrolled);
    }

    private void labelModuleEnrolledMouseExited(java.awt.event.MouseEvent event) {
        restoreAnimation(this.labelModuleEnrolled);
    }

    /**
     * Results Label events
     *
     * @param event
     */
    private void labelResultsMouseClicked(java.awt.event.MouseEvent event) {
        if (currentSelectedLabel == this.labelResults) {
            return;
        }

        this.labelResults.requestFocus();
        restoreAndAddAnimation(this.labelResults);
        this.currentSelectedLabel = this.labelResults;

        this.profilePage.clearInfo();
        this.profilePage.hideAllComponents();

        this.moduleEnrolledPage.clearInfo();
        this.moduleEnrolledPage.hideAllComponents();

        this.resultsPage.showAllComponents();
        this.resultsPage.updateInfo();
    }

    private void labelResultsMouseEntered(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelResults);
    }

    private void labelResultsMouseMoved(java.awt.event.MouseEvent event) {
        ButtonAnimation.addBold(this.labelResults);
    }

    private void labelResultsMouseExited(java.awt.event.MouseEvent event) {
        restoreAnimation(this.labelResults);
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
                newLabel = this.labelProfile;
                break;
            case 2:
                newLabel = this.labelModuleEnrolled;
                break;
            case 3:
                newLabel = this.labelResults;
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
        this.profilePage.clearInfo();
        this.profilePage.hideAllComponents();

        this.moduleEnrolledPage.clearInfo();
        this.moduleEnrolledPage.hideAllComponents();

        this.resultsPage.clearInfo();
        this.resultsPage.hideAllComponents();

        /**
         * Reset labels.
         */
        this.currentSelectedLabel = null;
        restoreAnimation(this.labelProfile);
        restoreAnimation(this.labelModuleEnrolled);
        restoreAnimation(this.labelResults);

        setPageVisible(false);
        AcademicCourseworkReportSystem.getLoginPage().clearInfo();
        AcademicCourseworkReportSystem.getLoginPage().setPageVisible(true);
        AcademicCourseworkReportSystem.currentLoginUser = null;
    }
}
