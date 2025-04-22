package com.hexaware.sis.entity;
import java.time.*;
import java.util.*;

public class Student {
	
	   private int studentId;
	   private String firstName;
	   private String lastName;
	   LocalDate dateOfBirth;
	   private String email;
	   private String phoneNumber;
	   private List<Enrollment> enrollments;
	   private List<Payment> payments;

	    
	   public Student(int studentId, String firstName, String lastName, LocalDate dateOfBirth, String email,
			String phoneNumber) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.enrollments = new ArrayList<>();
		this.payments = new ArrayList<>();
		
	}
	   
	   public Student(String firstName, String lastName, LocalDate dateOfBirth, String email,
			String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.enrollments = new ArrayList<>();
		this.payments = new ArrayList<>();
	   }

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}	   
	
	public void UpdateStudentInfo(String firstName,String lastName, LocalDate dateOfBirth, String email, String phoneNumber)
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.dateOfBirth=dateOfBirth;
		this.email=email;
		this.phoneNumber=phoneNumber;
	}
	
	public void displayStudentInfo()
    {
		 System.out.println("Student ID: " + studentId);
	        System.out.println("Name: " + firstName + " " + lastName);
	        System.out.println("DOB: " + dateOfBirth);
	        System.out.println("Email: " + email);
	        System.out.println("Phone: " + phoneNumber);
	}
	
	public List<Integer> getEnrolledCourses() {
        List<Integer> courseIds = new ArrayList<>();
        for (Enrollment e : enrollments) {
            courseIds.add(e.getCourseId());
        }
        return courseIds;
    }
	
    public List<Payment> getPaymentHistory() {
        return payments;
    }
    
    public void enrollInCourse(Course course) {
        Enrollment enrollment = new Enrollment(this.studentId, course.getCourseId(), LocalDate.now());
        enrollments.add(enrollment);
        System.out.println("Enrolled in course: " + course.getCourseName());
    }
    
    public void makePayment(double amount, LocalDate paymentDate) {
        Payment payment = new Payment(this.studentId, amount, paymentDate);
        payments.add(payment);
        System.out.println("Payment of ₹" + amount + " recorded on " + paymentDate);
    }

	
    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}

