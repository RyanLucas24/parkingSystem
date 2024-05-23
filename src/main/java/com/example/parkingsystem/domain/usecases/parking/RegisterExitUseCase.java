package com.example.parkingsystem.domain.usecases.parking;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.usecases.client.ClientBasicInformationInputRequestValidator;
import com.example.parkingsystem.domain.usecases.client.ClientDAO;
import com.example.parkingsystem.domain.usecases.utils.Notification;
import com.example.parkingsystem.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RegisterExitUseCase {

    private ParkingDAO parkingDAO;
    private ClientDAO clientDAO;

    public RegisterExitUseCase(ParkingDAO parkingDAO, ClientDAO clientDAO) {
        this.parkingDAO = parkingDAO;
        this.clientDAO = clientDAO;
    }

    public Client getClientByCpf(String cpf){
        Optional<Client> clientOptional = clientDAO.findOne(cpf);
        if(clientOptional.isEmpty())
            throw new IllegalArgumentException("Client not found");
        Client clientFound = clientOptional.get();

        if(Objects.equals(clientFound.getTypeService(), "servico padrao")) {
            //throw new IllegalArgumentException("Monthly customer cannot leave through this method");
            // TODO: chamar calculo de valor pagamento stantard Service here

        } else if (!clientFound.isPaymentChecked())
            throw new IllegalArgumentException("Payment not made");

        return clientFound;
    }

}
