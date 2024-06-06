package com.example.parkingsystem.domain.model.service;

import java.time.Duration;
import java.time.LocalDateTime;

public class MonthlyService extends Service{
    private LocalDateTime paymentDate;
    private boolean paymentChecked;

    public MonthlyService(double value) {
        super(value);
    }

    @Override
    public double calculateBilling() {
        double cost = getValue();

        // Verifique se a data atual é superior a um mês após a data de pagamento
        if(paymentDate.plusMonths(1).isBefore(LocalDateTime.now())) {
            setPaymentChecked(false);
//            throw new IllegalArgumentException("Pagamento não efetuado");
            // Adicione uma taxa de atraso ao custo (Serviço mensal não cobra taxa adicional)
            // Cobrar +1 mês;
        } else {
            setPaymentChecked(true);
//            throw new IllegalArgumentException("Pagamento efetuado");
        }
        // temp
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

    public boolean isPaymentChecked(){
        Duration between = Duration.between(paymentDate, LocalDateTime.now());
        if(between.toDays() > 30)
            paymentChecked = false;
        return paymentChecked;
    }

}
