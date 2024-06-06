package com.workmotion.copilot;

import com.workmotion.copilot.controller.CourseControllerImpl;
import com.workmotion.copilot.controller.StudentControllerImpl;
import com.workmotion.copilot.model.Course;
import com.workmotion.copilot.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CourseControllerImplTest {

    @Autowired
    private CourseControllerImpl courseController;

    @Autowired
    private StudentControllerImpl studentController;


    @Test
    public void addCourse_addsCourseToList() {
        Course course = new Course(1, "Math", 30);
        courseController.addCourse(course);
        assertEquals(1, courseController.getAllCourses().size());
    }

    @Test
    public void updateCourse_updatesExistingCourse() {
        Course course = new Course(1, "Math", 30);
        courseController.addCourse(course);
        course.setName("Physics");
        courseController.updateCourse(1 , course);
        assertEquals("Physics", courseController.getCourse(1).getName());
    }

    @Test
    public void deleteCourse_removesCourseFromList() {
        Course course = new Course(1, "Math", 30);
        courseController.addCourse(course);
        courseController.deleteCourse(1);
        assertEquals(0, courseController.getAllCourses().size());
    }

    @Test
    public void getCourse_returnsCorrectCourse() {
        Course course = new Course(1, "Math", 30);
        courseController.addCourse(course);
        Course retrievedCourse = courseController.getCourse(1);
        assertEquals(course, retrievedCourse);
    }

    @Test
    public void getAllCourses_returnsAllCourses() {
        Course course1 = new Course(1, "Math", 30);
        Course course2 = new Course(2, "Physics", 25);
        courseController.addCourse(course1);
        courseController.addCourse(course2);
        List<Course> courses = courseController.getAllCourses();
        assertEquals(2, courses.size());
        assertTrue(courses.contains(course1));
        assertTrue(courses.contains(course2));
    }

    @Test
    public void addStudentToCourse_addsStudentToCourse() {
        Course course = new Course(1, "Math", 30);
        Student student = new Student(1, "John Doe", "A");
        courseController.addCourse(course);
        courseController.addStudentToCourse(1, 1);
        assertTrue(course.getStudents().contains(student));
    }

    @Test
    public void addStudentToCourse_throwsExceptionWhenCourseIsFull() {
        Course course = new Course(1, "Math", 1);
        Student student1 = new Student(1, "John Doe", "A");
        Student student2 = new Student(2, "Jane Doe", "B");
        courseController.addCourse(course);
        courseController.addStudentToCourse(1, 1);
        assertThrows(RuntimeException.class, () -> courseController.addStudentToCourse(1, 2));
    }

    @Test
    public void addStudentToCourse_throwsExceptionWhenStudentExistsInCourse() {
        Course course = new Course(1, "Math", 30);
        Student student = new Student(1, "John Doe", "A");
        courseController.addCourse(course);
        courseController.addStudentToCourse(1, 1);
        assertThrows(RuntimeException.class, () -> courseController.addStudentToCourse(1, 1));
    }

    // create testcases for the following scenarios: student not found, course not found using StudentControllerImpl directly
    @Test
    public void addStudentToCourse_throwsExceptionWhenStudentNotFound() {
        Course course = new Course(1, "Math", 30);
        courseController.addCourse(course);
        assertThrows(RuntimeException.class, () -> courseController.addStudentToCourse(1, 1));
    }

    @Test
    public void addStudentToCourse_throwsExceptionWhenCourseNotFound() {
        Student student = new Student(1, "John Doe", "A");
        studentController.addStudent(student);
        assertThrows(RuntimeException.class, () -> courseController.addStudentToCourse(1, 1));
    }
}
