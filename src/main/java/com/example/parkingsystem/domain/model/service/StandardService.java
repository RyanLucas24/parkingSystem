package com.example.parkingsystem.domain.model.service;

import java.time.Duration;
import java.time.LocalDateTime;

public class StandardService extends Service{
    private double additionalValue;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    @Override
    public double getValue() {
        return 1; // preco por minuto
    }

    public StandardService(double toleranceTime, double additionalValue, LocalDateTime checkIn) {
        super(toleranceTime);
        this.additionalValue = additionalValue;
        this.checkOut = LocalDateTime.now();
        this.checkIn = checkIn;
    }

    @Override
    public double calculateBilling() {
        double minutosPermanecidos = calcularDuracao(this.checkIn);
        return ((minutosPermanecidos - toleranceTime) * getValue()) + additionalValue;
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

    private double calcularDuracao(LocalDateTime dataInicial) {
        LocalDateTime dataFinal = LocalDateTime.now();
        Duration d = Duration.between(dataInicial, dataFinal);
        return d.toMinutes();
    }

}
