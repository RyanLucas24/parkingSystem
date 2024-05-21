package com.example.parkingsystem.domain.usecases.parking;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.usecases.client.ClientBasicInformationInputRequestValidator;
import com.example.parkingsystem.domain.usecases.client.ClientDAO;
import com.example.parkingsystem.domain.usecases.utils.Notification;
import com.example.parkingsystem.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class RegisterEntryUseCase{
    private ParkingDAO parkingDAO;
    private ClientDAO clientDAO;

    public RegisterEntryUseCase(ParkingDAO parkingDAO, ClientDAO clientDAO) {
        this.parkingDAO = parkingDAO;
        this.clientDAO = clientDAO;
    }

    public Client insertClientBasicInformation(Client client){
        Validator<Client> validator = new ClientBasicInformationInputRequestValidator();
        Notification notification = validator.validate(client);
        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Optional<Client> clientOptional = clientDAO.findOne(client.getCpf());

        if(clientOptional.isPresent()){
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






}
