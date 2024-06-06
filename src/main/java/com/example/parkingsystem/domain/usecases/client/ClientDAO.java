package com.example.parkingsystem.domain.usecases.client;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.usecases.utils.DAO;

import java.util.Optional;

public interface ClientDAO extends DAO <Client, Integer> {
    Optional<Client> findOne(String cpf);
}
