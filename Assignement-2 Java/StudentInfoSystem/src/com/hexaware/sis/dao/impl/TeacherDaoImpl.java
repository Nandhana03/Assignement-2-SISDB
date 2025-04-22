package com.hexaware.sis.dao.impl;

import com.hexaware.sis.dao.TeacherDao;
import com.hexaware.sis.entity.Teacher;
import com.hexaware.sis.util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {

    private static final String DB_PROPS = "db.properties";

    @Override
    public void insertTeacher(Teacher teacher) throws SQLException {
        String sql = "INSERT INTO teacher (first_name, last_name, email) VALUES (?, ?, ?)";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, teacher.getFirstName());
            ps.setString(2, teacher.getLastName());
            ps.setString(3, teacher.getEmail());

            int rows = ps.executeUpdate();
            System.out.println("Teacher inserted. Rows affected: " + rows);
        }
    }

    @Override
    public void updateTeacher(Teacher teacher) throws SQLException {
        String sql = "UPDATE teacher SET first_name = ?, last_name = ?, email = ? WHERE teacher_id = ?";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, teacher.getFirstName());
            ps.setString(2, teacher.getLastName());
            ps.setString(3, teacher.getEmail());
            ps.setInt(4, teacher.getTeacherId());

            int rows = ps.executeUpdate();
            System.out.println("Teacher updated. Rows affected: " + rows);
        }
    }

    @Override
    public Teacher getTeacherById(int id) throws SQLException {
        String sql = "SELECT * FROM teacher WHERE teacher_id = ?";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Teacher(
                        rs.getInt("teacher_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")
                );
            }
        }
        return null;
    }

    @Override
    public List<Teacher> getAllTeachers() throws SQLException {
        List<Teacher> teacherList = new ArrayList<>();
        String sql = "SELECT * FROM teachers";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getInt("teacher_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")
                );
                teacherList.add(teacher);
            }
        }

        return teacherList;
    }

    @Override
    public void deleteTeacher(int teacherId) throws SQLException {
        String sql = "DELETE FROM teacher WHERE teacher_id = ?";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, teacherId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Teacher with ID " + teacherId + " deleted.");
            } else {
                System.out.println("No teacher found with ID: " + teacherId);
            }
        }
    }
}
