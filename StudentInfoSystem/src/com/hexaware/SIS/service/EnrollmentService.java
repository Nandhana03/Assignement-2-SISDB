package com.hexaware.SIS.service;

import com.hexaware.SIS.entity.Enrollment;
import java.sql.SQLException;
import java.util.List;

public interface EnrollmentService {
    
    // Create
    void createEnrollment(Enrollment enrollment) throws SQLException;
    
    // Update
    void updateEnrollment(Enrollment enrollment) throws SQLException;
    
    // Read
    Enrollment getEnrollmentById(int enrollmentId) throws SQLException;
    List<Enrollment> getAllEnrollments() throws SQLException;
    
    // Delete
    void deleteEnrollment(int enrollmentId) throws SQLException;
}
