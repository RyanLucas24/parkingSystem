package com.example.parkingsystem.domain.usecases.payment;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.model.payment.Payment;
import com.example.parkingsystem.domain.model.payment.PaymentMethodEnum;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class MakePaymentUseCaseTest {
    private final MakePaymentUseCase makePayment = new MakePaymentUseCase();
    private final Client client = new Client("123", "a123");

    @Test
    @DisplayName("Should create a new payment")
    void shouldCreateNewPayment() {
        client.setEntryDate(LocalDateTime.parse("2024-05-24T08:00:00.000"));
        Payment payment = makePayment.payment(client, PaymentMethodEnum.CARTAO);
        Assertions.assertNotNull(payment);
    }

    @Test
    @DisplayName("Should bill correctly regular clients")
    void shouldBillCorrectlyRegularClients() {
        LocalDateTime tenMinuteEntryDate = LocalDateTime.now().minusMinutes(20);
        client.setEntryDate(tenMinuteEntryDate);
        Payment payment = makePayment.payment(client, PaymentMethodEnum.CARTAO);
        Assertions.assertEquals(10,payment.getValue());
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
        monthlyClient.setEntryDate(LocalDateTime.now().minusDays(15));
        Payment payment = makePayment.monthlyPayment(monthlyClient, PaymentMethodEnum.CARTAO);
        Assertions.assertEquals(65,payment.getValue());
    }

    @Test
    @DisplayName("Should bill extra for late monthly clients")
    void shouldBillExtraForLateMonthlyClients() {
        Client monthlyClient = new Client(client, "Teste", "1699273342", "example@example.com","São Carlos");
        monthlyClient.setEntryDate(LocalDateTime.now().minusDays(31));
        Payment payment = makePayment.monthlyPayment(monthlyClient, PaymentMethodEnum.CARTAO);
        Assertions.assertEquals(115,payment.getValue());
    }

    @Test
    @DisplayName("Should return the correct payment method")
    void shouldReturnCorrectPaymentMethod() {
        Client monthlyClient = new Client(client, "Teste", "1699273342", "example@example.com","São Carlos");
        monthlyClient.setEntryDate(LocalDateTime.now());
        Payment payment = makePayment.monthlyPayment(monthlyClient, PaymentMethodEnum.CARTAO);
        Assertions.assertEquals(PaymentMethodEnum.CARTAO,payment.getPaymentMethod());
    }
}
