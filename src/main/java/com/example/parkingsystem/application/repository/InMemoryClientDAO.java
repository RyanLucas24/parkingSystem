package com.example.parkingsystem.application.repository;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.usecases.client.ClientDAO;

import java.util.*;

public class InMemoryClientDAO implements ClientDAO {
    private static final Map<Integer, Client> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Client create(Client client) {
        idCounter++;
        client.setId(idCounter);
        db.put(idCounter, client);
        return client;
    }

    @Override
    public List<Client> readAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public Optional<Client> findOne(Integer id) {
        if (db.containsKey(id))
            return Optional.of(db.get(id));
        return Optional.empty();
    }

    @Override
    public Optional<Client> findOne(String cpf) {
        return db.values().stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findAny();
    }

    @Override
    public boolean update(Client client) {
        int id = client.getId();
        if (db.containsKey(id)) {
            db.replace(id, client);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        if (db.containsKey(id)) {
            db.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Client client) {
        return deleteByKey(client.getId());
    }
}
