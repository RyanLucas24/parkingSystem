package com.example.parkingsystem.domain.usecases.client;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.usecases.utils.Notification;
import com.example.parkingsystem.domain.usecases.utils.Validator;

public class ClientBasicInformationInputRequestValidator extends Validator<Client> {
    @Override
    public Notification validate(Client client) {
        Notification notification = new Notification();
        if (client == null) {
            notification.addError("Cliente está nulo!!");
            return notification;
        }
        if (nullOrEmpty(client.getCpf())) {
            notification.addError("CPF está nulo ou vazio!!");
        }
        if (nullOrEmpty(client.getVehiclesPlate())){
            notification.addError("Placas do Veículo estão nulas ou vazias!!");
        }
        return notification;
    }
}
