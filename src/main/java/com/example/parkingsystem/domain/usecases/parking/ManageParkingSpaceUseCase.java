package com.example.parkingsystem.domain.usecases.parking;

import com.example.parkingsystem.domain.model.parking.Parking;
import com.example.parkingsystem.domain.usecases.utils.EntityNotFoundException;
import com.example.parkingsystem.domain.usecases.utils.UnavailableParkingSpaceRequestException;

import java.util.Optional;

public class ManageParkingSpaceUseCase {
    private ParkingDAO parkingDAO;

    public ManageParkingSpaceUseCase(ParkingDAO parkingDAO) {
        this.parkingDAO = parkingDAO;
    }

    public void setMonthlyParkingSpaceUseCase(Parking parking, int newMonthlyParkingSpace) {
        Optional<Parking> parkingOptional = parkingDAO.findOne(parking.getId());
        if (parkingOptional.isEmpty())
            throw new EntityNotFoundException("Estacionamento não encontrado no sistema.");
        Parking parkingFound = parkingOptional.get();

        if (hasEnoughParkingSpaceAvailable(parkingFound, newMonthlyParkingSpace)) {
            throw new UnavailableParkingSpaceRequestException(
                    "Valor está acima da quantidade de vagas disponíveis do estacionamento!");
        }

        updateParkingSpace(parkingFound, newMonthlyParkingSpace);
    }

    public void occupyAStandardParkingSpace(Parking parking) {
        if (parking.hasAvailableStandardParkingSpace()) {
            parking.setAvailableStandardParkingSpace(decreaseParkingSpace(parking.getAvailableStandardParkingSpace()));
        } else throw new UnavailableParkingSpaceRequestException("Vaga padrão indisponível!!");
    }

    public void occupyAMonthlyParkingSpace(Parking parking) {
        if (parking.hasAvailableMonthlyParkingSpace())
            parking.setMonthlyParkingSpace(decreaseParkingSpace(parking.getAvailableMonthlyParkingSpace()));

         else throw new UnavailableParkingSpaceRequestException("Vaga mensal indisponível!!");
    }

    public void liberateAStandardParkingSpace(Parking parking) {
        if (parking.hasAnOccupiedStandardParkingSpace()) {
            parking.setAvailableStandardParkingSpace(addParkingSpace(parking.getAvailableStandardParkingSpace()));
        } else throw new UnavailableParkingSpaceRequestException("Total de vagas padrão liberadas alcançado!!");
    }

    public void liberateAMonthlyParkingSpace(Parking parking) {
        if (parking.hasAnOccupiedMonthlyParkingSpace()) {
            parking.setAvailableMonthlyParkingSpace(addParkingSpace(parking.getAvailableMonthlyParkingSpace()));
        } else throw new UnavailableParkingSpaceRequestException("Total de vagas mensais liberadas alcançado!!");
    }

    private boolean hasEnoughParkingSpaceAvailable(Parking parking, int newMonthlyParkingSpace) {
        int monthlyParkingSpace = parking.getMonthlyParkingSpace();
        int availableStandardParkingSpace = parking.getAvailableStandardParkingSpace();
        return newMonthlyParkingSpace < monthlyParkingSpace + availableStandardParkingSpace;
    }

    public int addParkingSpace(int actualParkingSpace) {
        return actualParkingSpace + 1;
    }

    public int decreaseParkingSpace(int actualParkingSpace) {
        return actualParkingSpace - 1;
    }

    private void updateParkingSpace(Parking parking, int newMonthlyParkingSpace) {
        int occupiedStandardParkingSpace = parking.getUnavailableStandardParkingSpace();
        int occupiedMonthlyParkingSpace = parking.getUnavailableMonthlyParkingSpace();

        parking.setMonthlyParkingSpace(newMonthlyParkingSpace);

        int standardParkingSpace = parking.getParkingSpace() - parking.getMonthlyParkingSpace();
        parking.setStandardParkingSpace(standardParkingSpace);

        int availableStandardParkingSpace = standardParkingSpace - occupiedStandardParkingSpace;
        parking.setAvailableStandardParkingSpace(availableStandardParkingSpace);

        int availableMonthlyParkingSpace = parking.getMonthlyParkingSpace() - occupiedMonthlyParkingSpace;
        parking.setAvailableMonthlyParkingSpace(availableMonthlyParkingSpace);

        parkingDAO.update(parking);
    }
}
