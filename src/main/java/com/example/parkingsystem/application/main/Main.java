package com.example.parkingsystem.application.main;

import com.example.parkingsystem.application.repository.InMemoryClientDAO;
import com.example.parkingsystem.application.repository.InMemoryParkingDAO;
import com.example.parkingsystem.application.repository.InMemoryPaymentDAO;
import com.example.parkingsystem.application.repository.inMemoryServiceDAO;
import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.model.parking.Parking;
import com.example.parkingsystem.domain.model.service.MonthlyService;
import com.example.parkingsystem.domain.model.service.Service;
import com.example.parkingsystem.domain.usecases.client.ClientDAO;
import com.example.parkingsystem.domain.usecases.parking.ParkingDAO;
import com.example.parkingsystem.domain.usecases.parking.RegisterEntryUseCase;
import com.example.parkingsystem.domain.usecases.parking.RegisterExitUseCase;
import com.example.parkingsystem.domain.usecases.payment.MakePaymentUseCase;
import com.example.parkingsystem.domain.usecases.payment.PaymentDAO;
import com.example.parkingsystem.domain.usecases.service.ServiceDAO;

public class Main {
    private static RegisterEntryUseCase registerEntryUseCase;
    private static RegisterExitUseCase registerExitUseCase;
    private static MakePaymentUseCase makePaymentUseCase;

    public static void main(String[] args) {
        configureInjection();
        Parking parking = new Parking(20);
        parking.setMonthlyParkingSpace(10);
        Client client = new Client(
                "12345678910", "A123BC"
        );
        registerEntryUseCase.insertClientBasicInformation(client);

        Client clientComplete = new Client(
                client,
                "Usuario Teste",
                "16 9999-9999",
                "usuarioteste@gmail.com",
                "Rua Aleatoria, Bairro Tal - numero 123");

        Service service = new MonthlyService(50);

        registerEntryUseCase.chooseAService(clientComplete, service);
        registerEntryUseCase.liberateAccess(clientComplete, parking, makePaymentUseCase);
    }

    private static void configureInjection() {
        ClientDAO clientDAO = new InMemoryClientDAO();
        ServiceDAO serviceDAO = new inMemoryServiceDAO();
        PaymentDAO paymentDAO = new InMemoryPaymentDAO();
        ParkingDAO parkingDAO = new InMemoryParkingDAO();

        registerEntryUseCase = new RegisterEntryUseCase(clientDAO, serviceDAO, parkingDAO);
        registerExitUseCase = new RegisterExitUseCase(clientDAO);
        makePaymentUseCase =new MakePaymentUseCase(serviceDAO, paymentDAO, clientDAO);
    }
}
