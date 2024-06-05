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

    @Test
    @DisplayName("Should return null when client does not have entry time")
    void shouldReturnNullWhenClientDoesNotHaveEntryTime() {
        MakePaymentUseCase makePaymentUseCase = new MakePaymentUseCase();
        Assertions.assertNull(makePaymentUseCase.makePayment(client, PaymentMethodEnum.CARTAO));
    }
}
