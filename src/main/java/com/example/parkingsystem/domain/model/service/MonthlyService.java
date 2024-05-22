package com.example.parkingsystem.domain.model.service;

import com.example.parkingsystem.domain.model.client.Client;

import java.util.Date;

public class MonthlyService extends Service{
    private Date paymentDate;
    private boolean paymentChecked;

    @Override
    public double calculateBilling() {
        return 0;
    }
}
