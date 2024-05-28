package com.example.parkingsystem.domain.model.service;

import com.example.parkingsystem.domain.model.client.Client;

import java.util.Date;

public class MonthlyService extends Service{
    private Date paymentDate;
    private boolean paymentChecked;

    @Override
    public double calculateBilling() {
        return value;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentChecked(boolean paymentChecked) {
        this.paymentChecked = paymentChecked;
    }

    public boolean isPaymentChecked() {
        return paymentChecked;
    }
}
