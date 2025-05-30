package com.hexaware.sis.service.impl;

import com.hexaware.sis.dao.TeacherDao;
import com.hexaware.sis.dao.impl.TeacherDaoImpl;
import com.hexaware.sis.entity.Teacher;
import com.hexaware.sis.service.TeacherService;

import java.sql.SQLException;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private TeacherDao teacherDAO = new TeacherDaoImpl();

    @Override
    public void addTeacher(Teacher teacher) throws SQLException {
        teacherDAO.insertTeacher(teacher);
        System.out.println("New teacher added successfully!");
    }

    @Override
    public Teacher getTeacherById(int id) throws SQLException {
        return teacherDAO.getTeacherById(id);
    }

    @Override
    public List<Teacher> getAllTeachers() throws SQLException {
        return teacherDAO.getAllTeachers();
    }

    @Override
    public void updateTeacherDetails(int id, String firstName, String lastName, String email) throws SQLException {
        Teacher teacher = teacherDAO.getTeacherById(id);
        if (teacher != null) {
            teacher.setFirstName(firstName);
            teacher.setLastName(lastName);
            teacher.setEmail(email);
            teacherDAO.updateTeacher(teacher);
            System.out.println("Teacher updated successfully!");
        } else {
            System.out.println("Teacher not found with ID: " + id);
        }
    }

    @Override
    public void deleteTeacher(int teacherId) throws SQLException {
        teacherDAO.deleteTeacher(teacherId);
    }

    @Override
    public void displayTeacherInfo(Teacher teacher) {
        if (teacher != null) {
            System.out.println("Teacher Details:");
            System.out.println("ID: " + teacher.getTeacherId());
            System.out.println("Name: " + teacher.getFirstName() + " " + teacher.getLastName());
            System.out.println("Email: " + teacher.getEmail());
        } else {
            System.out.println("No teacher data to display.");
        }
    }
}
