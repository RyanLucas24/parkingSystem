package com.example.parkingsystem.domain.usecases.client;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.model.payment.*;
import com.example.parkingsystem.domain.usecases.payment.PaymentDAO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;


public class makePaymentUseCase{
    private PaymentDAO paymentDAO;
    private ClientDAO clientDAO;

    public makePaymentUseCase(PaymentDAO paymentDAO, ClientDAO clientDAO) {
        this.paymentDAO = paymentDAO;
        this.clientDAO = clientDAO;
    }

    public long calcularDuracao(LocalDateTime dataInicial) {
        LocalDateTime dataFinal = LocalDateTime.now();
        Duration d = Duration.between(dataInicial, dataFinal);
        return d.toMinutes();
    }
}
