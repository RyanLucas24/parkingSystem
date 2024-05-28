package com.example.parkingsystem.domain.model.service;

import java.time.LocalDateTime;

public class MonthlyService extends Service{
    private LocalDateTime paymentDate;
    private boolean paymentChecked;

    public MonthlyService(double value, double time, double toleranceTime) {
        super(value, time, toleranceTime);
    }

    @Override
    public double calculateBilling() {
        return value;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentChecked(boolean paymentChecked) {
        this.paymentChecked = paymentChecked;
    }

    public boolean isPaymentChecked() {
        return paymentChecked;
    }
}
