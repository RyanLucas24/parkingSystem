package com.example.parkingsystem.domain.model.payment;

import java.time.LocalDateTime;
import java.util.Date;

public class Payment {
    private double value;
    private PaymentMethodEnum paymentMethod;
    private LocalDateTime date;

    public Payment(double value, PaymentMethodEnum paymentMethod) {
        this.value = value;
        this.paymentMethod = paymentMethod;
        this.date = LocalDateTime.now();
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public PaymentMethodEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = LocalDateTime.now();
    }
}
