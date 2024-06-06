package com.workmotion.copilot.controller;

import com.workmotion.copilot.model.Course;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public interface CourseController {

    @PostMapping
    void addCourse(@RequestBody Course course);

    @PutMapping("/{courseId}")
    void updateCourse(@PathVariable int courseId, @RequestBody Course course);

    @DeleteMapping("/{courseId}")
    void deleteCourse(@PathVariable int courseId);

    @GetMapping("/{courseId}")
    Course getCourse(@PathVariable int courseId);

    @GetMapping
    List<Course> getAllCourses();

    @PostMapping("/{courseId}/students/{studentId}")
    void addStudentToCourse(@PathVariable int courseId, @PathVariable int studentId);
}
