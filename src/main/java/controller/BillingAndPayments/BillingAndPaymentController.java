package controller.BillingAndPayments;

import model.PaymentBill;

import java.util.List;

public class BillingAndPaymentController implements BillingAndPaymentServices{
    @Override
    public boolean add(PaymentBill paymentBill) {
        return false;
    }

    @Override
    public boolean update(PaymentBill paymentBill) {
        return false;
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
