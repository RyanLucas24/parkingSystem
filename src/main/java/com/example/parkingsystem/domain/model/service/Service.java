package com.example.parkingsystem.domain.model.service;

public abstract class Service {
    double value;
    double time;
    double toleranceTime;

    public abstract double calculateBilling();
}
