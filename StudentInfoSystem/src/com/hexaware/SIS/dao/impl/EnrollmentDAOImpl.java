package com.hexaware.SIS.dao.impl;

import com.hexaware.SIS.dao.EnrollmentDAO;
import com.hexaware.SIS.entity.Enrollment;
import com.hexaware.SIS.util.DBConnUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImpl implements EnrollmentDAO {

    private static final String DB_PROPS = "db.properties";

    @Override
    public void insertEnrollment(Enrollment enrollment) throws SQLException {
        String sql = "INSERT INTO enrollments (student_id, course_id, enrollment_date) VALUES (?, ?, ?)";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setDate(3, Date.valueOf(enrollment.getEnrollmentDate()));

            int rows = ps.executeUpdate();
            System.out.println("✅ Enrollment inserted. Rows affected: " + rows);

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    enrollment.setEnrollmentId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public void updateEnrollment(Enrollment enrollment) throws SQLException {
        String sql = "UPDATE enrollments SET student_id = ?, course_id = ?, enrollment_date = ? WHERE enrollment_id = ?";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setDate(3, Date.valueOf(enrollment.getEnrollmentDate()));
            ps.setInt(4, enrollment.getEnrollmentId());

            int rows = ps.executeUpdate();
            System.out.println("Enrollment updated. Rows affected: " + rows);
        }
    }

    @Override
    public Enrollment getEnrollmentById(int enrollmentId) throws SQLException {
        String sql = "SELECT * FROM enrollments WHERE enrollment_id = ?";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, enrollmentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("enrollment_date").toLocalDate()
                );
            }
        }
        return null;
    }

    @Override
    public List<Enrollment> getAllEnrollments() throws SQLException {
        List<Enrollment> enrollmentList = new ArrayList<>();
        String sql = "SELECT * FROM enrollments";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Enrollment enrollment = new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("enrollment_date").toLocalDate()
                );
                enrollmentList.add(enrollment);
            }
        }

        return enrollmentList;
    }

    @Override
    public void deleteEnrollment(int enrollmentId) throws SQLException {
        String sql = "DELETE FROM enrollments WHERE enrollment_id = ?";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, enrollmentId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Enrollment with ID " + enrollmentId + " deleted.");
            } else {
                System.out.println("No enrollment found with ID: " + enrollmentId);
            }
        }
    }
}
