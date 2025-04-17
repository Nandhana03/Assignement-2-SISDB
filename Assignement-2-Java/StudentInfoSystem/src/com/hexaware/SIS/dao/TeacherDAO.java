package com.hexaware.SIS.dao;

import com.hexaware.SIS.entity.Teacher;
import java.sql.SQLException;
import java.util.List;

public interface TeacherDAO {

    // Create
    void insertTeacher(Teacher teacher) throws SQLException;

    // Update
    void updateTeacher(Teacher teacher) throws SQLException;

    // Read
    Teacher getTeacherById(int teacherId) throws SQLException;
    List<Teacher> getAllTeachers() throws SQLException;

    // Delete
    void deleteTeacher(int teacherId) throws SQLException;
}
