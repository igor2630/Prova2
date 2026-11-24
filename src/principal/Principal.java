package principal;

import model.Estacionamento;
import view.TelaEstacionamento;

public class Principal {
    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento();
        TelaEstacionamento tela = new TelaEstacionamento(estacionamento);
        tela.setVisible(true);
    }
}
