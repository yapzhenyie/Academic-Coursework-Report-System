package oodj.group5.guis.subpages;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.objects.Module;
import oodj.group5.objects.users.Student;

public class StudentModuleEnrolledPage extends ISubPage {

    private ArrayList<Component> Components = new ArrayList<>();

    private JLabel labelTitle;

    private JTable tableModuleEnrolled;
    private JScrollPane scrollPaneModuleEnrolled;

    private boolean initialized = false;

    @Override
    protected void initComponents() {
        if (initialized) {
            return;
        }

        labelTitle = new JLabel();
        labelTitle.setBounds(10, 0, 230, 40);
        labelTitle.setFont(new java.awt.Font("Times New Roman", 1, 24));
        labelTitle.setText("Module Enrolled");
        Components.add(labelTitle);

        tableModuleEnrolled = new JTable() {
            public String getToolTipText(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());

                Object value = null;
                try {
                    value = getValueAt(row, column);
                } catch (ArrayIndexOutOfBoundsException ex) {

                }
                return value == null ? null : value.toString();
            }
        };
        tableModuleEnrolled.setFont(new java.awt.Font("Times New Roman", 0, 16));
        tableModuleEnrolled.setPreferredScrollableViewportSize(new Dimension(450, 200));
        tableModuleEnrolled.setFillsViewportHeight(true);
        tableModuleEnrolled.setRowHeight(30);
        tableModuleEnrolled.setOpaque(false);
        tableModuleEnrolled.setDefaultEditor(Object.class, null);

        scrollPaneModuleEnrolled = new javax.swing.JScrollPane();
        scrollPaneModuleEnrolled.setBounds(30, 80, 500, 250);
        scrollPaneModuleEnrolled.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        scrollPaneModuleEnrolled.setViewportView(tableModuleEnrolled);
        Components.add(scrollPaneModuleEnrolled);

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
            ArrayList<Module> modules = student.getModulesEnroll();
            String[][] data = new String[modules.size()][2];
            for (int i = 0; i < modules.size(); i++) {
                data[i][0] = modules.get(i).getModuleName();
                data[i][1] = modules.get(i).getAssignedLecturer() == null ? "Invalid" : modules.get(i).getAssignedLecturer().getName();
            }
            tableModuleEnrolled.setModel(new javax.swing.table.DefaultTableModel(
                    data,
                    new String[]{"Module Name", "Lecturer"}
            ));
            tableModuleEnrolled.getColumnModel().getColumn(0).setPreferredWidth(230);
            tableModuleEnrolled.getColumnModel().getColumn(1).setPreferredWidth(100);
            scrollPaneModuleEnrolled.setVisible(true);
        }
    }

    @Override
    public void clearInfo() {
        if (initialized) {
            tableModuleEnrolled.setModel(new javax.swing.table.DefaultTableModel());
            hideSomeComponents();
        }
    }
}
