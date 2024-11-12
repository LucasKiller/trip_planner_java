package telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import entities.Viagem;
import enums.RequestType;
import classes.ClientSocket;
import classes.Request;
import classes.Response;

import java.awt.event.ActionEvent;

public class TelaModificarViagens extends JFrame {
    private JLabel painelControle;
    private JSeparator separador;
    private JMenuBar menuBar;
    private JMenu menuOpcoes;
    private JMenuItem voltarViagemItem;
    private Viagem [] viagens;

    public TelaModificarViagens(ClientSocket clientSocket) {
        super("Painel de edição");

        painelControle = new JLabel("Painel de edição de viagens:");
        separador = new JSeparator(SwingConstants.HORIZONTAL);
        menuBar = new JMenuBar();
        menuOpcoes = new JMenu("Opções");
        voltarViagemItem = new JMenuItem("◀ Voltar");

        painelControle.setFont(painelControle.getFont().deriveFont(Font.BOLD, 16));
        painelControle.setAlignmentX(Component.CENTER_ALIGNMENT);

        separador.setForeground(Color.BLACK);

        JPanel caixa = new JPanel();
        caixa.setLayout(new BoxLayout(caixa, BoxLayout.Y_AXIS));

        caixa.add(painelControle);
        caixa.add(Box.createVerticalStrut(10));
        caixa.add(separador);

        menuOpcoes.add(voltarViagemItem);
        
        menuBar.add(menuOpcoes);

        setJMenuBar(menuBar);

        Request req = new Request(RequestType.GET_TRIPS, new Object[0]);

        Response res = clientSocket.doRequest(req);

        viagens = (Viagem[]) res.getParameters();

        JPanel gridPanel = new JPanel(new GridLayout(0, 4, 20, 20));

        for (Viagem viagem : viagens) {
            JPanel caixaViagem = new JPanel();
            caixaViagem.setLayout(new BoxLayout(caixaViagem, BoxLayout.Y_AXIS));
            caixaViagem.setBorder(BorderFactory.createTitledBorder("Viagem"));

            JLabel nomeViagem = new JLabel("Nome: " + viagem.getNomeViagem());
            JLabel descricaoViagem = new JLabel("Descrição: " + viagem.getDescricaoViagem());
            JLabel dataViagem = new JLabel("Data: " + viagem.getDiaInicial() + " - " + viagem.getDiaFinal());
            JLabel hotelNome = new JLabel("Hotel: " + viagem.getHotel().getNome());
            JLabel endereco = new JLabel("Endereço: " + viagem.getHotel().getEndereco());
            JLabel checkin = new JLabel("Check-in: " + viagem.getHotel().getCheckin());
            JLabel checkout = new JLabel("Check-out: " + viagem.getHotel().getCheckout());
            JLabel carroNome = new JLabel("Carro: " + viagem.getCarro().getNome());
            JLabel marca = new JLabel("Marca: " + viagem.getCarro().getMarca());
            JLabel placa = new JLabel("Placa: " + viagem.getCarro().getPlaca());
            JLabel seguro = new JLabel("Tem seguro: " + (viagem.getCarro().isTemSeguro() ? "Sim" : "Não"));
            JLabel valorSeguro = new JLabel("Valor do seguro: " + Integer.toString(viagem.getCarro().getValorSeguro()));

            if (viagem.getCarro().getNome().equals("")) {
                carroNome.setText("Carro: Não informado");
            }

            if (viagem.getCarro().getMarca().equals("")) {
                marca.setText("Marca: Não informado");
            }

            if (viagem.getCarro().getPlaca().equals("")) {
                placa.setText("Placa: Não informado");
            }

            if (viagem.getCarro().isTemSeguro() == false) {
                seguro.setText("Tem seguro: Não");
            }

            if (viagem.getCarro().getValorSeguro() == 0) {
                valorSeguro.setText("Valor do seguro: Não informado");
            }

            nomeViagem.setAlignmentX(Component.CENTER_ALIGNMENT);
            descricaoViagem.setAlignmentX(Component.CENTER_ALIGNMENT);
            dataViagem.setAlignmentX(Component.CENTER_ALIGNMENT);
            hotelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
            endereco.setAlignmentX(Component.CENTER_ALIGNMENT);
            checkin.setAlignmentX(Component.CENTER_ALIGNMENT);
            checkout.setAlignmentX(Component.CENTER_ALIGNMENT);
            carroNome.setAlignmentX(Component.CENTER_ALIGNMENT);
            marca.setAlignmentX(Component.CENTER_ALIGNMENT);
            placa.setAlignmentX(Component.CENTER_ALIGNMENT);
            seguro.setAlignmentX(Component.CENTER_ALIGNMENT);
            valorSeguro.setAlignmentX(Component.CENTER_ALIGNMENT);

            caixaViagem.add(nomeViagem);
            caixaViagem.add(descricaoViagem);
            caixaViagem.add(dataViagem);
            caixaViagem.add(hotelNome);
            caixaViagem.add(endereco);
            caixaViagem.add(checkin);
            caixaViagem.add(checkout);
            caixaViagem.add(carroNome);
            caixaViagem.add(marca);
            caixaViagem.add(placa);
            caixaViagem.add(seguro);
            caixaViagem.add(valorSeguro);
            caixaViagem.add(Box.createVerticalStrut(10));

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

            JButton btnExcluir = new JButton("❌");
            JButton btnEditar = new JButton("🖊");

            btnExcluir.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnEditar.setAlignmentX(Component.CENTER_ALIGNMENT);

            buttonPanel.add(btnExcluir);
            buttonPanel.add(Box.createHorizontalStrut(15));
            buttonPanel.add(btnEditar);

            btnExcluir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Request req = new Request(RequestType.DELETE_TRIP, viagem);
                    clientSocket.doRequest(req);

                    new TelaModificarViagens(clientSocket);
                    TelaModificarViagens.this.dispose();
                }
            });

            btnEditar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    TelaEditarViagem telaEditarViagem = new TelaEditarViagem(clientSocket, viagem, TelaModificarViagens.this);
                    telaEditarViagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            });

            caixaViagem.add(buttonPanel);

            gridPanel.add(caixaViagem);

            setSize(1280, 720);
        }

        if (viagens.length == 0) {
            JPanel caixaSemViagem = new JPanel();
            caixaSemViagem.setLayout(new BoxLayout(caixaSemViagem, BoxLayout.Y_AXIS));

            JLabel nomeViagemDefault = new JLabel("Não há nenhuma viagem adicionada!");
            nomeViagemDefault.setAlignmentX(Component.CENTER_ALIGNMENT);
            nomeViagemDefault.setFont(nomeViagemDefault.getFont().deriveFont(Font.BOLD, 16));
            caixaSemViagem.add(nomeViagemDefault);
            caixa.add(caixaSemViagem);

            setSize(1280, 720);
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        caixa.add(scrollPane);

        JPanel painelBorda = new JPanel();
        painelBorda.setLayout(new BoxLayout(painelBorda, BoxLayout.Y_AXIS));

        painelBorda.add(Box.createVerticalStrut(10));
        painelBorda.add(caixa);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoVoltar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaPainelViagens telaPainelViagens = new TelaPainelViagens(clientSocket);
                telaPainelViagens.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                TelaModificarViagens.this.dispose();
            }
        });
        painelBorda.add(Box.createVerticalStrut(10));
        painelBorda.add(botaoVoltar);
        painelBorda.add(Box.createVerticalStrut(10));

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(painelBorda, BorderLayout.CENTER);

        voltarViagemItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaPainelViagens telaPainelViagens = new TelaPainelViagens(clientSocket);
                telaPainelViagens.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                TelaModificarViagens.this.dispose();
            }
        });

        if (viagens.length <= 4 && viagens.length > 0) {
            pack();
        }
        
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Desconecta do DB ao fechar a janela
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clientSocket.doRequest(new Request(RequestType.CLOSE_CONNECTION, new Object[0]));
            }
        });
        

    }
}
