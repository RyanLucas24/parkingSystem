package com.example.parkingsystem.domain.usecases.payment;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.model.payment.Payment;
import com.example.parkingsystem.domain.model.payment.PaymentMethodEnum;
import com.example.parkingsystem.domain.model.service.MonthlyService;
import com.example.parkingsystem.domain.model.service.StandardService;
import com.example.parkingsystem.domain.model.service.Service;
import com.example.parkingsystem.domain.usecases.client.ClientDAO;
import com.example.parkingsystem.domain.usecases.service.MonthlyServiceDAO;
import com.example.parkingsystem.domain.usecases.service.StandardServiceDAO;

public class MakePaymentUseCase {
    StandardService standardService;
    MonthlyService monthlyService;
    private final MonthlyServiceDAO monthlyServiceDAO;
    private final PaymentDAO paymentDAO;
    private final ClientDAO clientDAO;

    public MakePaymentUseCase(PaymentDAO paymentDAO, ClientDAO clientDAO, MonthlyServiceDAO monthlyServiceDAO){
        this.paymentDAO = paymentDAO;
        this.monthlyServiceDAO = monthlyServiceDAO;
        this.clientDAO = clientDAO;
    }

    public Payment payment(Client client, PaymentMethodEnum paymentMethod) {
        standardService = new StandardService(10, 0,client.getEntryDate());
        double value = standardService.calculateBilling();
        return new Payment(value, paymentMethod);
    }

    public Payment monthlyPayment(Client client, PaymentMethodEnum paymentMethod) {
        monthlyService = new MonthlyService(client.getEntryDate(), 0);
        double value = monthlyService.calculateBilling();
        return new Payment(value, paymentMethod);

    public boolean makePayment(Client client, PaymentMethodEnum paymentMethod) {
        Service service = client.getService();
        Payment payment = new Payment(service.calculateBilling(), paymentMethod);
        paymentDAO.create(payment);

        if(service instanceof MonthlyService){
            ((MonthlyService) service).setPaymentChecked(true);
            monthlyServiceDAO.update(service);
            clientDAO.update(client);
        }
        return true;
    }
}
