package service.custom;

import model.PaymentBill;

import java.sql.SQLException;
import java.util.List;

public interface BillingAndPaymentBo {
    boolean add(PaymentBill paymentBill) throws SQLException;
    boolean update(PaymentBill paymentBill) throws SQLException;
    boolean delete(String id) throws SQLException;
    PaymentBill search(String id) throws SQLException;
    List<PaymentBill> getAll();
    String getLastId();
}
