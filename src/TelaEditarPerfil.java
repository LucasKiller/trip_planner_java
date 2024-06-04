import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;

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
    
    public TelaEditarPerfil() {
        super("Edição de Perfil");

        editarPerfil = new JLabel("Altere seus dados");
        nomePerfil = new JLabel("Editar nome:");
        campoNomePerfil = new JTextField(10); //deixar o nome do perfil aqui
        loginPerfil = new JLabel("Editar login:");
        campoLoginPerfil = new JTextField(10); //deixar o login do perfil aqui
        senhaPerfil = new JLabel("Editar senha:");
        campoSenhaPerfil = new JTextField(10); //deixar a senha do perfil aqui se não tiver tempo
        textImagemPerfil = new JLabel("Editar imagem:");
        imagemLabelPerfil = new JLabel();
        botaoSelecionarImagemPerfil = new JButton("Selecionar");
        botaoSalvar = new JButton("Salvar");
        botaoCancelar = new JButton("Cancelar");

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
