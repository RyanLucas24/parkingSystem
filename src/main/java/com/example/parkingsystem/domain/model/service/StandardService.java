package com.example.parkingsystem.domain.model.service;

import com.example.parkingsystem.domain.model.payment.Payment;

import java.time.Duration;
import java.time.LocalDateTime;

public class StandardService extends Service{
    private double additionalValue;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    public StandardService(double value, double time, double toleranceTime, double additionalValue, LocalDateTime checkOut) {
        super(value, time, toleranceTime);
        this.additionalValue = additionalValue;
        checkIn = LocalDateTime.now();
        this.checkOut = checkOut;
    }

    @Override
    public double calculateBilling() {
        return 0;
    }
}
