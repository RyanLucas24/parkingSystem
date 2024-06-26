package com.example.parkingsystem.application.repository.sqlite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {

    public void buildDatabaseIfMissing(){
        if (isDatabaseMissing()){
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("database.db"));
    }

    private void buildTables() {
        try(Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(createParkingTable());
            statement.addBatch(createPaymentTable());
            statement.addBatch(createMonthlyServiceTable());
            statement.addBatch(createStandardServiceTable());
            statement.addBatch(createClientTable());
            statement.executeBatch();

            System.out.println("\n Database successful created.");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    private String createStandardServiceTable() {
        StringBuilder builder = new StringBuilder();
        builder.append(" CREATE TABLE IF NOT EXISTS MonthlyService(\n");
        builder.append("    id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("    value REAL NOT NULL, \n");
        builder.append("    time REAL, \n");
        builder.append("    toleranceTime TEXT, \n");
        builder.append("    additionalValue REAL, \n");
        builder.append("    checkIn TEXT, \n");
        builder.append("    checkOut TEXT \n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createMonthlyServiceTable() {
        StringBuilder builder = new StringBuilder();
        builder.append(" CREATE TABLE IF NOT EXISTS StandardService(\n");
        builder.append("    id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("    value REAL NOT NULL, \n");
        builder.append("    paymentDate TEXT, \n");
        builder.append("    paymentChecked BOOLEAN \n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createClientTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS Client(\n");
        builder.append("    id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("    cpf TEXT UNIQUE, \n");
        builder.append("    name TEXT, \n");
        builder.append("    phone TEXT, \n");
        builder.append("    email TEXT UNIQUE, \n");
        builder.append("    address TEXT, \n");
        builder.append("    vehiclePlate TEXT NOT NULL, \n");
        builder.append("    service INTEGER, \n");
        builder.append("    FOREIGN KEY(service) REFERENCES Service(id) \n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createParkingTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS Parking(\n");
        builder.append("    id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("    availableStandardParkingSpace INTEGER NOT NULL, \n");
        builder.append("    parkingSpace INTEGER NOT NULL, \n");
        builder.append("    availableMonthlyParkingSpace INTEGER NOT NULL, \n");
        builder.append("    standardParkingSpace INTEGER NOT NULL, \n");
        builder.append("    monthlyParkingSpace INTEGER NOT NULL\n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createPaymentTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS Payment( \n");
        builder.append("    id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("    paymentMethod TEXT NOT NULL,\n");
        builder.append("    value REAL NOT NULL,\n");
        builder.append("    date TEXT NOT NULL\n");
        builder.append(");\n");

        System.out.println(builder);
        return builder.toString();
    }
}
