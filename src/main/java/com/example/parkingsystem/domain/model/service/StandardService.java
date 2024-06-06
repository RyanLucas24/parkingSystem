package com.example.parkingsystem.domain.model.service;

import com.example.parkingsystem.domain.usecases.parking.AdditionalCostUseCase;

import java.time.Duration;
import java.time.LocalDateTime;

public class StandardService extends Service{
    protected double time;
    protected double toleranceTime;
    private double additionalValue;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;


    public StandardService(double value, double time, double toleranceTime, double additionalValue, LocalDateTime checkOut) {
        super(value);
        this.time = time;
        this.toleranceTime = toleranceTime;
        this.additionalValue = additionalValue;
        checkIn = LocalDateTime.now();
        this.checkOut = checkOut;
    }

    @Override
    public double calculateBilling() {
        Duration duration = Duration.between(checkIn, checkOut);
        double hours = duration.toHours();
        double cost = hours * getValue();

        AdditionalCostUseCase additionalCostUseCase = new AdditionalCostUseCase();

        cost += additionalCostUseCase.calculateAdditionalCost(additionalValue, hours, toleranceTime);

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

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getToleranceTime() {
        return toleranceTime;
    }

    public void setToleranceTime(double toleranceTime) {
        this.toleranceTime = toleranceTime;
    }
}
