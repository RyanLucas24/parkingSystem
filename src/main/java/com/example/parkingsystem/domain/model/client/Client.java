package com.example.parkingsystem.domain.model.client;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String cpf;
    private String name;
    private String phone;
    private String email;
    private String address;
    private boolean paymentChecked;
    private String typeService;
    private List<String> vehiclesPlate = new ArrayList<>();

    public Client(String cpf, String vehiclePlate) {
        this.cpf = cpf;
        this.vehiclesPlate.add(vehiclePlate);
    }



    public Client(Client client, String name, String phone, String email, String address, String typeService) {
        this.cpf = client.getCpf();
        this.vehiclesPlate = client.getVehiclesPlate();
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.typeService = typeService;
        this.paymentChecked = false;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<String> getVehiclesPlate() {
        return vehiclesPlate;
    }

    public String getName() {
        return name;
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

    public boolean isPaymentChecked() {
        return paymentChecked;
    }

    public void setPaymentChecked(boolean paymentChecked) {
        this.paymentChecked = paymentChecked;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

}

