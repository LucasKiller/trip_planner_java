package utils;

import java.io.File;

/*
 *  O objetivo dessa classe é gerenciar a chave simetrica da criptografia AES.
 *  Caso o arquivo de chave nao exista inicialmente, essa classe fica responsavel por executar a classe de geração da chave,
 *  CryptoAES, e gerar uma nova
 */

public class CryptoKeyHandler {

    private static CryptoAES caes = new CryptoAES();
    private static File KEY = new File("src/utils/key/AES.key");

    public static boolean keyExists() {
        return KEY.exists();
    }

    public static void generateKey() throws Exception {
        if (!keyExists()) {
            caes.geraChave(new File("src/utils/key/AES.key"));
        }
    }

    public static String getCipherPass(String pass) {

        String result = "";

        try {

            caes.geraCifra(pass.getBytes("ISO-8859-1"), KEY);
            byte[] cyphredPass = caes.getTextoCifrado();
            result = new String(cyphredPass, "ISO-8859-1");

        } catch (Exception ex) {
            System.err.println("Não foi possível realizar a criptografia: " + ex);
        }

        return result;

    }

    public static String getDecipherPass(String cipherPass) {

        String result = "";

        try {
            caes.geraDecifra(cipherPass.getBytes("ISO-8859-1"), KEY);
            byte[] pass = caes.getTextoDecifrado();
            result = new String(pass, "ISO-8859-1");
        } catch (Exception ex) {
            System.err.println("Não foi possível realizar a decriptografia: " + ex);
        }

        return result;

    }

}
