package com.example.parkingsystem.domain.model.service;

import com.example.parkingsystem.domain.model.client.Client;

import java.util.Date;

public class StandardService extends Service{
    private double additionalValue;
    private Date checkIn;
    private Date checkOut;
    private Client client;
    long value;

    @Override
    public double calculateBilling() {
        if("mensalista".equalsIgnoreCase(client.getTypeService())) {
            throw new IllegalArgumentException("Monthly customer cannot leave through this method");
        } else {
            long diff = checkOut.getTime() - checkIn.getTime();
            long diffHours = diff / (60 * 60 * 1000);
            return (diffHours * value) + additionalValue;
        }
    }
}
