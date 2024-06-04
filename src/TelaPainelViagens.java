import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;

public class TelaPainelViagens extends JFrame {
    private JLabel painelControle;
    private JSeparator separador;
    private JMenuBar menuBar;
    private JMenu menuOpcoes;
    private JMenuItem addViagemItem;
    private JMenuItem editarViagemItem;
    private JMenu menuUser;
    private JMenuItem verUserItem;
    private JMenuItem editarUserItem;
    private JMenuItem sairUserItem;
    private ArrayList<Viagem> viagens;

    private JLabel nomeViagem;
    private JLabel descricaoViagem;
    private JLabel dataViagem;
    private JLabel hotelNome;
    private JLabel endereco;
    private JLabel checkin;
    private JLabel checkout;
    private JLabel carroNome;
    private JLabel marca;
    private JLabel placa;
    private JLabel seguro;
    private JLabel valorSeguro;

    public TelaPainelViagens(Connection conn, String login, ManageUserLogin manager) {
        super("Painel de controle");

        painelControle = new JLabel("Painel de controle de viagens:");
        separador = new JSeparator(SwingConstants.HORIZONTAL);
        menuBar = new JMenuBar();
        menuOpcoes = new JMenu("Opções");
        addViagemItem = new JMenuItem("➕ Adicionar Viagem");
        editarViagemItem = new JMenuItem("🖊 Editar Viagem"); 
        menuUser = new JMenu("Usuário");
        verUserItem = new JMenuItem("👔 Ver Perfil");
        editarUserItem = new JMenuItem("🖊 Editar Perfil");
        sairUserItem = new JMenuItem("🖐 Sair");
        viagens = new ArrayList<Viagem>();
        nomeViagem = new JLabel("Nome da viagem:");
        descricaoViagem = new JLabel("Descrição da viagem:");
        dataViagem = new JLabel("Data da viagem:");
        hotelNome = new JLabel("Nome do hotel:");
        endereco = new JLabel("Endereço:");
        checkin = new JLabel("Check-in:");
        checkout = new JLabel("Check-out:");
        carroNome = new JLabel("Nome do carro:");
        marca = new JLabel("Marca:");
        placa = new JLabel("Placa:");
        seguro = new JLabel("Tem seguro:");
        valorSeguro = new JLabel("Valor do seguro:");

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
        menuUser.add(editarUserItem);
        menuUser.add(sairUserItem);

        menuBar.add(menuUser);

        setJMenuBar(menuBar);

        viagens = GetTrips.getTrips(conn, manager);

        for (Viagem viagem : viagens) {
            JPanel cardViagem = new JPanel();
            cardViagem.setLayout(new BoxLayout(cardViagem, BoxLayout.Y_AXIS));

            JPanel caixaViagem = new JPanel();
            caixaViagem.setLayout(new FlowLayout());

            caixaViagem.add(nomeViagem);
            JLabel nomeViagem = new JLabel(viagem.getNomeViagem());
            cardViagem.add(caixaViagem);

            JPanel caixaDescricao = new JPanel();
            caixaDescricao.setLayout(new FlowLayout());

            caixaDescricao.add(descricaoViagem);
            JLabel descricaoViagem = new JLabel(viagem.getDescricaoViagem());
            cardViagem.add(caixaDescricao);

            JPanel caixaData = new JPanel();
            caixaData.setLayout(new FlowLayout());

            caixaData.add(dataViagem);
            JLabel dataViagem = new JLabel(viagem.getDiaInicial() + " - " + viagem.getDiaFinal());
            cardViagem.add(caixaData);

            JPanel caixaHotel = new JPanel();
            caixaHotel.setLayout(new FlowLayout());

            caixaHotel.add(hotelNome);
            JLabel hotelNome = new JLabel(viagem.getHotel().getNome());
            cardViagem.add(caixaHotel);

            JPanel caixaEndereco = new JPanel();
            caixaEndereco.setLayout(new FlowLayout());

            caixaEndereco.add(endereco);
            JLabel endereco = new JLabel(viagem.getHotel().getEndereco());
            cardViagem.add(caixaEndereco);

            JPanel caixaCheckin = new JPanel();
            caixaCheckin.setLayout(new FlowLayout());

            caixaCheckin.add(checkin);
            JLabel checkin = new JLabel(viagem.getHotel().getCheckin());
            cardViagem.add(caixaCheckin);

            JPanel caixaCheckout = new JPanel();
            caixaCheckout.setLayout(new FlowLayout());

            caixaCheckout.add(checkout);
            JLabel checkout = new JLabel(viagem.getHotel().getCheckout());
            cardViagem.add(caixaCheckout);

            JPanel caixaCarro = new JPanel();
            caixaCarro.setLayout(new FlowLayout());

            caixaCarro.add(carroNome);
            JLabel carroNome = new JLabel(viagem.getCarro().getNome());
            cardViagem.add(caixaCarro);

            JPanel caixaMarca = new JPanel();
            caixaMarca.setLayout(new FlowLayout());

            caixaMarca.add(marca);
            JLabel marca = new JLabel(viagem.getCarro().getMarca());
            cardViagem.add(caixaMarca);

            JPanel caixaPlaca = new JPanel();
            caixaPlaca.setLayout(new FlowLayout());

            caixaPlaca.add(placa);
            JLabel placa = new JLabel(viagem.getCarro().getPlaca());
            cardViagem.add(caixaPlaca);

            JPanel caixaSeguro = new JPanel();
            caixaSeguro.setLayout(new FlowLayout());

            caixaSeguro.add(seguro);
            JLabel seguro = new JLabel(viagem.getCarro().isTemSeguro() ? "Sim" : "Não");
            cardViagem.add(caixaSeguro);

            JPanel caixaValorSeguro = new JPanel();
            caixaValorSeguro.setLayout(new FlowLayout());

            caixaValorSeguro.add(valorSeguro);
            JLabel valorSeguro = new JLabel(Integer.toString(viagem.getCarro().getValorSeguro()));
            cardViagem.add(caixaValorSeguro);

            cardViagem.add(nomeViagem);
            cardViagem.add(descricaoViagem);
            cardViagem.add(dataViagem);
            cardViagem.add(hotelNome);
            cardViagem.add(endereco);
            cardViagem.add(checkin);
            cardViagem.add(checkout);
            cardViagem.add(carroNome);
            cardViagem.add(marca);
            cardViagem.add(placa);
            cardViagem.add(seguro);
            cardViagem.add(valorSeguro);

            caixa.add(cardViagem);
        }

        JPanel painelBorda = new JPanel();
        painelBorda.setLayout(new BoxLayout(painelBorda, BoxLayout.Y_AXIS));

        painelBorda.add(Box.createVerticalStrut(10));
        painelBorda.add(caixa);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(painelBorda, BorderLayout.CENTER);

        addViagemItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaCriarViagem telaCriarViagem = new TelaCriarViagem(conn, manager);
                telaCriarViagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        editarViagemItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaEditarViagem telaEditarViagem = new TelaEditarViagem();
                telaEditarViagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        verUserItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaVerPerfil telaVerPerfil = new TelaVerPerfil();
                telaVerPerfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        editarUserItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaEditarPerfil telaEditarPerfil = new TelaEditarPerfil();
                telaEditarPerfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        sairUserItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                TelaInicial telaInicial = new TelaInicial(conn);
            }
        });

        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
