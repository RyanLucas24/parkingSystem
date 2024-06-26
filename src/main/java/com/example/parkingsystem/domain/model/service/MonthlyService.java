package com.example.parkingsystem.domain.model.service;

import java.time.LocalDateTime;

public class MonthlyService extends Service{
    private LocalDateTime paymentDate;
    private boolean paymentChecked;

    @Override
    public double getValue() {
        return 65;
    }

    private double getLateFee() {
        return 50;
    }

    public MonthlyService(LocalDateTime paymentDate, double toleranceTime) {
        super(toleranceTime);
        this.paymentChecked = false;
        this.paymentDate = paymentDate;
    }

    @Override
    public double calculateBilling() {
        double cost = getValue();
        double lateFee = getLateFee();
        if (paymentDate.plusMonths(1).isBefore(LocalDateTime.now())) {
            setPaymentChecked(false);
            return cost + lateFee;
        } else {
            setPaymentChecked(true);
            return cost;
        }
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
