package com.hexaware.SIS.service.impl;

import com.hexaware.SIS.dao.CourseDAO;
import com.hexaware.SIS.dao.impl.CourseDAOImpl;
import com.hexaware.SIS.entity.Course;
import com.hexaware.SIS.service.CourseService;

import java.sql.SQLException;
import java.util.List;

public class CourseServiceImpl implements CourseService {

    private final CourseDAO courseDAO;

    public CourseServiceImpl() {
        this.courseDAO = new CourseDAOImpl(); 
    }

    @Override
    public void addCourse(Course course) throws SQLException {
        if (course.getCourseName() == null || course.getCourseName().trim().isEmpty()) {
            throw new IllegalArgumentException("Course name can't be null or empty");
        }
        courseDAO.insertCourse(course);
    }

    @Override
    public void updateCourse(Course course) throws SQLException {
        if (course.getCourseId() <= 0) {
            throw new IllegalArgumentException("Invalid Course ID");
        }
        courseDAO.updateCourse(course);
    }

    @Override
    public Course getCourseById(int courseId) throws SQLException {
        return courseDAO.getCourseById(courseId);
    }

    @Override
    public List<Course> getAllCourses() throws SQLException {
        return courseDAO.getAllCourses();
    }

    @Override
    public void deleteCourse(int courseId) throws SQLException {
        courseDAO.deleteCourse(courseId);
    }

    @Override
    public Course getCourseByName(String courseName) {
        return courseDAO.getCourseByName(courseName);  
    }
}
