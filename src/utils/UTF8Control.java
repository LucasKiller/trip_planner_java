package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class UTF8Control extends ResourceBundle.Control {
    @Override
    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
            throws IOException {
        // Define o nome do arquivo com base na localidade
        String bundleName = toBundleName(baseName, locale);
        String resourceName = toResourceName(bundleName, "properties");

        try (InputStream stream = loader.getResourceAsStream(resourceName);
             Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            return new PropertyResourceBundle(reader);
        }
    }
}
