package com.example.parkingsystem.domain.model.service;

import com.example.parkingsystem.domain.model.client.Client;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class StandardService extends Service{
    private final double additionalValue;

    @Override
    public double price(){
        return 0.3;
    }

    @Override
    public double calculateBilling(LocalDateTime dataInicial) {
        double minutosPermanecidos = calcularDuracao(dataInicial);
        return (minutosPermanecidos * price()) + additionalValue;
    }

    public StandardService(double additionalValue, LocalDateTime checkIn) {
        this.additionalValue = additionalValue;
        LocalDateTime checkOut = LocalDateTime.now();
    }

    private double calcularDuracao(LocalDateTime dataInicial) {
        LocalDateTime dataFinal = LocalDateTime.now();
        Duration d = Duration.between(dataInicial, dataFinal);
        return d.toMinutes();
    }
}
