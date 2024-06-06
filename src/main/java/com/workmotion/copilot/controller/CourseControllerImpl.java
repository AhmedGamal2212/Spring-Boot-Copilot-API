package com.workmotion.copilot.controller;

import com.workmotion.copilot.model.Course;
import com.workmotion.copilot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseControllerImpl implements CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    @Override
    public void addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }

    @PutMapping("/{courseId}")
    @Override
    public void updateCourse(@PathVariable int courseId, @RequestBody Course course) {
        courseService.updateCourse(course);
    }

    @DeleteMapping("/{courseId}")
    @Override
    public void deleteCourse(@PathVariable int courseId) {
        courseService.deleteCourse(courseId);
    }

    @GetMapping("/{courseId}")
    @Override
    public Course getCourse(@PathVariable int courseId) {
        return courseService.getCourse(courseId);
    }

    @GetMapping
    @Override
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping("/{courseId}/students/{studentId}")
    @Override
    public void addStudentToCourse(@PathVariable int courseId, @PathVariable int studentId) {
        courseService.addStudentToCourse(courseId, studentId);
    }
}
