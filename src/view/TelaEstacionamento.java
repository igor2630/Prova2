package view;

import exceptions.VagaNaoDisponivelException;
import model.Estacionamento;
import model.TicketEstacionamento;
import model.Veiculo;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.*;

public class TelaEstacionamento extends JFrame {
    private Estacionamento estacionamento;

    private JTextField campoPlaca;
    private JTextField campoModelo;
    private JTextField campoPlacaSaida;
    private JTextArea areaEstacionados;
    private JTextArea areaHistorico;
    private JTextField campoPesquisa;

    public TelaEstacionamento(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
        setTitle("Estacionamento Rotativo");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        montarTela();
        atualizarConsultas();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resposta = JOptionPane.showConfirmDialog(TelaEstacionamento.this, "Deseja realmente sair?", "Encerrar aplicação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    dispose();
                    System.exit(0);
                }
            }
        });
    }

    private void montarTela() {
        setLayout(new BorderLayout(10, 10));
        JPanel painelOperacoes = new JPanel(new GridLayout(1, 3, 10, 10));
        JPanel painelConsultas = new JPanel(new GridLayout(1, 2, 10, 10));
        painelOperacoes.add(criarPainelEntrada());
        painelOperacoes.add(criarPainelSaida());
        painelOperacoes.add(criarPainelPesquisa());
        painelConsultas.add(criarPainelEstacionados());
        painelConsultas.add(criarPainelHistorico());
        add(painelOperacoes, BorderLayout.NORTH);
        add(painelConsultas, BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);
    }

    private JPanel criarPainelEntrada() {
        JPanel painel = new JPanel(new GridLayout(3, 2, 5, 5));
        painel.setBorder(BorderFactory.createTitledBorder("Registrar Entrada"));

        campoPlaca = new JTextField();
        campoModelo = new JTextField();

        JButton botaoEntrada = new JButton("Registrar Entrada");
        botaoEntrada.addActionListener(e -> {
            String placa = campoPlaca.getText().trim();
            String modelo = campoModelo.getText().trim();
            if (placa.isEmpty() || modelo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                campoPlaca.setText("");
                campoModelo.setText("");
                return;
            }
            try {
                Veiculo veiculo = new Veiculo(placa, modelo);
                TicketEstacionamento ticket = estacionamento.registrarEntrada(veiculo);
                JOptionPane.showMessageDialog(this, "Veículo registrado com sucesso!\n" + "Vaga: " + ticket.getNumVaga() + "\n" + "Ticket: " + ticket.getTicket());

                campoPlaca.setText("");
                campoModelo.setText("");
                atualizarConsultas();

            } catch (VagaNaoDisponivelException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            campoPlaca.setText("");
            campoModelo.setText("");
        });
        painel.add(new JLabel("Placa:"));
        painel.add(campoPlaca);
        painel.add(new JLabel("Modelo:"));
        painel.add(campoModelo);
        painel.add(new JLabel(""));
        painel.add(botaoEntrada);
        return painel;
    }

    private JPanel criarPainelSaida() {
        JPanel painel = new JPanel(new GridLayout(2, 2, 5, 5));
        painel.setBorder(BorderFactory.createTitledBorder("Registrar Saída"));

        campoPlacaSaida = new JTextField();

        JButton botaoSaida = new JButton("Registrar Saída");
        botaoSaida.addActionListener(e -> {String placa = campoPlacaSaida.getText().trim();
            if (placa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe a placa do veículo!");
                return;
            }
            try {
                double valor = estacionamento.registrarSaida(placa);
                JOptionPane.showMessageDialog(this, "Saída registrada com sucesso!\n" + "Valor a pagar: R$ " + String.format("%.2f", valor));
                campoPlacaSaida.setText("");
                atualizarConsultas();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            campoPlacaSaida.setText("");
        });
        painel.add(new JLabel("Placa:"));
        painel.add(campoPlacaSaida);
        painel.add(new JLabel(""));
        painel.add(botaoSaida);
        return painel;
    }

    private JPanel criarPainelEstacionados() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Veículos Estacionados"));

        areaEstacionados = new JTextArea();
        areaEstacionados.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaEstacionados);
        painel.add(scroll, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarPainelHistorico() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Histórico de Estadias"));

        areaHistorico = new JTextArea();
        areaHistorico.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaHistorico);
        painel.add(scroll, BorderLayout.CENTER);
        return painel;
    }

    private void atualizarConsultas() {
        areaEstacionados.setText("");
        for (TicketEstacionamento t : estacionamento.listarVeiculosEstacionados()) {
            areaEstacionados.append("Vaga: " + t.getNumVaga() + "\n" + t.getVeiculo() + "\n" + "------------------------\n");
        }
        areaHistorico.setText("");
        for (TicketEstacionamento t : estacionamento.consultarHistorico()) {
            areaHistorico.append(t + "\n" + "------------------------\n");
        }
    }



    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel();
        JButton botaoSair = new JButton("Sair");
        botaoSair.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Encerrar aplicação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                dispose();
                System.exit(0);
            }
        });
        painel.add(botaoSair);
        JButton botaoOrdenar = new JButton("Ordenar");
        botaoOrdenar.addActionListener(e -> {
            atualizarConsultas();
            JOptionPane.showMessageDialog(this, "Histórico ordenado pelo horário de entrada!");
        });
        painel.add(botaoOrdenar);
        return painel;
    }

    private JPanel criarPainelPesquisa() {
        JPanel painel = new JPanel(new GridLayout(2, 2, 5, 5));
        painel.setBorder(BorderFactory.createTitledBorder("Pesquisar Ticket"));

        campoPesquisa = new JTextField();

        JButton botaoPesquisar = new JButton("Pesquisar");
        botaoPesquisar.addActionListener(e -> {
            String texto = campoPesquisa.getText().trim();
            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o número do ticket!");
                return;
            }
            try {
                int numeroTicket = Integer.parseInt(texto);
                TicketEstacionamento ticket = estacionamento.pesquisarTicket(numeroTicket);
                JOptionPane.showMessageDialog(this, ticket.toString(), "Resultado da Pesquisa", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            campoPesquisa.setText("");
        });

        painel.add(new JLabel("Número do ticket:"));
        painel.add(campoPesquisa);
        painel.add(new JLabel(""));
        painel.add(botaoPesquisar);
        return painel;
    }
}
