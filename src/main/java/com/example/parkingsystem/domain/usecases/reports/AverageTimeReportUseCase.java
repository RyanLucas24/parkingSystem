package com.example.parkingsystem.domain.usecases.reports;

import java.time.LocalDate;

public class AverageTimeReportUseCase {
    private LocalDate startDate;
    private LocalDate endDate;
    private double averageVisitTime;

    public AverageTimeReportUseCase(LocalDate startDate, LocalDate endDate, double averageVisitTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.averageVisitTime = averageVisitTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getAverageVisitTime() {
        return averageVisitTime;
    }

    @Override
    public String toString() {
        return "Average Time Report from " + startDate + " to " + endDate + ":\n" +
                "Average Visit Time: " + String.format("%.2f", averageVisitTime) + " minutes";
    }
}
