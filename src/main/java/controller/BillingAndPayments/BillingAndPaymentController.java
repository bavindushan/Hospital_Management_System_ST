package controller.BillingAndPayments;

import db.DBConnection;
import model.PaymentBill;

import java.sql.*;
import java.util.ArrayList;
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
    public boolean delete(String id) throws SQLException {
        String SQL = "DELETE FROM billing WHERE bill_id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1,id);
        int affectedrows = preparedStatement.executeUpdate();
        return affectedrows>0;
    }

    @Override
    public PaymentBill search(String id) throws SQLException {
        String SQL = "SELECT * FROM billing WHERE bill_id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){

            java.sql.Date sqlDate = resultSet.getDate(6);

            return new PaymentBill(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    sqlDate!=null? sqlDate.toLocalDate():null
            );
        }
        else return null;
    }

    @Override
    public List<PaymentBill> getAll() {
        List<PaymentBill> list = new ArrayList<>();
        String SQL = "SELECT * FROM billing";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);

            while(resultSet.next()){
                java.sql.Date sqlDate = resultSet.getDate(6);
                PaymentBill paymentBill = new PaymentBill(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        sqlDate!=null? sqlDate.toLocalDate():null
                );
                list.add(paymentBill);
            }
            return list;

        } catch (SQLException e) {
            System.out.println("An error occur!"+e.getMessage());
        }
        return null;
    }

    @Override
    public String getLastId() {
        List<PaymentBill> all = getAll();
        if (all.isEmpty()) return null;

        PaymentBill paymentBill = all.get(all.size() - 1);
        return paymentBill.getId();
    }
}
