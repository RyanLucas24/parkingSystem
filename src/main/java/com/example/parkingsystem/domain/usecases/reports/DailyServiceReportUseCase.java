package com.example.parkingsystem.domain.usecases.reports;

public class DailyServiceReportUseCase {
        private String day;
        private int numberOfServices;
        private double totalValue;

        public DailyServiceReportUseCase(String day, int numberOfServices, double totalValue) {
            this.day = day;
            this.numberOfServices = numberOfServices;
            this.totalValue = totalValue;
        }

        public String getDay() {
            return day;
        }

        public int getNumberOfServices() {
            return numberOfServices;
        }

        public double getTotalValue() {
            return totalValue;
        }

        @Override
        public String toString() {
            return "Day: " + day + ", Number of Services: " + numberOfServices + ", Total Value: " + totalValue;
        }
}
