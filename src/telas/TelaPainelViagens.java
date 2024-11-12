package telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import classes.*;
import entities.*;
import enums.*;

public class TelaPainelViagens extends JFrame {
    private JLabel painelControle;
    private JSeparator separador;
    private JMenuBar menuBar;
    private JMenu menuOpcoes;
    private JMenuItem addViagemItem;
    private JMenuItem excluirViagemItem;
    private JMenu menuUser;
    private JMenuItem verUserItem;
    // private JMenuItem editarUserItem;
    private JMenuItem sairUserItem;
    private Viagem[] viagens;

    public TelaPainelViagens(ClientSocket clientSocket) {
        super("Painel de controle");

        painelControle = new JLabel("Painel de controle de viagens:");
        separador = new JSeparator(SwingConstants.HORIZONTAL);
        menuBar = new JMenuBar();
        menuOpcoes = new JMenu("Opções");
        addViagemItem = new JMenuItem("➕ Adicionar Viagem");
        excluirViagemItem = new JMenuItem("🖋 Modificar Viagem");
        menuUser = new JMenu("Usuário");
        verUserItem = new JMenuItem("👔 Ver Perfil");
        sairUserItem = new JMenuItem("🖐 Sair");

        painelControle.setFont(painelControle.getFont().deriveFont(Font.BOLD, 16));
        painelControle.setAlignmentX(Component.CENTER_ALIGNMENT);

        separador.setForeground(Color.BLACK);

        JPanel caixa = new JPanel();
        caixa.setLayout(new BoxLayout(caixa, BoxLayout.Y_AXIS));

        caixa.add(painelControle);
        caixa.add(Box.createVerticalStrut(10));
        caixa.add(separador);

        menuOpcoes.add(addViagemItem);
        menuOpcoes.add(excluirViagemItem);
        
        menuBar.add(menuOpcoes);

        menuUser.add(verUserItem);
        // menuUser.add(editarUserItem);
        menuUser.add(sairUserItem);

        menuBar.add(menuUser);

        setJMenuBar(menuBar);

        // viagens = GetTrips.getTrips(conn, manager); REQUISICAO -> GET_TRIPS

        Request req = new Request(RequestType.GET_TRIPS, new Object[0]);

        Response res = clientSocket.doRequest(req);

        viagens = (Viagem[]) res.getParameters();

        JPanel gridPanel = new JPanel(new GridLayout(0, 4, 20, 20));

        int contViagem = 1;
        for (Viagem viagem : viagens) {
            JPanel caixaViagem = new JPanel();
            caixaViagem.setLayout(new BoxLayout(caixaViagem, BoxLayout.Y_AXIS));
            caixaViagem.setBorder(BorderFactory.createTitledBorder("Viagem " + contViagem));

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

            gridPanel.add(caixaViagem);

            contViagem += 1;

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

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(painelBorda, BorderLayout.CENTER);

        addViagemItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaCriarViagem telaCriarViagem = new TelaCriarViagem(clientSocket);
                telaCriarViagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                TelaPainelViagens.this.dispose();
            }
        });

        excluirViagemItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaModificarViagens telaExcluirViagem = new TelaModificarViagens(clientSocket);
                telaExcluirViagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                TelaPainelViagens.this.dispose();
            }
        });

        verUserItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaVerPerfil telaVerPerfil = new TelaVerPerfil(clientSocket);
                telaVerPerfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        // editarUserItem.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         TelaEditarPerfil telaEditarPerfil = new TelaEditarPerfil();
        //         telaEditarPerfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //     }
        // });

        sairUserItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaInicial(clientSocket);
            }
        });

        if (viagens.length <= 4 && viagens.length > 0) {
            pack();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Desconecta do DB ao fechar a janela
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clientSocket.doRequest(new Request(RequestType.CLOSE_CONNECTION, new Object[0]));
            }
        });
        setVisible(true);
        setLocationRelativeTo(null);

    }
}
