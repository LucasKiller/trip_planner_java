package telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.util.ResourceBundle;
import classes.ManageUserLogin;
import utils.ConnectDB;

public class TelaInicial extends JFrame {
    private JLabel bemVindo;
    private JLabel textLogin;
    private JButton botaoLogin;
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JLabel textRegistrar;
    private JButton botaoRegistrar;
    private JTextField campoNovoNome;
    private JTextField campoNovoLogin;
    private JPasswordField campoNovaSenha;

    private ManageUserLogin manager;

    public TelaInicial(Connection conn, ResourceBundle bundle) {
        super(bundle.getString("titulo.telaInicial"));
        manager = new ManageUserLogin();

        bemVindo = new JLabel(bundle.getString("bemVindo"));
        botaoLogin = new JButton(bundle.getString("botaoLogin"));
        textLogin = new JLabel(bundle.getString("textLogin"));
        campoLogin = new JTextField(bundle.getString("campoLogin"), 15);
        campoSenha = new JPasswordField(bundle.getString("campoSenha"), 15);
        textRegistrar = new JLabel(bundle.getString("textRegistrar"));
        botaoRegistrar = new JButton(bundle.getString("botaoRegistrar"));
        campoNovoNome = new JTextField(bundle.getString("campoNovoNome"), 15);
        campoNovoLogin = new JTextField(bundle.getString("campoNovoLogin"), 15);
        campoNovaSenha = new JPasswordField(bundle.getString("campoNovaSenha"), 15);

        bemVindo.setFont(bemVindo.getFont().deriveFont(Font.BOLD, 16));
        
        // Definir o tamanho máximo dos campos de entrada
        campoLogin.setMaximumSize(new Dimension(campoLogin.getPreferredSize().width, campoLogin.getPreferredSize().height));
        campoSenha.setMaximumSize(new Dimension(campoSenha.getPreferredSize().width, campoSenha.getPreferredSize().height));
        campoNovoNome.setMaximumSize(new Dimension(campoNovoNome.getPreferredSize().width, campoNovoNome.getPreferredSize().height));
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
        caixa.add(campoLogin);
        caixa.add(Box.createVerticalStrut(10));
        caixa.add(campoSenha);
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
                String login = campoLogin.getText();
                String senha = new String(campoSenha.getPassword());

                if (login.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(caixa, bundle.getString("erro.camposObrigatoriosLogin"));
                } else {
                    int result = manager.logUser(conn, login, senha);
                    if (result == -1) {
                        JOptionPane.showMessageDialog(caixa, bundle.getString("erro.loginIncorreto"));
                    } else {
                        JOptionPane.showMessageDialog(caixa, bundle.getString("confirmacao.loginSucesso"));
                        new TelaPainelViagens(conn, manager, bundle); // Passa o bundle para a próxima tela
                        dispose();
                    }
                }
            }
        });

        botaoRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (campoNovoLogin.getParent() == null) {
                    caixa.add(campoNovoNome);
                    caixa.add(Box.createVerticalStrut(3));
                    caixa.add(campoNovoLogin);
                    caixa.add(Box.createVerticalStrut(3));
                    caixa.add(campoNovaSenha);
                    caixa.add(Box.createVerticalStrut(3));
                    caixa.revalidate();
                    pack();
                } else {
                    String novoNome = campoNovoNome.getText();
                    String novoLogin = campoNovoLogin.getText();
                    String novaSenha = new String(campoNovaSenha.getPassword());

                    if (novoNome.isEmpty() || novoLogin.isEmpty() || novaSenha.isEmpty()) {
                        JOptionPane.showMessageDialog(caixa, bundle.getString("erro.camposObrigatoriosRegistro"));
                    } else {
                        int result = manager.registerUser(conn, novoNome, novoLogin, novaSenha);
                        if (result == -1) {
                            JOptionPane.showMessageDialog(caixa, bundle.getString("erro.registroFalhou"));
                        } else {
                            JOptionPane.showMessageDialog(caixa, bundle.getString("confirmacao.registroSucesso"));
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
                ConnectDB.desconectar(conn);
            }
        });
        
        setVisible(true);
        caixa.add(Box.createVerticalStrut(10));
        pack();
        setLocationRelativeTo(null);
    }
}
