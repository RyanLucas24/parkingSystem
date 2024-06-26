package com.example.parkingsystem.domain.usecases.client;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.usecases.utils.Notification;
import com.example.parkingsystem.domain.usecases.utils.Validator;

import java.util.Collection;
import java.util.List;

public class ClientBasicInformationInputRequestValidator extends Validator<Client> {
    @Override
    public Notification validate(Client client) {
        Notification notification = new Notification();
        if (client == null) {
            notification.addError("Cliente está nulo!!");
            return notification;
        }
        if (nullOrEmpty(client.getCpf()))
            notification.addError("CPF está nulo ou vazio!!");
        if(nullOrEmpty(client.getVehiclePlate()))
            notification.addError("Existem placas de veículos nulas ou vazias!!");
        return notification;
    }

    public static boolean hasANullOrEmptyElement(Collection<String> collection){
        List<String> list = collection.stream()
                .filter(Validator::nullOrEmpty)
                .toList();

        return !list.isEmpty();
    }
}
