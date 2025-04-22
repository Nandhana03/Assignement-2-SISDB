package com.hexaware.sis.service.impl;

import com.hexaware.sis.dao.EnrollmentDao;
import com.hexaware.sis.dao.impl.EnrollmentDaoImpl;
import com.hexaware.sis.entity.Enrollment;
import com.hexaware.sis.service.EnrollmentService;

import java.sql.SQLException;
import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {

    private EnrollmentDao enrollmentDAO;

    // Constructor to initialize DAO
    public EnrollmentServiceImpl() {
        this.enrollmentDAO = new EnrollmentDaoImpl(); 
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
