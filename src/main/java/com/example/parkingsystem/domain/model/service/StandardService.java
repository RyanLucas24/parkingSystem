package com.example.parkingsystem.domain.model.service;

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
        Duration duration = Duration.between(checkIn, checkOut);
        double hours = duration.toHours();
        double cost = hours * getValue();

        if (hours > getTime() + getToleranceTime()) {
            cost += additionalValue;
        }

        return cost;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public double getAdditionalValue() {
        return additionalValue;
    }

    public void setAdditionalValue(double additionalValue) {
        this.additionalValue = additionalValue;
    }


}
