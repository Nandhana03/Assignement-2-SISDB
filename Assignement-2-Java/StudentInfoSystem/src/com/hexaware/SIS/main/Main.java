package com.hexaware.sis.main;
import java.sql.SQLIntegrityConstraintViolationException;


import com.hexaware.sis.dao.Sis;
import com.hexaware.sis.entity.*;
import com.hexaware.sis.exception.*;
import com.hexaware.sis.service.*;
import com.hexaware.sis.service.impl.*;
import com.hexaware.sis.dao.Sis;

import java.time.LocalDate;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        // SIS & Service Objects Setup
        Sis sis = new Sis();
        StudentService studentService = new StudentServiceImpl();
        CourseService courseService = new CourseServiceImpl();
        EnrollmentService enrollmentService = new EnrollmentServiceImpl();
        TeacherService teacherService = new TeacherServiceImpl();

        // ---------- Task 8: Sample Data Setup ----------
        Student jane = new Student(101, "Jane", "Johnson", null, "jane.johnson@example.com", null);
        sis.addStudent(jane);
        Course cs101 = new Course(101,"Computer Science 101", null);
        sis.addCourse(cs101);

        try {
            // Enroll Jane in Computer Science 101
            sis.addEnrollment(jane, cs101, LocalDate.of(2023, 3, 1));
        } catch (DuplicateEnrollmentException e) {
            System.out.println(e.getMessage());
        }

        // ---------- Task 9: Enroll Student in Courses -----------
       Student john = new Student("John", "Doe", LocalDate.of(1995, 8, 15), "john.doe@example.com", "123-456-7890");
       try {
           studentService.createStudent(john);
           System.out.println("Student created: " + john.getFirstName() + " " + john.getLastName());

           enrollStudentInCourse(john, "Introduction to Programming", courseService, enrollmentService);
           enrollStudentInCourse(john, "Mathematics 101", courseService, enrollmentService);
       } catch (java.sql.SQLIntegrityConstraintViolationException e) {
           System.out.println("Duplicate entry: " + e.getMessage());
       } catch (SQLException e) {
           System.out.println("Something went wrong:");
           e.printStackTrace();
       }


        // ---------- Task 10: Record Payment ----------
        try {
            sis.addPayment(jane, 500.00, LocalDate.of(2023, 4, 10));
        } catch (PaymentValidationException e) {
            System.out.println(e.getMessage());
        }

        sis.generatePaymentReport(jane);

        // ---------- Task 11: Generate Enrollment Report ----------
        sis.generateEnrollmentReport(cs101); // one student is enrolled..

        try {
            assignTeacherToCourse(teacherService, courseService);
        } catch (SQLException e) {
            System.out.println("Error assigning teacher: " + e.getMessage());
        }
    }

    private static void enrollStudentInCourse(Student student, String courseName, CourseService courseService, EnrollmentService enrollmentService) throws SQLException {
        Course course = courseService.getCourseByName(courseName);
        if (course != null) {
            Enrollment enrollment = new Enrollment(student.getStudentId(), course.getCourseId(), LocalDate.now());
            enrollmentService.createEnrollment(enrollment);
            System.out.println("Enrolled in: " + course.getCourseName());
        } else {
            System.out.println("Course not found: " + courseName);
        }
    }

    private static void assignTeacherToCourse(TeacherService teacherService, CourseService courseService) throws SQLException {
        // Create Sarah
        Teacher sarah = new Teacher("Sarah", "Smith", "sarah.smith@example.com", "Computer Science");

        // Save teacher to DB
        teacherService.addTeacher(sarah);
        System.out.println("Teacher created: " + sarah.getFirstName() + " " + sarah.getLastName());

        // Get the course by code
        Course course = courseService.getCourseById(302);
        if (course != null) {
            //  Assign Sarah as instructor
            course.setAssignedTeacher(sarah);

            // Update course with instructor info
            courseService.updateCourse(course);

            System.out.println("Sarah Smith assigned to course: " + course.getCourseName());
        } else {
            System.out.println("Course with code CS302 not found.");
        }
    }
}

