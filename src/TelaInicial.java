import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JFrame implements ActionListener {
    private JLabel bemVindo;
    private JButton botaoLogin;
    private JTextField campoLogin;
    private JTextField campoSenha;
    private JLabel textRegistrar;
    private JButton botaoRegistrar;
    private JTextField campoNovoUser;
    private JTextField campoNovoLogin;
    private JTextField campoNovaSenha;

    public TelaInicial() {
        super("Login e Cadastro");

        bemVindo = new JLabel("Seja bem-vindo ao Trip Planner Java!");
        botaoLogin = new JButton("Entrar");
        campoLogin = new JTextField("Login", 20);
        campoSenha = new JTextField("Senha", 20);
        textRegistrar = new JLabel("Não tem uma conta ainda?");
        botaoRegistrar = new JButton("Registrar");
        campoNovoUser = new JTextField("Usuário", 20);
        campoNovoLogin = new JTextField("Login", 20);
        campoNovaSenha = new JTextField("Senha", 20);

        campoLogin.setMaximumSize(new Dimension(campoLogin.getPreferredSize().width, campoLogin.getPreferredSize().height));
        campoSenha.setMaximumSize(new Dimension(campoSenha.getPreferredSize().width, campoSenha.getPreferredSize().height));
        
        campoNovoUser.setMaximumSize(new Dimension(campoNovoUser.getPreferredSize().width, campoNovoUser.getPreferredSize().height));
        campoNovoLogin.setMaximumSize(new Dimension(campoNovoLogin.getPreferredSize().width, campoNovoLogin.getPreferredSize().height));
        campoNovaSenha.setMaximumSize(new Dimension(campoNovaSenha.getPreferredSize().width, campoNovaSenha.getPreferredSize().height));

        Container caixa = getContentPane();
        caixa.setLayout(new BoxLayout(caixa, BoxLayout.Y_AXIS));

        bemVindo.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        textRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        caixa.add(Box.createVerticalStrut(10));
        caixa.add(bemVindo);
        caixa.add(Box.createVerticalStrut(10), 2);
        caixa.add(botaoLogin);
        caixa.add(Box.createVerticalStrut(10), 4);
        caixa.add(textRegistrar);
        caixa.add(Box.createVerticalStrut(10));
        caixa.add(botaoRegistrar);

        botaoLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (campoLogin.getParent() == null) {
                    caixa.add(campoSenha, 4);
                    caixa.add(campoLogin, 4);
                    caixa.add(Box.createVerticalStrut(10), 4);
                    caixa.revalidate();
                } else {
                    JOptionPane.showMessageDialog(caixa, "Preencha o seu login e senha!");
                }
            }
        });

        botaoRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (campoNovoLogin.getParent() == null) {
                    caixa.add(Box.createVerticalStrut(10), -1);
                    caixa.add(campoNovoUser, -1);
                    caixa.add(campoNovoLogin, -1);
                    caixa.add(campoNovaSenha, -1);
                    caixa.revalidate();
                } else {
                    JOptionPane.showMessageDialog(caixa, "Cadastre o seu login e senha!");
                }
            }
        });

        setSize(350, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        
    }
}