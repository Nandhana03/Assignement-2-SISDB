package com.hexaware.SIS.dao.impl;

import com.hexaware.SIS.dao.StudentDAO;
import com.hexaware.SIS.entity.Student;
import com.hexaware.SIS.util.DBConnUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private static final String DB_PROPS = "db.properties";

    @Override
    public void insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (first_name, last_name, date_of_birth, email, phone_number) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setDate(3, Date.valueOf(student.getDateOfBirth()));
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhoneNumber());

            int rows = ps.executeUpdate();
            System.out.println("Student inserted. Rows affected: " + rows);
        }
    }

    @Override
    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, date_of_birth = ?, email = ?, phone_number = ? WHERE student_id = ?";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setDate(3, Date.valueOf(student.getDateOfBirth()));
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhoneNumber());
            ps.setInt(6, student.getStudentId());

            int rows = ps.executeUpdate();
            System.out.println("Student updated. Rows affected: " + rows);
        }
    }

    @Override
    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getInt("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("date_of_birth").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("phone_number")
                );
            }
        }
        return null;
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("date_of_birth").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("phone_number")
                );
                studentList.add(student);
            }
        }

        return studentList;
    }

    @Override
    public void deleteStudent(int studentId) throws SQLException {
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student with ID " + studentId + " deleted.");
            } else {
                System.out.println("No student found with ID: " + studentId);
            }
        }
    }
}
