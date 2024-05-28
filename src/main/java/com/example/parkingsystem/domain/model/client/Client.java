package com.example.parkingsystem.domain.model.client;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String cpf;
    private String name;
    private String phone;
    private String email;
    private String address;
    private List<String> vehiclesPlate = new ArrayList<>();

    public Client(String cpf, String vehiclePlate) {
        this.cpf = cpf;
        this.vehiclesPlate.add(vehiclePlate);
    }



    public Client(Client client, String name, String phone, String email, String address) {
        this.cpf = client.getCpf();
        this.vehiclesPlate = client.getVehiclesPlate();
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
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

}

