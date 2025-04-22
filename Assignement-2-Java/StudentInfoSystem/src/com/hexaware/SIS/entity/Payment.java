package com.hexaware.sis.entity;

import java.time.LocalDate;

public class Payment {
     
	private int paymentId;
	private int studentId;
	private double amount;
	private LocalDate paymentDate;
	private Student student;
	
	public Payment(int paymentId, int studentId, double amount, LocalDate paymentDate) {
		super();
		this.paymentId = paymentId;
		this.studentId = studentId;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}

	 public Payment(int studentId, double amount, LocalDate paymentDate) {
	        this.studentId = studentId;
	        this.amount = amount;
	        this.paymentDate = paymentDate;
	  }
	 
	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	 public Student getStudent() {
	        return student;
	    }
	 
	 public void setStudent(Student student) {
	        this.student = student;
	        this.studentId = student.getStudentId();
	    }

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", studentId=" + studentId + ", amount=" + amount + ", paymentDate="
				+ paymentDate + "]";
	}
	

}
