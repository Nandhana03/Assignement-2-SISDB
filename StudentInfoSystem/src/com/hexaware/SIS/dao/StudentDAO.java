package com.hexaware.SIS.dao;

import com.hexaware.SIS.entity.Student;
import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {

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
