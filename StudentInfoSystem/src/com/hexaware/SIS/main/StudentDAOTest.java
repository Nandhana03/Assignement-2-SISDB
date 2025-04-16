package com.hexaware.SIS.main;

import com.hexaware.SIS.dao.StudentDAO;
import com.hexaware.SIS.dao.impl.StudentDAOImpl;
import com.hexaware.SIS.entity.Student;

import java.time.LocalDate;
import java.util.List;

public class StudentDAOTest {

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAOImpl();

        try {
            Student student = new Student(0, "Nandhana", "Vivek", LocalDate.of(2000, 5, 15),
                    "newemail123@example.com", "78666651114");
////            studentDAO.insertStudent(student);
//
            System.out.println("\n📋 All Students:");
            List<Student> all = studentDAO.getAllStudents();
            for (Student s : all) {
                System.out.println(s);
            }

            student.setStudentId(1); 
            student.setFirstName("Nandy Updated");
            studentDAO.updateStudent(student);

            System.out.println("\n Student by ID (1):");
            Student found = studentDAO.getStudentById(1);
            System.out.println(found);

             studentDAO.deleteStudent(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
