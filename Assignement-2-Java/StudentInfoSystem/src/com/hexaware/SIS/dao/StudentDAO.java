package com.hexaware.sis.dao;

import com.hexaware.sis.entity.Student;
import java.sql.SQLException;
import java.util.List;

public interface StudentDao {

    //  Create
    void insertStudent(Student student) throws SQLException;

    //  Update
    void updateStudent(Student student) throws SQLException;

    // Read
    Student getStudentById(int studentId) throws SQLException;
    List<Student> getAllStudents() throws SQLException;

    // Delete 
    void deleteStudent(int studentId) throws SQLException;
}
