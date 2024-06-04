package Telas;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classes.ManageUserLogin;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.sql.Connection;

import javax.swing.Box;

public class TelaVerPerfil extends JFrame{
    private JLabel nomeUser;
    private JLabel loginUser;
    private JButton botaoVoltar;
    
    public TelaVerPerfil(Connection conn, ManageUserLogin manager) {
        super("Perfil");

        nomeUser = new JLabel("Nome: " + manager.getUser().getNome());
        loginUser = new JLabel("Usuário: @" + manager.getUser().getUser());
        botaoVoltar = new JButton("Voltar");

        nomeUser.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        loginUser.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        botaoVoltar.setAlignmentX(JButton.CENTER_ALIGNMENT);

        JPanel caixa = new JPanel();
        caixa.setLayout(new BoxLayout(caixa, BoxLayout.Y_AXIS));

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
