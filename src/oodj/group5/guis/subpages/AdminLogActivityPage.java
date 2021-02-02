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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import oodj.group5.AcademicCourseworkReportSystem;
import oodj.group5.objects.users.User;
import oodj.group5.utils.DialogUtils;
import oodj.group5.utils.EnumLogDuration;
import oodj.group5.utils.ReportGenerator;

public class AdminLogActivityPage extends ISubPage {

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
    private JLabel labelDate;

    private JLabel labelResultUsername;
    private JLabel labelResultName;
    private JLabel labelResultUserType;
    private JComboBox<String> comboBoxDate;

    private JButton buttonCheck;

    private ArrayList<User> searchResults = new ArrayList<>();
    private User selectedUser = null;

    private boolean initialized = false;

    @Override
    protected void initComponents() {
        if (initialized) {
            return;
        }

        labelTitle = new JLabel();
        labelTitle.setBounds(10, 0, 150, 40);
        labelTitle.setFont(new java.awt.Font("Times New Roman", 1, 24));
        labelTitle.setText("Log Activity");
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
        labelName.setBounds(93, 85, 80, 40);
        labelName.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelName.setText("Name :");
        Components.add(labelName);

        labelUserType = new JLabel();
        labelUserType.setBounds(66, 120, 80, 40);
        labelUserType.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelUserType.setText("User Type :");
        Components.add(labelUserType);

        labelDate = new JLabel();
        labelDate.setBounds(100, 155, 80, 40);
        labelDate.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelDate.setText("Date :");
        Components.add(labelDate);

        labelResultUsername = new JLabel();
        labelResultUsername.setBounds(155, 50, 300, 40);
        labelResultUsername.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultUsername.setText("");
        Components.add(labelResultUsername);

        labelResultName = new JLabel();
        labelResultName.setBounds(155, 85, 300, 40);
        labelResultName.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultName.setText("");
        Components.add(labelResultName);

        labelResultUserType = new JLabel();
        labelResultUserType.setBounds(155, 120, 100, 40);
        labelResultUserType.setFont(new java.awt.Font("Times New Roman", 0, 15));
        labelResultUserType.setText("");
        Components.add(labelResultUserType);

        comboBoxDate = new JComboBox();
        comboBoxDate.setBounds(155, 165, 120, 22);
        comboBoxDate.setFont(new java.awt.Font("Times New Roman", 0, 15));
        comboBoxDate.setModel(new DefaultComboBoxModel<>(new String[]{EnumLogDuration.PAST_30_DAYS.getName(), EnumLogDuration.PAST_90_DAYS.getName(),
            EnumLogDuration.THIS_YEAR.getName(), EnumLogDuration.LAST_YEAR.getName()}));
        Components.add(comboBoxDate);

        buttonCheck = new JButton();
        buttonCheck.setBounds(230, 250, 110, 35);
        buttonCheck.setFont(new java.awt.Font("Times New Roman", 1, 18));
        buttonCheck.setText("Check");
        buttonCheck.addActionListener(new ButtonCheckListener());
        Components.add(buttonCheck);

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
        labelUsername.setVisible(false);
        labelName.setVisible(false);
        labelUserType.setVisible(false);
        labelDate.setVisible(false);
        labelResultUsername.setVisible(false);
        labelResultName.setVisible(false);
        labelResultUserType.setVisible(false);
        comboBoxDate.setVisible(false);
        buttonCheck.setVisible(false);
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
            comboBoxDate.setModel(new DefaultComboBoxModel<>(new String[]{EnumLogDuration.PAST_30_DAYS.getName(), EnumLogDuration.PAST_90_DAYS.getName(),
                EnumLogDuration.THIS_YEAR.getName(), EnumLogDuration.LAST_YEAR.getName()}));
            hideSomeComponents();
            return;
        }

        searchResults.clear();
        selectedUser = null;
    }

    /**
     * Get the username info from the search box and shows the result.
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
        setSelectedUser(user);
    }

    /**
     * Search student info by username.
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
        labelDate.setVisible(true);
        labelResultUsername.setVisible(true);
        labelResultName.setVisible(true);
        labelResultUserType.setVisible(true);
        comboBoxDate.setVisible(true);
        buttonCheck.setVisible(true);

        searchResults.clear();

        labelResultUsername.setText(user.getUsername());
        labelResultName.setText(user.getName());
        labelResultUserType.setText(user.getUserType().toString().charAt(0) + user.getUserType().toString().toLowerCase().substring(1));
        comboBoxDate.setModel(new DefaultComboBoxModel<>(new String[]{EnumLogDuration.PAST_30_DAYS.getName(), EnumLogDuration.PAST_90_DAYS.getName(),
            EnumLogDuration.THIS_YEAR.getName(), EnumLogDuration.LAST_YEAR.getName()}));
        comboBoxDate.setSelectedItem(EnumLogDuration.PAST_30_DAYS.getName());
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

    private class ButtonCheckListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == buttonCheck) {
                if (comboBoxDate.getSelectedItem() == null) {
                    DialogUtils.showErrorMessageDialog(null, "Please select a date before proceed to the next step!");
                    return;
                }
                ReportGenerator.generateLogReport(selectedUser, EnumLogDuration.getEnum(comboBoxDate.getSelectedItem().toString()));
            }
        }
    }
}
