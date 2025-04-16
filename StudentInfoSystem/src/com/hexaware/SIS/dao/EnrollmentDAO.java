package com.hexaware.SIS.dao;

import com.hexaware.SIS.entity.Enrollment;

import java.sql.SQLException;
import java.util.List;

public interface EnrollmentDAO {
    void insertEnrollment(Enrollment enrollment) throws SQLException;
    void updateEnrollment(Enrollment enrollment) throws SQLException;
    Enrollment getEnrollmentById(int enrollmentId) throws SQLException;
    List<Enrollment> getAllEnrollments() throws SQLException;
    void deleteEnrollment(int enrollmentId) throws SQLException;
}
