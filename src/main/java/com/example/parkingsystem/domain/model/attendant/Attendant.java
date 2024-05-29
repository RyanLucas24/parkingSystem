package com.example.parkingsystem.domain.model.attendant;

import com.example.parkingsystem.domain.model.client.Client;

import java.util.ArrayList;
import java.util.List;

public class Attendant {
    private List<Client> clients;
    private int availableVacancies;

    public Attendant(int availableVacancies) {
        this.clients = new ArrayList<>();
        this.availableVacancies = availableVacancies;
    }

    public void registerEntry(Client client){
        if(availableVacancies > 0){
            clients.add(client);
            availableVacancies--;
            System.out.println("Entrada registrada:" + client);
        } else {
            System.out.println("Não há vagas disponíveis");
        }
    }

    public void registerExit(Client client) {
        if (clients.remove(client)){
            availableVacancies++;
            System.out.println("Saída registrada:" + client);
        } else {
            System.out.println("Cliente não encontrado");
        }
    }

    public void registerContactDetails(Client client){
        // adicionar lógica para atualizar dados de contato se necessário

        System.out.println("Detalhes de contato registrados:" + client);
    }

    public void makeThePayment(Client client){
        // Aqui adicionar lógica para efetuar pagamento
        System.out.println("Pagamento realizado:" + client);
    }

    public void chargeAdditionalAmount(Client client){
        // Aqui adicionar lógica para cobrar valor adicional
        System.out.println("Valor adicional cobrado:" + client);
    }

    public void gerenciarVagas(){
        // Aqui adicionar lógica para gerenciar vagas
        System.out.println("Vagas gerenciadas");
    }
}
