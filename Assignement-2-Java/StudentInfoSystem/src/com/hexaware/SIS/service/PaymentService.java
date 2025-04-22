package com.hexaware.sis.service;

import com.hexaware.sis.entity.Payment;
import java.util.List;

public interface PaymentService {

    // Create payment
    void createPayment(Payment payment);

    // Update payment
    void updatePayment(Payment payment);

    // Get payment by ID
    Payment getPaymentById(int paymentId);

    // Get all payments
    List<Payment> getAllPayments();

    // Delete payment
    void deletePayment(int paymentId);
}
