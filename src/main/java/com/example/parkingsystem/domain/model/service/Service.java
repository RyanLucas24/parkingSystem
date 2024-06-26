package com.example.parkingsystem.domain.model.service;

import com.example.parkingsystem.domain.model.client.Client;

import java.time.LocalDateTime;

public abstract class Service {

    public abstract double calculateBilling(LocalDateTime dataInicial);

    public double price() {
        return 0;
    }
}
