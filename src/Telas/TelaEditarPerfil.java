package Telas;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    
    public TelaEditarPerfil() {
        super("Editar Perfil");

        editarPerfil = new JLabel("Altere seus dados");
        nomePerfil = new JLabel("Editar nome:");
        campoNomePerfil = new JTextField(); //deixar o nome do perfil aqui
        loginPerfil = new JLabel("Editar login:");
        campoLoginPerfil = new JTextField(); //deixar o login do perfil aqui
        senhaPerfil = new JLabel("Editar senha:");
        campoSenhaPerfil = new JTextField(); //deixar a senha do perfil aqui se não tiver tempo
        textImagemPerfil = new JLabel("Editar imagem:");
        imagemLabelPerfil = new JLabel();
        botaoSelecionarImagemPerfil = new JButton("Selecionar imagem");

        editarPerfil.setFont(editarPerfil.getFont().deriveFont(Font.BOLD, 20));

        JPanel caixa = new JPanel();
        caixa.setLayout(new BoxLayout(caixa, BoxLayout.Y_AXIS));

        JPanel caixaEditarPerfil = new JPanel();
        caixaEditarPerfil.setLayout(new FlowLayout());

        caixaEditarPerfil.add(editarPerfil);
        caixa.add(caixaEditarPerfil);

    }
}
