package com.example.parkingsystem.domain.usecases.utils;

public class UnavailableParkingSpaceRequestException extends RuntimeException{
    public UnavailableParkingSpaceRequestException(String message) {
        super(message);
    }
}
