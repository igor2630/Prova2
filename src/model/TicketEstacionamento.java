package model;

import interfaces.Tarifavel;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.time.format.DateTimeFormatter;

public class TicketEstacionamento implements Tarifavel, Comparable<TicketEstacionamento> {
    private int ticket;
    private Veiculo veiculo;
    private int numVaga;
    private double valor = 0.0;
    private LocalDateTime hEntrada = LocalDateTime.now();
    private LocalDateTime hSaida = null;

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public TicketEstacionamento(int ticket, Veiculo veiculo, int numVaga) {
        this.ticket = ticket;
        this.veiculo = veiculo;
        this.numVaga = numVaga;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public int getNumVaga() {
        return numVaga;
    }

    public void setNumVaga(int numVaga) {
        this.numVaga = numVaga;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime gethEntrada() {
        return hEntrada;
    }

    public void sethEntrada(LocalDateTime hEntrada) {
        this.hEntrada = hEntrada;
    }

    public LocalDateTime gethSaida() {
        return hSaida;
    }

    public void sethSaida(LocalDateTime hSaida) {
        this.hSaida = hSaida;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Ticket = " + ticket + "\n" +
                "Veiculo = " + veiculo + "\n" +
                "Vaga = " + numVaga + "\n" +
                "Valor a pagar = " + valor + "\n" +
                "Horario Entrada = " + hEntrada.format(FORMATO_DATA) + "\n" +
                "Horario Saida = " + (hSaida == null ? "Em andamento" : hSaida.format(FORMATO_DATA));
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TicketEstacionamento that)) return false;
        return ticket == that.ticket;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ticket);
    }

    @Override
    public double calcularPrecoEstadia() {
        if (hSaida == null) return 0;
        Duration duracao = Duration.between(hEntrada, hSaida);
        long minutos = duracao.toMinutes();
        long temp = 0;
        long temp2 = 0;
        if (minutos <= 70) {
            return valor = 10.00;
        }else if(minutos > 70){
            temp = minutos - 70;
            temp2 = (temp + 59) /60;
            return valor = (temp2 * 10)+10;
        }
        return -1;
    }

    @Override
    public int compareTo(TicketEstacionamento o) {
        return hEntrada.compareTo(o.hEntrada);
    }

}
