package com.example.parkingsystem.domain.usecases.utils;

import java.util.List;
import java.util.Optional;

public interface DAO <T, K>{
    T create(T type);

    List<T> readAll();

    Optional<T> findOne(K key);

    boolean update(T type);

    boolean deleteByKey(K key);

    boolean delete(T type);
}
