package com.example.parkingsystem.domain.model.parking;

import com.example.parkingsystem.domain.usecases.utils.UnavailableParkingSpaceRequestException;

public class Parking {
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

    public void occupyAStandardParkingSpace() {
        if (hasAvailableStandardParkingSpace()) {
            availableStandardParkingSpace -= 1;
        } else throw new UnavailableParkingSpaceRequestException("Vaga padrão indisponível!!");
    }

    public void occupyAMonthlyParkingSpace() {
        if (hasAvailableMonthlyParkingSpace()) {
            availableMonthlyParkingSpace -= 1;
        } else throw new UnavailableParkingSpaceRequestException("Vaga mensal indisponível!!");}

    public void liberateAStandardParkingSpace() {
        if (hasAnOccupiedStandardParkingSpace()) {
            availableStandardParkingSpace += 1;
        } else throw new UnavailableParkingSpaceRequestException("Total de vagas padrão liberadas alcançado!!");
    }

    public void liberateAMonthlyParkingSpace() {
        if (hasAnOccupiedMonthlyParkingSpace()) {
            availableMonthlyParkingSpace += 1;
        } else throw new UnavailableParkingSpaceRequestException("Total de vagas mensais liberadas alcançado!!");
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

    public void setMonthlyParkingSpace(int newMonthlyParkingSpace) {
        if (newMonthlyParkingSpace > monthlyParkingSpace + availableStandardParkingSpace){
            throw new UnavailableParkingSpaceRequestException(
                    "Valor está acima da quantidade de vagas disponíveis do estacionamento!");}

        int occupiedStandardParkingSpace = getUnavailableStandardParkingSpace();
        int occupiedMonthlyParkingSpace = getUnavailableMonthlyParkingSpace();

        monthlyParkingSpace = newMonthlyParkingSpace;
        standardParkingSpace = parkingSpace - monthlyParkingSpace;
        availableStandardParkingSpace = standardParkingSpace - occupiedStandardParkingSpace;
        availableMonthlyParkingSpace = monthlyParkingSpace - occupiedMonthlyParkingSpace;
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
