package com.example.parkingsystem.domain.usecases.client;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.model.payment.Payment;
import com.example.parkingsystem.domain.model.payment.PaymentMethodEnum;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;

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
    @DisplayName("Should bill correctly")
    void shouldBillCorrectly() {
        Client client = new Client("123", "a123");
        MakePaymentUseCase makePayment = new MakePaymentUseCase();
        LocalDateTime tenMinuteEntryDate = LocalDateTime.now().minusMinutes(60);
        client.setEntryDate(tenMinuteEntryDate);
        Payment payment = makePayment.payment(client, PaymentMethodEnum.CARTAO);
        Assertions.assertEquals( 18,payment.getValue());
    }

    @Test
    @DisplayName("Should correctly save payment method")
    void shouldSavePaymentMethod() {
        Client client = new Client("123", "a123");
        MakePaymentUseCase makePayment = new MakePaymentUseCase();
        LocalDateTime tenMinuteEntryDate = LocalDateTime.now().minusMinutes(90);
        client.setEntryDate(tenMinuteEntryDate);
        Payment payment = makePayment.payment(client, PaymentMethodEnum.CARTAO);
        Assertions.assertEquals( PaymentMethodEnum.CARTAO,payment.getPaymentMethod());
    }
}
