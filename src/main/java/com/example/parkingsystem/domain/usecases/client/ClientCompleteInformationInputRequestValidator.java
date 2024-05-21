package com.example.parkingsystem.domain.usecases.client;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.usecases.utils.Notification;
import com.example.parkingsystem.domain.usecases.utils.Validator;

public class ClientCompleteInformationInputRequestValidator extends Validator<Client> {
    @Override
    public Notification validate(Client client) {
        ClientBasicInformationInputRequestValidator validator = new ClientBasicInformationInputRequestValidator();
        Notification notification = validator.validate(client);

        if (nullOrEmpty(client.getName()))
            notification.addError("Nome do cliente está nulo ou vazio!!");
        if (nullOrEmpty(client.getEmail()))
            notification.addError("Email do cliente está nulo ou vazio!!");
        if (nullOrEmpty(client.getPhone()))
            notification.addError("Telefone do cliente está nulo ou vazio!!");
        if (nullOrEmpty(client.getAddress()))
            notification.addError("Endereço do cliente está nulo ou vazio!!");

        return notification;
    }
}
