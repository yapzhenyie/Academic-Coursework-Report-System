package oodj.group5.guis.subpages;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.objects.Result;
import oodj.group5.objects.Intake;
import oodj.group5.objects.users.Student;

public class StudentResultsPage extends ISubPage {

    private ArrayList<Component> Components = new ArrayList<>();

    private JLabel labelTitle;

    private JLabel labelIntake;
    private JComboBox<String> comboBoxIntakes;

    private JTable tableResults;
    private JScrollPane scrollPaneResults;

    private boolean initialized = false;

    @Override
    protected void initComponents() {
        if (initialized) {
            return;
        }

        labelTitle = new JLabel();
        labelTitle.setBounds(10, 0, 100, 40);
        labelTitle.setFont(new java.awt.Font("Times New Roman", 1, 24));
        labelTitle.setText("Results");
        Components.add(labelTitle);

        labelIntake = new JLabel();
        labelIntake.setBounds(60, 50, 80, 40);
        labelIntake.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelIntake.setText("Intake :");
        Components.add(labelIntake);

        comboBoxIntakes = new JComboBox();
        comboBoxIntakes.setBounds(120, 60, 150, 22);
        comboBoxIntakes.setFont(new java.awt.Font("Times New Roman", 0, 14));
        comboBoxIntakes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxIntakesItemStateChanged(evt);
            }
        });
        Components.add(comboBoxIntakes);

        tableResults = new JTable();
        tableResults.setFont(new java.awt.Font("Times New Roman", 0, 16));
        tableResults.setPreferredScrollableViewportSize(new Dimension(450, 200));
        tableResults.setFillsViewportHeight(true);
        tableResults.setRowHeight(25);
        tableResults.setOpaque(false);
        tableResults.setDefaultEditor(Object.class, null);

        scrollPaneResults = new javax.swing.JScrollPane();
        scrollPaneResults.setBounds(65, 110, 450, 200);
        scrollPaneResults.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        scrollPaneResults.setViewportView(tableResults);
        Components.add(scrollPaneResults);

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

    }

    @Override
    public void updateInfo() {
        if (!initialized) {
            return;
        }
        Student student = (Student) AcademicCourseworkReportSystem.currentLoginUser;
        if (student != null) {
            ArrayList<Intake> intakes = Result.getIntakesAvailable(student.getUsername());
            String[] intakeCodes = new String[intakes.size()];
            for (int i = 0; i < intakes.size(); i++) {
                intakeCodes[i] = intakes.get(i).getIntakeCode();
            }
            comboBoxIntakes.setModel(new javax.swing.DefaultComboBoxModel<>(intakeCodes));
            if (intakeCodes.length > 0) {
                ArrayList<Result> results = Result.getResultsByIntake(student.getUsername(), intakes.get(0));
                String[][] data = new String[results.size()][2];
                for (int i = 0; i < results.size(); i++) {
                    data[i][0] = results.get(i).getModule().getModuleName();
                    data[i][1] = results.get(i).getGrade() == null ? "Invalid" : results.get(i).getGrade().getName();
                }
                tableResults.setModel(new javax.swing.table.DefaultTableModel(
                        data,
                        new String[]{"Module Name", "Grade"}
                ));
                tableResults.getColumnModel().getColumn(0).setPreferredWidth(250);
                tableResults.getColumnModel().getColumn(1).setPreferredWidth(30);
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setHorizontalAlignment(JLabel.CENTER);
                tableResults.getColumnModel().getColumn(1).setCellRenderer(renderer);
                scrollPaneResults.setVisible(true);
            }
        }
    }

    @Override
    public void clearInfo() {
        if (initialized) {
            comboBoxIntakes.setModel(new DefaultComboBoxModel<>());
            tableResults.setModel(new javax.swing.table.DefaultTableModel());
            hideSomeComponents();
        }
    }

    private void comboBoxIntakesItemStateChanged(java.awt.event.ItemEvent evt) {
        Intake selectedIntake = Intake.valueOf(comboBoxIntakes.getSelectedItem().toString());

        if (selectedIntake != null) {
            Student student = AcademicCourseworkReportSystem.currentLoginUser == null ? null : (Student) AcademicCourseworkReportSystem.currentLoginUser;
            if (student != null) {
                ArrayList<Result> results = Result.getResultsByIntake(student.getUsername(), selectedIntake);
                String[][] data = new String[results.size()][2];
                for (int i = 0; i < results.size(); i++) {
                    data[i][0] = results.get(i).getModule().getModuleName();
                    data[i][1] = results.get(i).getGrade() == null ? "Invalid" : results.get(i).getGrade().getName();
                }
                tableResults.setModel(new javax.swing.table.DefaultTableModel(
                        data,
                        new String[]{"Module Name", "Grade"}
                ));
                tableResults.getColumnModel().getColumn(0).setPreferredWidth(250);
                tableResults.getColumnModel().getColumn(1).setPreferredWidth(30);
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setHorizontalAlignment(JLabel.CENTER);
                tableResults.getColumnModel().getColumn(1).setCellRenderer(renderer);
                scrollPaneResults.setVisible(true);
            }
        }
    }
}
