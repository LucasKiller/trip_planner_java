package utils;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import telas.TelaInicial;

public class HandleLanguageChoice {

    private static int langChoice;
    private static Locale[] locales = { new Locale("pt", "BR"), new Locale("es", "ES"), new Locale("fr", "FR"),
            new Locale("en", "US"), new Locale("zh", "CN") };

    // private static ResourseBundle bundle = null;

    public static void setDefinedLang(int defined) {
        langChoice = defined;
    }

    public static ResourceBundle getDefinedLang(String currentScreen) {

        String baseName = "utils.lang." + currentScreen;
        ResourceBundle bundle = null;

        try {
            switch (langChoice) {
                case 0:
                    bundle = ResourceBundle.getBundle(baseName + "_Portugues_Brasil", locales[langChoice]);
                    break;
                case 1:
                    bundle = ResourceBundle.getBundle(baseName + "_Espanhol_Espanha", locales[langChoice]);
                    break;
                case 2:
                    bundle = ResourceBundle.getBundle(baseName + "_Frances_Franca", locales[langChoice]);
                    break;
                case 3:
                    bundle = ResourceBundle.getBundle(baseName + "_Ingles_USA", locales[langChoice]);
                    break;
                case 4:
                    bundle = ResourceBundle.getBundle(baseName + "_Mandarim_China", locales[langChoice]);
                    break;
            }
        } catch (Exception ex) {
             ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar o recurso de idioma", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return bundle;

    }

}
