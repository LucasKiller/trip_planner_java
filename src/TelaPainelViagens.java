import javax.swing.*;
import java.awt.*;

public class TelaPainelViagens extends JFrame {
    private JLabel painelControle;
    private JSeparator separador;
    private JButton addViagem;

    public TelaPainelViagens() {
        super("Painel de controle");

        painelControle = new JLabel("Painel de controle de viagens:");
        separador = new JSeparator(SwingConstants.HORIZONTAL);
        addViagem = new JButton("+ Adicionar Viagem");

        painelControle.setFont(painelControle.getFont().deriveFont(Font.BOLD, 16));
        painelControle.setAlignmentX(Component.CENTER_ALIGNMENT);

        separador.setForeground(Color.BLACK);

        addViagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        addViagem.setAlignmentY(Component.CENTER_ALIGNMENT);

        Container caixa = getContentPane();
        caixa.setLayout(new BoxLayout(caixa, BoxLayout.Y_AXIS));

        caixa.add(Box.createVerticalStrut(10));
        caixa.add(painelControle);
        caixa.add(Box.createVerticalStrut(10));
        caixa.add(separador);
        caixa.add(addViagem);

        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
    }
}
