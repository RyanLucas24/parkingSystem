package com.example.parkingsystem.domain.model.service;

import java.time.LocalDateTime;

public class MonthlyService extends Service{
    private LocalDateTime paymentDate;
    private boolean paymentChecked;
    private final double lateFee = 50;// Substitua 50 pelo valor da taxa de atraso

    public MonthlyService(double value, double time, double toleranceTime) {
        super(value, time, toleranceTime);
    }

    @Override
    public void calculateBilling() {
        double cost = getValue();

        // Verifique se a data atual é superior a um mês após a data de pagamento
        if (paymentDate.plusMonths(1).isBefore(LocalDateTime.now())) {
            setPaymentChecked(false);
//            throw new IllegalArgumentException("Pagamento não efetuado");
            // Adicione uma taxa de atraso ao custo
            //cost += lateFee;
        } else {
            setPaymentChecked(true);
//            throw new IllegalArgumentException("Pagamento efetuado");
        }
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentChecked(boolean paymentChecked) {
        this.paymentChecked = paymentChecked;
    }

    public boolean isPaymentChecked() {
        return paymentChecked;
    }
}
