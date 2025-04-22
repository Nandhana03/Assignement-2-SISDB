package com.hexaware.sis.service.impl;

import com.hexaware.sis.dao.StudentDao;
import com.hexaware.sis.dao.PaymentDao;
import com.hexaware.sis.dao.EnrollmentDao;
import com.hexaware.sis.dao.impl.StudentDaoImpl;
import com.hexaware.sis.dao.impl.PaymentDaoImpl;
import com.hexaware.sis.dao.impl.EnrollmentDaoImpl;
import com.hexaware.sis.entity.Student;
import com.hexaware.sis.exception.DuplicateEnrollmentException;
import com.hexaware.sis.entity.Course;
import com.hexaware.sis.entity.Payment;
import com.hexaware.sis.entity.Enrollment;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.sis.service.StudentService;

public class StudentServiceImpl implements StudentService {

    // Initializing DAO classes
    private final StudentDao studentDAO = new StudentDaoImpl();
    private final PaymentDao paymentDAO = new PaymentDaoImpl();
    private final EnrollmentDao enrollmentDAO = new EnrollmentDaoImpl();
    
    @Override
    public void createStudent(Student student) throws SQLException {
        studentDAO.insertStudent(student);  
    }

    @Override
    public void updateStudentInfo(Student student, String firstName, String lastName, LocalDate dob, String email, String phone) throws Exception {
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setDateOfBirth(dob);
        student.setEmail(email);
        student.setPhoneNumber(phone);
        studentDAO.updateStudent(student);
        System.out.println("Student info updated in the database.");
    }

    @Override
    public void displayStudentInfo(Student student) {
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("DOB: " + student.getDateOfBirth());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Phone: " + student.getPhoneNumber());
    }

    @Override
    public void enrollInCourse(Student student, Course course) throws Exception,DuplicateEnrollmentException {
        // Create an Enrollment object and enroll the student in the course
        Enrollment enrollment = new Enrollment(student.getStudentId(), course.getCourseId(), LocalDate.now());
        enrollmentDAO.insertEnrollment(enrollment);
        System.out.println("Student with ID " + student.getStudentId() + " enrolled in course: " + course.getCourseName());
    }

    @Override
    public void processStudentPayment(Student student, double amount, LocalDate paymentDate) throws Exception {
        Payment payment = new Payment(student.getStudentId(), amount, paymentDate);
        paymentDAO.insertPayment(payment);
        System.out.println("Payment of ₹" + amount + " recorded for student ID " + student.getStudentId());
    }

    @Override
   public List<Integer> getEnrolledCourses(Student student) throws Exception {
       List<Enrollment> enrollments = enrollmentDAO.getAllEnrollments();
        List<Integer> courseIds = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == student.getStudentId()) {
                courseIds.add(e.getCourseId());
            }
        }
        return courseIds;
   }

    @Override
    public List<Payment> getPaymentHistory(Student student) throws Exception {
        // Fetch the payment history for the student
        List<Payment> payments = paymentDAO.getAllPayments();
        List<Payment> studentPayments = new ArrayList<>();
        for (Payment p : payments) {
            if (p.getStudentId() == student.getStudentId()) {
                studentPayments.add(p);
            }
        }
        return studentPayments;
    }
}
