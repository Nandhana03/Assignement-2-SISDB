package com.hexaware.sis.service;

import com.hexaware.sis.entity.Teacher;
import java.sql.SQLException;
import java.util.List;

public interface TeacherService {

    // Add new teacher
    void addTeacher(Teacher teacher) throws SQLException;

    // Get teacher by ID
    Teacher getTeacherById(int id) throws SQLException;

    // Get all teachers
    List<Teacher> getAllTeachers() throws SQLException;

    // Update teacher details
    void updateTeacherDetails(int id, String firstName, String lastName, String email) throws SQLException;

    // Delete teacher
    void deleteTeacher(int teacherId) throws SQLException;

    // Display teacher info
    void displayTeacherInfo(Teacher teacher);
}
