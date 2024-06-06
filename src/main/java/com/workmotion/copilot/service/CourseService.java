package com.workmotion.copilot.service;

import com.workmotion.copilot.model.Course;
import com.workmotion.copilot.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private List<Course> courses = new ArrayList<>();

    @Autowired
    private StudentService studentService;

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void updateCourse(Course course) {
        int index = courses.indexOf(course);
        if (index != -1) {
            courses.set(index, course);
        }
    }

    public void deleteCourse(int courseId) {
        courses.removeIf(course -> course.getId() == courseId);
    }

    public Course getCourse(int courseId) {
        Optional<Course> course = courses.stream().filter(c -> c.getId() == courseId).findFirst();
        return course.orElse(null);
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public void addStudentToCourse(int courseId, int studentId) {
        Course course = getCourse(courseId);
        Student student = studentService.getStudent(studentId);
        if (course == null) {
            throw new RuntimeException("Course not found");
        }
        if (student == null) {
            throw new RuntimeException("Student not found");
        }
        if (course.getStudents().size() >= course.getMaxStudents()) {
            throw new RuntimeException("Course is full");
        }
        if (course.getStudents().contains(student)) {
            throw new RuntimeException("Student exists in the course");
        }
        course.addStudent(student);
    }
}
