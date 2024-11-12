package telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

import classes.*;
import entities.*;
import enums.*;
import utils.HandleLanguageChoice;

public class TelaPainelViagens extends JFrame {
    private JLabel painelControle;
    private JSeparator separador;
    private JMenuBar menuBar;
    private JMenu menuOpcoes;
    private JMenuItem addViagemItem;
    private JMenuItem editarViagemItem;
    private JMenu menuUser;
    private JMenuItem verUserItem;
    private JMenuItem sairUserItem;
    private Viagem[] viagens;
    private static ResourceBundle bundle = HandleLanguageChoice.getDefinedLang("TelaPainelViagens");

    public TelaPainelViagens(ClientSocket clientSocket) {
        super(bundle.getString("painelControle"));

        painelControle = new JLabel(bundle.getString("painelControle"));
        separador = new JSeparator(SwingConstants.HORIZONTAL);
        menuBar = new JMenuBar();
        menuOpcoes = new JMenu(bundle.getString("menuOpcoes"));
        addViagemItem = new JMenuItem(bundle.getString("addViagemItem"));
        editarViagemItem = new JMenuItem(bundle.getString("excluirViagemItem"));
        menuUser = new JMenu(bundle.getString("menuUser"));
        verUserItem = new JMenuItem(bundle.getString("verUserItem"));
        sairUserItem = new JMenuItem(bundle.getString("sairUserItem"));

        painelControle.setFont(painelControle.getFont().deriveFont(Font.BOLD, 16));
        painelControle.setAlignmentX(Component.CENTER_ALIGNMENT);

        separador.setForeground(Color.BLACK);

        JPanel caixa = new JPanel();
        caixa.setLayout(new BoxLayout(caixa, BoxLayout.Y_AXIS));

        caixa.add(painelControle);
        caixa.add(Box.createVerticalStrut(10));
        caixa.add(separador);

        menuOpcoes.add(addViagemItem);
        menuOpcoes.add(editarViagemItem);
        
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
            caixaViagem.setBorder(BorderFactory.createTitledBorder(bundle.getString("viagemTitulo") + contViagem));

            JLabel nomeViagem = new JLabel(bundle.getString("nomeViagem") + viagem.getNomeViagem());
            JLabel descricaoViagem = new JLabel(bundle.getString("descricaoViagem") + viagem.getDescricaoViagem());
            JLabel dataViagem = new JLabel(bundle.getString("dataViagem") + viagem.getDiaInicial() + " - " + viagem.getDiaFinal());
            JLabel hotelNome = new JLabel(bundle.getString("hotelNome") + viagem.getHotel().getNome());
            JLabel endereco = new JLabel(bundle.getString("endereco") + viagem.getHotel().getEndereco());
            JLabel checkin = new JLabel(bundle.getString("checkin") + viagem.getHotel().getCheckin());
            JLabel checkout = new JLabel(bundle.getString("checkout") + viagem.getHotel().getCheckout());
            JLabel carroNome = new JLabel(bundle.getString("carroNome") + viagem.getCarro().getNome());
            JLabel marca = new JLabel(bundle.getString("marca") + viagem.getCarro().getMarca());
            JLabel placa = new JLabel(bundle.getString("placa") + viagem.getCarro().getPlaca());
            JLabel seguro = new JLabel(bundle.getString("seguro") + (viagem.getCarro().isTemSeguro() ? bundle.getString("sim") : bundle.getString("nao")));
            JLabel valorSeguro = new JLabel(bundle.getString("valorSeguro") + (viagem.getCarro().getValorSeguro() > 0 ? Integer.toString(viagem.getCarro().getValorSeguro()) : bundle.getString("valorSeguroNaoInformado")));

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

        editarViagemItem.addActionListener(new ActionListener() {
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