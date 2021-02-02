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
import oodj.group5.objects.Module;
import oodj.group5.objects.users.Lecturer;
import oodj.group5.utils.DialogUtils;
import oodj.group5.utils.ReportGenerator;

public class LecturerReportsPage extends ISubPage {

    private ArrayList<Component> Components = new ArrayList<>();

    private JLabel labelTitle;

    private JLabel labelReport;
    private JLabel labelModule;
    private JComboBox<String> comboBoxReports;
    private JComboBox<String> comboBoxModules;

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
        labelReport.setBounds(116, 85, 80, 40);
        labelReport.setFont(new java.awt.Font("Times New Roman", 0, 18));
        labelReport.setText("Report :");
        Components.add(labelReport);

        labelModule = new JLabel();
        labelModule.setBounds(110, 130, 80, 40);
        labelModule.setFont(new java.awt.Font("Times New Roman", 0, 18));
        labelModule.setText("Module :");
        Components.add(labelModule);

        comboBoxReports = new JComboBox();
        comboBoxReports.setBounds(200, 94, 180, 25);
        comboBoxReports.setFont(new java.awt.Font("Times New Roman", 0, 18));
        comboBoxReports.setModel(new DefaultComboBoxModel<>(new String[]{"Summary Result", "Student Numbers", "Student Intakes"}));
        comboBoxReports.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxReportsItemStateChanged(evt);
            }
        });
        Components.add(comboBoxReports);

        comboBoxModules = new JComboBox();
        comboBoxModules.setBounds(200, 139, 180, 25);
        comboBoxModules.setFont(new java.awt.Font("Times New Roman", 0, 18));
        Components.add(comboBoxModules);

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

    }

    @Override
    public void updateInfo() {
        if (!initialized) {
            return;
        }
        Lecturer lecturer = (Lecturer) AcademicCourseworkReportSystem.currentLoginUser;
        if (lecturer != null) {
            ArrayList<Module> modulesTeach = Module.getModulesTeach(lecturer);
            String[] modules = new String[modulesTeach.size()];
            for (int i = 0; i < modulesTeach.size(); i++) {
                modules[i] = modulesTeach.get(i).getModuleCode();
            }
            comboBoxModules.setModel(new javax.swing.DefaultComboBoxModel<>(modules));
        }
    }

    @Override
    public void clearInfo() {
        if (!initialized) {
            return;
        }
        comboBoxReports.setSelectedIndex(0);
    }

    private void comboBoxReportsItemStateChanged(java.awt.event.ItemEvent evt) {
        if (comboBoxReports.getSelectedIndex() == 1) {
            labelModule.setVisible(false);
            comboBoxModules.setVisible(false);
        } else {
            labelModule.setVisible(true);
            comboBoxModules.setVisible(true);
        }
    }

    private class ButtonCheckListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == buttonGenerate) {
                if (comboBoxReports.getSelectedItem() == null) {
                    DialogUtils.showErrorMessageDialog(null, "Please select a report before proceed to the next step!");
                    return;
                }
                Module module = Module.valueOf(comboBoxModules.getSelectedItem().toString());
                if (comboBoxReports.getSelectedIndex() != 1) {
                    if (comboBoxModules.getSelectedItem() == null) {
                        DialogUtils.showErrorMessageDialog(null, "Please select a module before proceed to the next step!");
                        return;
                    }
                    if (module == null) {
                        DialogUtils.showErrorMessageDialog(null, "The module you selected is invalid!\nPlease try again.");
                        return;
                    }
                }
                switch (comboBoxReports.getSelectedIndex()) {
                    case 0:
                    default:
                        ReportGenerator.generateSummaryResultReport(true, module);
                        break;
                    case 1:
                        Lecturer lecturer = (Lecturer) AcademicCourseworkReportSystem.currentLoginUser;
                        if (lecturer == null) {
                            DialogUtils.showErrorMessageDialog(null, "You must log in before you can generate this report!");
                            return;
                        }
                        ReportGenerator.generateStudentNumbersReport(true, lecturer);
                        break;
                    case 2:
                        ReportGenerator.generateStudentIntakesReport(true, module);
                        break;
                }
            }
        }
    }
}
