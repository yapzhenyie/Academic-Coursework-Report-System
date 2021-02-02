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
import oodj.group5.objects.Result;
import oodj.group5.objects.users.Lecturer;
import oodj.group5.objects.users.Student;
import oodj.group5.utils.DialogUtils;

public class LecturerResultsPage extends ISubPage {

    private ArrayList<Component> Components = new ArrayList<>();

    private JLabel labelTitle;

    private JLabel labelModule;
    private JLabel labelStudent;
    private JComboBox<String> comboBoxModules;
    private JComboBox<String> comboBoxStudents;
    private JLabel labelName;
    private JLabel labelTPNumber;
    private JLabel labelResultName;
    private JLabel labelResultTPNumber;

    private JLabel labelAssessment1;
    private JLabel labelAssessment2;
    private JLabel labelAssessment3;
    private JLabel labelComment;
    private JTextField labelResultMark1;
    private JTextField labelResultMark2;
    private JTextField labelResultMark3;
    private JLabel labelMarksAllocate1;
    private JLabel labelMarksAllocate2;
    private JLabel labelMarksAllocate3;
    private JTextField labelResultComment;

    private JButton buttonSave;
    private JButton buttonCancel;

    private boolean initialized = false;

    private Student selectedStudent;

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

        labelModule = new JLabel();
        labelModule.setBounds(50, 40, 80, 40);
        labelModule.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelModule.setText("Module :");
        Components.add(labelModule);

        labelStudent = new JLabel();
        labelStudent.setBounds(50, 75, 80, 40);
        labelStudent.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelStudent.setText("Student :");
        Components.add(labelStudent);

        comboBoxModules = new JComboBox();
        comboBoxModules.setBounds(120, 50, 150, 22);
        comboBoxModules.setFont(new java.awt.Font("Times New Roman", 0, 14));
        comboBoxModules.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxModulesItemStateChanged(evt);
            }
        });
        Components.add(comboBoxModules);

        comboBoxStudents = new JComboBox();
        comboBoxStudents.setBounds(120, 85, 150, 22);
        comboBoxStudents.setFont(new java.awt.Font("Times New Roman", 0, 14));
        comboBoxStudents.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxStudentsItemStateChanged(evt);
            }
        });
        Components.add(comboBoxStudents);

        labelName = new JLabel();
        labelName.setBounds(10, 110, 100, 40);
        labelName.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelName.setText("Student Name :");
        Components.add(labelName);

        labelTPNumber = new JLabel();
        labelTPNumber.setBounds(380, 110, 80, 40);
        labelTPNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelTPNumber.setText("TP Number :");
        Components.add(labelTPNumber);

        labelResultName = new JLabel();
        labelResultName.setBounds(120, 110, 240, 40);
        labelResultName.setFont(new java.awt.Font("Times New Roman", 0, 15));
        Components.add(labelResultName);

        labelResultTPNumber = new JLabel();
        labelResultTPNumber.setBounds(475, 110, 80, 40);
        labelResultTPNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        Components.add(labelResultTPNumber);

        labelAssessment1 = new JLabel();
        labelAssessment1.setBounds(1, 145, 100, 40);
        labelAssessment1.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelAssessment1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Components.add(labelAssessment1);

        labelAssessment2 = new JLabel();
        labelAssessment2.setBounds(1, 180, 100, 40);
        labelAssessment2.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelAssessment2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Components.add(labelAssessment2);

        labelAssessment3 = new JLabel();
        labelAssessment3.setBounds(1, 215, 100, 40);
        labelAssessment3.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelAssessment3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Components.add(labelAssessment3);

        labelComment = new JLabel();
        labelComment.setBounds(37, 250, 70, 40);
        labelComment.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelComment.setText("Comment :");
        Components.add(labelComment);

        labelResultMark1 = new JTextField();
        labelResultMark1.setBounds(120, 155, 40, 25);
        labelResultMark1.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultMark1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Components.add(labelResultMark1);

        labelResultMark2 = new JTextField();
        labelResultMark2.setBounds(120, 190, 40, 25);
        labelResultMark2.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultMark2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Components.add(labelResultMark2);

        labelResultMark3 = new JTextField();
        labelResultMark3.setBounds(120, 225, 40, 25);
        labelResultMark3.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultMark3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Components.add(labelResultMark3);

        labelMarksAllocate1 = new JLabel();
        labelMarksAllocate1.setBounds(165, 145, 40, 40);
        labelMarksAllocate1.setFont(new java.awt.Font("Times New Roman", 0, 15));
        Components.add(labelMarksAllocate1);

        labelMarksAllocate2 = new JLabel();
        labelMarksAllocate2.setBounds(165, 180, 40, 40);
        labelMarksAllocate2.setFont(new java.awt.Font("Times New Roman", 0, 15));
        Components.add(labelMarksAllocate2);

        labelMarksAllocate3 = new JLabel();
        labelMarksAllocate3.setBounds(165, 215, 40, 40);
        labelMarksAllocate3.setFont(new java.awt.Font("Times New Roman", 0, 15));
        Components.add(labelMarksAllocate3);

        labelResultComment = new JTextField();
        labelResultComment.setBounds(120, 260, 200, 25);
        labelResultComment.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultComment.setCaretPosition(0);
        Components.add(labelResultComment);

        buttonSave = new JButton();
        buttonSave.setBounds(150, 320, 110, 35);
        buttonSave.setFont(new java.awt.Font("Times New Roman", 1, 18));
        buttonSave.setText("Save");
        buttonSave.addActionListener(new ButtonListener());
        Components.add(buttonSave);

        buttonCancel = new JButton();
        buttonCancel.setBounds(280, 320, 110, 35);
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
        if (!initialized) {
            return;
        }

        labelName.setVisible(false);
        labelTPNumber.setVisible(false);
        labelResultName.setVisible(false);
        labelResultTPNumber.setVisible(false);
        labelAssessment1.setVisible(false);
        labelAssessment2.setVisible(false);
        labelAssessment3.setVisible(false);
        labelComment.setVisible(false);
        labelResultMark1.setVisible(false);
        labelResultMark2.setVisible(false);
        labelResultMark3.setVisible(false);
        labelMarksAllocate1.setVisible(false);
        labelMarksAllocate2.setVisible(false);
        labelMarksAllocate3.setVisible(false);
        labelResultComment.setVisible(false);
        buttonSave.setVisible(false);
        buttonCancel.setVisible(false);
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
            if (modules.length > 0) {
                Module module = modulesTeach.get(0);
                ArrayList<Student> students = new ArrayList<>();
                for (Student student : Student.getStudents()) {
                    if (student.getModulesEnroll().contains(module)) {
                        students.add(student);
                    }
                }
                String[] studentNames = new String[students.size()];
                for (int i = 0; i < students.size(); i++) {
                    studentNames[i] = students.get(i).getUsername();
                }
                comboBoxStudents.setModel(new javax.swing.DefaultComboBoxModel<>(studentNames));
                if (studentNames.length > 0) {
                    updateStudentInformation(students.get(0), module);
                }
            }
        }
    }

    @Override
    public void clearInfo() {
        if (initialized) {
            comboBoxModules.setModel(new DefaultComboBoxModel<>());
            comboBoxStudents.setModel(new DefaultComboBoxModel<>());
            hideSomeComponents();
        }
    }

    private void updateStudentInformation(Student student, Module module) {
        if (student == null) {
            System.out.println("Student cannot be null");
            hideSomeComponents();
            return;
        }
        selectedStudent = student;

        labelName.setVisible(true);
        labelTPNumber.setVisible(true);
        labelResultName.setVisible(true);
        labelResultTPNumber.setVisible(true);
        labelResultName.setText(selectedStudent.getName());
        labelResultTPNumber.setText(selectedStudent.getTPNumber());

        int assessmentsAvailable = module.getAssessments().length;
        JLabel[] assessments = {labelAssessment1, labelAssessment2, labelAssessment3};
        JTextField[] assessmentMarks = {labelResultMark1, labelResultMark2, labelResultMark3};
        JLabel[] marksAllocate = {labelMarksAllocate1, labelMarksAllocate2, labelMarksAllocate3};
        Result result = Result.getResult(selectedStudent.getUsername(), module);
        try {
            for (int i = 0; i < assessmentsAvailable; i++) {
                assessments[i].setVisible(true);
                assessmentMarks[i].setVisible(true);
                marksAllocate[i].setVisible(true);
                assessments[i].setText(module.getAssessments()[i] + " :");
                if (result != null) {
                    assessmentMarks[i].setText(String.valueOf(result.getMarks()[i]));
                    assessmentMarks[i].setCaretPosition(0);
                } else {
                    assessmentMarks[i].setText("");
                }
                marksAllocate[i].setText(String.valueOf("(" + module.getMarksAllocated()[i] + "%)"));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        // If assessments less than 3
        if (assessmentsAvailable < 3) {
            for (int hide = assessmentsAvailable; hide < 3; hide++) {
                assessments[hide].setVisible(false);
                assessmentMarks[hide].setVisible(false);
                marksAllocate[hide].setVisible(false);
                assessments[hide].setText("");
                assessmentMarks[hide].setText("");
                marksAllocate[hide].setText("");
            }
        }
        labelComment.setVisible(true);
        labelResultComment.setVisible(true);
        if (result != null) {
            labelResultComment.setText(result.getComment());
            labelResultComment.setCaretPosition(0);
        } else {
            labelResultComment.setText("");
        }
        buttonSave.setVisible(true);
        buttonCancel.setVisible(true);
    }

    private void comboBoxModulesItemStateChanged(java.awt.event.ItemEvent evt) {
        Module selectedModule = Module.valueOf(comboBoxModules.getSelectedItem().toString());

        if (selectedModule == null) {
            DialogUtils.showErrorMessageDialog(null, "Module selected is invalid!");
            return;
        }
        Lecturer lecturer = (Lecturer) AcademicCourseworkReportSystem.currentLoginUser;
        if (lecturer == null) {
            DialogUtils.showErrorMessageDialog(null, "Please log in before you can insert student's marks.");
            return;
        }

        ArrayList<Student> students = new ArrayList<>();
        for (Student student : Student.getStudents()) {
            if (student.getModulesEnroll().contains(selectedModule)) {
                students.add(student);
            }
        }
        String[] studentNames = new String[students.size()];
        for (int i = 0; i < students.size(); i++) {
            studentNames[i] = students.get(i).getUsername();
        }
        comboBoxStudents.setModel(new javax.swing.DefaultComboBoxModel<>(studentNames));
        if (studentNames.length > 0) {
            updateStudentInformation(students.get(0), selectedModule);
        }
    }

    private void comboBoxStudentsItemStateChanged(java.awt.event.ItemEvent evt) {
        Module selectedModule = Module.valueOf(comboBoxModules.getSelectedItem().toString());

        if (selectedModule == null) {
            DialogUtils.showErrorMessageDialog(null, "Module selected is invalid!");
            return;
        }
        Student student = (Student) Student.valueOf(comboBoxStudents.getSelectedItem().toString());

        if (student == null) {
            DialogUtils.showErrorMessageDialog(null, "Student selected is invalid!");
            hideSomeComponents();
            return;
        }
        updateStudentInformation(student, selectedModule);
    }

    private class ButtonListener implements ActionListener {

        @Override
        @SuppressWarnings("empty-statement")
        public void actionPerformed(ActionEvent event) {
            Module selectedModule = Module.valueOf(comboBoxModules.getSelectedItem().toString());

            if (selectedModule == null) {
                DialogUtils.showErrorMessageDialog(null, "Module selected is invalid!");
                hideSomeComponents();
                return;
            }

            if (event.getSource() == buttonSave) {
                if (selectedStudent == null) {
                    DialogUtils.showErrorMessageDialog(null, "Student selected is invalid!");
                    return;
                }

                int assessmentsAvailable = selectedModule.getAssessments().length;
                JTextField[] assessmentMarks = {labelResultMark1, labelResultMark2, labelResultMark3};
                int[] marks = new int[assessmentsAvailable];
                for (int i = 0; i < assessmentsAvailable; i++) {
                    int mark;
                    try {
                        mark = Integer.parseInt(assessmentMarks[i].getText());
                    } catch (NumberFormatException e) {
                        DialogUtils.showErrorMessageDialog(null, "The marks can only contain numbers!");
                        return;
                    }
                    if (mark < 0) {
                        DialogUtils.showErrorMessageDialog(null, "The marks cannot have negative number!");
                        return;
                    }
                    if (mark > selectedModule.getMarksAllocated()[i]) {
                        DialogUtils.showErrorMessageDialog(null, "The marks cannot exceed its limit!");
                        return;
                    }
                    marks[i] = mark;
                }

                Result result = Result.getResult(selectedStudent.getUsername(), selectedModule);
                if (result == null) {
                    result = new Result(selectedStudent.getUsername(), selectedStudent.getIntake(), selectedModule, marks, labelResultComment.getText());
                    AcademicCourseworkReportSystem.RESULTS.add(result);
                } else {
                    result.setMarks(marks);
                    result.setComment(labelResultComment.getText());
                }
                JOptionPane.showMessageDialog(null, "Successful saved student result!");
                updateStudentInformation(selectedStudent, selectedModule);
            } else if (event.getSource() == buttonCancel) {
                updateStudentInformation(selectedStudent, selectedModule);
            }
        }
    }
}
