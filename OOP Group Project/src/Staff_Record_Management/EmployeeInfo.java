package Staff_Record_Management;


import Dashboardpak.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Scanner;

public class EmployeeInfo {
    private final int ID, trn;
    private final String fname;
    private final String midname;
    private final String lname;
    private final String DOE;
    private final String Department;
    private final String JobT;
    private final String supervisor;

    public static int sizeOfRecord = 64;

    public EmployeeInfo(int ID, int trn, String fname, String midname, String lname, String DOE, String Department, String Gender, String JobT, String supervisor) {
        this.ID = ID;
        this.trn = trn;
        this.fname = fname;
        this.midname = midname;
        this.lname = lname;
        this.DOE = DOE;
        this.Department = Department;
        this.JobT = JobT;
        this.supervisor = supervisor;
    }

    //getters
    public int getID() {
        return ID;
    }
    public int getTrn() {
        return trn;
    }
    public String getFname() {
        return fname;
    }
    public String getMidname() {
        return midname;
    }
    public String getLname() {
        return lname;
    }
    public String getDOE() {
        return DOE;
    }
    public String getDepartment() {
        return Department;
    }
    public String getJobT() {
        return JobT;
    }
    public String getSupervisor(){
        return supervisor;
    }

    public static void storeData(int id,int trn,String fname,String midname,String lname, String DOE,String Department, String JobT, String supervisor){
        try(FileWriter fW = new FileWriter("EmployeeProfiles.txt",true)){
            fW.write("First Name         : " + fname + "\n");
            fW.write("Middle Name        : " + midname + "\n");
            fW.write("Last Name          : " + lname + "\n");
            fW.write("ID                 : " + id + "\n");
            fW.write("TRN                : " + trn + "\n");
            fW.write("Date of Employment : " + DOE + "\n");
            fW.write("Department         : " + Department + "\n");
            fW.write("Job Title          : " + JobT + "\n");
            fW.write("Supervisor         : " + supervisor + "\n");
            fW.write("---------------------------------------------\n");
            System.out.println("Data successfully saved!");
        } catch (IOException e){
            System.err.println("Couldn't find file");
        }
    }

    public static String readFile(String name) {
        try (Scanner scanner = new Scanner(new File("EmployeeProfiles.txt"))) {
            StringBuilder employeeData = new StringBuilder(); // To accumulate employee block data
            boolean employeeFound = false; // Track if the specific employee is found

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Check for block delimiter
                if (line.trim().equals("---------------------------------------------")) {
                    // Process the collected employee block
                    if (!employeeData.isEmpty()) {
                        String employeeBlock = employeeData.toString();
                        if (employeeBlock.contains("First Name         : " + name)) {
                            employeeFound = true;
                            return employeeBlock; // Return employee block
                        }
                        employeeData.setLength(0); // Reset for the next block
                    }
                } else {
                    // Accumulate data in the current block
                    employeeData.append(line).append("\n");
                }
            }

            // Process the final block (in case the file doesn't end with a delimiter)
            if (!employeeData.isEmpty()) {
                String employeeBlock = employeeData.toString();
                if (employeeBlock.contains("First Name         : " + name)) {
                    employeeFound = true;
                    return employeeBlock;
                }
            }

            if (!employeeFound) {
                return "Employee with first name '" + name + "' not found.";
            }
        } catch (FileNotFoundException e) {
            return "Couldn't find file 'EmployeeProfiles.txt'.";
        }
        return "Error: Unexpected result.";
    }

    public static void viewEmployees() {
        // Create the search frame
        JFrame empFrame = new JFrame("Employee Search");
        empFrame.setSize(600, 400);
        empFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Components
        JTextField patientField = new JTextField(15);
        JButton search = new JButton("Search");
        JButton returnButton = new JButton("Return to Options");

        // Layout setup
        JPanel panel = new JPanel();
        panel.add(new JLabel("Employee Name: "));
        panel.add(patientField);
        panel.add(search);
        panel.add(returnButton);

        empFrame.add(panel);
        empFrame.setVisible(true);

        // ActionListener for the search button
        search.addActionListener(e -> {
            String name = patientField.getText();
            if (!name.isEmpty()) {
                // Call readFile and display result in popup
                String result = readFile(name);

                // Popup window to display employee details
                JFrame resultFrame = new JFrame("Employee Details");
                resultFrame.setSize(400, 300);
                resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JTextArea textArea = new JTextArea(result);
                textArea.setEditable(false);
                JButton backButton = new JButton("Return to Search");

                // Back button action
                backButton.addActionListener(event -> {
                    resultFrame.dispose(); // Close popup
                    empFrame.setVisible(true); // Show search frame again
                });

                resultFrame.setLayout(new BorderLayout());
                resultFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);
                resultFrame.add(backButton, BorderLayout.SOUTH);

                resultFrame.setVisible(true);
                empFrame.setVisible(false); // Hide search frame
            } else {
                JOptionPane.showMessageDialog(empFrame, "Please enter a name!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        //ActionListener for the return button
        returnButton.addActionListener(e -> {
            // Dispose of the current frame
            ((JFrame) SwingUtilities.getWindowAncestor(returnButton)).dispose();
            Dashboard.open();
        });
    }

    public static void addEmployeeProfile(){
        JFrame Emp = new JFrame("Employee Entry");
        Emp.setSize(400,600);
        Emp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //components
        JTextField IDField = new JTextField(15);
        JTextField trnField = new JTextField(15);
        JTextField fNameField = new JTextField(15);
        JTextField midNameField = new JTextField(15);
        JTextField doeField = new JTextField(15);
        JTextField departmentField = new JTextField(15);
        JTextField jobtField = new JTextField(15);
        JTextField superviserField = new JTextField(15);
        JTextField lNameField = new JTextField(15);

        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure all data entered is correct?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (response == JOptionPane.YES_OPTION) {
                try {
                    storeData(Integer.parseInt(IDField.getText()), Integer.parseInt(trnField.getText()), fNameField.getText(), midNameField.getText(), lNameField.getText(), doeField.getText(), departmentField.getText(), jobtField.getText(), superviserField.getText());
                    JOptionPane.showMessageDialog(null, "Employee data saved successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please ensure numeric fields are correct.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (response == JOptionPane.NO_OPTION) {
                System.out.println("Cancelled");
            }
        });

        JButton back = new JButton("Return");
        back.addActionListener(e -> {
            Emp.dispose();
            ManageUsers.open();
        });

        //Layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("ID                  "));
        panel.add(IDField);
        panel.add(new JLabel("TRN                 "));
        panel.add(trnField);
        panel.add(new JLabel("First Name          "));
        panel.add(fNameField);
        panel.add(new JLabel("Middle Name         "));
        panel.add(midNameField);
        panel.add(new JLabel("Last Name           "));
        panel.add(lNameField);
        panel.add(new JLabel("Date of Employment  "));
        panel.add(doeField);
        panel.add(new JLabel("Department          "));
        panel.add(departmentField);
        panel.add(new JLabel("Job Title           "));
        panel.add(jobtField);
        panel.add(new JLabel("Supervisor          "));
        panel.add(superviserField);

        panel.add(confirm);
        panel.add(back);

        Emp.add(panel);
        Emp.setVisible(true);
    }

    public static void addEmployee(int id, String firstname, String Password, String role, int fa, boolean isLocked) {
        String filePath = "Employees.dat";

        try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
            // Ensure file isn't shorter than where we're about to write
            if ((long) ( - 1) * sizeOfRecord >= raf.length()) {
                raf.seek(raf.length()); // Append at end if record position doesn't exist
            } else {
                raf.seek((long) (id - 1) * sizeOfRecord); // Jump to correct position
            }

            // Writing structured data
            raf.writeInt(id);
            raf.writeUTF(firstname);
            raf.writeUTF(Password);
            raf.writeUTF(role);
            raf.writeInt(fa);
            raf.writeBoolean(isLocked);

            System.out.println("Employee added successfully!");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public static void readRaf(int id) {
        String filePath = "Employees.dat";

        try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
            // Validate ID before seeking
            if ((long) (id - 1) * sizeOfRecord >= raf.length()) {
                System.out.println("Invalid ID: Record does not exist.");
                return;
            }

            raf.seek((long) (id - 1) * sizeOfRecord);

            // Read data safely
            int ID = raf.readInt();
            String fname = raf.readUTF();
            String password = raf.readUTF();
            String role = raf.readUTF();
            int fa = raf.readInt();
            boolean islocked = raf.readBoolean();

            System.out.println(fname + " " + password + " " + role);
        } catch (IOException e) {
            System.err.println("File read error: " + e.getMessage());
        }
    }


    public static void open() {
        JFrame frame = new JFrame("Remove Employee");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));

        JLabel nameLabel = new JLabel("Enter Employee First Name:");
        JTextField nameField = new JTextField();
        JButton removeButton = new JButton("Remove Employee");

        JLabel feedbackLabel = new JLabel("", JLabel.CENTER);
        feedbackLabel.setForeground(Color.RED);

        removeButton.addActionListener((ActionEvent e) -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                int confirm = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to removeEmployeprofile '" + name + "'?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = removeEmployeprofile(name);
                    if (success) {
                        feedbackLabel.setText("Employee '" + name + "' removed successfully.");
                    } else {
                        feedbackLabel.setText("Employee '" + name + "' not found.");
                    }
                } else {
                    feedbackLabel.setText("Operation canceled.");
                }
            } else {
                feedbackLabel.setText("Please enter a name.");
            }
        });

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(removeButton);
        frame.add(feedbackLabel);
        frame.setVisible(true);
    }

    public static boolean removeEmployeprofile(String targetName) {
        String filePath = "EmployeeProfiles.txt";
        File inputFile = new File(filePath);
        File tempFile = new File("temp_" + filePath);

        boolean removed = false;

        try (Scanner scanner = new Scanner(inputFile); PrintWriter writer = new PrintWriter(tempFile)) {
            boolean skipRecord = false;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.startsWith("First Name") && line.split(":")[1].trim().equalsIgnoreCase(targetName)) {
                    skipRecord = true;
                    removed = true; // Mark as removed
                } else if (line.equals("---------------------------------------------")) {
                    if (skipRecord) {
                        skipRecord = false;
                        continue;
                    }
                }

                if (!skipRecord) {
                    writer.println(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
            return false;
        }

        return removed && inputFile.delete() && tempFile.renameTo(inputFile);
    }

    public static void main(String[] args){
        addEmployee(1,"Daren","bryan","admin",0,false);
        readRaf(1);
    }
}

