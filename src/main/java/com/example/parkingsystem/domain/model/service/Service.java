package com.example.parkingsystem.domain.model.service;

import com.example.parkingsystem.domain.model.client.Client;

public abstract class Service {
    protected double value;
    protected double time;
    protected double toleranceTime;

    public abstract double calculateBilling();

    public double getValue() {
        return value;
    }

    public double getTime() {
        return time;
    }

    public double getToleranceTime() {
        return toleranceTime;
    }
}
