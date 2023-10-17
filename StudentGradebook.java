import java.io.*;
import java.util.*;

public class StudentGradebook {

    private HashMap<String, ArrayList<Double>> gradebook;
    private File file;
    private Scanner scanner;

    public StudentGradebook(String filename) {
        this.gradebook = new HashMap<>();
        this.file = new File(filename);
        this.scanner = new Scanner(System.in);
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            loadGradebook();
        }
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
            System.out.println("Student: " + studentName + " Grades: " + grades);
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

    public void startProgram() {
        boolean running = true;
        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Add a student");
            System.out.println("2. View student gradebook");
            System.out.println("3. Remove a student");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewGradebook();
                    break;
                case 3:
                    removeStudentOption();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    private void addStudent() {
        System.out.println("Enter student name:");
        String studentName = scanner.next();
        System.out.println("Enter the number of subjects:");
        int numberOfSubjects = scanner.nextInt();
        ArrayList<Double> grades = new ArrayList<>();
        for (int j = 1; j <= numberOfSubjects; j++) {
            System.out.println("Enter grade for subject " + j + ":");
            double grade = scanner.nextDouble();
            grades.add(grade);
        }
        addStudentGrade(studentName, grades);
    }

    private void viewGradebook() {
        System.out.println("Choose an option:");
        System.out.println("1. View all students");
        System.out.println("2. View a specific student");
        int choice = scanner.nextInt();
        if (choice == 1) {
            printGradebook();
        } else if (choice == 2) {
            System.out.println("Enter the name of the student:");
            String studentName = scanner.next();
            if (gradebook.containsKey(studentName)) {
                ArrayList<Double> grades = gradebook.get(studentName);
                double totalGrade = 0;
                for (double grade : grades) {
                    totalGrade += grade;
                }
                double averageGrade = totalGrade / grades.size();
                System.out.println("Student: " + studentName);
                System.out.println("Grades: " + grades);
                System.out.println("Total Grade: " + totalGrade);
                System.out.println("Average Grade: " + averageGrade);
            } else {
                System.out.println("Student not found.");
            }
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void removeStudentOption() {
        System.out.println("Enter the name of the student to be removed:");
        String studentName = scanner.next();
        removeStudent(studentName);
    }

    public static void main(String[] args) {
        StudentGradebook gradebook = new StudentGradebook("gradebook.txt");
        gradebook.startProgram();
    }
}
