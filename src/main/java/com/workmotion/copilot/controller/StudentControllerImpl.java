package com.workmotion.copilot.controller;

import com.workmotion.copilot.model.Student;
import com.workmotion.copilot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentControllerImpl implements StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    @Override
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PutMapping("/{studentId}")
    @Override
    public void updateStudent(@PathVariable int studentId, @RequestBody Student student) {
        studentService.updateStudent(student);
    }

    @DeleteMapping("/{studentId}")
    @Override
    public void deleteStudent(@PathVariable int studentId) {
        studentService.deleteStudent(studentId);
    }

    @GetMapping("/{studentId}")
    @Override
    public Student getStudent(@PathVariable int studentId) {
        return studentService.getStudent(studentId);
    }

    @GetMapping
    @Override
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}