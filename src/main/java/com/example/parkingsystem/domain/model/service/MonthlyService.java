package com.example.parkingsystem.domain.model.service;

import java.time.Duration;
import java.time.LocalDateTime;

public class MonthlyService extends Service{
    private LocalDateTime paymentDate;
    private boolean paymentChecked;
  
    public MonthlyService(double value) {
        super(value);
    }

    public MonthlyService(int id, double value, LocalDateTime paymentDate, boolean paymentChecked) {
        super(id, value);
        this.paymentDate = paymentDate;
        this.paymentChecked = paymentChecked;
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

    public boolean isPaymentChecked(){
        if(paymentDate == null){
            return false;
        }
        Duration between = Duration.between(paymentDate, LocalDateTime.now());
        if(between.toDays() > 30)
            paymentChecked = false;
        return paymentChecked;
    }

}
