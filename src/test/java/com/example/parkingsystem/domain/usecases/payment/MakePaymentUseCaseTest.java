package com.example.parkingsystem.domain.usecases.payment;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.model.payment.Payment;
import com.example.parkingsystem.domain.model.payment.PaymentMethodEnum;
import com.example.parkingsystem.domain.usecases.client.MakePaymentUseCase;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class MakePaymentUseCaseTest {
    private final com.example.parkingsystem.domain.usecases.client.MakePaymentUseCase makePayment = new MakePaymentUseCase();
    private final Client client = new Client("123", "a123");

    @Test
    @DisplayName("Should create a new payment")
    void shouldCreateNewPayment() {
        client.setEntryDate(LocalDateTime.parse("2024-05-24T08:00:00.000"));
        Payment payment = makePayment.payment(client, PaymentMethodEnum.CARTAO);
        Assertions.assertNotNull(payment);
    }

    @Test
    @DisplayName("Should bill correctly")
    void shouldBillCorrectly() {
        LocalDateTime tenMinuteEntryDate = LocalDateTime.now().minusMinutes(60);
        client.setEntryDate(tenMinuteEntryDate);
        Payment payment = makePayment.payment(client, PaymentMethodEnum.CARTAO);
        Assertions.assertEquals(18,payment.getValue());
    }

    @Test
    @DisplayName("Should correctly save payment method")
    void shouldSavePaymentMethod() {
        LocalDateTime tenMinuteEntryDate = LocalDateTime.now().minusMinutes(90);
        client.setEntryDate(tenMinuteEntryDate);
        Payment payment = makePayment.payment(client, PaymentMethodEnum.CARTAO);
        Assertions.assertEquals( PaymentMethodEnum.CARTAO,payment.getPaymentMethod());
    }

    @Test
    @DisplayName("Should correctly bill monthly clients")
    void shouldBillMonthlyClients() {
        Client monthlyClient = new Client(client, "Teste", "1699273342", "example@example.com","São Carlos");
        Payment payment = makePayment.monthlyPayment(client, PaymentMethodEnum.CARTAO);
        Assertions.assertEquals(PaymentMethodEnum.CARTAO,payment.getPaymentMethod());
    }
}
