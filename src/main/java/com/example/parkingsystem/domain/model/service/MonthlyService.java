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
    public double calculateBilling() {
        double cost = getValue();
<<<<<<< Updated upstream

        // Verifique se a data atual é superior a um mês após a data de pagamento
        if (paymentDate.plusMonths(1).isBefore(LocalDateTime.now())) {
            setPaymentChecked(false);
//            throw new IllegalArgumentException("Pagamento não efetuado");
            // Adicione uma taxa de atraso ao custo
            //cost += lateFee;
=======
        //double lateFee = getLateFee();
        if (paymentDate.plusMonths(1).isBefore(LocalDateTime.now())) {
            setPaymentChecked(false);
            return cost; //+ lateFee;
>>>>>>> Stashed changes
        } else {
            setPaymentChecked(true);
//            throw new IllegalArgumentException("Pagamento efetuado");
        }
        return cost;
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
