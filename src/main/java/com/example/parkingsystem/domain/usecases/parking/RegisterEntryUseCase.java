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
import com.example.parkingsystem.domain.usecases.service.ServiceDAO;
import com.example.parkingsystem.domain.usecases.utils.EntityNotFoundException;
import com.example.parkingsystem.domain.usecases.utils.Notification;
import com.example.parkingsystem.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class RegisterEntryUseCase {
    private final ClientDAO clientDAO;
    private final ServiceDAO serviceDAO;
    private final ParkingDAO parkingDAO;

    public RegisterEntryUseCase(ClientDAO clientDAO, ServiceDAO serviceDAO, ParkingDAO parkingDAO) {
        this.clientDAO = clientDAO;
        this.serviceDAO = serviceDAO;
        this.parkingDAO = parkingDAO;
    }

    public Client insertClientBasicInformation(Client client) {
        Validator<Client> validator = new ClientBasicInformationInputRequestValidator();
        Notification notification = validator.validate(client);
        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Optional<Client> clientOptional = clientDAO.findOne(client.getCpf());

        if (clientOptional.isPresent()) {
            Client clientFound = clientOptional.get();
            List<String> vehiclesPlate = clientFound.getVehiclesPlate();

            List<String> differencesCollected = client.getVehiclesPlate()
                    .stream()
                    .filter(vp -> !vehiclesPlate.contains(vp))
                    .toList();

            clientFound.getVehiclesPlate().addAll(differencesCollected);
            return clientFound;
        }

        return clientDAO.create(client);
    }

    public boolean chooseAService(Client client, Service service) {
        client.setService(service);
        Validator<Client> validator = new ClientCompleteInformationInputRequestValidator();
        Notification notification = validator.validate(client);
        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Optional<Client> clientOptional = clientDAO.findOne(client.getCpf());
        if (clientOptional.isPresent()) {
            serviceDAO.create(service);
            return clientDAO.update(client);
        } else throw new EntityNotFoundException("Cliente não existente com o CPF: " + client.getCpf());
    }

    private boolean isClientReadyToEnter(Service service) {
        if (service instanceof StandardService)
            return true;
        return ((MonthlyService) service).isPaymentChecked();
    }

    public void liberateAccess(Client client, Parking parking, MakePaymentUseCase useCase) {
        if (!isClientReadyToEnter(client.getService()))
            useCase.makePayment(client, PaymentMethodEnum.PIX);

        parking.occupyAMonthlyParkingSpace();
        parkingDAO.update(parking);
    }
}
