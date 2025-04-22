package com.hexaware.sis.entity;
import java.util.*;

public class Teacher {
    
	private int teacherId;
	private String firstName;
	private String lastName;
	private String email;
	private String expertise;
	private List<Course> assignedCourses;
	
	public Teacher(int teacherId, String firstName, String lastName, String email) {
		super();
		this.teacherId = teacherId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.assignedCourses = new ArrayList<>();


	}
	
	public Teacher(String firstname, String lastName, String email, String expertise) {
        this.firstName = firstname;
        this.lastName=lastName;
        this.email = email;
        this.expertise = expertise;
    }
	
	  public Teacher(String firstName, String lastName, String email) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	        this.assignedCourses = new ArrayList<>();
	    }

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + "]";
	}
	
	public void updateTeacherInfo(String firstName, String lastName, String email) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.email = email;
	}

	 public void displayTeacherInfo() {
	        System.out.println("ID: " + teacherId);
	        System.out.println("Name: " + firstName + " " + lastName);
	        System.out.println("Email: " + email);

	    }
	 
	  public List<Course> getAssignedCourses() {
	        return assignedCourses;
	    }
	  
	  public void setAssignedCourses(List<Course> assignedCourses) {
	        this.assignedCourses = assignedCourses;
	    }
	
	 
}
