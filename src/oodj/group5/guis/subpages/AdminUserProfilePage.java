package oodj.group5.guis.subpages;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.objects.Intake;
import oodj.group5.objects.users.Student;
import oodj.group5.objects.users.User;
import oodj.group5.utils.DialogUtils;
import oodj.group5.utils.PasswordUtils;
import oodj.group5.utils.UserType;

public class AdminUserProfilePage extends ISubPage {

    private ArrayList<Component> Components = new ArrayList<>();

    private JLabel labelTitle;

    private JTextField fieldSearchBox;
    private JButton buttonSearch;
    private JList<String> listSearchResult;
    private JScrollPane scrollPaneSearchResult;
    private JTable tableUserList;
    private JScrollPane scrollPaneUserList;

    private JLabel labelUsername;
    private JLabel labelName;
    private JLabel labelUserType;
    private JLabel labelTPNumber;
    private JLabel labelICNumber;
    private JLabel labelIntake;

    private JLabel labelResultUsername;
    private JLabel labelResultName;
    private JLabel labelResultUserType;
    private JLabel labelResultTPNumber;
    private JLabel labelResultICNumber;
    private JLabel labelResultIntake;

    private JTextField fieldName;
    private JTextField fieldICNumber;
    private JComboBox<String> comboBoxResultIntake;

    private JButton buttonEdit;
    private JButton buttonDelete;
    private JButton buttonResetPassword;
    private JButton buttonSave;
    private JButton buttonCancel;

    private ArrayList<User> searchResults = new ArrayList<>();
    private User selectedUser = null;

    private boolean initialized = false;

    @Override
    protected void initComponents() {
        if (initialized) {
            return;
        }

        labelTitle = new JLabel();
        labelTitle.setBounds(10, 0, 200, 40);
        labelTitle.setFont(new java.awt.Font("Times New Roman", 1, 24));
        labelTitle.setText("User Profile");
        Components.add(labelTitle);

        fieldSearchBox = new JTextField();
        fieldSearchBox.setBounds(335, 20, 130, 25);
        fieldSearchBox.setText("Username");
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
                searchByUsername(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchByUsername(false);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                searchByUsername(false);
            }
        });
        fieldSearchBox.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchByUsername(true);
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

        tableUserList = new JTable();
        tableUserList.setFont(new java.awt.Font("Times New Roman", 0, 14));
        tableUserList.setPreferredScrollableViewportSize(new Dimension(450, 250));
        tableUserList.setFillsViewportHeight(true);
        String[][] data = new String[AcademicCourseworkReportSystem.USERS.size()][3];
        for (int i = 0; i < AcademicCourseworkReportSystem.USERS.size(); i++) {
            User user = AcademicCourseworkReportSystem.USERS.get(i);
            data[i][0] = user.getUsername();
            data[i][1] = user.getName();
            data[i][2] = user.getUserType().getName();
        }
        tableUserList.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[]{"Username", "Name", "Type"}
        ));
        tableUserList.setRowHeight(25);
        tableUserList.setOpaque(false);
        tableUserList.setDefaultEditor(Object.class, null);
        tableUserList.getColumnModel().getColumn(0).setPreferredWidth(30);
        tableUserList.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableUserList.getColumnModel().getColumn(2).setPreferredWidth(15);

        scrollPaneUserList = new javax.swing.JScrollPane();
        scrollPaneUserList.setBounds(50, 80, 450, 250);
        scrollPaneUserList.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        scrollPaneUserList.setViewportView(tableUserList);
        Components.add(scrollPaneUserList);

        labelUsername = new JLabel();
        labelUsername.setBounds(68, 50, 80, 40);
        labelUsername.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelUsername.setText("Username :");
        Components.add(labelUsername);

        labelName = new JLabel();
        labelName.setBounds(93, 85, 135, 40);
        labelName.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelName.setText("Name :");
        Components.add(labelName);

        labelUserType = new JLabel();
        labelUserType.setBounds(66, 120, 135, 40);
        labelUserType.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelUserType.setText("User Type :");
        Components.add(labelUserType);

        labelTPNumber = new JLabel();
        labelTPNumber.setBounds(59, 155, 80, 40);
        labelTPNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelTPNumber.setText("TP Number :");
        Components.add(labelTPNumber);

        labelICNumber = new JLabel();
        labelICNumber.setBounds(62, 190, 80, 40);
        labelICNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelICNumber.setText("IC Number :");
        Components.add(labelICNumber);

        labelIntake = new JLabel();
        labelIntake.setBounds(92, 225, 60, 40);
        labelIntake.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelIntake.setText("Intake :");
        Components.add(labelIntake);

        labelResultUsername = new JLabel();
        labelResultUsername.setBounds(155, 50, 250, 40);
        labelResultUsername.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultUsername.setText("");
        Components.add(labelResultUsername);

        labelResultName = new JLabel();
        labelResultName.setBounds(155, 85, 250, 40);
        labelResultName.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultName.setText("");
        Components.add(labelResultName);

        labelResultUserType = new JLabel();
        labelResultUserType.setBounds(155, 120, 250, 40);
        labelResultUserType.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultUserType.setText("");
        Components.add(labelResultUserType);

        labelResultTPNumber = new JLabel();
        labelResultTPNumber.setBounds(155, 155, 100, 40);
        labelResultTPNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultTPNumber.setText("");
        Components.add(labelResultTPNumber);

        labelResultICNumber = new JLabel();
        labelResultICNumber.setBounds(155, 190, 150, 40);
        labelResultICNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultICNumber.setText("");
        Components.add(labelResultICNumber);

        labelResultIntake = new JLabel();
        labelResultIntake.setBounds(155, 225, 150, 40);
        labelResultIntake.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultIntake.setText("");
        Components.add(labelResultIntake);

        fieldName = new JTextField();
        fieldName.setBounds(155, 95, 150, 25);
        fieldName.setFont(new java.awt.Font("Times New Roman", 0, 15));
        fieldName.setText("");
        Components.add(fieldName);

        fieldICNumber = new JTextField();
        fieldICNumber.setBounds(155, 200, 150, 25);
        fieldICNumber.setFont(new java.awt.Font("Times New Roman", 0, 15));
        fieldICNumber.setText("");
        Components.add(fieldICNumber);

        comboBoxResultIntake = new JComboBox();
        comboBoxResultIntake.setBounds(155, 235, 150, 22);
        comboBoxResultIntake.setFont(new java.awt.Font("Times New Roman", 0, 15));
        Components.add(comboBoxResultIntake);

        buttonEdit = new JButton();
        buttonEdit.setBounds(420, 60, 70, 25);
        buttonEdit.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(new ButtonListener());
        Components.add(buttonEdit);

        buttonDelete = new JButton();
        buttonDelete.setBounds(420, 90, 70, 25);
        buttonDelete.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonDelete.setText("Delete");
        buttonDelete.addActionListener(new ButtonListener());
        Components.add(buttonDelete);

        buttonResetPassword = new JButton();
        buttonResetPassword.setBounds(420, 60, 125, 25);
        buttonResetPassword.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonResetPassword.setText("Reset Password");
        buttonResetPassword.addActionListener(new ButtonListener());
        Components.add(buttonResetPassword);

        buttonSave = new JButton();
        buttonSave.setBounds(150, 300, 110, 35);
        buttonSave.setFont(new java.awt.Font("Times New Roman", 1, 18));
        buttonSave.setText("Save");
        buttonSave.addActionListener(new ButtonListener());
        Components.add(buttonSave);

        buttonCancel = new JButton();
        buttonCancel.setBounds(280, 300, 110, 35);
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
        buttonResetPassword.setVisible(false);
        labelUsername.setVisible(false);
        labelName.setVisible(false);
        labelUserType.setVisible(false);
        labelTPNumber.setVisible(false);
        labelICNumber.setVisible(false);
        labelIntake.setVisible(false);
        labelResultUsername.setVisible(false);
        labelResultName.setVisible(false);
        labelResultUserType.setVisible(false);
        labelResultTPNumber.setVisible(false);
        labelResultICNumber.setVisible(false);
        labelResultIntake.setVisible(false);
        fieldName.setVisible(false);
        fieldICNumber.setVisible(false);
        buttonEdit.setVisible(false);
        buttonDelete.setVisible(false);
        buttonSave.setVisible(false);
        buttonCancel.setVisible(false);
        comboBoxResultIntake.setVisible(false);
    }

    @Override
    public void updateInfo() {

    }

    @Override
    public void clearInfo() {
        if (initialized) {
            fieldSearchBox.setText("Username");
            fieldSearchBox.setForeground(Color.GRAY);
            listSearchResult.setModel(new DefaultListModel<>());
            scrollPaneSearchResult.setVisible(false);
            String[][] data = new String[AcademicCourseworkReportSystem.USERS.size()][3];
            for (int i = 0; i < AcademicCourseworkReportSystem.USERS.size(); i++) {
                User user = AcademicCourseworkReportSystem.USERS.get(i);
                data[i][0] = user.getUsername();
                data[i][1] = user.getName();
                data[i][2] = user.getUserType().getName();
            }
            tableUserList.setModel(new javax.swing.table.DefaultTableModel(
                    data,
                    new String[]{"Username", "Name", "Type"}
            ));
            tableUserList.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableUserList.getColumnModel().getColumn(1).setPreferredWidth(150);
            tableUserList.getColumnModel().getColumn(2).setPreferredWidth(15);
            labelResultUsername.setText("");
            labelResultName.setText("");
            labelResultUserType.setText("");
            labelResultTPNumber.setText("");
            labelResultICNumber.setText("");
            labelResultIntake.setText("");
            comboBoxResultIntake.setModel(new DefaultComboBoxModel<>());
            hideSomeComponents();
            return;
        }

        searchResults.clear();
        selectedUser = null;
    }

    /**
     * Enable profile edit mode.
     */
    private void enterEditMode() {
        buttonEdit.setVisible(false);
        buttonResetPassword.setVisible(true);

        labelResultName.setVisible(false);
        fieldName.setVisible(true);
        fieldName.setText(selectedUser == null ? "Invalid" : selectedUser.getName());
        fieldName.setCaretPosition(0);
        if (selectedUser.getUserType() == UserType.STUDENT) {
            labelResultICNumber.setVisible(false);
            fieldICNumber.setVisible(true);
            Student student = (Student) selectedUser;
            if (student != null) {
                fieldICNumber.setText(student.getICNumber());
            }
            labelResultIntake.setVisible(false);
            comboBoxResultIntake.setVisible(true);
            String[] intakes = new String[AcademicCourseworkReportSystem.INTAKES.size()];
            for (int i = 0; i < AcademicCourseworkReportSystem.INTAKES.size(); i++) {
                intakes[i] = AcademicCourseworkReportSystem.INTAKES.get(i).toString();
            }
            comboBoxResultIntake.setModel(new javax.swing.DefaultComboBoxModel<>(intakes));
            comboBoxResultIntake.setSelectedItem(student.getIntake().getIntakeCode());
        }
        buttonSave.setVisible(true);
        buttonCancel.setVisible(true);
    }

    /**
     * Quit profile edit mode.
     */
    private void quitEditMode() {
        fieldName.setVisible(false);
        fieldICNumber.setVisible(false);
        comboBoxResultIntake.setVisible(false);
        buttonEdit.setVisible(true);
        buttonResetPassword.setVisible(false);
        buttonSave.setVisible(false);
        buttonCancel.setVisible(false);
        setSelectedUser(selectedUser);
    }

    /**
     * Get the user info from the search box and shows the result.
     *
     * @param index The index of result list.
     */
    private void updateUserInformation(int index) {
        User user = searchResults.get(index);
        if (user == null) {
            DialogUtils.showErrorMessageDialog(null, "An error occurred while doing this process.");
            return;
        }

        fieldSearchBox.setText("Username");
        fieldSearchBox.setForeground(Color.GRAY);
        quitEditMode();
        setSelectedUser(user);
    }

    /**
     * Search user info by Username.
     *
     * @param showErrorMsg shows up a message box when failed to find the user.
     */
    private void searchByUsername(boolean showErrorMsg) {
        String username = fieldSearchBox.getText();
        if (username.equals("") || username.replace(" ", "").equals("")
                || username.equals("Username")) {
            if (showErrorMsg) {
                DialogUtils.showErrorMessageDialog(null, "You must enter a username\nbefore doing the searching!");
            }
            scrollPaneSearchResult.setVisible(false);
            return;
        }

        ArrayList<User> userList = User.getUsers(username);
        if (userList == null || userList.size() == 0) {
            if (showErrorMsg) {
                DialogUtils.showErrorMessageDialog(null, "User not found!");
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

        searchResults = userList;
        scrollPaneSearchResult.setVisible(true);
        listSearchResult.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return userList.size();
            }

            public String getElementAt(int i) {
                return userList.get(i).getUsername();
            }
        });
        if (userList.size() < 6) {
            scrollPaneSearchResult.setSize(130, userList.size() * (userList.size() <= 2 ? (userList.size() == 2 ? 20 : 22) : 19));
        } else {
            scrollPaneSearchResult.setSize(130, 110);
        }
    }

    private void setSelectedUser(User user) {
        if (user == null) {
            return;
        }
        selectedUser = user;

        scrollPaneSearchResult.setVisible(false);
        scrollPaneUserList.setVisible(false);
        labelUsername.setVisible(true);
        labelName.setVisible(true);
        labelUserType.setVisible(true);
        labelResultUsername.setVisible(true);
        labelResultName.setVisible(true);
        labelResultUserType.setVisible(true);
        buttonEdit.setVisible(true);
        buttonDelete.setVisible(true);

        if (user.getUserType() == UserType.STUDENT) {
            labelTPNumber.setVisible(true);
            labelICNumber.setVisible(true);
            labelIntake.setVisible(true);
            labelResultTPNumber.setVisible(true);
            labelResultICNumber.setVisible(true);
            labelResultIntake.setVisible(true);
        } else {
            labelTPNumber.setVisible(false);
            labelICNumber.setVisible(false);
            labelIntake.setVisible(false);
            labelResultTPNumber.setVisible(false);
            labelResultICNumber.setVisible(false);
            labelResultIntake.setVisible(false);
        }
        searchResults.clear();

        labelResultUsername.setText(user.getUsername());
        labelResultName.setText(user.getName());
        labelResultUserType.setText(user.getUserType().getName());
        if (user.getUserType() == UserType.STUDENT) {
            Student student = (Student) selectedUser;
            if (student != null) {
                labelResultTPNumber.setText(student.getTPNumber());
                labelResultICNumber.setText(student.getICNumber());
                labelResultIntake.setText(student.getIntake().getIntakeCode());
            }
        }
    }

    private void fieldSearchBoxFocusGained(java.awt.event.FocusEvent evt) {
        if (fieldSearchBox.getText().equals("Username")) {
            fieldSearchBox.setText("");
            fieldSearchBox.setForeground(Color.BLACK);
        }
    }

    private void fieldSearchBoxFocusLost(java.awt.event.FocusEvent evt) {
        if (fieldSearchBox.getText().equals("")) {
            fieldSearchBox.setText("Username");
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
            updateUserInformation(listSearchResult.getSelectedIndex());
        }
    }

    private class ButtonSearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == buttonSearch) {
                searchByUsername(true);
                if (searchResults.size() == 1) {
                    updateUserInformation(0);
                }
            }
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == buttonEdit) {
                enterEditMode();
            } else if (event.getSource() == buttonDelete) {
                Object[] options = {"Confirm", "Cancel"};
                int selection = DialogUtils.showOptionDialog(null, "Are you sure you to delete this account?",
                        "Delete Account", options, options[0]);
                if (selection == 0) {
                    if (selectedUser == null) {
                        DialogUtils.showErrorMessageDialog(null, "Failded to delete this account,\nplease try again!");
                        quitEditMode();
                        hideSomeComponents();
                        return;
                    }
                    AcademicCourseworkReportSystem.USERS.remove(selectedUser);
                    quitEditMode();
                    hideSomeComponents();
                }
            } else if (event.getSource() == buttonSave) {
                if (selectedUser == null) {
                    DialogUtils.showErrorMessageDialog(null, "An error has occured while doing this process,\nplease try again!");
                    quitEditMode();
                    hideSomeComponents();
                    return;
                }
                if (fieldName.getText().equals("")) {
                    DialogUtils.showErrorMessageDialog(null, "User's name cannot be empty!");
                    return;
                }
                if (selectedUser.getUserType() == UserType.STUDENT) {
                    if (fieldICNumber.getText().equals("")) {
                        DialogUtils.showErrorMessageDialog(null, "IC Number cannot be empty!");
                        return;
                    }
                    String icNumber = fieldICNumber.getText();
                    if (icNumber.length() != 12) {
                        DialogUtils.showErrorMessageDialog(null, "IC Number must be 12 numbers format!");
                        return;
                    }
                    try {
                        Long.parseLong(icNumber);
                    } catch (NumberFormatException e) {
                        DialogUtils.showErrorMessageDialog(null, "IC Number can only contains number!");
                        return;
                    }
                    if (Integer.parseInt(icNumber.substring(2, 4)) > 12 || Integer.parseInt(icNumber.substring(4, 6)) > 31) {
                        DialogUtils.showErrorMessageDialog(null, "The IC Number is wrong format!");
                        return;
                    }
                    if (comboBoxResultIntake.getSelectedItem() == null) {
                        DialogUtils.showErrorMessageDialog(null, "Intake cannot be null!");
                        return;
                    }
                }
                Object[] options = {"Confirm", "Cancel"};
                int selection = DialogUtils.showOptionDialog(null, "Are you sure you to save profile changes?",
                        "Save Profile", options, options[0]);
                if (selection == 0) {
                    selectedUser.setName(fieldName.getText());
                    if (selectedUser.getUserType() == UserType.STUDENT) {
                        Student student = (Student) selectedUser;
                        if (student == null) {
                            DialogUtils.showErrorMessageDialog(null, "Failed to save student's info!");
                            return;
                        }
                        student.setICNumber(fieldICNumber.getText());
                        student.setIntake(Intake.valueOf(comboBoxResultIntake.getSelectedItem().toString()));
                    }
                    quitEditMode();
                }
            } else if (event.getSource() == buttonCancel) {
                quitEditMode();
            } else if (event.getSource() == buttonResetPassword) {
                Object[] options = {"Yes", "No"};
                int selection = DialogUtils.showOptionDialog(null, "Are you sure you to reset password?",
                        "Reset Password", options, options[0]);
                if (selection == 0) {
                    if (selectedUser == null) {
                        DialogUtils.showErrorMessageDialog(null, "An error has occured while doing this process,\nplease try again!");
                        quitEditMode();
                        hideSomeComponents();
                        return;
                    }
                    String newPassword = PasswordUtils.generatePassword(10);
                    selectedUser.setPassword(newPassword);
                    JPanel panel = new JPanel();
                    JLabel label = new JLabel("New password: ");
                    JTextField field = new JTextField(newPassword);
                    field.setEditable(false);
                    panel.add(label);
                    panel.add(field);
                    JOptionPane.showMessageDialog(null, panel);
                    quitEditMode();
                }
            }
        }
    }
}
