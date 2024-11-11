package telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Locale;
import java.util.ResourceBundle;
import utils.ConnectDB;

public class TelaEscolherIdioma extends JFrame {
    private JComboBox<String> comboIdiomas;
    private JButton botaoConfirmar;
    private String[] idiomas = {"Português", "Español", "Français", "English", "中文"};
    private Locale[] locales = {
        new Locale("pt", "BR"),
        new Locale("es", "ES"),
        new Locale("fr", "FR"),
        new Locale("en", "US"),
        new Locale("zh", "CN")
    };

    public TelaEscolherIdioma(Connection conn) {
        super("Escolha de Idioma");

        JLabel label = new JLabel("Escolha o idioma:");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        comboIdiomas = new JComboBox<>(idiomas);
        comboIdiomas.setMaximumSize(new Dimension(200, 30));
        
        botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.add(label);
        painel.add(Box.createVerticalStrut(10));
        painel.add(comboIdiomas);
        painel.add(Box.createVerticalStrut(20));
        painel.add(botaoConfirmar);

        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboIdiomas.getSelectedIndex();
                Locale selectedLocale = locales[selectedIndex];
                ResourceBundle bundle = ResourceBundle.getBundle("messages", selectedLocale);

                // Abre a TelaInicial com o idioma selecionado
                new TelaInicial(conn, bundle);
                dispose();
            }
        });

        add(painel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
