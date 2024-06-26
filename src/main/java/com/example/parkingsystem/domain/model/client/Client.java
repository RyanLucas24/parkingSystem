package com.example.parkingsystem.domain.model.client;

import com.example.parkingsystem.domain.model.service.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Client {
    private int id;
    private final String cpf;
    private String name;
    private String phone;
    private String email;
    private String address;
    private final String vehiclePlate;
    private Service service;

    private LocalDateTime entryDate;

    public Client(String cpf, String vehiclePlate) {
        this.cpf = cpf;
        this.vehiclePlate = vehiclePlate;
    }

    public Client(int id, String cpf, String name, String phone, String email, String address, String vehiclePlate) {
        this.id = id;
        this.cpf = cpf;
        this.vehiclePlate = vehiclePlate;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getCpf() {
        return cpf;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public String getName() {
        return name;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }
}

