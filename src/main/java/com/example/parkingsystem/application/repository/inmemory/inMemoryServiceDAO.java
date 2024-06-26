package com.example.parkingsystem.application.repository.inmemory;

import com.example.parkingsystem.domain.model.service.Service;
import com.example.parkingsystem.domain.usecases.service.StandardServiceDAO;

import java.util.*;

public class inMemoryServiceDAO implements StandardServiceDAO {
    private static final Map<Integer, Service> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Service create(Service service) {
        idCounter++;
        service.setId(idCounter);
        db.put(idCounter, service);
        return service;
    }

    @Override
    public List<Service> readAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public Optional<Service> findOne(Integer id) {
        if (db.containsKey(id))
            return Optional.of(db.get(id));
        return Optional.empty();
    }

    @Override
    public boolean update(Service service) {
        int id = service.getId();
        if (db.containsKey(id)) {
            db.replace(id, service);
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
    public boolean delete(Service service) {
        return deleteByKey(service.getId());
    }
}
