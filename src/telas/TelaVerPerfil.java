package telas;

import javax.swing.*;
import java.awt.*;

import classes.*;
import entities.*;

public class TelaVerPerfil extends JFrame{
    private JLabel nomeUser;
    private JLabel loginUser;
    private JButton botaoVoltar;
    
    public TelaVerPerfil(ClientSocket clientSocket) {
        super("Perfil");

        // Request req = new Request(RequestType.GET_USER, new Object[0]);

        // Response res = clientSocket.doRequest(req);

        // User user = (User) res.getParameters()[0];

        User user = ManageUserInstance.getUserInstance();

        nomeUser = new JLabel("Nome: " + user.getNome());
        loginUser = new JLabel("Usuário: @" + user.getUser());
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
