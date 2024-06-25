package com.example.parkingsystem.domain.usecases.client;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.model.payment.Payment;
import com.example.parkingsystem.domain.model.payment.PaymentMethodEnum;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDateTime;

public class MakePaymentUseCaseTest {
    @BeforeEach
    void setUp() {
        MakePaymentUseCase makePayment;

    }
    
    @Test
    @DisplayName("Should create a new payment")
    void testDisplayName() {
        Client client = new Client("123", "a123");
        MakePaymentUseCase makePayment = new MakePaymentUseCase();
        client.setEntryDate(LocalDateTime.parse("2024-05-24T08:00:00.000"));
        Payment payment = makePayment.payment(client, PaymentMethodEnum.CARTAO);
        Assertions.assertNotNull(payment);
    }

    @Test
    @DisplayName("Should create correct stay time")
    void shouldCreateCorrectStayTime() {
        Client client = new Client("123", "a123");
        MakePaymentUseCase makePayment = new MakePaymentUseCase();
        LocalDateTime tenMinuteEntryDate = LocalDateTime.now().minusMinutes(60);
        client.setEntryDate(tenMinuteEntryDate);
        Payment payment = makePayment.payment(client, PaymentMethodEnum.CARTAO);
        Assertions.assertEquals( 18,payment.getValue());
    }
}
