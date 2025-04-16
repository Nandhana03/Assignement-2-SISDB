package com.hexaware.SIS.service.impl;

import com.hexaware.SIS.dao.PaymentDAO;
import com.hexaware.SIS.dao.impl.PaymentDAOImpl;
import com.hexaware.SIS.entity.Payment;
import com.hexaware.SIS.service.PaymentService;

import java.sql.SQLException;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private PaymentDAO paymentDAO;

    public PaymentServiceImpl() {
        this.paymentDAO = new PaymentDAOImpl(); 
    }

    @Override
    public void createPayment(Payment payment) {
        try {
            paymentDAO.insertPayment(payment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePayment(Payment payment) {
        try {
            paymentDAO.updatePayment(payment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Payment getPaymentById(int paymentId) {
        try {
            return paymentDAO.getPaymentById(paymentId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        try {
            return paymentDAO.getAllPayments();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deletePayment(int paymentId) {
        try {
            paymentDAO.deletePayment(paymentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
