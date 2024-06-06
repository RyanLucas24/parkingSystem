package com.example.parkingsystem.domain.usecases.payment;

import com.example.parkingsystem.domain.model.payment.Payment;
import com.example.parkingsystem.domain.usecases.utils.DAO;

public interface PaymentDAO extends DAO<Payment, Integer> {
}
