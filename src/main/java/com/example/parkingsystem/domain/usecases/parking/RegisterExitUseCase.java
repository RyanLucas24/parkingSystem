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
        if(clientOptional.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        Client clientFound = clientOptional.get();
        // utilizar switch case para verificar tipo de serviço seria mais adequado TODO: implementar switch case
//        if(Objects.equals(clientFound.getTypeService(), "servico padrao")) {
//            if(!clientFound.isPaymentChecked()) {
//                // TODO: chamar calculo de valor pagamento standard Service aqui
//                // TODO: liberar vaga e libera cliente
//                clientFound.setPaymentChecked(true);
//            } else {
//                throw new IllegalArgumentException("Pagamento não efetuado");
//            }
//        // fluxo alternativo 1
//        } else if (Objects.equals(clientFound.getTypeService(), "mensalista")){
//            if(clientFound.isPaymentChecked()) {
//                throw new IllegalArgumentException("Pagamento recebido");
//                // TODO: liberar vaga mensalista e libera cliente
//            } else {
//                throw new IllegalArgumentException("Pagamento não efetuado");
//            }
//        }


        switch (clientFound.getTypeService()) {
            case "standard":
                if (!clientFound.isPaymentChecked()) {
                    // Chama o cálculo de valor do pagamento para serviço standard
                    calculateStandardServicePayment(clientFound);
                    // Libera a vaga e o cliente
                    freeParkingSpot(clientFound);
                    releaseClient(clientFound);
                    clientFound.setPaymentChecked(true);
                } else {
                    throw new IllegalArgumentException("Pagamento não efetuado");
                }
                break;

            case "monthly":
                if (clientFound.isPaymentChecked()) {
                    // Libera a vaga e o cliente
                    freeParkingSpot(clientFound);
                    releaseClient(clientFound);
                } else {
                    throw new IllegalArgumentException("Pagamento não efetuado");
                }
                break;

            default:
                throw new IllegalArgumentException("Tipo de serviço inválido");
        }

        return clientFound;
    }

}
