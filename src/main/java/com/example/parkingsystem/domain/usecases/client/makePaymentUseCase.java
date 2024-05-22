package com.example.parkingsystem.domain.usecases.client;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.usecases.payment.PaymentDAO;

import java.util.Optional;


public class makePaymentUseCase{
    private PaymentDAO paymentDAO;
    private ClientDAO clientDAO;

    public makePaymentUseCase(PaymentDAO paymentDAO, ClientDAO clientDAO) {
        this.paymentDAO = paymentDAO;
        this.clientDAO = clientDAO;
    }
}
