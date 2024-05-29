package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Course;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private List<Course> listCourses = new ArrayList<>();

    public void add(Course newCourse) {
        listCourses.add(newCourse);
    }

    public List<Course> getAll() {
        return listCourses;
    }
}
