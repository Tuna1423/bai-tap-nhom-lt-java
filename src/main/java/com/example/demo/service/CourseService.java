package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Course;
import com.example.demo.repositories.CourseRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;

    private List<Course> listCourses = new ArrayList<>();

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Transactional
    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(@NotNull Course course) {
        Course existingCourse = courseRepository.findById(course.getId())
                .orElseThrow(() -> new IllegalStateException("Course with ID " +
                        course.getId() + " does not exist."));
        existingCourse.setTeacherName(course.getTeacherName());
        existingCourse.setAddress(course.getAddress());
        existingCourse.setStartDate(course.getStartDate());
        courseRepository.save(existingCourse);
    }

    public void deleteCourseById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new IllegalStateException("Course with ID " + id + " does not exist.");
        }
        courseRepository.deleteById(id);
    }

    public List<Course> searchCourses(String query) {
        return courseRepository.findByNameContainingIgnoreCaseOrTeacherNameContainingIgnoreCaseOrAddressContainingIgnoreCase(query, query, query);
    }
    public List<Course> getAll() {
        return listCourses;
    }

}
