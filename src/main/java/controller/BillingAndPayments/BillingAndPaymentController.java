package controller.BillingAndPayments;

import db.DBConnection;
import model.PaymentBill;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BillingAndPaymentController implements BillingAndPaymentServices{
    @Override
    public boolean add(PaymentBill paymentBill) throws SQLException {
        String SQL = "INSERT INTO billing (bill_id,patient_id,total_amount,payment_status,invoice_pdf,generated_date) VALUES (?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, paymentBill.getId());
        preparedStatement.setString(2, paymentBill.getPatientID());
        preparedStatement.setDouble(3,paymentBill.getTotal());
        preparedStatement.setString(4, paymentBill.getStatus());
        preparedStatement.setString(5, paymentBill.getInvoiceName());
        preparedStatement.setDate(6, Date.valueOf(paymentBill.getDate()));

        int affectedRows = preparedStatement.executeUpdate();
        return affectedRows>0;
    }

    @Override
    public boolean update(PaymentBill paymentBill) throws SQLException {
        String SQL = "UPDATE billing SET patient_id=?,total_amount=?,payment_status=?,invoice_pdf=?,generated_date=? WHERE bill_id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, paymentBill.getPatientID());
        preparedStatement.setDouble(2,paymentBill.getTotal());
        preparedStatement.setString(3, paymentBill.getStatus());
        preparedStatement.setString(4, paymentBill.getInvoiceName());
        preparedStatement.setDate(5, Date.valueOf(paymentBill.getDate()));

        preparedStatement.setString(6, paymentBill.getId());

        int affectedRows = preparedStatement.executeUpdate();
        return affectedRows>0;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public PaymentBill search(String id) {
        return null;
    }

    @Override
    public List<PaymentBill> getAll() {
        return List.of();
    }

    @Override
    public String getLastId() {
        return "";
    }
}
