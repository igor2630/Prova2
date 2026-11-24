package model;

import exceptions.VagaNaoDisponivelException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Estacionamento {
    private List<TicketEstacionamento> vagas;
    private List<TicketEstacionamento> historico;
    private int proximoTicket = 1;

    public Estacionamento() {
        vagas = new ArrayList<>();
        historico = new ArrayList<>();
        for (int i=0; i<10;i++){
            vagas.add(null);
        }
    }

    public List<TicketEstacionamento> getVagas() {
        return vagas;
    }

    public void setVagas(List<TicketEstacionamento> vagas) {
        this.vagas = vagas;
    }

    public List<TicketEstacionamento> getHistorico() {
        return historico;
    }

    public void setHistorico(List<TicketEstacionamento> historico) {
        this.historico = historico;
    }

    public TicketEstacionamento registrarEntrada(Veiculo veiculo) throws VagaNaoDisponivelException {
            for (TicketEstacionamento t : vagas) {
                if (t != null && t.getVeiculo().equals(veiculo)) {
                    throw new IllegalArgumentException("Este veículo já está estacionado!");
                }
            }

        for (int i = 0; i < vagas.size(); i++) {
            if (vagas.get(i)==null) {
                TicketEstacionamento ticket = new TicketEstacionamento(proximoTicket, veiculo, i + 1);
                vagas.set(i, ticket);
                proximoTicket++;
                return ticket;
            }
        }
        throw new VagaNaoDisponivelException("Todas as vagas estão ocupadas!");
    }

    public double registrarSaida(String placa){
        for (int i = 0; i < vagas.size(); i++) {
            TicketEstacionamento ticket = vagas.get(i);

            if (ticket != null && ticket.getVeiculo().getPlaca().equalsIgnoreCase(placa.trim())) {
                ticket.sethSaida(LocalDateTime.now());
                double valor = ticket.calcularPrecoEstadia();
                historico.add(ticket);
                vagas.set(i, null);
                return valor;
            }
        }
        throw new IllegalArgumentException("Veículo não encontrado ou não está estacionado!");
    }


    public List<TicketEstacionamento> listarVeiculosEstacionados(){
        List<TicketEstacionamento> estacionados = new ArrayList<>();
        for (TicketEstacionamento t : vagas) {
            if (t != null) {
                estacionados.add(t);
            }
        }
        return estacionados;
    }

    public List<TicketEstacionamento> consultarHistorico(){
        List<TicketEstacionamento> copia = new ArrayList<>(historico);
        Collections.sort(copia);
        return copia;
    }

    public TicketEstacionamento pesquisarTicket(int numeroTicket) {
        for (TicketEstacionamento t : vagas) {
            if (t != null && t.getTicket() == numeroTicket) {
                return t;
            }
        }

        for (TicketEstacionamento t : historico) {
            if (t.getTicket() == numeroTicket) {
                return t;
            }
        }

        throw new IllegalArgumentException("Ticket não encontrado!");
    }
}
