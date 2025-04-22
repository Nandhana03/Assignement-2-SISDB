package com.hexaware.sis.dao;

import com.hexaware.sis.entity.Course;
import java.sql.SQLException;
import java.util.List;

public interface CourseDao {

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
