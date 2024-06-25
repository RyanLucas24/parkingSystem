package com.example.parkingsystem.domain.model.service;

import com.example.parkingsystem.domain.model.client.Client;

import java.time.LocalDateTime;

public abstract class Service {
    private double value;
    private double time;
    private double toleranceTime;
    private Client client;

    public abstract double calculateBilling(LocalDateTime dataInicial);

    public double pricePerMinute() {
        return 0;
    }
}
