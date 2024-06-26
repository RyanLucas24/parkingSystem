package com.example.parkingsystem.domain.usecases.parking;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.model.parking.Parking;
import com.example.parkingsystem.domain.model.payment.PaymentMethodEnum;
import com.example.parkingsystem.domain.model.service.MonthlyService;
import com.example.parkingsystem.domain.model.service.Service;
import com.example.parkingsystem.domain.model.service.StandardService;
import com.example.parkingsystem.domain.usecases.client.ClientBasicInformationInputRequestValidator;
import com.example.parkingsystem.domain.usecases.client.ClientCompleteInformationInputRequestValidator;
import com.example.parkingsystem.domain.usecases.client.ClientDAO;
import com.example.parkingsystem.domain.usecases.payment.MakePaymentUseCase;
import com.example.parkingsystem.domain.usecases.service.MonthlyServiceDAO;
import com.example.parkingsystem.domain.usecases.service.StandardServiceDAO;
import com.example.parkingsystem.domain.usecases.utils.EntityNotFoundException;
import com.example.parkingsystem.domain.usecases.utils.Notification;
import com.example.parkingsystem.domain.usecases.utils.Validator;

import java.util.Optional;

public class RegisterEntryUseCase {
    private final ClientDAO clientDAO;
    private final StandardServiceDAO standardServiceDAO;
    private final MonthlyServiceDAO monthlyServiceDAO;

    private final ParkingDAO parkingDAO;

    public RegisterEntryUseCase(ClientDAO clientDAO, StandardServiceDAO standardServiceDAO,
                                ParkingDAO parkingDAO, MonthlyServiceDAO monthlyServiceDAO) {
        this.clientDAO = clientDAO;
        this.standardServiceDAO = standardServiceDAO;
        this.monthlyServiceDAO = monthlyServiceDAO;
        this.parkingDAO = parkingDAO;
    }

    public Client insertClientBasicInformation(Client client) {
        Validator<Client> validator = new ClientBasicInformationInputRequestValidator();
        Notification notification = validator.validate(client);
        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Optional<Client> clientOptional = clientDAO.findOne(client.getCpf());

        return clientOptional.orElseGet(() -> clientDAO.create(client));

    }

    public boolean chooseAMonthlyService(Client client, Service service) {
        client.setService(service);
        Validator<Client> validator = new ClientCompleteInformationInputRequestValidator();
        Notification notification = validator.validate(client);
        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Optional<Client> clientOptional = clientDAO.findOne(client.getCpf());

        if (clientOptional.isPresent()) {
            monthlyServiceDAO.create(service);
            return clientDAO.update(client);
        } else throw new EntityNotFoundException("Cliente não existente com o CPF: " + client.getCpf());
    }

    public boolean chooseAStandardService(Client client, Service service) {
        client.setService(service);
        Validator<Client> validator = new ClientCompleteInformationInputRequestValidator();
        Notification notification = validator.validate(client);
        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Optional<Client> clientOptional = clientDAO.findOne(client.getCpf());

        if (clientOptional.isPresent()) {
            standardServiceDAO.create(service);
            return clientDAO.update(client);
        } else throw new EntityNotFoundException("Cliente não existente com o CPF: " + client.getCpf());
    }


    private boolean isClientReadyToEnter(Service service) {
        if (service instanceof StandardService)
            return true;
        return ((MonthlyService) service).isPaymentChecked();
    }

    public void liberateAccess(Client client, Parking parking, MakePaymentUseCase makePaymentUseCase,
                               ManageParkingSpaceUseCase manageParkingSpaceUseCase) {
        Optional<Client> clientOptional = clientDAO.findOne(client.getCpf());
        if (clientOptional.isEmpty()) {
            throw new EntityNotFoundException("Cliente não encontrado");
        }
        if (!isClientReadyToEnter(client.getService()))
            makePaymentUseCase.makePayment(client, PaymentMethodEnum.PIX);
        manageParkingSpaceUseCase.occupyAMonthlyParkingSpace(parking);
        parkingDAO.update(parking);
        System.out.println("Acesso Liberado Com Sucesso!!");
    }
}
