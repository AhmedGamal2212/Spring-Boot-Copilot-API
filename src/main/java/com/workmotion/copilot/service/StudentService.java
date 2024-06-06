package com.workmotion.copilot.service;

import com.workmotion.copilot.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void updateStudent(Student student) {
        int index = students.indexOf(student);
        if (index != -1) {
            students.set(index, student);
        }
    }

    public void deleteStudent(int studentId) {
        students.removeIf(student -> student.getId() == studentId);
    }

    public Student getStudent(int studentId) {
        Optional<Student> student = students.stream().filter(s -> s.getId() == studentId).findFirst();
        return student.orElse(null);
    }

    public List<Student> getAllStudents() {
        return students;
    }
}
