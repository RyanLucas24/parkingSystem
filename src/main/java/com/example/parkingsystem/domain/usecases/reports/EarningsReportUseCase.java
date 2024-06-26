package com.example.parkingsystem.domain.usecases.reports;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.usecases.client.ClientDAO;
import com.example.parkingsystem.domain.usecases.payment.PaymentDAO;

import java.util.List;
import java.util.Optional;

public class EarningsReportUseCase {
    PaymentDAO paymentDAO;

    private String relatorioDePagamento() {
        paymentDAO.readAll();
        return "";
    }
}


//O sistema deve permitir que o gerente selecione um determinado período de
// tempo e deve ser gerado uma visualização de quanto foi o valor adquirido
// e quantas visitas foram realizadas por dia nesse período.