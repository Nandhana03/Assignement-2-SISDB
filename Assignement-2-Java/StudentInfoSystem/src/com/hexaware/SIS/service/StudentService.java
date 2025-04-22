package com.hexaware.sis.service;
//
import com.hexaware.sis.entity.Student;
import com.hexaware.sis.exception.*;
import com.hexaware.sis.entity.Course;
import com.hexaware.sis.entity.Payment;

import java.sql.SQLException;
//
import java.time.LocalDate;
import java.util.List;
//
public interface StudentService {
//    // Update student information
     void updateStudentInfo(Student student, String firstName, String lastName, LocalDate dob, String email, String phone) throws Exception;
//		// TODO Auto-generated method stub
//		
//
//    // Display student information
    void displayStudentInfo(Student student);
//
//    // Enroll student in a course
    void enrollInCourse(Student student, Course course) throws Exception,DuplicateEnrollmentException;
//
//    // Process student payment
    void processStudentPayment(Student student, double amount, LocalDate paymentDate) throws Exception;
//
//    // Get list of enrolled courses for a student
    List<Integer> getEnrolledCourses(Student student) throws Exception;
//
//    // Get payment history for a student
    List<Payment> getPaymentHistory(Student student) throws Exception;
    
    void createStudent(Student student) throws SQLException;

}


