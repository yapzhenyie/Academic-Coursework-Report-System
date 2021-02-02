package oodj.group5.guis.subpages;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.utils.DialogUtils;
import oodj.group5.utils.ReportGenerator;

public class AdminReportsPage extends ISubPage {

    private ArrayList<Component> Components = new ArrayList<>();

    private JLabel labelTitle;

    private JLabel labelReport;
    private JComboBox<String> comboBoxReports;

    private JButton buttonGenerate;

    private boolean initialized = false;

    @Override
    protected void initComponents() {
        if (initialized) {
            return;
        }

        labelTitle = new JLabel();
        labelTitle.setBounds(10, 0, 150, 40);
        labelTitle.setFont(new java.awt.Font("Times New Roman", 1, 24));
        labelTitle.setText("Reports");
        Components.add(labelTitle);

        labelReport = new JLabel();
        labelReport.setBounds(80, 85, 80, 40);
        labelReport.setFont(new java.awt.Font("Times New Roman", 0, 18));
        labelReport.setText("Report :");
        Components.add(labelReport);

        comboBoxReports = new JComboBox();
        comboBoxReports.setBounds(160, 94, 250, 25);
        comboBoxReports.setFont(new java.awt.Font("Times New Roman", 0, 18));
        comboBoxReports.setModel(new DefaultComboBoxModel<>(new String[]{"Summary Users", "Modules", "Summary Students Enrolled",
            "Average Age of Students", "Modules Taught by Lecturers"}));
        Components.add(comboBoxReports);

        buttonGenerate = new JButton();
        buttonGenerate.setBounds(190, 250, 165, 35);
        buttonGenerate.setFont(new java.awt.Font("Times New Roman", 1, 18));
        buttonGenerate.setText("Generate Report");
        buttonGenerate.addActionListener(new ButtonCheckListener());
        Components.add(buttonGenerate);

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

    }

    @Override
    public void clearInfo() {

    }

    private class ButtonCheckListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == buttonGenerate) {
                if (comboBoxReports.getSelectedItem() == null) {
                    DialogUtils.showErrorMessageDialog(null, "Please select a report before proceed to the next step!");
                    return;
                }
                switch (comboBoxReports.getSelectedIndex()) {
                    case 0:
                    default:
                        ReportGenerator.generateSummaryUsersReport(true);
                        break;
                    case 1:
                        ReportGenerator.generateModulesReport(true);
                        break;
                    case 2:
                        ReportGenerator.generateSummaryStudentsEnrolledReport(true);
                        break;
                    case 3:
                        ReportGenerator.generateAverageAgeOfStudentsReport(true);
                        break;
                    case 4:
                        ReportGenerator.generateModulesTaughtByLecturersReport(true);
                        break;
                }
            }
        }
    }
}
