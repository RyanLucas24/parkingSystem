package com.example.parkingsystem.domain.model.service;

import com.example.parkingsystem.domain.model.client.Client;

import java.time.LocalDateTime;
import java.util.Date;

public class MonthlyService extends Service{
    private LocalDateTime paymentDate;
    private boolean paymentChecked = false;

    @Override
    public double calculateBilling(LocalDateTime dataInicial) {
        this.paymentChecked = true;
        this.paymentDate = dataInicial;
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
