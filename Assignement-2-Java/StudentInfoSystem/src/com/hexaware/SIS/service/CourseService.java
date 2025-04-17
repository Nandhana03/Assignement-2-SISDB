package com.hexaware.SIS.service;

import com.hexaware.SIS.entity.Course;
import java.sql.SQLException;
import java.util.List;

public interface CourseService {

    void addCourse(Course course) throws SQLException;

    void updateCourse(Course course) throws SQLException;

    Course getCourseById(int courseId) throws SQLException;

    List<Course> getAllCourses() throws SQLException;

    void deleteCourse(int courseId) throws SQLException;

	Course getCourseByName(String string);
}
