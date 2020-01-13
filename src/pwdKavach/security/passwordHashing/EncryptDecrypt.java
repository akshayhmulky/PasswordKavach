package pwdKavach.security.passwordHashing;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/**
 * Program to Encrypt/Decrypt String Using AES 128 bit Encryption Algorithm
 */
public class EncryptDecrypt {

    private static final String encryptionKey = "ABCDEFGHIJKLMNOP";
    private static final String characterEncoding = "UTF-8";
    private static final String cipherTransformation = "AES/CBC/PKCS5PADDING";
    private static final String aesEncryptionAlgorithem = "AES";
    //String key = "gfg3rewtg323efdgferyre";

    /**
     * Method for Encrypt Plain String Data
     *
     * @param plainText
     * @return encryptedText
     */
    public static String encryptAccountPassword(String plainText) {
        String encryptedText = "";
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedText = encoder.encodeToString(cipherText);

        } catch (Exception E) {
            System.err.println("Encrypt Exception : " + E.getMessage());
        }
        return encryptedText;
    }
    /**
     * Method For Get encryptedText and Decrypted provided String
     *
     * @param encryptedText
     * @return decryptedText
     */
    public static String decryptAccountPassword(String encryptedText) {
        String decryptedText = "";
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(encryptedText.getBytes("UTF8"));
            decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");

        } catch (Exception E) {
            System.err.println("decrypt Exception : " + E.getMessage());
        }
        return decryptedText;
    }
    

 //$$$$$$$$$$$$$$$$$$$$$$$new code $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    /*
    public static byte[] getRandomInitialVector() {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecureRandom randomSecureRandom = SecureRandom.getInstance("SHA1PRNG");
            byte[] initVector = new byte[cipher.getBlockSize()];
            randomSecureRandom.nextBytes(initVector);
            return initVector;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static byte[] passwordTo16BitKey(String password) {
        try {
            byte[] srcBytes = password.getBytes("UTF-8");
            byte[] dstBytes = new byte[16];

            if (srcBytes.length == 16) {
                return srcBytes;
            }

            if (srcBytes.length < 16) {
                for (int i = 0; i < dstBytes.length; i++) {
                    dstBytes[i] = (byte) ((srcBytes[i % srcBytes.length]) * (srcBytes[(i + 1) % srcBytes.length]));
                }
            } else if (srcBytes.length > 16) {
                for (int i = 0; i < srcBytes.length; i++) {
                    dstBytes[i % dstBytes.length] += srcBytes[i];
                }
            }

            return dstBytes;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String encryptAccountPassword(String value) {
        String key = "gfg3rewtg323efdgferyre";
        return encryptAccountPassword(passwordTo16BitKey(key), value);
    }

    public static String encryptAccountPassword(byte[] key, String value) {
        try {
           // byte[] initVector = Encryptor.getRandomInitialVector();
            byte[] initVector = EncryptDecrypt.getRandomInitialVector();
            IvParameterSpec iv = new IvParameterSpec(initVector);
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted) + " " + Base64.getEncoder().encodeToString(initVector);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decryptAccountPassword(String encrypted) {
        String key = "gfg3rewtg323efdgferyre";
        return decryptAccountPassword(passwordTo16BitKey(key), encrypted);
    }

    public static String decryptAccountPassword(byte[] key, String encrypted) {
        try {
            String[] encryptedParts = encrypted.split(" ");
            byte[] initVector = Base64.getDecoder().decode(encryptedParts[1]);
            if (initVector.length != 16) {
                return null;
            }

            IvParameterSpec iv = new IvParameterSpec(initVector);
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedParts[0]));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    */
    

}
