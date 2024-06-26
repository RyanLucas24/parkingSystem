package com.example.parkingsystem.domain.model.parking;

import com.example.parkingsystem.domain.usecases.utils.UnavailableParkingSpaceRequestException;

public class Parking {

    private int id;
    private int availableStandardParkingSpace;
    private int availableMonthlyParkingSpace;
    private final int parkingSpace;
    private int standardParkingSpace;
    private int monthlyParkingSpace;

    public Parking(int parkingSpace) {
        this.parkingSpace = parkingSpace;
        standardParkingSpace = parkingSpace;
        availableStandardParkingSpace = parkingSpace;
        monthlyParkingSpace = 0;
        availableMonthlyParkingSpace = 0;
    }

    public Parking(int availableStandardParkingSpace,
                   int parkingSpace,
                   int availableMonthlyParkingSpace,
                   int standardParkingSpace,
                   int monthlyParkingSpace,
                   int id) {
        this.availableStandardParkingSpace = availableStandardParkingSpace;
        this.availableMonthlyParkingSpace = availableMonthlyParkingSpace;
        this.parkingSpace = parkingSpace;
        this.standardParkingSpace = standardParkingSpace;
        this.monthlyParkingSpace = monthlyParkingSpace;
        this.id = id;
    }

    public boolean hasAnOccupiedStandardParkingSpace() {
        return availableStandardParkingSpace != standardParkingSpace;
    }

    public boolean hasAnOccupiedMonthlyParkingSpace() {
        return availableMonthlyParkingSpace != monthlyParkingSpace;
    }

    public boolean hasAvailableStandardParkingSpace() {
        return availableStandardParkingSpace > 0;
    }

    public boolean hasAvailableMonthlyParkingSpace() {
        return availableMonthlyParkingSpace > 0;
    }

    public int getAvailableStandardParkingSpace() {
        return availableStandardParkingSpace;
    }

    public int getAvailableMonthlyParkingSpace() {
        return availableMonthlyParkingSpace;
    }

    public int getUnavailableStandardParkingSpace() {
        return standardParkingSpace - availableStandardParkingSpace;
    }

    public int getParkingSpace() {
        return parkingSpace;
    }

    public int getUnavailableMonthlyParkingSpace() {
        return monthlyParkingSpace - availableMonthlyParkingSpace;
    }

    public int getStandardParkingSpace() {
        return standardParkingSpace;
    }

    public int getMonthlyParkingSpace() {
        return monthlyParkingSpace;
    }

    public void setAvailableStandardParkingSpace(int availableStandardParkingSpace) {
        this.availableStandardParkingSpace = availableStandardParkingSpace;
    }

    public void setAvailableMonthlyParkingSpace(int availableMonthlyParkingSpace) {
        this.availableMonthlyParkingSpace = availableMonthlyParkingSpace;
    }

    public void setStandardParkingSpace(int standardParkingSpace) {
        this.standardParkingSpace = standardParkingSpace;
    }

    public void setMonthlyParkingSpace(int monthlyParkingSpace) {
        this.monthlyParkingSpace = monthlyParkingSpace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\nVagas totais: " + parkingSpace +
                "\nVagas mensais: " + monthlyParkingSpace +
                "\nVagas mensais livres: " + availableMonthlyParkingSpace +
                "\nVagas mensais ocupadas: " + getUnavailableMonthlyParkingSpace() +
                "\nVagas padrão: " + standardParkingSpace +
                "\nVagas padrão livres: " + availableStandardParkingSpace +
                "\nVagas padrão ocupadas: " + getUnavailableStandardParkingSpace();
    }
}
