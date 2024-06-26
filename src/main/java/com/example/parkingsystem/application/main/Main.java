package com.example.parkingsystem.application.main;

import com.example.parkingsystem.application.repository.inmemory.InMemoryClientDAO;
import com.example.parkingsystem.application.repository.inmemory.InMemoryParkingDAO;
import com.example.parkingsystem.application.repository.inmemory.InMemoryPaymentDAO;
import com.example.parkingsystem.application.repository.sqlite.DatabaseBuilder;
import com.example.parkingsystem.application.repository.sqlite.SqliteMonthlyServiceDAO;
import com.example.parkingsystem.application.repository.sqlite.SqliteStandardServiceDAO;
import com.example.parkingsystem.domain.usecases.client.ClientDAO;
import com.example.parkingsystem.domain.usecases.parking.ManageParkingSpaceUseCase;
import com.example.parkingsystem.domain.usecases.parking.ParkingDAO;
import com.example.parkingsystem.domain.usecases.parking.RegisterEntryUseCase;
import com.example.parkingsystem.domain.usecases.parking.RegisterExitUseCase;
import com.example.parkingsystem.domain.usecases.payment.MakePaymentUseCase;
import com.example.parkingsystem.domain.usecases.payment.PaymentDAO;
import com.example.parkingsystem.domain.usecases.service.MonthlyServiceDAO;
import com.example.parkingsystem.domain.usecases.service.StandardServiceDAO;

public class Main {
    private static RegisterEntryUseCase registerEntryUseCase;
    private static RegisterExitUseCase registerExitUseCase;
    private static MakePaymentUseCase makePaymentUseCase;
    private static ManageParkingSpaceUseCase manageParkingSpaceUseCase;

    public static void main(String[] args) {
        configureInjection();
        setUpDatabase();
    }

    private static void setUpDatabase() {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }

    private static void configureInjection() {
        ClientDAO clientDAO = new InMemoryClientDAO();
        StandardServiceDAO standardServiceDAO = new SqliteStandardServiceDAO();
        PaymentDAO paymentDAO = new InMemoryPaymentDAO();
        ParkingDAO parkingDAO = new InMemoryParkingDAO();
        MonthlyServiceDAO monthlyServiceDAO = new SqliteMonthlyServiceDAO();

        registerEntryUseCase = new RegisterEntryUseCase(clientDAO, standardServiceDAO, parkingDAO, monthlyServiceDAO);
        registerExitUseCase = new RegisterExitUseCase(clientDAO, parkingDAO);
        makePaymentUseCase =new MakePaymentUseCase(paymentDAO, clientDAO, monthlyServiceDAO);
        manageParkingSpaceUseCase = new ManageParkingSpaceUseCase(parkingDAO);
    }
}
