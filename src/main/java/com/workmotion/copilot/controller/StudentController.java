package com.workmotion.copilot.controller;

import com.workmotion.copilot.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public interface StudentController {

    @PostMapping
    void addStudent(@RequestBody Student student);

    @PutMapping("/{studentId}")
    void updateStudent(@PathVariable int studentId, @RequestBody Student student);

    @DeleteMapping("/{studentId}")
    void deleteStudent(@PathVariable int studentId);

    @GetMapping("/{studentId}")
    Student getStudent(@PathVariable int studentId);

    @GetMapping
    List<Student> getAllStudents();
}