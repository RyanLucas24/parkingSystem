package com.example.parkingsystem.application.repository;

import com.example.parkingsystem.domain.model.client.Client;
import com.example.parkingsystem.domain.model.payment.Payment;
import com.example.parkingsystem.domain.usecases.payment.PaymentDAO;

import java.util.*;

public class InMemoryPaymentDAO implements PaymentDAO {
    private static final Map<Integer, Payment> db = new LinkedHashMap<>();
    private static int idCounter;
    @Override
    public Payment create(Payment payment) {
        idCounter++;
        payment.setId(idCounter);
        db.put(idCounter, payment);
        return payment;
    }

    @Override
    public List<Payment> readAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public Optional<Payment> findOne(Integer id) {
        if (db.containsKey(id))
            return Optional.of(db.get(id));
        return Optional.empty();
    }

    @Override
    public boolean update(Payment payment) {
        int id = payment.getId();
        if (db.containsKey(id)) {
            db.replace(id,payment);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        if(db.containsKey(id)){
            db.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Payment payment) {
        return deleteByKey(payment.getId());
    }
}
