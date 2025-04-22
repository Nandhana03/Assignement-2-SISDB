package com.hexaware.sis.dao;

import com.hexaware.sis.entity.Enrollment;

import java.sql.SQLException;
import java.util.List;

public interface EnrollmentDao {
    void insertEnrollment(Enrollment enrollment) throws SQLException;
    void updateEnrollment(Enrollment enrollment) throws SQLException;
    Enrollment getEnrollmentById(int enrollmentId) throws SQLException;
    List<Enrollment> getAllEnrollments() throws SQLException;
    void deleteEnrollment(int enrollmentId) throws SQLException;
}
