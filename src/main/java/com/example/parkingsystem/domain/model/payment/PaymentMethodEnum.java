package com.example.parkingsystem.domain.model.payment;

import java.util.Arrays;

public enum PaymentMethodEnum {
    CARTAO("Cartão"),
    DINHEIRO("Dinheiro"),
    PIX("Pix");

    private String label;
    PaymentMethodEnum(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static PaymentMethodEnum toEnum(String value){
        return Arrays.stream(PaymentMethodEnum.values())
                .filter(m -> value.equals(m.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
