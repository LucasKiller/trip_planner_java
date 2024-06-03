import javax.swing.*;
import java.awt.*;

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

        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
