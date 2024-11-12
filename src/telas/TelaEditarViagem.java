package telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import classes.*;

import entities.Viagem;
import enums.*;



public class TelaEditarViagem extends JFrame{
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
    // private JLabel textImagem;
    // private JLabel imagemLabel;
    // private JButton botaoSelecionarImagem;
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
    // private JLabel textImagemCarro;
    // private JLabel imagemLabelCarro;
    // private JButton botaoSelecionarImagemCarro;

    public TelaEditarViagem(ClientSocket clientSocket, Viagem viagem, TelaModificarViagens telaModificarViagens) {
        super("Edição de Viagem");

        criarViagem = new JLabel("Altere os dados da viagem");
        nomeViagem = new JLabel("Nome da viagem:");
        campoNomeViagem = new JTextField(viagem.getNomeViagem(), 10);
        descricaoViagem = new JLabel("Descrição da viagem:");
        campoDescricaoViagem = new JTextField(viagem.getDescricaoViagem(), 10);
        nomeHotel = new JLabel("Hotel:");
        campoNomeHotel = new JTextField(viagem.getHotel().getNome(), 10);
        enderecoHotel = new JLabel("Endereço:");
        campoEnderecoHotel = new JTextField(viagem.getHotel().getEndereco(), 10);
        checkInHotel = new JLabel("Check-in:");
        campoCheckInHotel = new JTextField(viagem.getHotel().getCheckin(), 10);
        checkOutHotel = new JLabel("Check-out:");
        campoCheckOutHotel = new JTextField(viagem.getHotel().getCheckout(), 10);
        alugarCarro = new JLabel("Você vai alugar um carro?");
        temCarro = new JCheckBox("Sim", viagem.isTemCarro());
        semCarro = new JCheckBox("Não", !viagem.isTemCarro());
        dataInicio = new JLabel("Data de início:");
        campoDataInicio = new JTextField(viagem.getDiaInicial(), 10);
        dataFim = new JLabel("Data de fim:");
        campoDataFim = new JTextField(viagem.getDiaFinal(), 10);
        // textImagem = new JLabel("Imagem:");
        // botaoSelecionarImagem = new JButton("Selecionar Imagem");
        // imagemLabel = new JLabel();
        botaoCriarViagem = new JButton("Editar Viagem");
        botaoCancelarViagem = new JButton("Cancelar");
        nomeCarro = new JLabel("Nome do carro:");
        campoNomeCarro = new JTextField(viagem.getCarro().getNome(), 10);
        modeloCarro = new JLabel("Modelo do carro:");
        campoModeloCarro = new JTextField(viagem.getCarro().getMarca(), 10);
        placaCarro = new JLabel("Placa do carro:");
        campoPlacaCarro = new JTextField(viagem.getCarro().getPlaca(), 10);
        seguroCarro = new JLabel("Você contratou um seguro?");
        temSeguro = new JCheckBox("Sim", viagem.getCarro().isTemSeguro());
        semSeguro = new JCheckBox("Não", !viagem.getCarro().isTemSeguro());
        valorSeguro = new JLabel("Valor do seguro:");
        campoValorSeguro = new JTextField(String.valueOf(viagem.getCarro().getValorSeguro()), 10);
        // textImagemCarro = new JLabel("Imagem Carro:");
        // imagemLabelCarro = new JLabel();
        // botaoSelecionarImagemCarro = new JButton("Selecionar Imagem");

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

        JPanel caixaImagem = new JPanel();
        caixaImagem.setLayout(new FlowLayout());

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

        temCarro.setSelected(false);
        semCarro.setSelected(true);

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

                    nomeCarro.setVisible(false);
                    campoNomeCarro.setVisible(false);
                    modeloCarro.setVisible(false);
                    campoModeloCarro.setVisible(false);
                    placaCarro.setVisible(false);
                    campoPlacaCarro.setVisible(false);
                    seguroCarro.setVisible(false);
                    temSeguro.setVisible(false);
                    semSeguro.setVisible(false);
                    valorSeguro.setVisible(false);
                    campoValorSeguro.setVisible(false);
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

        botaoCriarViagem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viagem.setNomeViagem(campoNomeViagem.getText());
                viagem.setDescricaoViagem(campoDescricaoViagem.getText());
                viagem.setDiaInicial(campoDataInicio.getText());
                viagem.setDiaFinal(campoDataFim.getText());

                viagem.getHotel().setNome(campoNomeHotel.getText());
                viagem.getHotel().setEndereco(campoEnderecoHotel.getText());
                viagem.getHotel().setCheckin(campoCheckInHotel.getText());
                viagem.getHotel().setCheckout(campoCheckOutHotel.getText());

                if (temCarro.isSelected()) {
                    viagem.getCarro().setNome(campoNomeCarro.getText());
                    viagem.getCarro().setMarca(campoModeloCarro.getText());
                    viagem.getCarro().setPlaca(campoPlacaCarro.getText());
                    viagem.getCarro().setTemSeguro(temSeguro.isSelected());
                    int valorSeguroInt = campoValorSeguro.getText().isEmpty() ? 0 : Integer.parseInt(campoValorSeguro.getText());
                    viagem.getCarro().setValorSeguro(valorSeguroInt);

                } else {
                    viagem.getCarro().setNome("");
                    viagem.getCarro().setMarca("");
                    viagem.getCarro().setPlaca("");
                    viagem.getCarro().setTemSeguro(false);
                    viagem.getCarro().setValorSeguro(0);
                }

                Request req = new Request(RequestType.UPDATE_TRIP, viagem); 
                
                clientSocket.doRequest(req);

                telaModificarViagens.dispose();

                dispose();

                new TelaModificarViagens(clientSocket);
            }
        });

        botaoCancelarViagem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

        caixa.add(Box.createVerticalStrut(10));
        pack();
        setLocationRelativeTo(null);
        
    }
}

