package com.example.parkingsystem.domain.model.service;

import com.example.parkingsystem.domain.model.client.Client;

import java.util.Date;

public class StandardService extends Service{
    private double additionalValue;
    private Date checkIn;
    private Date checkOut;

    @Override
    public double calculateBilling() {
        return 0;
    }
}
