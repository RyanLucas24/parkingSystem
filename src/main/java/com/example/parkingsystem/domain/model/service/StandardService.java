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

    @Override
    public double getValue() {
        return 1; // preco por minuto
    }

    public StandardService(double toleranceTime, double additionalValue, LocalDateTime checkIn) {
        super(toleranceTime);
    public StandardService(double value, double time, double toleranceTime,
                           double additionalValue, LocalDateTime checkOut) {
        super(value);
        this.time = time;
        this.toleranceTime = toleranceTime;
        this.additionalValue = additionalValue;
        this.checkOut = LocalDateTime.now();
        this.checkIn = checkIn;
    }

    public StandardService(int id, double value, double time,
                           double toleranceTime, double additionalValue,
                           LocalDateTime checkIn, LocalDateTime checkOut) {
        super(id, value);
        this.time = time;
        this.toleranceTime = toleranceTime;
        this.additionalValue = additionalValue;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public double calculateBilling() {
        double minutosPermanecidos = calcularDuracao(this.checkIn);
        return ((minutosPermanecidos - toleranceTime) * getValue()) + additionalValue;
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

    private double calcularDuracao(LocalDateTime dataInicial) {
        LocalDateTime dataFinal = LocalDateTime.now();
        Duration d = Duration.between(dataInicial, dataFinal);
        return d.toMinutes();
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
