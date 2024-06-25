package com.example.parkingsystem.domain.usecases.client;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.model.payment.Payment;
import com.example.parkingsystem.domain.model.payment.PaymentMethodEnum;
import com.example.parkingsystem.domain.model.service.StandardService;

import java.time.Duration;
import java.time.LocalDateTime;

public class MakePaymentUseCase {
    StandardService standardService;

    public Payment payment(Client client, PaymentMethodEnum paymentMethod) {
        standardService = new StandardService(0, client.getEntryDate());
        double value = standardService.calculateBilling(client.getEntryDate());
        return new Payment(value, paymentMethod);
    }
}
