package controller.BillingAndPayments;

import model.PaymentBill;

import java.util.List;

public interface BillingAndPaymentServices {
    boolean add(PaymentBill paymentBill);
    boolean update(PaymentBill paymentBill);
    boolean delete(String id);
    PaymentBill search(String id);
    List<PaymentBill> getAll();
    String getLastId();
}
