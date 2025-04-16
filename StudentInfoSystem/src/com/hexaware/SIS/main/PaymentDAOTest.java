package com.hexaware.SIS.main;

import com.hexaware.SIS.dao.PaymentDAO;
import com.hexaware.SIS.dao.impl.PaymentDAOImpl;
import com.hexaware.SIS.entity.Payment;

import java.time.LocalDate;
import java.util.List;

public class PaymentDAOTest {
    public static void main(String[] args) {
        PaymentDAO paymentDAO = new PaymentDAOImpl();

        try {
            // Insert
            Payment newPayment = new Payment(1, 1500.00, LocalDate.now());
            paymentDAO.insertPayment(newPayment);

            // Update
            Payment updatePayment = new Payment(1, 1, 1750.00, LocalDate.now());
            updatePayment.setPaymentId(1); 
            paymentDAO.updatePayment(updatePayment);

            //  Get by ID
            Payment fetched = paymentDAO.getPaymentById(1);
            System.out.println("Fetched Payment: " + fetched);

            //  Get all
            List<Payment> allPayments = paymentDAO.getAllPayments();
            allPayments.forEach(System.out::println);

            //  Delete
            paymentDAO.deletePayment(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
