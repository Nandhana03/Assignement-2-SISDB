package com.hexaware.sis.service.impl;

import com.hexaware.sis.dao.PaymentDao;
import com.hexaware.sis.dao.impl.PaymentDaoImpl;
import com.hexaware.sis.entity.Payment;
import com.hexaware.sis.service.PaymentService;

import java.sql.SQLException;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private PaymentDao paymentDAO;

    public PaymentServiceImpl() {
        this.paymentDAO = new PaymentDaoImpl(); 
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
