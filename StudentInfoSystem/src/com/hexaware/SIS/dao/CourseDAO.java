package com.hexaware.SIS.dao;

import com.hexaware.SIS.entity.Course;
import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {

    // Create
    void insertCourse(Course course) throws SQLException;

    // Update
    void updateCourse(Course course) throws SQLException;

    // Read
    Course getCourseById(int courseId) throws SQLException;
    List<Course> getAllCourses() throws SQLException;

    //Delete
    void deleteCourse(int courseId) throws SQLException;

	Course getCourseById(String string);

	Course getCourseByName(String courseName);
}
