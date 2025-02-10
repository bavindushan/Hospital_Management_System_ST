package controller.BillingAndPayments;

import model.PaymentBill;

import java.sql.SQLException;
import java.util.List;

public interface BillingAndPaymentServices {
    boolean add(PaymentBill paymentBill) throws SQLException;
    boolean update(PaymentBill paymentBill);
    boolean delete(String id);
    PaymentBill search(String id);
    List<PaymentBill> getAll();
    String getLastId();
}
