package telas;

import javax.swing.*;

import classes.ClientSocket;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class TelaLinguagem extends JFrame {

    private JComboBox<String> languageComboBox;
    private JButton confirmButton;
    private JLabel titleLabel;
    private ResourceBundle bundle;

    // Opções de idiomas e seus códigos de Locale
    private String[] idiomas = {"Português (Brasil)", "Espanhol (Espanha)", "Francês (França)", "Inglês (EUA)", "Mandarim (China)"};
    private Locale[] locales = {new Locale("pt", "BR"), new Locale("es", "ES"), new Locale("fr", "FR"), new Locale("en", "US"), new Locale("zh", "CN")};

    public TelaLinguagem(ClientSocket clientSocket) {
        // Configurações da janela
        setTitle("Seleção de Idioma");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Carrega o idioma padrão
        try {
            bundle = ResourceBundle.getBundle("TelaLinguagem_Portugues_Brasil", locales[0]);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar o recurso de idioma", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Título na parte superior com o texto do arquivo de propriedades
        titleLabel = new JLabel(bundle.getString("title"), JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Painel central com ComboBox e botão Confirmar
        JPanel selectionPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        selectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));

        // ComboBox para seleção de linguagem
        languageComboBox = new JComboBox<>(idiomas);
        languageComboBox.setPreferredSize(new Dimension(200, 30));
        JPanel comboPanel = new JPanel(); // Um painel para centralizar a ComboBox
        comboPanel.add(languageComboBox);
        selectionPanel.add(comboPanel);

        // Botão de confirmação com texto do arquivo de propriedades
        confirmButton = new JButton(bundle.getString("confirmButton"));
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = languageComboBox.getSelectedIndex();
                changeLanguage(selectedIndex);
                JOptionPane.showMessageDialog(TelaLinguagem.this, bundle.getString("confirmButton") + " selecionado.");
                new TelaInicial(clientSocket, bundle);
            }
        });
        selectionPanel.add(confirmButton);

        add(selectionPanel, BorderLayout.CENTER);
    }

    // Método para trocar o idioma
    private void changeLanguage(int index) {
        try {
            String baseName = "utils.lang.TelaLinguagem";
            switch (index) {
                case 0:
                    bundle = ResourceBundle.getBundle(baseName + "_Portugues_Brasil", locales[index]);
                    break;
                case 1:
                    bundle = ResourceBundle.getBundle(baseName + "_Espanhol_Espanha", locales[index]);
                    break;
                case 2:
                    bundle = ResourceBundle.getBundle(baseName + "_Frances_Franca", locales[index]);
                    break;
                case 3:
                    bundle = ResourceBundle.getBundle(baseName + "_Ingles_USA", locales[index]);
                    break;
                case 4:
                    bundle = ResourceBundle.getBundle(baseName + "_Mandarim_China", locales[index]);
                    break;
            }
            titleLabel.setText(bundle.getString("title"));
            confirmButton.setText(bundle.getString("confirmButton"));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar o recurso de idioma", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}