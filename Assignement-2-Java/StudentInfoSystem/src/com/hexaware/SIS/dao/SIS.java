package com.hexaware.SIS.dao;

import com.hexaware.SIS.entity.*;
import com.hexaware.SIS.exception.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SIS {

    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Enrollment> enrollments = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();

    // Add Student to the system
    public void addStudent(Student student) {
        students.add(student);
    }

    // Add Course to the system
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Add Teacher to the system
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    // Add Enrollment (Task 6.1)
    public void addEnrollment(Student student, Course course, LocalDate enrollmentDate) 
            throws DuplicateEnrollmentException {

        for (Enrollment e : student.getEnrollments()) {
            if (e.getCourseId() == course.getCourseId()) {
                throw new DuplicateEnrollmentException("Student is already enrolled in this course.");
            }
        }

        Enrollment enrollment = new Enrollment(student.getStudentId(), course.getCourseId(), enrollmentDate);
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        student.getEnrollments().add(enrollment);
        course.getEnrollments().add(enrollment);
        enrollments.add(enrollment);

        System.out.println("Student enrolled successfully in course: " + course.getCourseName());
    }

    // Assign Teacher (Task 6.2)
    public void assignCourseToTeacher(Course course, Teacher teacher) 
            throws TeacherNotFoundException {

        if (teacher == null) {
            throw new TeacherNotFoundException("Teacher not found.");
        }

        course.setAssignedTeacher(teacher);
        teacher.getAssignedCourses().add(course);

        System.out.println("Course assigned to teacher: " + teacher.getFirstName());
    }

    // Add Payment (Task 6.3)
    public void addPayment(Student student, double amount, LocalDate paymentDate) 
            throws PaymentValidationException {

        if (amount <= 0) {
            throw new PaymentValidationException("Invalid payment amount. Amount must be greater than 0.");
        }

        Payment payment = new Payment(student.getStudentId(), amount, paymentDate);
        payment.setStudent(student);

        student.getPayments().add(payment);
        payments.add(payment);

        System.out.println("Payment of ₹" + amount + " recorded for student: " + student.getFirstName());
    }

    // Get Enrollments For Student (Task 6.4)
    public List<Enrollment> getEnrollmentsForStudent(Student student) {
        return student.getEnrollments();
    }

    // Get Courses For Teacher (Task 6.5)
    public List<Course> getCoursesForTeacher(Teacher teacher) {
        return teacher.getAssignedCourses();
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public List<Payment> getPayments() {
        return payments;
    }
    
 // Generate enrollment report
    public void generateEnrollmentReport(Course course) {
        System.out.println("Students enrolled in course: " + course.getCourseName());
        for (Enrollment e : enrollments) {
            if (e.getCourseId() == course.getCourseId()) {
                Student student = findStudentById(e.getStudentId());
                if (student != null) {
                    System.out.println("- " + student.getFirstName() + " " + student.getLastName());
                }
            }
        }
    }

    // Generate payment report
    public void generatePaymentReport(Student student) {
        System.out.println("Payment history for " + student.getFirstName() + ":");
        for (Payment p : payments) {
            if (p.getStudentId() == student.getStudentId()) {
                System.out.println("- ₹" + p.getAmount() + " on " + p.getPaymentDate());
            }
        }
    }

    // Calculate course statistics
    public void calculateCourseStatistics(Course course) {
        int enrollmentCount = 0;
        double totalPayments = 0.0;

        for (Enrollment e : enrollments) {
            if (e.getCourseId() == course.getCourseId()) {
                enrollmentCount++;
                Student student = findStudentById(e.getStudentId());
                if (student != null) {
                    for (Payment p : student.getPayments()) {
                        totalPayments += p.getAmount();
                    }
                }
            }
        }

        System.out.println("Stats for course: " + course.getCourseName());
        System.out.println("Total Enrollments: " + enrollmentCount);
        System.out.println("Total Payments: ₹" + totalPayments);
    }

    public Student findStudentById(int studentId) {
        for (Student s : students) {
            if (s.getStudentId() == studentId) {
                return s;
            }
        }
        return null;
    }

}
