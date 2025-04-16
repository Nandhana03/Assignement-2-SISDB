package com.hexaware.SIS.service.impl;

import com.hexaware.SIS.dao.EnrollmentDAO;
import com.hexaware.SIS.dao.impl.EnrollmentDAOImpl;
import com.hexaware.SIS.entity.Enrollment;
import com.hexaware.SIS.service.EnrollmentService;

import java.sql.SQLException;
import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {

    private EnrollmentDAO enrollmentDAO;

    // Constructor to initialize DAO
    public EnrollmentServiceImpl() {
        this.enrollmentDAO = new EnrollmentDAOImpl(); 
    }

    @Override
    public void createEnrollment(Enrollment enrollment) throws SQLException {
        enrollmentDAO.insertEnrollment(enrollment);
    }

    @Override
    public void updateEnrollment(Enrollment enrollment) throws SQLException {
        enrollmentDAO.updateEnrollment(enrollment);
    }

    @Override
    public Enrollment getEnrollmentById(int enrollmentId) throws SQLException {
        return enrollmentDAO.getEnrollmentById(enrollmentId);
    }

    @Override
    public List<Enrollment> getAllEnrollments() throws SQLException {
        return enrollmentDAO.getAllEnrollments();
    }

    @Override
    public void deleteEnrollment(int enrollmentId) throws SQLException {
        enrollmentDAO.deleteEnrollment(enrollmentId);
    }
}
