package com.example.parkingsystem.domain.usecases.payment;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.model.payment.Payment;
import com.example.parkingsystem.domain.model.payment.PaymentMethodEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

public class MakePaymentUseCaseTest {

    private final Client client = new Client("123.123.123-12",
            "A2BS2");
    private final MakePaymentUseCase makePaymentUseCase = new MakePaymentUseCase();
    double pricePerMinute = 0.2;
    int timeSpentParked = 30;

    @Test
    @DisplayName("Should return null when client does not have entry time")
    void shouldReturnNullWhenClientDoesNotHaveEntryTime() {
        Assertions.assertNull(makePaymentUseCase.makePayment(client, PaymentMethodEnum.CARTAO));
    }

    @Test
    @DisplayName("Should return the right amount of minutes that the client spent")
    void shouldReturnTheRightAmountOfMinutesWhenClientSpent() {
        client.setEntryDate(LocalDateTime.now().minusMinutes(timeSpentParked));
        Payment payment = makePaymentUseCase.makePayment(client, PaymentMethodEnum.CARTAO);
        Assertions.assertEquals(timeSpentParked*pricePerMinute, payment.getValue());
    }

    @Test
    @DisplayName("Should correctly creat a payement instance")
    void shouldCorrectlyCreatePaymentInstance() {
        client.setEntryDate(LocalDateTime.now().minusMinutes(timeSpentParked));
        Payment payment = makePaymentUseCase.makePayment(client, PaymentMethodEnum.DINHEIRO);
        Assertions.assertEquals(timeSpentParked*pricePerMinute, payment.getValue());
        Assertions.assertEquals(PaymentMethodEnum.DINHEIRO, payment.getPaymentMethod());
    }
}
