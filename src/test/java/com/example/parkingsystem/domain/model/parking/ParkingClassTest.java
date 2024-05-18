package com.example.parkingsystem.domain.model.parking;

import com.example.parkingsystem.domain.usecases.utils.UnavailableParkingSpaceRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingClassTest {

    private final Parking parking = new Parking(20);

    @Test
    public void setMonthlyParkingSpace_ValidValue_SuccessChange(){
        int value = 10;
        int expectedStandardParkingSpace = parking.getParkingSpace() - value;

        parking.setMonthlyParkingSpace(value);

        Assertions.assertEquals(value, parking.getMonthlyParkingSpace());
        Assertions.assertEquals(expectedStandardParkingSpace, parking.getStandardParkingSpace());
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
    public void occupyAStandardParkingSpace_AvailableSpace_SuccessOccupy(){
        int expectedAvailableSpace = parking.getAvailableStandardParkingSpace() - 1;
        int expectedUnavailableSpace = parking.getUnavailableStandardParkingSpace() + 1;

        parking.occupyAStandardParkingSpace();

        Assertions.assertEquals(expectedAvailableSpace, parking.getAvailableStandardParkingSpace());
        Assertions.assertEquals(expectedUnavailableSpace, parking.getUnavailableStandardParkingSpace());
    }
}
