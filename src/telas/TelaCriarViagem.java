package telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

import classes.ClientSocket;
import classes.ManageUserInstance;
import classes.Request;
import entities.*;

import enums.RequestType;
import utils.HandleLanguageChoice;

public class TelaCriarViagem extends JFrame {
    private JLabel criarViagem;
    private JLabel nomeViagem;
    private JTextField campoNomeViagem;
    private JLabel descricaoViagem;
    private JTextField campoDescricaoViagem;
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
    private static ResourceBundle bundle = HandleLanguageChoice.getDefinedLang("TelaCriarViagem");

    public TelaCriarViagem(ClientSocket clientSocket) {
        super(bundle.getString("tela.criarViagem.titulo"));

        criarViagem = new JLabel(bundle.getString("tela.criarViagem.titulo"));
        nomeViagem = new JLabel(bundle.getString("tela.nomeViagem.rotulo"));
        campoNomeViagem = new JTextField(10);
        descricaoViagem = new JLabel(bundle.getString("tela.descricaoViagem.rotulo"));
        campoDescricaoViagem = new JTextField(10);
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

        JPanel caixaCriarViagem = new JPanel();
        caixaCriarViagem.setLayout(new FlowLayout());

        caixaCriarViagem.add(criarViagem);
        caixa.add(caixaCriarViagem);

        caixa.add(Box.createVerticalStrut(10));

        JPanel caixaViagem = new JPanel();
        caixaViagem.setLayout(new FlowLayout());
        caixaViagem.add(nomeViagem);
        caixaViagem.add(campoNomeViagem);
        caixa.add(caixaViagem);
        
        JPanel caixaDescricao = new JPanel();
        caixaDescricao.setLayout(new FlowLayout());

        caixaDescricao.add(descricaoViagem);
        caixaDescricao.add(campoDescricaoViagem);
        caixa.add(caixaDescricao);

        JPanel caixaNomeHotel = new JPanel();
        caixaNomeHotel.setLayout(new FlowLayout());

        caixaNomeHotel.add(nomeHotel);
        caixaNomeHotel.add(campoNomeHotel);
        caixa.add(caixaNomeHotel);

        JPanel caixaEnderecoHotel = new JPanel();
        caixaEnderecoHotel.setLayout(new FlowLayout());

        caixaEnderecoHotel.add(enderecoHotel);
        caixaEnderecoHotel.add(campoEnderecoHotel);
        caixa.add(caixaEnderecoHotel);

        JPanel caixaCheckInHotel = new JPanel();
        caixaCheckInHotel.setLayout(new FlowLayout());

        caixaCheckInHotel.add(checkInHotel);
        caixaCheckInHotel.add(campoCheckInHotel);
        caixa.add(caixaCheckInHotel);

        JPanel caixaCheckOutHotel = new JPanel();
        caixaCheckOutHotel.setLayout(new FlowLayout());

        caixaCheckOutHotel.add(checkOutHotel);
        caixaCheckOutHotel.add(campoCheckOutHotel);
        caixa.add(caixaCheckOutHotel);

        JPanel caixaDataInicio = new JPanel();
        caixaDataInicio.setLayout(new FlowLayout());

        caixaDataInicio.add(dataInicio);
        caixaDataInicio.add(campoDataInicio);
        caixa.add(caixaDataInicio);

        JPanel caixaDataFim = new JPanel();
        caixaDataFim.setLayout(new FlowLayout());

        caixaDataFim.add(dataFim);
        caixaDataFim.add(campoDataFim);
        caixa.add(caixaDataFim);

        // JPanel caixaImagem = new JPanel();
        // caixaImagem.setLayout(new FlowLayout());

        // caixaImagem.add(textImagem);
        // caixaImagem.add(botaoSelecionarImagem);
        // caixa.add(caixaImagem);

        // JPanel caixaImagemLabel = new JPanel();
        // caixaImagemLabel.setLayout(new FlowLayout());

        // caixaImagemLabel.add(imagemLabel);
        // caixa.add(caixaImagemLabel);

        JPanel caixaAlugarCarro = new JPanel();
        caixaAlugarCarro.setLayout(new FlowLayout());

        caixaAlugarCarro.add(alugarCarro);
        caixa.add(caixaAlugarCarro);

        JPanel caixaCarro = new JPanel();
        caixaCarro.setLayout(new FlowLayout());

        caixaCarro.add(temCarro);
        caixaCarro.add(semCarro);
        caixa.add(caixaCarro);        

        caixa.add(Box.createVerticalStrut(1));



        caixa.add(Box.createVerticalStrut(1));

        JPanel painelBorda = new JPanel();
        painelBorda.setLayout(new BoxLayout(painelBorda, BoxLayout.Y_AXIS));
        
        painelBorda.add(Box.createVerticalStrut(10));
        painelBorda.add(caixa);
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        contentPane.add(Box.createHorizontalStrut(10));
        contentPane.add(painelBorda);
        contentPane.add(Box.createHorizontalStrut(10));

        temCarro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (temCarro.isSelected()) {
                    semCarro.setSelected(false);

                    nomeCarro.setVisible(true);
                    campoNomeCarro.setVisible(true);
                    modeloCarro.setVisible(true);
                    campoModeloCarro.setVisible(true);
                    placaCarro.setVisible(true);
                    campoPlacaCarro.setVisible(true);
                    seguroCarro.setVisible(true);
                    temSeguro.setVisible(true);
                    semSeguro.setVisible(true);
                    // textImagemCarro.setVisible(true);
                    // botaoSelecionarImagemCarro.setVisible(true);
                    // imagemLabelCarro.setVisible(true);

                    if (campoNomeCarro.getParent() == null) {
                        JPanel caixaNomeCarro = new JPanel();
                        caixaNomeCarro.setLayout(new FlowLayout());

                        caixa.add(Box.createVerticalStrut(10), 15);
                        caixaNomeCarro.add(nomeCarro);
                        caixaNomeCarro.add(campoNomeCarro);
                        caixa.add(caixaNomeCarro, 14);

                        JPanel caixaModeloCarro = new JPanel();
                        caixaModeloCarro.setLayout(new FlowLayout());

                        caixaModeloCarro.add(modeloCarro);
                        caixaModeloCarro.add(campoModeloCarro);
                        caixa.add(caixaModeloCarro, 15);

                        JPanel caixaPlacaCarro = new JPanel();
                        caixaPlacaCarro.setLayout(new FlowLayout());

                        caixaPlacaCarro.add(placaCarro);
                        caixaPlacaCarro.add(campoPlacaCarro);
                        caixa.add(caixaPlacaCarro, 16);

                        JPanel caixaSeguroCarro = new JPanel();
                        caixaSeguroCarro.setLayout(new FlowLayout());

                        caixaSeguroCarro.add(seguroCarro);
                        caixa.add(caixaSeguroCarro, 17);

                        JPanel caixaCarroSeguro = new JPanel();
                        caixaCarroSeguro.setLayout(new FlowLayout());

                        caixaCarroSeguro.add(temSeguro);
                        caixaCarroSeguro.add(semSeguro);
                        caixa.add(caixaCarroSeguro, 18);

                        // JPanel caixaImagemCarro = new JPanel();
                        // caixaImagemCarro.setLayout(new FlowLayout());

                        // caixaImagemCarro.add(textImagemCarro);
                        // caixaImagemCarro.add(botaoSelecionarImagemCarro);
                        // caixa.add(caixaImagemCarro, 20);

                        // JPanel caixaImagemLabelCarro = new JPanel();
                        // caixaImagemLabelCarro.setLayout(new FlowLayout());

                        // caixaImagemLabelCarro.add(imagemLabelCarro);
                        // caixa.add(caixaImagemLabelCarro, 21);
                        caixa.add(Box.createVerticalStrut(10), 19);

                        caixa.revalidate();
                        pack();
                        setLocationRelativeTo(null);
                    }
                } else {
                    nomeCarro.setVisible(false);
                    campoNomeCarro.setVisible(false);
                    modeloCarro.setVisible(false);
                    campoModeloCarro.setVisible(false);
                    placaCarro.setVisible(false);
                    campoPlacaCarro.setVisible(false);
                    seguroCarro.setVisible(false);
                    temSeguro.setVisible(false);
                    semSeguro.setVisible(false);
                    // textImagemCarro.setVisible(false);
                    // botaoSelecionarImagemCarro.setVisible(false);
                    // imagemLabelCarro.setVisible(false);
                }
                caixa.revalidate();
                pack();
        }});

        semCarro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (semCarro.isSelected()) {
                    temCarro.setSelected(false);

                    campoNomeCarro.setText("");
                    campoModeloCarro.setText("");
                    campoPlacaCarro.setText("");
                    campoValorSeguro.setText("0");
                    semSeguro.setSelected(true);

                    nomeCarro.setVisible(false);
                    campoNomeCarro.setVisible(false);
                    modeloCarro.setVisible(false);
                    campoModeloCarro.setVisible(false);
                    placaCarro.setVisible(false);
                    campoPlacaCarro.setVisible(false);
                    seguroCarro.setVisible(false);
                    temSeguro.setVisible(false);
                    semSeguro.setVisible(false);
                    // textImagemCarro.setVisible(false);
                    // botaoSelecionarImagemCarro.setVisible(false);
                    // imagemLabelCarro.setVisible(false);
                }
                pack();
            }
        });

        temSeguro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (temSeguro.isSelected()) {
                    semSeguro.setSelected(false);

                    valorSeguro.setVisible(true);
                    campoValorSeguro.setVisible(true);

                    if (campoValorSeguro.getParent() == null) {
                        JPanel caixaValorSeguro = new JPanel();
                        caixaValorSeguro.setLayout(new FlowLayout());

                        caixaValorSeguro.add(valorSeguro);
                        caixaValorSeguro.add(campoValorSeguro);
                        caixa.add(caixaValorSeguro, 19);

                        caixa.revalidate();
                        pack();
                    }
                } else {
                    valorSeguro.setVisible(false);
                    campoValorSeguro.setVisible(false);
            }
        }});

        semSeguro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (semSeguro.isSelected()) {
                    temSeguro.setSelected(false);

                    campoValorSeguro.setText("0");

                    valorSeguro.setVisible(false);
                    campoValorSeguro.setVisible(false);
                };
                pack();
            }
        });

        // botaoSelecionarImagem.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         JFileChooser fileChooser = new JFileChooser();
        //         int returnValue = fileChooser.showOpenDialog(null);
        //         if (returnValue == JFileChooser.APPROVE_OPTION) {
        //             File selectedFile = fileChooser.getSelectedFile();
        //             ImageIcon imagemIcon = new ImageIcon(selectedFile.getPath());
        //             Image image = imagemIcon.getImage();
        //             Image newimg = image.getScaledInstance(200, 150,  java.awt.Image.SCALE_SMOOTH);
        //             imagemIcon = new ImageIcon(newimg);
        //             imagemLabel.setIcon(imagemIcon);
        //             pack();
        //             setLocationRelativeTo(null);
        //         }
        //     }
        // });

        // botaoSelecionarImagemCarro.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         JFileChooser fileChooser = new JFileChooser();
        //         int returnValue = fileChooser.showOpenDialog(null);
        //         if (returnValue == JFileChooser.APPROVE_OPTION) {
        //             File selectedFile = fileChooser.getSelectedFile();
        //             ImageIcon imagemIcon = new ImageIcon(selectedFile.getPath());
        //             Image image = imagemIcon.getImage();
        //             Image newimg = image.getScaledInstance(200, 150,  java.awt.Image.SCALE_SMOOTH);
        //             imagemIcon = new ImageIcon(newimg);
        //             imagemLabelCarro.setIcon(imagemIcon);
        //             pack();
        //             setLocationRelativeTo(null);
        //         }
        //     }
        // });

        JPanel caixaBotoes = new JPanel();
        caixaBotoes.setLayout(new FlowLayout());

        caixaBotoes.add(botaoCriarViagem);
        caixaBotoes.add(Box.createHorizontalStrut(20));
        caixaBotoes.add(botaoCancelarViagem);
        caixa.add(caixaBotoes);

        botaoCancelarViagem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TelaPainelViagens(clientSocket);
                dispose();
            }
        });

        botaoCriarViagem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                Hotel hotel = new Hotel(campoNomeHotel.getText(), campoEnderecoHotel.getText(),campoCheckInHotel.getText(), campoCheckOutHotel.getText());
                Carro carro = new Carro(campoNomeCarro.getText(), campoModeloCarro.getText(), campoPlacaCarro.getText(), temSeguro.isSelected(), Integer.parseInt(campoValorSeguro.getText()),"Teste");

                // Request reqUser = new Request(RequestType.GET_USER, new Object[0]);

                // Response resUser = clientSocket.doRequest(reqUser);

                User user = ManageUserInstance.getUserInstance();

                Viagem trip = new Viagem(user, hotel, carro, campoDataFim.getText(), campoDataInicio.getText(), campoNomeViagem.getText(), campoDescricaoViagem.getText());

                Request reqCreateTrip = new Request(RequestType.CREATE_TRIP, hotel, carro, trip);

                clientSocket.doRequest(reqCreateTrip);
                
                new TelaPainelViagens(clientSocket);
                dispose();
            }
        });

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

        caixa.add(Box.createVerticalStrut(10));
        pack();
    }
}
