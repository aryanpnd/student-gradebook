# StudentGradebook
This is a Java class that can be used to store and manage student grades. It can be used to add, remove, and view student grades. It also provides methods to calculate the total grade and average grade for a student.

## Features
Easy to use and extend
Can be used to develop a variety of gradebook applications
Efficient and uses appropriate data structures and algorithms
## Usage
To use the StudentGradebook class, you first need to create a new instance of the class. You can then add student grades to the gradebook using the addStudentGrade() method. To remove a student from the gradebook, you can use the removeStudent() method. To view the gradebook, you can use the printGradebook() method.

### Here is an example of how to use the StudentGradebook class:

```java
StudentGradebook gradebook = new StudentGradebook();

// Add a student to the gradebook.
gradebook.addStudentGrade("Alice", new ArrayList<>(Arrays.asList(90, 95, 85)));

// Remove a student from the gradebook.
gradebook.removeStudent("Bob");

// Print the gradebook to the console.
gradebook.printGradebook();

// Calculate the total grade for a student.
int totalGrade = gradebook.calculateTotalGrade("Alice");

// Calculate the average grade for a student.
double averageGrade = gradebook.calculateAverageGrade("Alice");
```


## The StudentGradebook class provides a number of benefits, including:

It saves teachers time and effort by automating the process of managing student grades.
It reduces the risk of errors by eliminating the need to manually calculate grades.
It provides teachers with a more efficient way to track student progress and assess learning.
It makes it easier for teachers to generate reports for parents and administrators.
Conclusion
The StudentGradebook class is a valuable tool for teachers and schools. It saves teachers time and effort, reduces the risk of errors, and provides them with a more efficient way to track student progress and assess learning.