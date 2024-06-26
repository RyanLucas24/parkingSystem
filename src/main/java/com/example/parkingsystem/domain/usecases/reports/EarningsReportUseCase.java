package com.example.parkingsystem.domain.usecases.reports;

public class EarningsReportUseCase {
    private String day;
    private int visitCount;
    private double totalValue;

    public EarningsReportUseCase(String day, int visitCount, double totalValue) {
        this.day = day;
        this.visitCount = visitCount;
        this.totalValue = totalValue;
    }

    public String getDay() {
        return day;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public double getTotalValue() {
        return totalValue;
    }

    @Override
    public String toString() {
        return "Dia: " + day + ", Número de Visitas: " + visitCount + ", Valor Total: $" + totalValue;
    }
}