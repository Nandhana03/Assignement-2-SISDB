package com.hexaware.SIS.dao;

import com.hexaware.SIS.entity.Payment;
import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO {

    // Create
    void insertPayment(Payment payment) throws SQLException;

    // Update
    void updatePayment(Payment payment) throws SQLException;

    // Read
    Payment getPaymentById(int paymentId) throws SQLException;
    List<Payment> getAllPayments() throws SQLException;

    // Delete
    void deletePayment(int paymentId) throws SQLException;
}
