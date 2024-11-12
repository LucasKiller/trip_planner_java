package telas;

import java.io.File;
import java.util.ResourceBundle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaEditarPerfil extends JFrame {
    private JLabel editarPerfil;
    private JLabel nomePerfil;
    private JTextField campoNomePerfil;
    private JLabel loginPerfil;
    private JTextField campoLoginPerfil;
    private JLabel senhaPerfil;
    private JTextField campoSenhaPerfil;
    private JLabel textImagemPerfil;
    private JLabel imagemLabelPerfil;
    private JButton botaoSelecionarImagemPerfil;
    private JButton botaoSalvar;
    private JButton botaoCancelar;

    public TelaEditarPerfil(ResourceBundle bundle) {
        super(bundle.getString("editarPerfil"));

        editarPerfil = new JLabel(bundle.getString("editarPerfil"));
        nomePerfil = new JLabel(bundle.getString("nomePerfil"));
        campoNomePerfil = new JTextField(10);
        loginPerfil = new JLabel(bundle.getString("loginPerfil"));
        campoLoginPerfil = new JTextField(10);
        senhaPerfil = new JLabel(bundle.getString("senhaPerfil"));
        campoSenhaPerfil = new JTextField(10);
        textImagemPerfil = new JLabel(bundle.getString("textImagemPerfil"));
        imagemLabelPerfil = new JLabel();
        botaoSelecionarImagemPerfil = new JButton(bundle.getString("botaoSelecionarImagemPerfil"));
        botaoSalvar = new JButton(bundle.getString("botaoSalvar"));
        botaoCancelar = new JButton(bundle.getString("botaoCancelar"));

        editarPerfil.setFont(editarPerfil.getFont().deriveFont(Font.BOLD, 20));

        JPanel caixa = new JPanel();
        caixa.setLayout(new BoxLayout(caixa, BoxLayout.Y_AXIS));

        JPanel caixaEditarPerfil = new JPanel();
        caixaEditarPerfil.setLayout(new FlowLayout());

        caixaEditarPerfil.add(editarPerfil);
        caixa.add(caixaEditarPerfil);

        caixa.add(Box.createVerticalStrut(10));

        JPanel caixaNomePerfil = new JPanel();
        caixaNomePerfil.setLayout(new FlowLayout());

        caixaNomePerfil.add(nomePerfil);
        caixaNomePerfil.add(campoNomePerfil);
        caixa.add(caixaNomePerfil);

        caixa.add(Box.createVerticalStrut(10));

        JPanel caixaLoginPerfil = new JPanel();
        caixaLoginPerfil.setLayout(new FlowLayout());

        caixaLoginPerfil.add(loginPerfil);
        caixaLoginPerfil.add(campoLoginPerfil);
        caixa.add(caixaLoginPerfil);

        caixa.add(Box.createVerticalStrut(10));

        JPanel caixaSenhaPerfil = new JPanel();
        caixaSenhaPerfil.setLayout(new FlowLayout());

        caixaSenhaPerfil.add(senhaPerfil);
        caixaSenhaPerfil.add(campoSenhaPerfil);
        caixa.add(caixaSenhaPerfil);

        caixa.add(Box.createVerticalStrut(10));

        JPanel caixaImagemPerfil = new JPanel();
        caixaImagemPerfil.setLayout(new FlowLayout());

        caixaImagemPerfil.add(textImagemPerfil);
        caixaImagemPerfil.add(botaoSelecionarImagemPerfil);
        caixa.add(caixaImagemPerfil);

        JPanel caixaImagemLabel = new JPanel();
        caixaImagemLabel.setLayout(new FlowLayout());

        caixaImagemLabel.add(imagemLabelPerfil);
        caixa.add(caixaImagemLabel);

        caixa.add(Box.createVerticalStrut(20));

        JPanel caixaBotoes = new JPanel();
        caixaBotoes.setLayout(new FlowLayout());

        caixaBotoes.add(botaoSalvar);
        caixaBotoes.add(Box.createHorizontalStrut(3));
        caixaBotoes.add(botaoCancelar);
        caixa.add(caixaBotoes);

        JPanel painelBorda = new JPanel();
        painelBorda.setLayout(new BoxLayout(painelBorda, BoxLayout.Y_AXIS));

        painelBorda.add(Box.createVerticalStrut(10));
        painelBorda.add(caixa);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(painelBorda, BorderLayout.CENTER);

        botaoSelecionarImagemPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon imagemIcon = new ImageIcon(selectedFile.getPath());
                    Image image = imagemIcon.getImage();
                    Image newimg = image.getScaledInstance(200, 150,  java.awt.Image.SCALE_SMOOTH);
                    imagemIcon = new ImageIcon(newimg);
                    imagemLabelPerfil.setIcon(imagemIcon);
                    pack();
                    setLocationRelativeTo(null);
                }
            }
        });

        botaoCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        caixa.add(Box.createVerticalStrut(10));
        pack();
        setLocationRelativeTo(null);
    }
}
