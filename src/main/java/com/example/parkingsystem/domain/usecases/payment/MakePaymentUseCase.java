package com.example.parkingsystem.domain.usecases.payment;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.model.payment.Payment;
import com.example.parkingsystem.domain.model.payment.PaymentMethodEnum;
import com.example.parkingsystem.domain.model.service.MonthlyService;
import com.example.parkingsystem.domain.model.service.Service;
import com.example.parkingsystem.domain.model.service.StandardService;
import com.example.parkingsystem.domain.usecases.client.ClientDAO;
import com.example.parkingsystem.domain.usecases.service.ServiceDAO;

import java.time.Duration;
import java.time.LocalDateTime;

public class MakePaymentUseCase {
    private ServiceDAO serviceDAO;
    private PaymentDAO paymentDAO;
    private ClientDAO clientDAO;
    public MakePaymentUseCase(ServiceDAO serviceDAO, PaymentDAO paymentDAO, ClientDAO clientDAO){
        this.paymentDAO = paymentDAO;
        this.serviceDAO = serviceDAO;
        this.clientDAO = clientDAO;
    }

    public boolean makePayment(Client client, PaymentMethodEnum paymentMethod) {
        Service service = client.getService();
        Payment payment = new Payment(service.calculateBilling(), paymentMethod);
        paymentDAO.create(payment);

        if(service instanceof MonthlyService){
            ((MonthlyService) service).setPaymentChecked(true);
            serviceDAO.update(service);
            clientDAO.update(client);
        }
        return true;
    }
}
