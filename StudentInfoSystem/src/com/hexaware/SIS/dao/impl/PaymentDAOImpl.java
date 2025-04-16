package com.hexaware.SIS.dao.impl;

import com.hexaware.SIS.dao.PaymentDAO;
import com.hexaware.SIS.entity.Payment;
import com.hexaware.SIS.util.DBConnUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    private static final String DB_PROPS = "db.properties";

    @Override
    public void insertPayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO payments (student_id, amount, payment_date) VALUES (?, ?, ?)";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, payment.getStudentId());
            ps.setDouble(2, payment.getAmount());
            ps.setDate(3, Date.valueOf(payment.getPaymentDate()));

            int rows = ps.executeUpdate();
            System.out.println("Payment inserted. Rows affected: " + rows);
        }
    }

    @Override
    public void updatePayment(Payment payment) throws SQLException {
        String sql = "UPDATE payments SET student_id = ?, amount = ?, payment_date = ? WHERE payment_id = ?";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, payment.getStudentId());
            ps.setDouble(2, payment.getAmount());
            ps.setDate(3, Date.valueOf(payment.getPaymentDate()));
            ps.setInt(4, payment.getPaymentId());

            int rows = ps.executeUpdate();
            System.out.println("Payment updated. Rows affected: " + rows);
        }
    }

    @Override
    public Payment getPaymentById(int id) throws SQLException {
        String sql = "SELECT * FROM payments WHERE payment_id = ?";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Payment(
                        rs.getInt("payment_id"),
                        rs.getInt("student_id"),
                        rs.getDouble("amount"),
                        rs.getDate("payment_date").toLocalDate()
                );
            }
        }

        return null;
    }

    @Override
    public List<Payment> getAllPayments() throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Payment payment = new Payment(
                        rs.getInt("payment_id"),
                        rs.getInt("student_id"),
                        rs.getDouble("amount"),
                        rs.getDate("payment_date").toLocalDate()
                );
                payments.add(payment);
            }
        }

        return payments;
    }

    @Override
    public void deletePayment(int paymentId) throws SQLException {
        String sql = "DELETE FROM payments WHERE payment_id = ?";

        try (Connection conn = DBConnUtil.getConnection(DB_PROPS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, paymentId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Payment with ID " + paymentId + " deleted.");
            } else {
                System.out.println("No payment found with ID: " + paymentId);
            }
        }
    }
}
