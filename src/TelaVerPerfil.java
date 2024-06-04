import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Box;

public class TelaVerPerfil extends JFrame{
    private JLabel fotoUser;
    private JLabel nomeUser;
    private JLabel loginUser;
    private JButton botaoVoltar;
    
    public TelaVerPerfil() {
        super("Perfil");

        fotoUser = new JLabel("Foto do usuário");
        nomeUser = new JLabel("Nome do usuário");
        loginUser = new JLabel("Login do usuário");
        botaoVoltar = new JButton("Voltar");

        fotoUser.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        nomeUser.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        loginUser.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        botaoVoltar.setAlignmentX(JButton.CENTER_ALIGNMENT);

        JPanel caixa = new JPanel();
        caixa.setLayout(new BoxLayout(caixa, BoxLayout.Y_AXIS));

        caixa.add(fotoUser);
        caixa.add(Box.createVerticalStrut(10));
        caixa.add(nomeUser);
        caixa.add(Box.createVerticalStrut(10));
        caixa.add(loginUser);
        caixa.add(Box.createVerticalStrut(20));
        caixa.add(botaoVoltar);

        botaoVoltar.addActionListener((e) -> {
            dispose();
        });

        JPanel painelBorda = new JPanel();
        painelBorda.setLayout(new BoxLayout(painelBorda, BoxLayout.Y_AXIS));

        painelBorda.add(Box.createVerticalStrut(10));
        painelBorda.add(caixa);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(painelBorda, BorderLayout.CENTER);

        setSize(new Dimension(220, 180));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        caixa.add(Box.createVerticalStrut(10));
        setLocationRelativeTo(null);
    }
}
