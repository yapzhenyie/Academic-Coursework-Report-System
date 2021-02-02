package oodj.group5.guis.subpages;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.objects.Module;
import oodj.group5.objects.users.Lecturer;
import oodj.group5.objects.users.User;
import oodj.group5.utils.DialogUtils;

public class AdminModulesPage extends ISubPage {

    private ArrayList<Component> Components = new ArrayList<>();

    private JLabel labelTitle;

    private JLabel labelModule;
    private JLabel labelModuleCode;
    private JLabel labelModuleName;
    private JLabel labelLecturer;
    private JLabel labelAssessment1;
    private JLabel labelAssessment2;
    private JLabel labelAssessment3;
    private JLabel labelMarksAllocate1;
    private JLabel labelMarksAllocate2;
    private JLabel labelMarksAllocate3;

    private JComboBox<String> comboBoxModules;
    private JTextField fieldModuleCode;
    private JTextField fieldModuleName;
    private JComboBox<String> comboBoxLecturer;
    private JTextField fieldAssessment1;
    private JTextField fieldAssessment2;
    private JTextField fieldAssessment3;
    private JComboBox<String> comboBoxMarksAllocate1;
    private JComboBox<String> comboBoxMarksAllocate2;
    private JComboBox<String> comboBoxMarksAllocate3;

    /**
     * Button used for save and create purpose.
     */
    private JButton button;
    private JButton buttonDelete;

    private boolean initialized = false;

    private Module selectedModule = null;

    @Override
    protected void initComponents() {
        if (initialized) {
            return;
        }
        labelTitle = new JLabel();
        labelTitle.setBounds(10, 0, 100, 40);
        labelTitle.setFont(new java.awt.Font("Times New Roman", 1, 24));
        labelTitle.setText("Modules");
        Components.add(labelTitle);

        labelModule = new JLabel();
        labelModule.setBounds(50, 50, 135, 40);
        labelModule.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelModule.setText("Module :");
        Components.add(labelModule);

        labelModuleCode = new JLabel();
        labelModuleCode.setBounds(15, 90, 135, 40);
        labelModuleCode.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelModuleCode.setText("Module Code :");
        Components.add(labelModuleCode);

        labelModuleName = new JLabel();
        labelModuleName.setBounds(10, 130, 135, 40);
        labelModuleName.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelModuleName.setText("Module Name :");
        Components.add(labelModuleName);

        labelLecturer = new JLabel();
        labelLecturer.setBounds(43, 170, 135, 40);
        labelLecturer.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelLecturer.setText("Lecturer :");
        Components.add(labelLecturer);

        labelAssessment1 = new JLabel();
        labelAssessment1.setBounds(12, 210, 135, 40);
        labelAssessment1.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelAssessment1.setText("Assessment 1 :");
        Components.add(labelAssessment1);

        labelAssessment2 = new JLabel();
        labelAssessment2.setBounds(12, 250, 135, 40);
        labelAssessment2.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelAssessment2.setText("Assessment 2 :");
        Components.add(labelAssessment2);

        labelAssessment3 = new JLabel();
        labelAssessment3.setBounds(12, 290, 135, 40);
        labelAssessment3.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelAssessment3.setText("Assessment 3 :");
        Components.add(labelAssessment3);

        labelMarksAllocate1 = new JLabel();
        labelMarksAllocate1.setBounds(300, 210, 135, 40);
        labelMarksAllocate1.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelMarksAllocate1.setText("Marks Allocate :");
        Components.add(labelMarksAllocate1);

        labelMarksAllocate2 = new JLabel();
        labelMarksAllocate2.setBounds(300, 250, 135, 40);
        labelMarksAllocate2.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelMarksAllocate2.setText("Marks Allocate :");
        Components.add(labelMarksAllocate2);

        labelMarksAllocate3 = new JLabel();
        labelMarksAllocate3.setBounds(300, 290, 135, 40);
        labelMarksAllocate3.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelMarksAllocate3.setText("Marks Allocate :");
        Components.add(labelMarksAllocate3);

        comboBoxModules = new JComboBox();
        comboBoxModules.setBounds(120, 60, 150, 22);
        comboBoxModules.setFont(new java.awt.Font("Times New Roman", 0, 14));
        comboBoxModules.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxModulesItemStateChanged(evt);
            }
        });
        Components.add(comboBoxModules);

        fieldModuleCode = new JTextField();
        fieldModuleCode.setBounds(120, 100, 250, 25);
        fieldModuleCode.setFont(new java.awt.Font("Times New Roman", 0, 14));
        Components.add(fieldModuleCode);

        fieldModuleName = new JTextField();
        fieldModuleName.setBounds(120, 140, 250, 25);
        fieldModuleName.setFont(new java.awt.Font("Times New Roman", 0, 14));
        Components.add(fieldModuleName);

        comboBoxLecturer = new JComboBox();
        comboBoxLecturer.setBounds(120, 180, 150, 22);
        comboBoxLecturer.setFont(new java.awt.Font("Times New Roman", 0, 14));
        Components.add(comboBoxLecturer);

        fieldAssessment1 = new JTextField();
        fieldAssessment1.setBounds(120, 220, 150, 25);
        fieldAssessment1.setFont(new java.awt.Font("Times New Roman", 0, 14));
        Components.add(fieldAssessment1);

        fieldAssessment2 = new JTextField();
        fieldAssessment2.setBounds(120, 260, 150, 25);
        fieldAssessment2.setFont(new java.awt.Font("Times New Roman", 0, 14));
        Components.add(fieldAssessment2);

        fieldAssessment3 = new JTextField();
        fieldAssessment3.setBounds(120, 300, 150, 25);
        fieldAssessment3.setFont(new java.awt.Font("Times New Roman", 0, 14));
        Components.add(fieldAssessment3);

        comboBoxMarksAllocate1 = new JComboBox();
        comboBoxMarksAllocate1.setBounds(420, 220, 60, 22);
        comboBoxMarksAllocate1.setFont(new java.awt.Font("Times New Roman", 0, 14));
        Components.add(comboBoxMarksAllocate1);

        comboBoxMarksAllocate2 = new JComboBox();
        comboBoxMarksAllocate2.setBounds(420, 260, 60, 22);
        comboBoxMarksAllocate2.setFont(new java.awt.Font("Times New Roman", 0, 14));
        Components.add(comboBoxMarksAllocate2);

        comboBoxMarksAllocate3 = new JComboBox();
        comboBoxMarksAllocate3.setBounds(420, 300, 60, 22);
        comboBoxMarksAllocate3.setFont(new java.awt.Font("Times New Roman", 0, 14));
        Components.add(comboBoxMarksAllocate3);

        button = new JButton();
        button.setBounds(210, 330, 110, 35);
        button.setFont(new java.awt.Font("Times New Roman", 1, 18));
        button.setText("Create");
        button.addActionListener(new ButtonListener());
        Components.add(button);

        buttonDelete = new JButton();
        buttonDelete.setBounds(260, 330, 110, 35);
        buttonDelete.setFont(new java.awt.Font("Times New Roman", 1, 18));
        buttonDelete.setText("Delete");
        buttonDelete.addActionListener(new ButtonDeleteListener());
        buttonDelete.setVisible(false);
        Components.add(buttonDelete);

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
            if (component == buttonDelete) {
                continue;
            }
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
        String[] modules = new String[AcademicCourseworkReportSystem.MODULES.size() + 1];
        modules[0] = "New Module";
        for (int i = 0; i < AcademicCourseworkReportSystem.MODULES.size(); i++) {
            modules[i + 1] = AcademicCourseworkReportSystem.MODULES.get(i).getModuleCode();
        }
        comboBoxModules.setModel(new javax.swing.DefaultComboBoxModel<>(modules));

        ArrayList<Lecturer> lecturers = Lecturer.getLecturers();
        String[] lecturerUsernames = new String[lecturers.size()];
        for (int i = 0; i < lecturers.size(); i++) {
            lecturerUsernames[i] = lecturers.get(i).getUsername();
        }
        comboBoxLecturer.setModel(new javax.swing.DefaultComboBoxModel<>(lecturerUsernames));

        String[] marksAllocate = new String[21];
        int i = 0;
        for (int mark = 0; mark <= 100; mark = mark + 5) {
            marksAllocate[i] = mark + "%";
            i++;
        }
        comboBoxMarksAllocate1.setModel(new javax.swing.DefaultComboBoxModel<>(marksAllocate));
        comboBoxMarksAllocate2.setModel(new javax.swing.DefaultComboBoxModel<>(marksAllocate));
        comboBoxMarksAllocate3.setModel(new javax.swing.DefaultComboBoxModel<>(marksAllocate));
    }

    @Override
    public void clearInfo() {
        if (initialized) {
            comboBoxModules.setModel(new DefaultComboBoxModel<>());
            fieldModuleCode.setText("");
            fieldModuleName.setText("");
            comboBoxLecturer.setModel(new DefaultComboBoxModel<>());
            fieldAssessment1.setText("");
            fieldAssessment2.setText("");
            fieldAssessment3.setText("");
            button.setText("Create");
            button.setBounds(210, 330, 110, 35);
            comboBoxMarksAllocate1.setSelectedIndex(0);
            //comboBoxMarksAllocate2.setSelectedIndex(0);
            comboBoxMarksAllocate3.setSelectedIndex(0);
            buttonDelete.setVisible(false);
        }
        selectedModule = null;
    }

    private void updateModuleInfo() {
        if (!initialized || selectedModule == null) {
            return;
        }
        fieldModuleCode.setText(selectedModule.getModuleCode());
        fieldModuleCode.setCaretPosition(0);
        fieldModuleName.setText(selectedModule.getModuleName());
        fieldModuleName.setCaretPosition(0);
        ArrayList<Lecturer> lecturers = Lecturer.getLecturers();
        String[] lecturerUsernames = new String[lecturers.size()];
        for (int i = 0; i < lecturers.size(); i++) {
            lecturerUsernames[i] = lecturers.get(i).getUsername();
        }
        comboBoxLecturer.setModel(new javax.swing.DefaultComboBoxModel<>(lecturerUsernames));
        if (lecturerUsernames.length > 0 && selectedModule.getAssignedLecturer() != null) {
            comboBoxLecturer.setSelectedItem(selectedModule.getAssignedLecturer().getUsername());
        }

        JTextField[] fieldAssessments = new JTextField[]{fieldAssessment1, fieldAssessment2, fieldAssessment3};
        for (int i = 0; i <= 2; i++) {
            if (selectedModule.getAssessments().length >= i + 1) {
                fieldAssessments[i].setText(selectedModule.getAssessments()[i]);
                fieldAssessments[i].setCaretPosition(0);
            } else {
                fieldAssessments[i].setText("");
            }
        }
        JComboBox[] comboBoxMarks = new JComboBox[]{comboBoxMarksAllocate1, comboBoxMarksAllocate2, comboBoxMarksAllocate3};
        for (int i = 0; i <= 2; i++) {
            if (selectedModule.getMarksAllocated().length >= i + 1) {
                comboBoxMarks[i].setSelectedItem(selectedModule.getMarksAllocated()[i] + "%");
            } else {
                comboBoxMarks[i].setSelectedIndex(0);
            }
        }
    }

    private void comboBoxModulesItemStateChanged(java.awt.event.ItemEvent evt) {
        selectedModule = Module.valueOf(comboBoxModules.getSelectedItem().toString());

        if (selectedModule == null) {
            fieldModuleCode.setText("");
            fieldModuleName.setText("");
            ArrayList<Lecturer> lecturers = Lecturer.getLecturers();
            String[] lecturerUsernames = new String[lecturers.size()];
            for (int i = 0; i < lecturers.size(); i++) {
                lecturerUsernames[i] = lecturers.get(i).getUsername();
            }
            comboBoxLecturer.setModel(new javax.swing.DefaultComboBoxModel<>(lecturerUsernames));
            if (lecturerUsernames.length > 0) {
                comboBoxLecturer.setSelectedIndex(0);
            }
            button.setText("Create");
            button.setBounds(210, 330, 110, 35);
            buttonDelete.setVisible(false);
            return;
        }
        updateModuleInfo();
        button.setText("Save");
        button.setBounds(140, 330, 110, 35);
        buttonDelete.setVisible(true);
    }

    private class ButtonListener implements ActionListener {

        @Override
        @SuppressWarnings("empty-statement")
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == button) {
                if (selectedModule == null && comboBoxModules.getSelectedItem().equals("New Module")) {
                    if (fieldModuleCode.getText().equals("")) {
                        DialogUtils.showErrorMessageDialog(null, "Module code cannot be empty.");
                        return;
                    }
                    if (fieldModuleCode.getText().contains(";")) {
                        DialogUtils.showErrorMessageDialog(null, "Module code cannot contains \';\' symbol.");
                        return;
                    }
                    Module similarModule = Module.valueOf(fieldModuleCode.getText());
                    if (similarModule != null) {
                        DialogUtils.showErrorMessageDialog(null, "This module code has been taken!\nPlease use other code.");
                        return;
                    }
                    if (fieldModuleName.getText().equals("")) {
                        DialogUtils.showErrorMessageDialog(null, "Module name cannot be empty.");
                        return;
                    }
                    User lecturer = Lecturer.valueOf(comboBoxLecturer.getSelectedItem().toString());
                    if (lecturer == null) {
                        DialogUtils.showErrorMessageDialog(null, "Failed to find selected lecturer.\nPlease assign again the lecturer.");
                        ArrayList<Lecturer> lecturers = Lecturer.getLecturers();
                        String[] lecturerUsernames = new String[lecturers.size()];
                        for (int i = 0; i < lecturers.size(); i++) {
                            lecturerUsernames[i] = lecturers.get(i).getUsername();
                        }
                        comboBoxLecturer.setModel(new javax.swing.DefaultComboBoxModel<>(lecturerUsernames));
                        if (lecturerUsernames.length > 0) {
                            comboBoxLecturer.setSelectedIndex(0);
                        }
                        return;
                    }

                    if ((fieldAssessment1.getText().replace(" ", "").equals("")
                            && fieldAssessment2.getText().replace(" ", "").equals("")
                            && fieldAssessment3.getText().replace(" ", "").equals(""))) {
                        DialogUtils.showErrorMessageDialog(null, "You must set at least one assessment for the module.");
                        return;
                    }
                    if (fieldAssessment1.getText().replace(" ", "").equals("")) {
                        DialogUtils.showErrorMessageDialog(null, "You must fill in the assessment 1 before fill in other assessments.");
                        return;
                    }

                    int index1 = comboBoxMarksAllocate1.getSelectedIndex();
                    int index2 = comboBoxMarksAllocate2.getSelectedIndex();
                    int index3 = comboBoxMarksAllocate3.getSelectedIndex();

                    if ((index1 * 5 + index2 * 5 + index3 * 5) == 0) {
                        DialogUtils.showErrorMessageDialog(null, "You must allocate the marks for each assessment and\nmake sure is 100% in total for all sssessments.");
                        return;
                    }
                    if (comboBoxMarksAllocate1.getSelectedIndex() == 0) {
                        DialogUtils.showErrorMessageDialog(null, "You must allocate the marks for assessment 1\nbefore allocate for the others.");
                        return;
                    }
                    if ((!fieldAssessment2.getText().replace(" ", "").equals("") && index2 > 0 && (index1 * 5) >= 100)
                            || (!fieldAssessment3.getText().replace(" ", "").equals("") && index3 > 0 && (index1 * 5 + index2 * 5) >= 100)
                            || ((index1 * 5 + index2 * 5 + index3 * 5) > 100)
                            || ((index1 * 5 + index2 * 5 + index3 * 5) < 100)) {
                        DialogUtils.showErrorMessageDialog(null, "Make sure the marks allocate for all\nassessments is 100% in total.");
                        return;
                    }

                    if (fieldAssessment2.getText().replace(" ", "").equals("") && index2 > 0) {
                        DialogUtils.showErrorMessageDialog(null, "Assessment 2 name is empty!");
                        return;
                        // Assessment 2 is not empty but mark allocate 0%
                    } else if (!fieldAssessment2.getText().replace(" ", "").equals("") && index2 == 0) {
                        DialogUtils.showErrorMessageDialog(null, "Marks allocate for assessment 2 is 0%\nyou need to change the marks allocate or remove this assessment.");
                        return;
                    }

                    if (fieldAssessment3.getText().replace(" ", "").equals("") && index3 > 0) {
                        DialogUtils.showErrorMessageDialog(null, "Assessment 3 name is empty!");
                        return;
                        // Assessment 3 is not empty but mark allocate 0%
                    } else if (!fieldAssessment3.getText().replace(" ", "").equals("") && index3 == 0) {
                        DialogUtils.showErrorMessageDialog(null, "Marks allocate for assessment 3 is 0%\nyou need to change the marks allocate or remove this assessment.");
                        return;
                    }

                    String[] assessments = null;
                    int[] marksAllocate = null;
                    if ((!fieldAssessment2.getText().replace(" ", "").equals("") && !fieldAssessment3.getText().replace(" ", "").equals(""))
                            && (index2 > 0 && index3 > 0) && (index1 * 5 + index2 * 5 + index3 * 5) == 100) {
                        assessments = new String[]{fieldAssessment1.getText(), fieldAssessment2.getText(), fieldAssessment3.getText()};
                        marksAllocate = new int[]{index1 * 5, index2 * 5, index3 * 5};
                    } else if (!fieldAssessment2.getText().replace(" ", "").equals("") && (index1 * 5 + index2 * 5) == 100) {
                        assessments = new String[]{fieldAssessment1.getText(), fieldAssessment2.getText()};
                        marksAllocate = new int[]{index1 * 5, index2 * 5};
                    } else if (index1 * 5 == 100) {
                        assessments = new String[]{fieldAssessment1.getText()};
                        marksAllocate = new int[]{index1 * 5};
                    }
                    if (assessments == null || marksAllocate == null || assessments.length != marksAllocate.length) {
                        DialogUtils.showErrorMessageDialog(null, "Failed to get assessments and marks allocated.\n Please try again.");
                        return;
                    }

                    String[] options = new String[]{"Confirm", "Cancel"};
                    int selection = DialogUtils.showOptionDialog(null, "Confirm creating this module?", "Create New Module", options, options[0]);
                    if (selection == 0) {
                        Module module = new Module(fieldModuleCode.getText(), fieldModuleName.getText(), lecturer, assessments, marksAllocate);
                        AcademicCourseworkReportSystem.MODULES.add(module);
                        clearInfo();
                        updateInfo();
                    }
                } else {
                    if (fieldModuleCode.getText().equals("")) {
                        DialogUtils.showErrorMessageDialog(null, "Module code cannot be empty.");
                        return;
                    }
                    boolean moduleCodeChanged = false;
                    if (!fieldModuleCode.getText().equals(selectedModule.getModuleCode())) {
                        Module similarModule = Module.valueOf(fieldModuleCode.getText());
                        if (similarModule != null) {
                            DialogUtils.showErrorMessageDialog(null, "This module code has been taken!\nPlease use other code.");
                            return;
                        }
                        moduleCodeChanged = true;
                    }
                    if (fieldModuleName.getText().equals("")) {
                        DialogUtils.showErrorMessageDialog(null, "Module name cannot be empty.");
                        return;
                    }
                    User lecturer = Lecturer.valueOf(comboBoxLecturer.getSelectedItem().toString());
                    if (lecturer == null) {
                        DialogUtils.showErrorMessageDialog(null, "Failed to find selected lecturer.\nPlease assign again the lecturer.");
                        ArrayList<Lecturer> lecturers = Lecturer.getLecturers();
                        String[] lecturerUsernames = new String[lecturers.size()];
                        for (int i = 0; i < lecturers.size(); i++) {
                            lecturerUsernames[i] = lecturers.get(i).getUsername();
                        }
                        comboBoxLecturer.setModel(new javax.swing.DefaultComboBoxModel<>(lecturerUsernames));
                        if (lecturerUsernames.length > 0) {
                            comboBoxLecturer.setSelectedIndex(0);
                        }
                        return;
                    }

                    if ((fieldAssessment1.getText().replace(" ", "").equals("")
                            && fieldAssessment2.getText().replace(" ", "").equals("")
                            && fieldAssessment3.getText().replace(" ", "").equals(""))) {
                        DialogUtils.showErrorMessageDialog(null, "You must set at least one assessment for the module.");
                        return;
                    }
                    if (fieldAssessment1.getText().replace(" ", "").equals("")) {
                        DialogUtils.showErrorMessageDialog(null, "You must fill in the assessment 1 before fill in other assessments.");
                        return;
                    }

                    int index1 = comboBoxMarksAllocate1.getSelectedIndex();
                    int index2 = comboBoxMarksAllocate2.getSelectedIndex();
                    int index3 = comboBoxMarksAllocate3.getSelectedIndex();

                    if ((index1 * 5 + index2 * 5 + index3 * 5) == 0) {
                        DialogUtils.showErrorMessageDialog(null, "You must allocate the marks for each assessment and\nmake sure is 100% in total for all sssessments.");
                        return;
                    }
                    if (comboBoxMarksAllocate1.getSelectedIndex() == 0) {
                        DialogUtils.showErrorMessageDialog(null, "You must allocate the marks for assessment 1\nbefore allocate for the others.");
                        return;
                    }
                    if ((!fieldAssessment2.getText().replace(" ", "").equals("") && index2 > 0 && (index1 * 5) >= 100)
                            || (!fieldAssessment3.getText().replace(" ", "").equals("") && index3 > 0 && (index1 * 5 + index2 * 5) >= 100)
                            || ((index1 * 5 + index2 * 5 + index3 * 5) > 100)
                            || ((index1 * 5 + index2 * 5 + index3 * 5) < 100)) {
                        DialogUtils.showErrorMessageDialog(null, "Make sure the marks allocate for all\nassessments is 100% in total.");
                        return;
                    }

                    if (fieldAssessment2.getText().replace(" ", "").equals("") && index2 > 0) {
                        DialogUtils.showErrorMessageDialog(null, "Assessment 2 name is empty!");
                        return;
                        // Assessment 2 is not empty but mark allocate 0%
                    } else if (!fieldAssessment2.getText().replace(" ", "").equals("") && index2 == 0) {
                        DialogUtils.showErrorMessageDialog(null, "Marks allocate for assessment 2 is 0%\nyou need to change the marks allocate or remove this assessment.");
                        return;
                    }

                    if (fieldAssessment3.getText().replace(" ", "").equals("") && index3 > 0) {
                        DialogUtils.showErrorMessageDialog(null, "Assessment 3 name is empty!");
                        return;
                        // Assessment 3 is not empty but mark allocate 0%
                    } else if (!fieldAssessment3.getText().replace(" ", "").equals("") && index3 == 0) {
                        DialogUtils.showErrorMessageDialog(null, "Marks allocate for assessment 3 is 0%\nyou need to change the marks allocate or remove this assessment.");
                        return;
                    }

                    String[] assessments = null;
                    int[] marksAllocate = null;
                    if ((!fieldAssessment2.getText().replace(" ", "").equals("") && !fieldAssessment3.getText().replace(" ", "").equals(""))
                            && (index2 > 0 && index3 > 0) && (index1 * 5 + index2 * 5 + index3 * 5) == 100) {
                        assessments = new String[]{fieldAssessment1.getText(), fieldAssessment2.getText(), fieldAssessment3.getText()};
                        marksAllocate = new int[]{index1 * 5, index2 * 5, index3 * 5};
                    } else if (!fieldAssessment2.getText().replace(" ", "").equals("") && (index1 * 5 + index2 * 5) == 100) {
                        assessments = new String[]{fieldAssessment1.getText(), fieldAssessment2.getText()};
                        marksAllocate = new int[]{index1 * 5, index2 * 5};
                    } else if (index1 * 5 == 100) {
                        assessments = new String[]{fieldAssessment1.getText()};
                        marksAllocate = new int[]{index1 * 5};
                    }
                    if (assessments == null || marksAllocate == null || assessments.length != marksAllocate.length) {
                        DialogUtils.showErrorMessageDialog(null, "Failed to get assessments and marks allocated.\n Please try again.");
                        return;
                    }

                    for (Module module : AcademicCourseworkReportSystem.MODULES) {
                        if (module == selectedModule) {
                            module.setModuleCode(fieldModuleCode.getText());
                            module.setModuleName(fieldModuleName.getText());
                            module.setAssignedLecturer(lecturer);
                            module.setAssessments(assessments);
                            module.setMarksAllocated(marksAllocate);
                            selectedModule = module;
                            break;
                        }
                    }
                    if (moduleCodeChanged) {
                        String[] modules = new String[AcademicCourseworkReportSystem.MODULES.size() + 1];
                        modules[0] = "New Module";
                        for (int i = 0; i < AcademicCourseworkReportSystem.MODULES.size(); i++) {
                            modules[i + 1] = AcademicCourseworkReportSystem.MODULES.get(i).getModuleCode();
                        }
                        comboBoxModules.setModel(new javax.swing.DefaultComboBoxModel<>(modules));
                        comboBoxModules.setSelectedItem(selectedModule.getModuleCode());
                    }
                    updateModuleInfo();
                    JOptionPane.showMessageDialog(null, "Module info saved.");
                }
            }
        }
    }

    private class ButtonDeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == buttonDelete) {
                String[] options = new String[]{"Yes", "No"};
                int selection = DialogUtils.showOptionDialog(null, "Are you sure you want to remove this module?", "Remove Module", options, options[0]);
                if (selection == 0) {
                    AcademicCourseworkReportSystem.MODULES.remove(selectedModule);
                    clearInfo();
                    updateInfo();
                }
            }
        }
    }
}
