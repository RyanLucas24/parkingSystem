package com.example.parkingsystem.domain.model.service;

public abstract class Service {

    protected int id;
    protected double value;

    public abstract double calculateBilling();

    public Service(double value) {
        this.value = value;
    }

    public Service(int id, double value) {
        this.id = id;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
