package com.example.parkingsystem.domain.usecases.payment;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.model.payment.Payment;
import com.example.parkingsystem.domain.model.payment.PaymentMethodEnum;

import java.time.Duration;
import java.time.LocalDateTime;

public class MakePaymentUseCase {

    public Payment makePayment (Client client, PaymentMethodEnum paymentMethod) {
        if (client.getEntryDate() == null) return null;
        double pricePerMinute = 0.2;
        double totalPaymentValue = pricePerMinute * calcularDuracao(client);
        return new Payment(totalPaymentValue, paymentMethod);
    }

    private double calcularDuracao(Client client) {
        LocalDateTime dataInicial = client.getEntryDate();
        LocalDateTime dataFinal = LocalDateTime.now();
        Duration d = Duration.between(dataInicial, dataFinal);
        return d.toMinutes();
    }
}
