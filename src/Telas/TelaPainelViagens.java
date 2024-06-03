package Telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;

public class TelaPainelViagens extends JFrame {
    private JLabel painelControle;
    private JSeparator separador;
    private JButton addViagem;
    private JButton editarViagem;

    public TelaPainelViagens() {
        super("Painel de controle");

        painelControle = new JLabel("Painel de controle de viagens:");
        separador = new JSeparator(SwingConstants.HORIZONTAL);
        addViagem = new JButton("➕ Adicionar Viagem");
        editarViagem = new JButton("🖊 Editar Viagem");

        painelControle.setFont(painelControle.getFont().deriveFont(Font.BOLD, 16));
        painelControle.setAlignmentX(Component.CENTER_ALIGNMENT);

        separador.setForeground(Color.BLACK);

        JPanel caixa = new JPanel();
        caixa.setLayout(new BoxLayout(caixa, BoxLayout.Y_AXIS));

        caixa.add(painelControle);
        caixa.add(Box.createVerticalStrut(10));
        caixa.add(separador);

        JPanel painelEsquerda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelEsquerda.add(Box.createHorizontalStrut(10));
        painelEsquerda.add(editarViagem);

        JPanel painelDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelDireita.add(addViagem);
        painelDireita.add(Box.createHorizontalStrut(10));

        JPanel painelOpcoes = new JPanel(new BorderLayout());

        painelOpcoes.add(painelEsquerda, BorderLayout.WEST);
        painelOpcoes.add(painelDireita, BorderLayout.EAST);
        
        caixa.add(Box.createVerticalStrut(10));
        caixa.add(painelOpcoes);

        JPanel painelBorda = new JPanel();
        painelBorda.setLayout(new BoxLayout(painelBorda, BoxLayout.Y_AXIS));

        painelBorda.add(Box.createVerticalStrut(10));
        painelBorda.add(caixa);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(painelBorda, BorderLayout.CENTER);

        addViagem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaCriarViagem telaCriarViagem = new TelaCriarViagem();
                telaCriarViagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
