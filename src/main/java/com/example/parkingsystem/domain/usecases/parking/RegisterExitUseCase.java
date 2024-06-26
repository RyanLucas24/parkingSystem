package com.example.parkingsystem.domain.usecases.parking;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.model.payment.PaymentMethodEnum;
import com.example.parkingsystem.domain.model.parking.Parking;
import com.example.parkingsystem.domain.model.service.MonthlyService;
import com.example.parkingsystem.domain.model.service.Service;
import com.example.parkingsystem.domain.model.service.StandardService;
import com.example.parkingsystem.domain.usecases.client.ClientDAO;
import com.example.parkingsystem.domain.usecases.payment.MakePaymentUseCase;
import com.example.parkingsystem.domain.usecases.utils.EntityNotFoundException;
import com.example.parkingsystem.domain.usecases.utils.UnavailableParkingSpaceRequestException;

import java.util.Optional;

public class RegisterExitUseCase {
    private final ClientDAO clientDAO;
    private final ParkingDAO parkingDAO;

    public RegisterExitUseCase(ClientDAO clientDAO, ParkingDAO parkingDAO) {
        this.clientDAO = clientDAO;
        this.parkingDAO = parkingDAO;
    }

    public void registerExit(Client client, Parking parking, MakePaymentUseCase paymentUseCase, ManageParkingSpaceUseCase manageParkingSpaceUseCase) {
        Optional<Client> clientOptional = clientDAO.findOne(client.getCpf());
        if (clientOptional.isEmpty()) {
            throw new EntityNotFoundException("Cliente não encontrado");
        }

        Service clientService = client.getService();
        double totalCost = calculatePayment(clientService);

        boolean paymentSuccessful = paymentUseCase.makePayment(client, PaymentMethodEnum.PIX);

        if (paymentSuccessful) {
            try {
                if (clientService instanceof StandardService) {
                    manageParkingSpaceUseCase.liberateAStandardParkingSpace(parking);
                } else if (clientService instanceof MonthlyService) {
                    manageParkingSpaceUseCase.liberateAMonthlyParkingSpace(parking);
                }
                parkingDAO.update(parking);
                System.out.println("Saída registrada com sucesso!");
            } catch (UnavailableParkingSpaceRequestException e) {
                throw new IllegalArgumentException("Erro ao liberar vaga no estacionamento.");
            }
        } else {
            throw new IllegalArgumentException("Pagamento não foi realizado com sucesso.");
        }
    }

    private double calculatePayment(Service service) {
        if (service instanceof StandardService) {
            return 20.0; // chamar calculo
        } else if (service instanceof MonthlyService) {
            return 0.0; // cliente mensal n paga na saída
        } else {
            throw new IllegalArgumentException("Serviço inválido!");
        }
    }
}