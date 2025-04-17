package com.hexaware.SIS.main;

import com.hexaware.SIS.dao.EnrollmentDAO;
import com.hexaware.SIS.dao.impl.EnrollmentDAOImpl;
import com.hexaware.SIS.entity.Enrollment;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class EnrollmentDAOTest {
    public static void main(String[] args) {
        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl();

        try {
            // Insert a new enrollment
            Enrollment newEnrollment = new Enrollment(1, 101, LocalDate.now());
            enrollmentDAO.insertEnrollment(newEnrollment);
            System.out.println("Inserted Enrollment ID: " + newEnrollment.getEnrollmentId());

            // Retrieve all enrollments
            List<Enrollment> enrollments = enrollmentDAO.getAllEnrollments();
            enrollments.forEach(System.out::println);

            // Update an enrollment
            newEnrollment.setEnrollmentDate(LocalDate.of(2025, 4, 14));
            enrollmentDAO.updateEnrollment(newEnrollment);

            // Retrieve enrollment by ID
            Enrollment retrievedEnrollment = enrollmentDAO.getEnrollmentById(newEnrollment.getEnrollmentId());
            System.out.println("Retrieved: " + retrievedEnrollment);

            // Delete the enrollment
            enrollmentDAO.deleteEnrollment(newEnrollment.getEnrollmentId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
