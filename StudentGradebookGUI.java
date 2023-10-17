import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class StudentGradebookGUI {

    private HashMap<String, ArrayList<Double>> gradebook;
    private File file;

    private JFrame frame;
    private JPanel panel;
    private JTextArea textArea;
    private JTextField nameField;
    private JTextField subjectField;
    private JTextField gradeField;
    private JButton addButton;
    private JButton viewButton;
    private JButton removeButton;

    public StudentGradebookGUI(String filename) {
        this.gradebook = new HashMap<>();
        this.file = new File(filename);
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            loadGradebook();
        }
        prepareGUI();
    }

    private void prepareGUI() {
        frame = new JFrame("Student Gradebook");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        textArea = new JTextArea();
        textArea.setBounds(10, 10, 480, 300);
        panel.add(textArea);

        nameField = new JTextField();
        nameField.setBounds(10, 320, 100, 30);
        panel.add(nameField);

        subjectField = new JTextField();
        subjectField.setBounds(120, 320, 100, 30);
        panel.add(subjectField);

        gradeField = new JTextField();
        gradeField.setBounds(230, 320, 100, 30);
        panel.add(gradeField);

        addButton = new JButton("Add");
        addButton.setBounds(340, 320, 70, 30);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentName = nameField.getText();
                double grade = Double.parseDouble(gradeField.getText());
                ArrayList<Double> grades = new ArrayList<>();
                grades.add(grade);
                addStudentGrade(studentName, grades);
                nameField.setText("");
                gradeField.setText("");
            }
        });
        panel.add(addButton);

        viewButton = new JButton("View");
        viewButton.setBounds(420, 320, 70, 30);
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                printGradebook();
            }
        });
        panel.add(viewButton);

        removeButton = new JButton("Remove");
        removeButton.setBounds(10, 360, 100, 30);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentName = nameField.getText();
                removeStudent(studentName);
                nameField.setText("");
            }
        });
        panel.add(removeButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void addStudentGrade(String studentName, ArrayList<Double> grades) {
        gradebook.put(studentName, grades);
        saveGradebook();
    }

    public void removeStudent(String studentName) {
        gradebook.remove(studentName);
        saveGradebook();
    }

    public void printGradebook() {
        for (Map.Entry<String, ArrayList<Double>> entry : gradebook.entrySet()) {
            String studentName = entry.getKey();
            ArrayList<Double> grades = entry.getValue();
            textArea.append("Student: " + studentName + " Grades: " + grades + "\n");
        }
    }

    private void saveGradebook() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Map.Entry<String, ArrayList<Double>> entry : gradebook.entrySet()) {
                String studentName = entry.getKey();
                ArrayList<Double> grades = entry.getValue();
                StringBuilder sb = new StringBuilder();
                for (Double grade : grades) {
                    sb.append(grade).append(",");
                }
                writer.println(studentName + ":" + sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGradebook() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String studentName = parts[0];
                    String[] gradeStrings = parts[1].split(",");
                    ArrayList<Double> grades = new ArrayList<>();
                    for (String gradeString : gradeStrings) {
                        grades.add(Double.parseDouble(gradeString));
                    }
                    gradebook.put(studentName, grades);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StudentGradebookGUI gradebook = new StudentGradebookGUI("gradebook.txt");
    }
}
