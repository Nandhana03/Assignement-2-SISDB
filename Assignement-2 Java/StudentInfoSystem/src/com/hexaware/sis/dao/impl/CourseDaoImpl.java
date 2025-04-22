package com.hexaware.sis.dao.impl;

import com.hexaware.sis.dao.CourseDao;
import com.hexaware.sis.entity.Course;
import com.hexaware.sis.entity.Teacher;
import com.hexaware.sis.util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    private static final String DB_PROPS = "db.properties";

    @Override
    public void insertCourse(Course course) throws SQLException {
        String sql = "INSERT INTO courses (course_name, credits, teacher_id) VALUES (?, ?, ?)";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getCredits());
            ps.setInt(3, course.getTeacherId());

            int rows = ps.executeUpdate();
            System.out.println("Course inserted. Rows affected: " + rows);
        }
    }

    @Override
    public void updateCourse(Course course) throws SQLException {
        String sql = "UPDATE courses SET course_name = ?, credits = ?, teacher_id = ? WHERE course_id = ?";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getCredits());
            ps.setInt(3, course.getTeacherId());  
            ps.setInt(4, course.getCourseId());

            int rows = ps.executeUpdate();
            System.out.println("Course updated. Rows affected: " + rows);
        }
    }

    @Override
    public Course getCourseById(int courseId) throws SQLException {
        String sql = "SELECT c.course_id, c.course_name, c.credits, c.teacher_id, t.first_name, t.last_name, t.email " +
                     "FROM courses c " +
                     "JOIN teacher t ON c.teacher_id = t.teacher_id " +
                     "WHERE c.course_id = ?";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Teacher teacher = new Teacher(
                    rs.getInt("teacher_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email")
                );

                return new Course(
                    rs.getInt("course_id"),
                    rs.getString("course_name"),
                    rs.getInt("credits"),
                    rs.getInt("teacher_id")
                );
            }
        }
        return null;
    }

    @Override
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courseList = new ArrayList<>();
        String sql = "SELECT c.course_id, c.course_name, c.credits, c.teacher_id, t.first_name, t.last_name, t.email " +
                     "FROM courses c " +
                     "JOIN teacher t ON c.teacher_id = t.teacher_id";

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

                Course course = new Course(
                    rs.getInt("course_id"),
                    rs.getString("course_name"),
                    rs.getInt("credits"),
                    rs.getInt("teacher_id")
                );
                course.setAssignedTeacher(teacher); 
                courseList.add(course);
            }
        }

        return courseList;
    }

    @Override
    public void deleteCourse(int courseId) throws SQLException {
        String sql = "DELETE FROM courses WHERE course_id = ?";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, courseId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Course with ID " + courseId + " deleted.");
            } else {
                System.out.println("No course found with ID: " + courseId);
            }
        }
    }

    @Override
    public Course getCourseById(String courseId) {
        String sql = "SELECT * FROM courses WHERE course_id = ?";  
        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, courseId); 
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Course(rs.getInt("course_id"), rs.getString("course_name"), 0, 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
   
    public Course getCourseByName(String courseName) {
        String sql = "SELECT * FROM courses WHERE course_name = ?";  
        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, courseName); 
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Course(rs.getInt("course_id"), rs.getString("course_name"), 0, 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
}
