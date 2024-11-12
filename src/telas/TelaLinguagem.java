package telas;

import javax.swing.*;

import classes.ClientSocket;
import classes.Request;
import enums.RequestType;
import utils.HandleLanguageChoice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class TelaLinguagem extends JFrame {

    private JComboBox<String> languageComboBox;
    private JButton confirmButton;
    private JLabel titleLabel;
    private ResourceBundle bundle;

    private static String[] idiomas = {"Português (Brasil)", "Espanhol (Espanha)", "Francês (França)", "Inglês (EUA)", "Mandarim (China)"};
    private static Locale[] locales = {new Locale("pt", "BR"), new Locale("es", "ES"), new Locale("fr", "FR"), new Locale("en", "US"), new Locale("zh", "CN")};
    
    public TelaLinguagem(ClientSocket clientSocket) {
        // Configurações da janela
        setTitle("Seleção de Idioma");
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Carrega o idioma padrão
        try {
            bundle = ResourceBundle.getBundle("utils.lang.TelaLinguagem_Portugues_Brasil", locales[0]);
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
                HandleLanguageChoice.setDefinedLang(selectedIndex);
                JOptionPane.showMessageDialog(TelaLinguagem.this, bundle.getString("confirmButton") + " selecionado.");
                new TelaInicial(clientSocket, HandleLanguageChoice.getDefinedLang("TelaInicial"));
            }
        });
        selectionPanel.add(confirmButton);

        add(selectionPanel, BorderLayout.CENTER);

       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Desconecta do DB ao fechar a janela
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clientSocket.doRequest(new Request(RequestType.CLOSE_CONNECTION, new Object[0]));
            }
        });
        setVisible(true);
        setLocationRelativeTo(null);


    }

    
}