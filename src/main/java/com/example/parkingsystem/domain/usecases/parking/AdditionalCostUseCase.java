package com.example.parkingsystem.domain.usecases.parking;

public class AdditionalCostUseCase {

    public static double calculateAdditionalCost(double value, double time, double toleranceTime) {
        double exceededTime = time - toleranceTime;
        if (exceededTime < 0)
                exceededTime = 0;

        return exceededTime * value;
    }
}
