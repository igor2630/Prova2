package model;

import java.util.Objects;

public class Veiculo {
    private String placa;
    private String modelo;

    public Veiculo(String placa, String modelo) {
        this.placa = placa.trim().toUpperCase();
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa.trim().toUpperCase();
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString(){
        return "Placa: " + placa + "\n" +
                "Modelo: " + modelo;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Veiculo veiculo)) return false;
        return Objects.equals(placa, veiculo.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(placa);
    }
}