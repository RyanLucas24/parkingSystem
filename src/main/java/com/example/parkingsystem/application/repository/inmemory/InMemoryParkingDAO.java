package com.example.parkingsystem.application.repository.inmemory;

import com.example.parkingsystem.domain.model.parking.Parking;
import com.example.parkingsystem.domain.usecases.parking.ParkingDAO;

import java.util.*;

public class InMemoryParkingDAO implements ParkingDAO {
    private static final Map<Integer, Parking> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Parking create(Parking parking) {
        idCounter++;
        parking.setId(idCounter);
        db.put(idCounter, parking);
        return parking;
    }

    @Override
    public List<Parking> readAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public Optional<Parking> findOne(Integer id) {
        if (db.containsKey(id))
            return Optional.of(db.get(id));
        return Optional.empty();
    }

    @Override
    public boolean update(Parking parking) {
        int id = parking.getId();
        if (db.containsKey(id)) {
            db.replace(id, parking);
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
    public boolean delete(Parking parking) {
        return deleteByKey(parking.getId());
    }
}
