package com.example.parkingsystem.domain.usecases.client;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.model.payment.Payment;
import com.example.parkingsystem.domain.model.payment.PaymentMethodEnum;
import com.example.parkingsystem.domain.usecases.payment.PaymentDAO;

import java.time.Duration;
import java.time.LocalDateTime;


public class MakePaymentUseCase {
    private PaymentDAO paymentDAO;
    private ClientDAO clientDAO;

    public MakePaymentUseCase(PaymentDAO paymentDAO, ClientDAO clientDAO) {
        this.paymentDAO = paymentDAO;
        this.clientDAO = clientDAO;
    }

    private double calcularDuracao(LocalDateTime dataInicial) {
        LocalDateTime dataFinal = LocalDateTime.now();
        Duration d = Duration.between(dataInicial, dataFinal);
        return d.toMinutes();
    }

    public Payment payment(Client client, PaymentMethodEnum paymentMethod) {
        double tempoEstacionado = calcularDuracao(client.getEntryDate());
        double precoPorMinuto = 0.2;
        return new Payment((tempoEstacionado*precoPorMinuto), paymentMethod);
    }
}
