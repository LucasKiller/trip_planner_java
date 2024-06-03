import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class TelaInicial extends JFrame {
    private JLabel bemVindo;
    private JLabel textLogin;
    private JButton botaoLogin;
    private JTextField campoLogin;
    private JTextField campoSenha;
    private JLabel textRegistrar;
    private JButton botaoRegistrar;
    private JTextField campoNovoUser;
    private JTextField campoNovoLogin;
    private JTextField campoNovaSenha;

    private Connection connection;
    private ManageUserLogin manager;

    public TelaInicial() {
        super("Login");

        manager = new ManageUserLogin();

        bemVindo = new JLabel("Seja bem-vindo ao Trip Planner Java!");
        botaoLogin = new JButton("Entrar");
        textLogin = new JLabel("Login:");
        campoLogin = new JTextField("Login", 15);
        campoSenha = new JTextField("Senha", 15);
        textRegistrar = new JLabel("Não tem uma conta ainda?");
        botaoRegistrar = new JButton("Registrar");
        campoNovoUser = new JTextField("Usuário", 15);
        campoNovoLogin = new JTextField("Login", 15);
        campoNovaSenha = new JTextField("Senha", 15);

        bemVindo.setFont(bemVindo.getFont().deriveFont(Font.BOLD, 16));

        campoLogin.setMaximumSize(new Dimension(campoLogin.getPreferredSize().width, campoLogin.getPreferredSize().height));
        campoSenha.setMaximumSize(new Dimension(campoSenha.getPreferredSize().width, campoSenha.getPreferredSize().height));
        
        campoNovoUser.setMaximumSize(new Dimension(campoNovoUser.getPreferredSize().width, campoNovoUser.getPreferredSize().height));
        campoNovoLogin.setMaximumSize(new Dimension(campoNovoLogin.getPreferredSize().width, campoNovoLogin.getPreferredSize().height));
        campoNovaSenha.setMaximumSize(new Dimension(campoNovaSenha.getPreferredSize().width, campoNovaSenha.getPreferredSize().height));

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
                    String senha = campoSenha.getText();

                    if (login.isEmpty() || senha.isEmpty()) {
                        JOptionPane.showMessageDialog(caixa, "Preencha todos os campos para logar!");
                    } else {
                        int result = manager.logUser(connection, login, senha);
                        if (result == -1) {
                            JOptionPane.showMessageDialog(caixa, "Login ou senha incorretos!");
                        } else {
                            JOptionPane.showMessageDialog(caixa, "Login bem-sucedido!");
                        }
                }
            }
        }});

        botaoRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (campoNovoLogin.getParent() == null) {
                    caixa.add(campoNovoUser, -1);
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
                    String novoUser = campoNovoUser.getText();
                    String novoLogin = campoNovoLogin.getText();
                    String novaSenha = campoNovaSenha.getText();

                    if (novoUser.isEmpty() || novoLogin.isEmpty() || novaSenha.isEmpty()) {
                        JOptionPane.showMessageDialog(caixa, "Preencha todos os campos para se registrar!");
                    } else {
                        manager.registerUser(connection, novoLogin, novaSenha, novoUser);
                    }
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        caixa.add(Box.createVerticalStrut(10));
        pack();
        setLocationRelativeTo(null);
    }
}
