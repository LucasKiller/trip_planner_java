package Telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;

public class TelaPainelViagens extends JFrame {
    private JLabel painelControle;
    private JSeparator separador;
    private JMenuBar menuBar;
    private JMenu menuOpcoes;
    private JMenuItem addViagemItem;
    private JMenuItem editarViagemItem;
    private JMenu menuUser;
    private JMenuItem verUserItem;
    private JMenuItem editarUserItem;
    private JMenuItem sairUserItem;


    public TelaPainelViagens() {
        super("Painel de controle");

        painelControle = new JLabel("Painel de controle de viagens:");
        separador = new JSeparator(SwingConstants.HORIZONTAL);
        menuBar = new JMenuBar();
        menuOpcoes = new JMenu("Opções");
        addViagemItem = new JMenuItem("➕ Adicionar Viagem");
        editarViagemItem = new JMenuItem("🖊 Editar Viagem"); 
        menuUser = new JMenu("Usuário");
        verUserItem = new JMenuItem("👔 Ver Perfil");
        editarUserItem = new JMenuItem("🖊 Editar Perfil");
        sairUserItem = new JMenuItem("🖐 Sair");

        painelControle.setFont(painelControle.getFont().deriveFont(Font.BOLD, 16));
        painelControle.setAlignmentX(Component.CENTER_ALIGNMENT);

        separador.setForeground(Color.BLACK);

        JPanel caixa = new JPanel();
        caixa.setLayout(new BoxLayout(caixa, BoxLayout.Y_AXIS));

        caixa.add(painelControle);
        caixa.add(Box.createVerticalStrut(10));
        caixa.add(separador);

        menuOpcoes.add(addViagemItem);
        menuOpcoes.add(editarViagemItem);
        
        menuBar.add(menuOpcoes);

        menuUser.add(verUserItem);
        menuUser.add(editarUserItem);
        menuUser.add(sairUserItem);

        menuBar.add(menuUser);

        setJMenuBar(menuBar);

        JPanel painelBorda = new JPanel();
        painelBorda.setLayout(new BoxLayout(painelBorda, BoxLayout.Y_AXIS));

        painelBorda.add(Box.createVerticalStrut(10));
        painelBorda.add(caixa);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(painelBorda, BorderLayout.CENTER);

        addViagemItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaCriarViagem telaCriarViagem = new TelaCriarViagem();
                telaCriarViagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        editarViagemItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fazer a logica de editr
            }
        });

        verUserItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaVerPerfil telaVerPerfil = new TelaVerPerfil();
                telaVerPerfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        editarUserItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaEditarPerfil telaEditarPerfil = new TelaEditarPerfil();
                telaEditarPerfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
