package com.example.parkingsystem.domain.model.service;

import java.time.LocalDateTime;

public class MonthlyService extends Service{
    private boolean paymentChecked = false;

    @Override
    public double calculateBilling(LocalDateTime dataInicial) {
        this.paymentChecked = true;
        return price();
    }

    public MonthlyService(boolean paymentChecked) {
        this.paymentChecked = paymentChecked;
    }

    @Override
    public double price() {
        return 30;
    }
}
