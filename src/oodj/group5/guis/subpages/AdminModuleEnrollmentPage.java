package oodj.group5.guis.subpages;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.objects.Intake;
import oodj.group5.objects.Module;
import oodj.group5.objects.users.Student;
import oodj.group5.utils.DialogUtils;

public class AdminModuleEnrollmentPage extends ISubPage {

    private ArrayList<Component> Components = new ArrayList<>();

    private JLabel labelTitle;

    private JTextField fieldSearchBox;
    private JButton buttonSearch;
    private JList<String> listSearchResult;
    private JScrollPane scrollPaneSearchResult;
    private JTable tableStudentList;
    private JScrollPane scrollPaneStudentList;

    private JLabel labelStudentName;
    private JLabel labelUsername;
    private JLabel labelTPNumber;
    private JLabel labelICNumber;
    private JLabel labelIntake;
    private JLabel labelModulesEnroll;

    private JLabel labelResultStudentName;
    private JLabel labelResultUsername;
    private JLabel labelResultTPNumber;
    private JLabel labelResultICNumber;
    private JComboBox<String> comboBoxResultIntake;
    private JList<String> listModulesEnroll;
    private JScrollPane scrollPaneModulesEnroll;
    private JButton buttonDisenroll;

    private JLabel labelModulesAvailable;
    private JList<String> listModulesAvailable;
    private JScrollPane scrollPaneModulesAvailable;
    private JButton buttonEnroll;

    private ArrayList<Student> searchResults = new ArrayList<>();
    private Student selectedStudent = null;

    private boolean initialized = false;

    @Override
    protected void initComponents() {
        if (initialized) {
            return;
        }

        labelTitle = new JLabel();
        labelTitle.setBounds(10, 0, 250, 40);
        labelTitle.setFont(new java.awt.Font("Times New Roman", 1, 24));
        labelTitle.setText("Module Enrollment");
        Components.add(labelTitle);

        fieldSearchBox = new JTextField();
        fieldSearchBox.setBounds(335, 20, 130, 25);
        fieldSearchBox.setText("TP Number");
        fieldSearchBox.setForeground(Color.GRAY);
        fieldSearchBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldSearchBoxFocusGained(evt);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldSearchBoxFocusLost(evt);
            }
        });
        fieldSearchBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                searchByTPNumber(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchByTPNumber(false);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                searchByTPNumber(false);
            }
        });
        fieldSearchBox.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchByTPNumber(true);
                }
            }

            @Override
            public void keyTyped(KeyEvent paramKeyEvent) {
            }

            @Override
            public void keyReleased(KeyEvent paramKeyEvent) {
            }
        });
        Components.add(fieldSearchBox);

        buttonSearch = new JButton();
        buttonSearch.setBounds(465, 20, 75, 25);
        buttonSearch.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new ButtonSearchListener());
        Components.add(buttonSearch);

        listSearchResult = new JList<>();
        listSearchResult.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listSearchResultValueChanged(evt);
            }
        });

        scrollPaneSearchResult = new javax.swing.JScrollPane();
        scrollPaneSearchResult.setBounds(335, 44, 130, 110);
        scrollPaneSearchResult.setViewportView(listSearchResult);
        Components.add(scrollPaneSearchResult);

        tableStudentList = new JTable();
        tableStudentList.setFont(new java.awt.Font("Times New Roman", 0, 14));
        tableStudentList.setPreferredScrollableViewportSize(new Dimension(450, 250));
        tableStudentList.setFillsViewportHeight(true);
        String[][] data = new String[Student.getStudents().size()][3];
        for (int i = 0; i < Student.getStudents().size(); i++) {
            Student student = Student.getStudents().get(i);
            data[i][0] = student.getUsername();
            data[i][1] = student.getName();
            data[i][2] = student.getTPNumber();
        }
        tableStudentList.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[]{"Username", "Student Name", "TP Number"}
        ));
        tableStudentList.setRowHeight(25);
        tableStudentList.setOpaque(false);
        tableStudentList.setDefaultEditor(Object.class, null);
        tableStudentList.getColumnModel().getColumn(0).setPreferredWidth(30);
        tableStudentList.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableStudentList.getColumnModel().getColumn(2).setPreferredWidth(15);

        scrollPaneStudentList = new javax.swing.JScrollPane();
        scrollPaneStudentList.setBounds(50, 80, 450, 250);
        scrollPaneStudentList.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        scrollPaneStudentList.setViewportView(tableStudentList);
        Components.add(scrollPaneStudentList);

        labelStudentName = new JLabel();
        labelStudentName.setBounds(45, 50, 135, 40);
        labelStudentName.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelStudentName.setText("Student Name :");
        Components.add(labelStudentName);

        labelUsername = new JLabel();
        labelUsername.setBounds(68, 85, 80, 40);
        labelUsername.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelUsername.setText("Username :");
        Components.add(labelUsername);

        labelTPNumber = new JLabel();
        labelTPNumber.setBounds(59, 120, 80, 40);
        labelTPNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelTPNumber.setText("TP Number :");
        Components.add(labelTPNumber);

        labelICNumber = new JLabel();
        labelICNumber.setBounds(62, 155, 80, 40);
        labelICNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelICNumber.setText("IC Number :");
        Components.add(labelICNumber);

        labelIntake = new JLabel();
        labelIntake.setBounds(92, 190, 60, 40);
        labelIntake.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelIntake.setText("Intake :");
        Components.add(labelIntake);

        labelModulesEnroll = new JLabel();
        labelModulesEnroll.setBounds(41, 225, 100, 40);
        labelModulesEnroll.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelModulesEnroll.setText("Modules Enroll :");
        Components.add(labelModulesEnroll);

        labelResultStudentName = new JLabel();
        labelResultStudentName.setBounds(155, 50, 250, 40);
        labelResultStudentName.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultStudentName.setText("");
        Components.add(labelResultStudentName);

        labelResultUsername = new JLabel();
        labelResultUsername.setBounds(155, 85, 250, 40);
        labelResultUsername.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultUsername.setText("");
        Components.add(labelResultUsername);

        labelResultTPNumber = new JLabel();
        labelResultTPNumber.setBounds(155, 120, 100, 40);
        labelResultTPNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultTPNumber.setText("");
        Components.add(labelResultTPNumber);

        labelResultICNumber = new JLabel();
        labelResultICNumber.setBounds(155, 155, 150, 40);
        labelResultICNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultICNumber.setText("");
        Components.add(labelResultICNumber);

        comboBoxResultIntake = new JComboBox();
        comboBoxResultIntake.setBounds(155, 200, 150, 22);
        comboBoxResultIntake.setFont(new java.awt.Font("Times New Roman", 0, 15));
        comboBoxResultIntake.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxResultIntakeItemStateChanged(evt);
            }
        });
        Components.add(comboBoxResultIntake);

        listModulesEnroll = new JList<>();
        listModulesEnroll.setFont(new java.awt.Font("Times New Roman", 0, 15));

        scrollPaneModulesEnroll = new javax.swing.JScrollPane();
        scrollPaneModulesEnroll.setBounds(155, 235, 180, 62);
        scrollPaneModulesEnroll.setViewportView(listModulesEnroll);
        Components.add(scrollPaneModulesEnroll);

        buttonDisenroll = new JButton();
        buttonDisenroll.setBounds(195, 305, 90, 25);
        buttonDisenroll.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonDisenroll.setText("Disenroll");
        buttonDisenroll.addActionListener(new ButtonListener());
        Components.add(buttonDisenroll);

        labelModulesAvailable = new JLabel();
        labelModulesAvailable.setBounds(350, 70, 150, 40);
        labelModulesAvailable.setFont(new java.awt.Font("Times New Roman", 1, 16));
        labelModulesAvailable.setText("Modules Available");
        Components.add(labelModulesAvailable);

        listModulesAvailable = new JList<>();
        listModulesAvailable.setFont(new java.awt.Font("Times New Roman", 0, 15));
        listModulesAvailable.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return AcademicCourseworkReportSystem.MODULES.size();
            }

            public String getElementAt(int i) {
                return AcademicCourseworkReportSystem.MODULES.get(i).getModuleCode();
            }
        });

        scrollPaneModulesAvailable = new javax.swing.JScrollPane();
        scrollPaneModulesAvailable.setBounds(350, 110, 150, 122);
        scrollPaneModulesAvailable.setViewportView(listModulesAvailable);
        Components.add(scrollPaneModulesAvailable);

        buttonEnroll = new JButton();
        buttonEnroll.setBounds(385, 245, 70, 25);
        buttonEnroll.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonEnroll.setText("Enroll");
        buttonEnroll.addActionListener(new ButtonListener());
        Components.add(buttonEnroll);

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
            if (component == scrollPaneSearchResult) {
                continue;
            }
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
        scrollPaneSearchResult.setVisible(false);
        labelStudentName.setVisible(false);
        labelUsername.setVisible(false);
        labelTPNumber.setVisible(false);
        labelICNumber.setVisible(false);
        labelIntake.setVisible(false);
        labelResultStudentName.setVisible(false);
        labelResultUsername.setVisible(false);
        labelResultTPNumber.setVisible(false);
        labelResultICNumber.setVisible(false);
        comboBoxResultIntake.setVisible(false);
        labelModulesEnroll.setVisible(false);
        scrollPaneModulesEnroll.setVisible(false);
        buttonDisenroll.setVisible(false);
        labelModulesAvailable.setVisible(false);
        scrollPaneModulesAvailable.setVisible(false);
        buttonEnroll.setVisible(false);
    }

    @Override
    public void updateInfo() {

    }

    @Override
    public void clearInfo() {
        if (initialized) {
            fieldSearchBox.setText("TP Number");
            fieldSearchBox.setForeground(Color.GRAY);
            listSearchResult.setModel(new DefaultListModel<>());
            scrollPaneSearchResult.setVisible(false);
            String[][] data = new String[Student.getStudents().size()][3];
            for (int i = 0; i < Student.getStudents().size(); i++) {
                Student student = Student.getStudents().get(i);
                data[i][0] = student.getUsername();
                data[i][1] = student.getName();
                data[i][2] = student.getTPNumber();
            }
            tableStudentList.setModel(new javax.swing.table.DefaultTableModel(
                    data,
                    new String[]{"Username", "Student Name", "TP Number"}
            ));
            tableStudentList.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableStudentList.getColumnModel().getColumn(1).setPreferredWidth(150);
            tableStudentList.getColumnModel().getColumn(2).setPreferredWidth(15);
            labelResultStudentName.setText("");
            labelResultUsername.setText("");
            labelResultTPNumber.setText("");
            labelResultICNumber.setText("");
            hideSomeComponents();
            return;
        }

        searchResults.clear();
        selectedStudent = null;
    }

    /**
     * Get the student info from the search box and shows the result.
     *
     * @param index The index of result list.
     */
    private void updateStudentInformation(int index) {
        Student student = searchResults.get(index);
        if (student == null) {
            DialogUtils.showErrorMessageDialog(null, "An error occurred while doing this process.");
            return;
        }

        fieldSearchBox.setText("TP Number");
        fieldSearchBox.setForeground(Color.GRAY);
        setSelectedStudent(student);
    }

    /**
     * Search student info by TP Number.
     *
     * @param showErrorMsg shows up a message box when failed to find the
     * student.
     */
    private void searchByTPNumber(boolean showErrorMsg) {
        String tpNumber = fieldSearchBox.getText();
        if (tpNumber.equals("") || tpNumber.replace(" ", "").equals("")
                || tpNumber.equals("TP Number")) {
            if (showErrorMsg) {
                DialogUtils.showErrorMessageDialog(null, "You must enter a student's TP Number\nbefore doing the searching!");
            }
            scrollPaneSearchResult.setVisible(false);
            return;
        }
        try {
            Integer.parseInt(tpNumber);
        } catch (NumberFormatException e) {
            if (showErrorMsg) {
                DialogUtils.showErrorMessageDialog(null, "This is not a valid TP Number!");
            } else {
                scrollPaneSearchResult.setVisible(true);
                scrollPaneSearchResult.setSize(130, 110);
                listSearchResult.setModel(new javax.swing.AbstractListModel<String>() {
                    String[] strings = {"No Result Found!"};

                    public int getSize() {
                        return strings.length;
                    }

                    public String getElementAt(int i) {
                        return strings[i];
                    }
                });
            }
            return;
        }
        ArrayList<Student> studentList = Student.getStudentsByTPNumber(tpNumber);
        if (studentList == null || studentList.size() == 0) {
            if (showErrorMsg) {
                DialogUtils.showErrorMessageDialog(null, "Student not found!");
            } else {
                scrollPaneSearchResult.setVisible(true);
                listSearchResult.setModel(new javax.swing.AbstractListModel<String>() {
                    String[] strings = {"No Result Found!"};

                    public int getSize() {
                        return strings.length;
                    }

                    public String getElementAt(int i) {
                        return strings[i];
                    }
                });
            }
            return;
        }

        searchResults = studentList;
        scrollPaneSearchResult.setVisible(true);
        listSearchResult.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return studentList.size();
            }

            public String getElementAt(int i) {
                return studentList.get(i).getTPNumber();
            }
        });
        if (studentList.size() < 6) {
            scrollPaneSearchResult.setSize(130, studentList.size() * (studentList.size() <= 2 ? (studentList.size() == 2 ? 20 : 22) : 19));
        } else {
            scrollPaneSearchResult.setSize(130, 110);
        }
    }

    private void setSelectedStudent(Student student) {
        if (student == null) {
            return;
        }
        selectedStudent = student;

        scrollPaneSearchResult.setVisible(false);
        scrollPaneStudentList.setVisible(false);

        labelStudentName.setVisible(true);
        labelUsername.setVisible(true);
        labelTPNumber.setVisible(true);
        labelICNumber.setVisible(true);
        labelIntake.setVisible(true);
        labelResultStudentName.setVisible(true);
        labelResultUsername.setVisible(true);
        labelResultTPNumber.setVisible(true);
        labelResultICNumber.setVisible(true);
        comboBoxResultIntake.setVisible(true);

        labelModulesEnroll.setVisible(true);
        scrollPaneModulesEnroll.setVisible(true);
        buttonDisenroll.setVisible(true);
        labelModulesAvailable.setVisible(true);
        scrollPaneModulesAvailable.setVisible(true);
        buttonEnroll.setVisible(true);

        searchResults.clear();

        labelResultStudentName.setText(student.getName());
        labelResultUsername.setText(student.getUsername());
        labelResultTPNumber.setText(student.getTPNumber());
        labelResultICNumber.setText(student.getICNumber());
        String[] intakes = new String[AcademicCourseworkReportSystem.INTAKES.size()];
        for (int i = 0; i < AcademicCourseworkReportSystem.INTAKES.size(); i++) {
            intakes[i] = AcademicCourseworkReportSystem.INTAKES.get(i).toString();
        }
        comboBoxResultIntake.setModel(new javax.swing.DefaultComboBoxModel<>(intakes));
        comboBoxResultIntake.setSelectedItem(student.getIntake().toString());

        listModulesAvailable.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return AcademicCourseworkReportSystem.MODULES.size();
            }

            public String getElementAt(int i) {
                return AcademicCourseworkReportSystem.MODULES.get(i).getModuleCode();
            }
        });
        listModulesEnroll.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return student.getModulesEnroll().size();
            }

            public String getElementAt(int i) {
                return student.getModulesEnroll().get(i).getModuleCode();
            }
        });
    }

    private void fieldSearchBoxFocusGained(java.awt.event.FocusEvent evt) {
        if (fieldSearchBox.getText().equals("TP Number")) {
            fieldSearchBox.setText("");
            fieldSearchBox.setForeground(Color.BLACK);
        }
    }

    private void fieldSearchBoxFocusLost(java.awt.event.FocusEvent evt) {
        if (fieldSearchBox.getText().equals("")) {
            fieldSearchBox.setText("TP Number");
            fieldSearchBox.setForeground(Color.GRAY);
        }
    }

    private void listSearchResultValueChanged(javax.swing.event.ListSelectionEvent evt) {
        if (evt.getValueIsAdjusting()) {
            if (listSearchResult.getSelectedValue().equals("No Result Found!")) {
                return;
            }
            if (searchResults.size() == 0) {
                DialogUtils.showErrorMessageDialog(null, "An error occurred while doing this process.");
            }
            updateStudentInformation(listSearchResult.getSelectedIndex());
        }
    }

    private class ButtonSearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == buttonSearch) {
                searchByTPNumber(true);
                if (searchResults.size() == 1) {
                    updateStudentInformation(0);
                }
            }
        }
    }

    private void comboBoxResultIntakeItemStateChanged(java.awt.event.ItemEvent evt) {
        if (selectedStudent == null) {
            DialogUtils.showErrorMessageDialog(null, "An error occurred while doing this process.");
            return;
        }
        if (comboBoxResultIntake.getSelectedItem() == null) {
            DialogUtils.showErrorMessageDialog(null, "The student must have an intake.");
            return;
        }
        Intake intake = Intake.valueOf(comboBoxResultIntake.getSelectedItem().toString());
        if (intake == null) {
            DialogUtils.showErrorMessageDialog(null, "This is not a valid intake!");
            return;
        }
        selectedStudent.setIntake(intake);
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == buttonEnroll) {
                if (selectedStudent == null) {
                    DialogUtils.showErrorMessageDialog(null, "An error occurred while doing this process.");
                    return;
                }
                if (listModulesAvailable.getSelectedValue() == null) {
                    DialogUtils.showErrorMessageDialog(null, "You must select a module to enroll.");
                    return;
                }
                Module moduleEnroll = Module.valueOf(listModulesAvailable.getSelectedValue());
                if (moduleEnroll == null) {
                    DialogUtils.showErrorMessageDialog(null, "You must select a module to enroll.");
                    return;
                }
                if (selectedStudent.getModulesEnroll().contains(moduleEnroll)) {
                    DialogUtils.showErrorMessageDialog(null, "This student has already enrolled for this module.");
                    return;
                }
                selectedStudent.addModuleEnroll(moduleEnroll);
                listModulesAvailable.setSelectedValue(null, false);
                listModulesEnroll.setModel(new javax.swing.AbstractListModel<String>() {
                    public int getSize() {
                        return selectedStudent.getModulesEnroll().size();
                    }

                    public String getElementAt(int i) {
                        return selectedStudent.getModulesEnroll().get(i).getModuleCode();
                    }
                });
            } else if (event.getSource() == buttonDisenroll) {
                if (selectedStudent == null) {
                    DialogUtils.showErrorMessageDialog(null, "An error occurred while doing this process.");
                    return;
                }
                if (listModulesEnroll.getSelectedValue() == null) {
                    DialogUtils.showErrorMessageDialog(null, "You must select a module that already enrolled to disenroll.");
                    return;
                }
                Module moduleDisenroll = Module.valueOf(listModulesEnroll.getSelectedValue());
                if (moduleDisenroll == null) {
                    DialogUtils.showErrorMessageDialog(null, "You must select a module that already enrolled to disenroll.");
                    return;
                }
                selectedStudent.removeModuleEnroll(moduleDisenroll);
                listModulesEnroll.setModel(new javax.swing.AbstractListModel<String>() {
                    public int getSize() {
                        return selectedStudent.getModulesEnroll().size();
                    }

                    public String getElementAt(int i) {
                        return selectedStudent.getModulesEnroll().get(i).getModuleCode();
                    }
                });
            }
        }
    }
}
