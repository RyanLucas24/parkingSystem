package com.example.parkingsystem.domain.model.parking;

import com.example.parkingsystem.domain.usecases.utils.UnavailableParkingSpaceRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingClassTest {

    private final Parking parking = new Parking(20);

    @Test
    public void setMonthlyParkingSpace_ValidValue_SuccessfulChange(){
        int value = parking.getParkingSpace() / 2;
        int expectedStandardParkingSpace = parking.getParkingSpace() - value;

        parking.setMonthlyParkingSpace(value);

        Assertions.assertEquals(value, parking.getMonthlyParkingSpace());
        Assertions.assertEquals(value, parking.getAvailableMonthlyParkingSpace());
        Assertions.assertEquals(expectedStandardParkingSpace, parking.getStandardParkingSpace());
        Assertions.assertEquals(expectedStandardParkingSpace, parking.getAvailableStandardParkingSpace());
    }

    @Test
    public void setMonthlyParkingSpace_InvalidValue_ThrowUnavailableParkingSpaceRequestException(){
        int value = parking.getParkingSpace() + 1;

        Exception exception = Assertions.assertThrows(
                UnavailableParkingSpaceRequestException.class,
                () -> parking.setMonthlyParkingSpace(value)
        );

        Assertions.assertEquals(
                "Valor está acima da quantidade de vagas disponíveis do estacionamento!",
                exception.getMessage()
        );

        Assertions.assertNotEquals(value, parking.getMonthlyParkingSpace());
    }

    @Test
    public void occupyAStandardParkingSpace_AvailableSpace_SuccessfulOccupy(){
        int expectedAvailableSpace = parking.getAvailableStandardParkingSpace() - 1;
        int expectedUnavailableSpace = parking.getUnavailableStandardParkingSpace() + 1;

        parking.occupyAStandardParkingSpace();

        Assertions.assertEquals(expectedAvailableSpace, parking.getAvailableStandardParkingSpace());
        Assertions.assertEquals(expectedUnavailableSpace, parking.getUnavailableStandardParkingSpace());
    }

    @Test
    public void occupyAStandardParkingSpace_UnavailableSpace_ThrowUnavailableParkingSpaceRequestException(){
        parking.setMonthlyParkingSpace(parking.getParkingSpace());

        Exception exception = Assertions.assertThrows(
                UnavailableParkingSpaceRequestException.class,
                parking::occupyAStandardParkingSpace
        );

        Assertions.assertEquals("Vaga padrão indisponível!!", exception.getMessage());
    }

    @Test
    public void occupyAMonthlyParkingSpace_AvailableSpace_SuccessOccupy(){
        parking.setMonthlyParkingSpace(1);
        int expectedAvailableSpace = parking.getAvailableMonthlyParkingSpace() - 1;
        int expectedUnavailableSpace = parking.getUnavailableMonthlyParkingSpace() + 1;

        parking.occupyAMonthlyParkingSpace();

        Assertions.assertEquals(expectedAvailableSpace, parking.getAvailableMonthlyParkingSpace());
        Assertions.assertEquals(expectedUnavailableSpace, parking.getUnavailableMonthlyParkingSpace());
    }

    @Test
    public void occupyAMonthlyParkingSpace_UnavailableSpace_ThrowUnavailableParkingSpaceRequestException(){
        Exception exception = Assertions.assertThrows(
                UnavailableParkingSpaceRequestException.class,
                parking::occupyAMonthlyParkingSpace
        );

        Assertions.assertEquals("Vaga mensal indisponível!!", exception.getMessage());
    }

    @Test
    public void liberateAStandardParkingSpace_OccupiedSpace_SuccessfulLiberation(){
        int expectedFreeSpace = parking.getAvailableStandardParkingSpace();
        int expectedOccupiedSpace = parking.getUnavailableStandardParkingSpace() + 1;

        parking.occupyAStandardParkingSpace();
        Assertions.assertEquals(expectedOccupiedSpace, parking.getUnavailableStandardParkingSpace());

        parking.liberateAStandardParkingSpace();
        Assertions.assertEquals(expectedFreeSpace, parking.getAvailableStandardParkingSpace());
    }

    @Test
    public void liberateAStandardParkingSpace_MaxAvailableSpace_ThrowUnavailableParkingSpaceRequestException(){
        Exception exception = Assertions.assertThrows(
                UnavailableParkingSpaceRequestException.class,
                parking::liberateAStandardParkingSpace
        );

        Assertions.assertEquals("Total de vagas padrão liberadas alcançado!!", exception.getMessage());
    }
    @Test
    public void liberateAMonthlyParkingSpace_OccupiedSpace_SuccessfulLiberation(){
        parking.setMonthlyParkingSpace(1);

        int expectedFreeSpace = parking.getAvailableMonthlyParkingSpace();
        int expectedOccupiedSpace = parking.getUnavailableMonthlyParkingSpace() + 1;

        parking.occupyAMonthlyParkingSpace();
        Assertions.assertEquals(expectedOccupiedSpace, parking.getUnavailableMonthlyParkingSpace());

        parking.liberateAMonthlyParkingSpace();
        Assertions.assertEquals(expectedFreeSpace, parking.getAvailableMonthlyParkingSpace());
    }

    @Test
    public void liberateAMonthlyParkingSpace_MaxAvailableSpace_ThrowUnavailableParkingSpaceRequestException(){
        Exception exception = Assertions.assertThrows(
                UnavailableParkingSpaceRequestException.class,
                parking::liberateAMonthlyParkingSpace
        );

        Assertions.assertEquals("Total de vagas mensais liberadas alcançado!!", exception.getMessage());
    }
}
