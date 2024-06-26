package com.example.parkingsystem.domain.usecases.parking;

import com.example.parkingsystem.domain.model.parking.Parking;
import com.example.parkingsystem.domain.usecases.client.ClientDAO;

public class RegisterExitUseCase {

    private ParkingDAO parkingDAO;
    private ClientDAO clientDAO;
    private Parking parking;
    private boolean paymentChecked;

    public RegisterExitUseCase(ClientDAO clientDAO) {
        this.parkingDAO = parkingDAO;
        this.clientDAO = clientDAO;
    }

//    public Client getClientByCpf(String cpf){
//        Optional<Client> clientOptional = clientDAO.findOne(cpf);
//        if(clientOptional.isEmpty()) {
//            throw new IllegalArgumentException("Cliente não encontrado");
//        }
//
//        Client clientFound = clientOptional.get();
//        MonthlyService monthlyService = (MonthlyService) clientFound.getService();
//        StandardService standardService = (StandardService) clientFound.getService();
//
//        switch (clientFound.getService()) {
//            case "standard": // TODO - Refatorar para usar o enum ou trocar para string
//                // Chama o cálculo de valor do pagamento para serviço standard
//                standardService.setCheckOut(LocalDateTime.now());
//                standardService.calculateBilling();
//                setPaymentChecked(true);
//                if (isPaymentChecked()) {
//                    // Libera a vaga e o cliente
//                    parking.liberateAStandardParkingSpace();
//                } else {
//                    throw new IllegalArgumentException("Pagamento não efetuado");
//                }
//                break;
//            // fluxo alternativo 1
//            case "monthly": // TODO - Refatorar para usar o enum ou trocar para string
//                if (monthlyService.isPaymentChecked()) {
//                    // Libera a vaga e o cliente
//                    parking.liberateAMonthlyParkingSpace();
//                } else {
//                    throw new IllegalArgumentException("Pagamento não efetuado");
//                }
//                break;
//
//            default:
//                throw new IllegalArgumentException("Tipo de serviço inválido");
//        }
//
//        return clientFound; // TODO - Verificar se é necessário retornar o cliente
////        switch (clientFound.getService()) {
//            // case "standard": // TODO - Refatorar para usar o enum ou trocar para string
//                // Chama o cálculo de valor do pagamento para serviço standard
////                standardService.setCheckOut(LocalDateTime.now());
////                standardService.calculateBilling();
////                setPaymentChecked(true);
////                if (isPaymentChecked()) {
////                    // Libera a vaga e o cliente
////                    parking.liberateAStandardParkingSpace();
////                } else {
////                    throw new IllegalArgumentException("Pagamento não efetuado");
////                }
////                break;
//            // fluxo alternativo 1
//            // case "monthly": // TODO - Refatorar para usar o enum ou trocar para string
////                if (monthlyService.isPaymentChecked()) {
////                    // Libera a vaga e o cliente
////                    parking.liberateAMonthlyParkingSpace();
////                } else {
////                    throw new IllegalArgumentException("Pagamento não efetuado");
////                }
////                break;
////
////            default:
////                throw new IllegalArgumentException("Tipo de serviço inválido");
////        }
//
////        return clientFound; // TODO - Verificar se é necessário retornar o cliente
//    }

    public boolean isPaymentChecked() {
        return paymentChecked;
    }

    public void setPaymentChecked(boolean paymentChecked) {
        this.paymentChecked = paymentChecked;
    }

}
