package com.workmotion.copilot;

import com.workmotion.copilot.controller.StudentControllerImpl;
import com.workmotion.copilot.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentControllerImplTest {

    private StudentControllerImpl studentController;

    @BeforeEach
    public void setup() {
        studentController = new StudentControllerImpl();
    }

    // Create all needed testcases for addStudent
    @Test
    public void addStudent_addsStudentToList() {
        Student student = new Student(1, "John Doe", "A");
        studentController.addStudent(student);
        assertEquals(1, studentController.getAllStudents().size());
    }

    @Test
    public void addStudent_throwsExceptionIfStudentExists() {
        Student student = new Student(1, "John Doe", "A");
        studentController.addStudent(student);
        assertThrows(RuntimeException.class, () -> studentController.addStudent(student));
    }

    @Test
    public void updateStudent_updatesExistingStudent() {
        Student student = new Student(1, "John Doe", "A");
        studentController.addStudent(student);
        student.setName("Jane Doe");
        studentController.updateStudent(1, student);
        assertEquals("Jane Doe", studentController.getStudent(1).getName());
    }

    @Test
    public void deleteStudent_removesStudentFromList() {
        Student student = new Student(1, "John Doe", "A");
        studentController.addStudent(student);
        studentController.deleteStudent(1);
        assertEquals(0, studentController.getAllStudents().size());
    }

    @Test
    public void getStudent_returnsCorrectStudent() {
        Student student = new Student(1, "John Doe", "A");
        studentController.addStudent(student);
        Student retrievedStudent = studentController.getStudent(1);
        assertEquals(student, retrievedStudent);
    }

    @Test
    public void getAllStudents_returnsAllStudents() {
        Student student1 = new Student(1, "John Doe", "A");
        Student student2 = new Student(2, "Jane Doe", "B");
        studentController.addStudent(student1);
        studentController.addStudent(student2);
        List<Student> students = studentController.getAllStudents();
        assertEquals(2, students.size());
        assertTrue(students.contains(student1));
        assertTrue(students.contains(student2));
    }
}
