package com.example.parkingsystem.domain.model.service;

import com.example.parkingsystem.domain.model.client.Client;

import java.time.LocalDateTime;
import java.util.Date;

public class MonthlyService extends Service{
    private Date paymentDate;
    private boolean paymentChecked;

    @Override
    public double calculateBilling(LocalDateTime dataInicial) {
        return 0;
    }
}
