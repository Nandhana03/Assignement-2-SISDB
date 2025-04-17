package com.hexaware.SIS.entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Enrollment {
    
    private int enrollmentId;
    private int studentId;
    private int courseId;
    private LocalDate enrollmentDate;
    
    private Student student;
    private Course course;
    private List<Enrollment> enrollments = new ArrayList<>();


    public Enrollment(int enrollmentId, int studentId, int courseId, LocalDate enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }

    public Enrollment(int studentId, int courseId, LocalDate enrollmentDate) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }


    public Enrollment(int enrollmentId, Student student, Course course, LocalDate enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
        this.studentId = student.getStudentId();
        this.courseId = course.getCourseId();
        this.enrollmentDate = enrollmentDate;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Student getStudent() {
        return student;
    }
    
    public Course getCourse() {
        return course;
    }
    
    public void setStudent(Student student) {
        this.student = student;
        this.studentId = student.getStudentId();
    }
    
    public void setCourse(Course course) {
        this.course = course;
        this.courseId = course.getCourseId();
    }

    @Override
    public String toString() {
        return "Enrollment [enrollmentId=" + enrollmentId + ", studentId=" + studentId + ", courseId=" + courseId
                + ", enrollmentDate=" + enrollmentDate + "]";
    }

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
}
