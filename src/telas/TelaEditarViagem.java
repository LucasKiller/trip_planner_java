package telas;

import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.FlowLayout;
import java.io.File;
import java.util.ResourceBundle;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEditarViagem extends JFrame {
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
    private JLabel textImagem;
    private JLabel imagemLabel;
    private JButton botaoSelecionarImagem;
    private JButton botaoEditarViagem;
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
    private JLabel textImagemCarro;
    private JLabel imagemLabelCarro;
    private JButton botaoSelecionarImagemCarro;

    public TelaEditarViagem(ResourceBundle bundle) {
        super(bundle.getString("tela.editarViagem.titulo"));

        criarViagem = new JLabel(bundle.getString("tela.editarViagem.titulo"));
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
        textImagem = new JLabel(bundle.getString("tela.imagemViagem.rotulo"));
        botaoSelecionarImagem = new JButton(bundle.getString("botao.selecionarImagem"));
        imagemLabel = new JLabel();
        botaoEditarViagem = new JButton(bundle.getString("botao.editarViagem"));
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
        textImagemCarro = new JLabel(bundle.getString("tela.imagemCarro.rotulo"));
        imagemLabelCarro = new JLabel();
        botaoSelecionarImagemCarro = new JButton(bundle.getString("botao.selecionarImagemCarro"));

        criarViagem.setFont(criarViagem.getFont().deriveFont(Font.BOLD, 20));

        JPanel caixa = new JPanel();
        caixa.setLayout(new BoxLayout(caixa, BoxLayout.Y_AXIS));

        adicionarComponente(caixa, criarViagem);
        adicionarCampo(caixa, nomeViagem, campoNomeViagem);
        adicionarCampo(caixa, descricaoViagem, campoDescricaoViagem);
        adicionarCampo(caixa, cidade, campoCidade);
        adicionarCampo(caixa, nomeHotel, campoNomeHotel);
        adicionarCampo(caixa, enderecoHotel, campoEnderecoHotel);
        adicionarCampo(caixa, checkInHotel, campoCheckInHotel);
        adicionarCampo(caixa, checkOutHotel, campoCheckOutHotel);
        adicionarCampo(caixa, dataInicio, campoDataInicio);
        adicionarCampo(caixa, dataFim, campoDataFim);
        adicionarComponente(caixa, textImagem);
        adicionarComponente(caixa, imagemLabel);

        JPanel caixaAlugarCarro = new JPanel(new FlowLayout());
        caixaAlugarCarro.add(alugarCarro);
        caixaAlugarCarro.add(temCarro);
        caixaAlugarCarro.add(semCarro);
        caixa.add(caixaAlugarCarro);

        JPanel caixaBotoes = new JPanel(new FlowLayout());
        caixaBotoes.add(botaoEditarViagem);
        caixaBotoes.add(botaoCancelarViagem);
        caixa.add(caixaBotoes);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(caixa);

        // Listeners
        botaoSelecionarImagem.addActionListener(e -> selecionarImagem(imagemLabel));
        botaoSelecionarImagemCarro.addActionListener(e -> selecionarImagem(imagemLabelCarro));
        botaoCancelarViagem.addActionListener(e -> dispose());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void adicionarComponente(JPanel caixa, JLabel label) {
        JPanel painel = new JPanel(new FlowLayout());
        painel.add(label);
        caixa.add(painel);
    }

    private void adicionarCampo(JPanel caixa, JLabel label, JTextField campo) {
        JPanel painel = new JPanel(new FlowLayout());
        painel.add(label);
        painel.add(campo);
        caixa.add(painel);
    }

    private void selecionarImagem(JLabel imagemLabel) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon imagemIcon = new ImageIcon(selectedFile.getPath());
            Image image = imagemIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            imagemLabel.setIcon(new ImageIcon(image));
            pack();
        }
    }
}
