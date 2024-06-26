package com.example.parkingsystem.domain.model.service;

public abstract class Service {
    protected double value;
    protected double time;
    protected double toleranceTime;


    public abstract double calculateBilling();

    public Service(double toleranceTime) {
        this.toleranceTime = toleranceTime;
    }

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
