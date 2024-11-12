package telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import classes.ClientSocket;
import classes.ManageUserInstance;
import classes.Request;
import classes.Response;
import entities.User;
import enums.*;

public class TelaInicial extends JFrame {
    private JLabel bemVindo;
    private JLabel textLogin;
    private JButton botaoLogin;
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JLabel textRegistrar;
    private JButton botaoRegistrar;
    private JTextField campoNovoNome; // senha
    private JTextField campoNovoLogin; // nome
    private JPasswordField campoNovaSenha; // login

    public TelaInicial(ClientSocket clientSocket) {
        super("Login");

        bemVindo = new JLabel("Seja bem-vindo ao Trip Planner Java!");
        botaoLogin = new JButton("Entrar");
        textLogin = new JLabel("Login:");
        campoLogin = new JTextField("Login", 15);
        campoSenha = new JPasswordField("Senha", 15);
        textRegistrar = new JLabel("Não tem uma conta ainda?");
        botaoRegistrar = new JButton("Registrar");
        campoNovoNome = new JTextField("Usuário", 15);
        campoNovoLogin = new JTextField("Login", 15);
        campoNovaSenha = new JPasswordField("Senha", 15);

        bemVindo.setFont(bemVindo.getFont().deriveFont(Font.BOLD, 16));

        campoLogin.setMaximumSize(
                new Dimension(campoLogin.getPreferredSize().width, campoLogin.getPreferredSize().height));
        campoSenha.setMaximumSize(
                new Dimension(campoSenha.getPreferredSize().width, campoSenha.getPreferredSize().height));

        campoNovoNome.setMaximumSize(
                new Dimension(campoNovoNome.getPreferredSize().width, campoNovoNome.getPreferredSize().height));
        campoNovoLogin.setMaximumSize(
                new Dimension(campoNovoLogin.getPreferredSize().width, campoNovoLogin.getPreferredSize().height));
        campoNovaSenha.setMaximumSize(
                new Dimension(campoNovaSenha.getPreferredSize().width, campoNovaSenha.getPreferredSize().height));

        bemVindo.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        textRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel caixa = new JPanel();
        caixa.setLayout(new BoxLayout(caixa, BoxLayout.Y_AXIS));

        caixa.add(bemVindo);
        caixa.add(Box.createVerticalStrut(20));
        caixa.add(textLogin);
        caixa.add(Box.createVerticalStrut(10));
        caixa.add(botaoLogin);
        caixa.add(Box.createVerticalStrut(10));
        caixa.add(textRegistrar);
        caixa.add(Box.createVerticalStrut(10));
        caixa.add(botaoRegistrar);

        JPanel painelBorda = new JPanel();
        painelBorda.setLayout(new BoxLayout(painelBorda, BoxLayout.Y_AXIS));

        painelBorda.add(Box.createVerticalStrut(10));
        painelBorda.add(caixa);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        contentPane.add(Box.createHorizontalStrut(10));
        contentPane.add(painelBorda);
        contentPane.add(Box.createHorizontalStrut(10));

        botaoLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (campoLogin.getParent() == null) {
                    caixa.add(Box.createVerticalStrut(10), 6);
                    caixa.add(campoSenha, 6);
                    caixa.add(Box.createVerticalStrut(3), 6);
                    caixa.add(campoLogin, 6);
                    caixa.revalidate();
                    pack();
                } else {
                    String login = campoLogin.getText();
                    String senha = new String(campoSenha.getPassword());

                    if (login.isEmpty() || senha.isEmpty()) {
                        JOptionPane.showMessageDialog(caixa, "Preencha todos os campos para logar!");
                    } else {

                        Request req = new Request(RequestType.LOG_USER, new User(login, senha));

                        Response res = clientSocket.doRequest(req);
                        
                        if (res.getType() == ResponseType.USER_NOT_LOGGED) {
                            JOptionPane.showMessageDialog(caixa, "Login ou senha incorretos!");
                        } else if (res.getType() == ResponseType.USER_LOGGED) {

                            ManageUserInstance.setUserInstance( (User) res.getParameters()[0]);

                            JOptionPane.showMessageDialog(caixa, "Login bem-sucedido!");
                            new TelaPainelViagens(clientSocket);
                            dispose();
                        }
                    }
                }
            }
        });

        botaoRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (campoNovoLogin.getParent() == null) {
                    caixa.add(campoNovoNome, -1);
                    caixa.add(Box.createVerticalStrut(3), -1);
                    caixa.add(campoNovoLogin, -1);
                    caixa.add(Box.createVerticalStrut(3), -1);
                    caixa.add(campoNovaSenha, -1);
                    caixa.add(Box.createVerticalStrut(3), -1);
                    caixa.revalidate();
                    caixa.add(Box.createVerticalStrut(10));
                    pack();
                }

                else {
                    String novoNome = campoNovoNome.getText();
                    String novoLogin = campoNovoLogin.getText();
                    String novaSenha = new String(campoNovaSenha.getPassword());

                    if (novoNome.isEmpty() || novoLogin.isEmpty() || novaSenha.isEmpty()) {
                        JOptionPane.showMessageDialog(caixa, "Preencha todos os campos para se registrar!");
                    } else {
                        try {

                            Request req = new Request(RequestType.CREATE_USER, new User(novoNome, novoLogin, novaSenha));

                            Response res = clientSocket.doRequest(req);

                            if(res.getType() == ResponseType.USER_NOT_CREATED) {
                                JOptionPane.showMessageDialog(contentPane, "Registro não foi efetuado! Usuário já existente!");
                            } else if (res.getType() == ResponseType.USER_CREATED){
                                JOptionPane.showMessageDialog(contentPane, "Registro efetuado com sucesso!");
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
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
