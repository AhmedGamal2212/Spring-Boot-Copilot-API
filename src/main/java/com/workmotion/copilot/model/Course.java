package com.workmotion.copilot.model;

/*
* Course class that represnts all courses in the system and also get all student the are enrolled in the course
*
*
* */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
* Use lombok to generate getters and setters
*
*
* */
@Getter
@Setter
@AllArgsConstructor
public class Course {

    private int id;
    private String name;
    private String description;
    private int maxStudents;
    private List<Student> students;

    public Course(int id, String name, String description, int maxStudents) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.maxStudents = maxStudents;
        this.students = new ArrayList<>();
    }

    // make contructor with id, name and maxStudents
    public Course(int id, String name, int maxStudents) {
        this.id = id;
        this.name = name;
        this.maxStudents = maxStudents;
        this.students = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        if (students.size() < maxStudents) {
            students.add(student);
        } else {
            throw new RuntimeException("Course is full");
        }
    }

    public void removeStudent(int studentId) {
        students.removeIf(student -> student.getId() == studentId);
    }

    public Student getStudent(int studentId) {
        return students.stream()
                .filter(student -> student.getId() == studentId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void updateStudent(Student student) {
        Student existingStudent = getStudent(student.getId());
        existingStudent.setName(student.getName());
        existingStudent.setGrade(student.getGrade());
    }

    public void updateCourse(Course course) {
        this.name = course.getName();
        this.description = course.getDescription();
        this.maxStudents = course.getMaxStudents();
    }

    public void removeCourse() {
        students.clear();
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // Create toString method and use id and name and maxStudents
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxStudents=" + maxStudents +
                '}';
    }

    // Create equals method to compare id, name, description, and maxStudents
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return id == course.id && maxStudents == course.maxStudents && name.equals(course.name) && description.equals(course.description);
    }
}
