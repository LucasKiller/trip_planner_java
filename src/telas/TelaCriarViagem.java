package telas;
import java.awt.Container;
import java.awt.Font;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import classes.Carro;
import classes.Hotel;
import classes.ManageUserLogin;
import classes.Viagem;

public class TelaCriarViagem extends JFrame {
    private JLabel criarViagem;
    private JLabel nomeViagem;
    private JTextField campoNomeViagem;
    private JLabel descricaoViagem;
    private JTextField campoDescricaoViagem;
    private JLabel cidade;
    private JTextField campoCidade;
    private JLabel nomeHotel;
    private JTextField campoNomeHotel;
    private JLabel enderecoHotel;
    private JTextField campoEnderecoHotel;
    private JLabel checkInHotel;
    private JTextField campoCheckInHotel;
    private JLabel checkOutHotel;
    private JTextField campoCheckOutHotel;
    private JLabel alugarCarro;
    private JCheckBox temCarro;
    private JCheckBox semCarro;
    private JLabel dataInicio;
    private JTextField campoDataInicio;
    private JLabel dataFim;
    private JTextField campoDataFim;
    private JButton botaoCriarViagem;
    private JButton botaoCancelarViagem;
    private JLabel nomeCarro;
    private JTextField campoNomeCarro;
    private JLabel modeloCarro;
    private JTextField campoModeloCarro;
    private JLabel placaCarro;
    private JTextField campoPlacaCarro;
    private JLabel seguroCarro;
    private JCheckBox temSeguro;
    private JCheckBox semSeguro;
    private JLabel valorSeguro;
    private JTextField campoValorSeguro;

    public TelaCriarViagem(Connection conn, ManageUserLogin manager, ResourceBundle bundle) {
        super(bundle.getString("tela.criarViagem.titulo"));

        criarViagem = new JLabel(bundle.getString("tela.criarViagem.titulo"));
        nomeViagem = new JLabel(bundle.getString("tela.nomeViagem.rotulo"));
        campoNomeViagem = new JTextField(10);
        descricaoViagem = new JLabel(bundle.getString("tela.descricaoViagem.rotulo"));
        campoDescricaoViagem = new JTextField(10);
        cidade = new JLabel(bundle.getString("tela.cidade.rotulo"));
        campoCidade = new JTextField(10);
        nomeHotel = new JLabel(bundle.getString("tela.nomeHotel.rotulo"));
        campoNomeHotel = new JTextField(10);
        enderecoHotel = new JLabel(bundle.getString("tela.enderecoHotel.rotulo"));
        campoEnderecoHotel = new JTextField(10);
        checkInHotel = new JLabel(bundle.getString("tela.checkInHotel.rotulo"));
        campoCheckInHotel = new JTextField(10);
        checkOutHotel = new JLabel(bundle.getString("tela.checkOutHotel.rotulo"));
        campoCheckOutHotel = new JTextField(10);
        alugarCarro = new JLabel(bundle.getString("tela.alugarCarro.rotulo"));
        temCarro = new JCheckBox(bundle.getString("botao.sim"));
        semCarro = new JCheckBox(bundle.getString("botao.nao"));
        dataInicio = new JLabel(bundle.getString("tela.dataInicio.rotulo"));
        campoDataInicio = new JTextField(10);
        dataFim = new JLabel(bundle.getString("tela.dataFim.rotulo"));
        campoDataFim = new JTextField(10);
        botaoCriarViagem = new JButton(bundle.getString("botao.criarViagem"));
        botaoCancelarViagem = new JButton(bundle.getString("botao.cancelar"));
        nomeCarro = new JLabel(bundle.getString("tela.nomeCarro.rotulo"));
        campoNomeCarro = new JTextField(10);
        modeloCarro = new JLabel(bundle.getString("tela.modeloCarro.rotulo"));
        campoModeloCarro = new JTextField(10);
        placaCarro = new JLabel(bundle.getString("tela.placaCarro.rotulo"));
        campoPlacaCarro = new JTextField(10);
        seguroCarro = new JLabel(bundle.getString("tela.seguroCarro.rotulo"));
        temSeguro = new JCheckBox(bundle.getString("botao.sim"));
        semSeguro = new JCheckBox(bundle.getString("botao.nao"));
        valorSeguro = new JLabel(bundle.getString("tela.valorSeguro.rotulo"));
        campoValorSeguro = new JTextField(10);

        criarViagem.setFont(criarViagem.getFont().deriveFont(Font.BOLD, 20));
        
        JPanel caixa = new JPanel();
        caixa.setLayout(new BoxLayout(caixa, BoxLayout.Y_AXIS));

        // Add components to layout
        adicionarComponentes(caixa);
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        contentPane.add(Box.createHorizontalStrut(10));
        contentPane.add(caixa);
        contentPane.add(Box.createHorizontalStrut(10));

        // Add listeners for actions
        adicionarListeners(conn, manager);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void adicionarComponentes(JPanel caixa) {
        JPanel caixaCriarViagem = new JPanel(new FlowLayout());
        caixaCriarViagem.add(criarViagem);
        caixa.add(caixaCriarViagem);
        caixa.add(Box.createVerticalStrut(10));

        adicionarCampo(caixa, nomeViagem, campoNomeViagem);
        adicionarCampo(caixa, descricaoViagem, campoDescricaoViagem);
        adicionarCampo(caixa, cidade, campoCidade);
        adicionarCampo(caixa, nomeHotel, campoNomeHotel);
        adicionarCampo(caixa, enderecoHotel, campoEnderecoHotel);
        adicionarCampo(caixa, checkInHotel, campoCheckInHotel);
        adicionarCampo(caixa, checkOutHotel, campoCheckOutHotel);
        adicionarCampo(caixa, dataInicio, campoDataInicio);
        adicionarCampo(caixa, dataFim, campoDataFim);
        
        JPanel caixaAlugarCarro = new JPanel(new FlowLayout());
        caixaAlugarCarro.add(alugarCarro);
        caixa.add(caixaAlugarCarro);

        JPanel caixaCarro = new JPanel(new FlowLayout());
        caixaCarro.add(temCarro);
        caixaCarro.add(semCarro);
        caixa.add(caixaCarro);

        JPanel caixaBotoes = new JPanel(new FlowLayout());
        caixaBotoes.add(botaoCriarViagem);
        caixaBotoes.add(Box.createHorizontalStrut(20));
        caixaBotoes.add(botaoCancelarViagem);
        caixa.add(caixaBotoes);
    }

    private void adicionarCampo(JPanel caixa, JLabel label, JTextField campo) {
        JPanel painel = new JPanel(new FlowLayout());
        painel.add(label);
        painel.add(campo);
        caixa.add(painel);
    }

    private void adicionarListeners(Connection conn, ManageUserLogin manager) {
        botaoCancelarViagem.addActionListener(e -> {
            new TelaPainelViagens(conn, manager, null);
            dispose();
        });

        botaoCriarViagem.addActionListener(e -> {
            Hotel hotel = new Hotel(campoNomeHotel.getText(), campoEnderecoHotel.getText(), campoCheckInHotel.getText(), campoCheckOutHotel.getText());
            Carro carro = new Carro(campoNomeCarro.getText(), campoModeloCarro.getText(), campoPlacaCarro.getText(), temSeguro.isSelected(), Integer.parseInt(campoValorSeguro.getText()),"Teste");
            Viagem trip = new Viagem(manager.getUser(), hotel, carro, campoDataInicio.getText(), campoDataFim.getText(), campoNomeViagem.getText(), campoDescricaoViagem.getText());

            try {
                hotel.inserir(conn);
                carro.inserir(conn);
                trip.inserir(conn);
                conn.commit();
            } catch (SQLException sql_ex) {
                sql_ex.printStackTrace();
            }

            new TelaPainelViagens(conn, manager, null);
            dispose();
        });
    }
}
