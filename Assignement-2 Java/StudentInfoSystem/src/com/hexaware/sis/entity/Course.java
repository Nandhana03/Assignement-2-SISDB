


package com.hexaware.sis.entity;

import java.util.*;

public class Course {

    private int courseId;            
    private String courseName;
    private int credits;
    private int teacherId;               
    private Teacher assignedTeacher; 
    private List<Enrollment> enrollments = new ArrayList<>();

    // Full constructor with teacherId
    public Course(int courseId, String courseName, int credits, int teacherId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.teacherId = teacherId;
        this.enrollments = new ArrayList<>();
    }

    
    public Course(String courseName, int credits, int teacherId) {
        this.courseName = courseName;
        this.credits = credits;
        this.teacherId = teacherId;
        this.enrollments = new ArrayList<>();
    }


	public Course(int i, String courseName, String string2) {
		this.courseId=i;
		this.courseName=courseName;
	}

    public Course(int courseId2, String courseName2, int credits2, Teacher updatedTeacher) {
		// TODO Auto-generated constructor stub
    	this.courseId=courseId2;
    	this.courseName=courseName2;
    	this.credits=credits2;
    	this.assignedTeacher=updatedTeacher;
	}


	public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        if (credits > 0) {
            this.credits = credits;
        } else {
            throw new IllegalArgumentException("Credits must be greater than 0");
        }
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public Teacher getAssignedTeacher() {
        return assignedTeacher;
    }

    public void setAssignedTeacher(Teacher teacher) {
        this.assignedTeacher = teacher;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void assignTeacherById(Teacher teacher) {
        this.assignedTeacher = teacher;
        this.teacherId = teacher.getTeacherId(); 
    }

    public void updateCourseInfo(String courseName, int credits) {
        this.courseName = courseName;
        setCredits(credits); 
    }

    public void displayCourseInfo() {
        System.out.println("Course ID   : " + courseId);
        System.out.println("Course Name : " + courseName);
        System.out.println("Credits     : " + credits);
        System.out.println("Instructor  : " + (assignedTeacher != null ? 
            assignedTeacher.getFirstName() + " " + assignedTeacher.getLastName() : "Not Assigned"));
    }

    @Override
    public String toString() {
        return "Course [courseId=" + courseId + 
               ", courseName=" + courseName + 
               ", credits=" + credits + 
               ", teacherId=" + teacherId + "]";
    }

	public int getCourseById(int courseId2) {
		// TODO Auto-generated method stub
		return courseId;
	}
}
