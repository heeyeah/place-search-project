package place.common;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;
import java.io.DataInputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;

public class CommonUtil {



    public static class Encrypt {
        private static String key = null;
        private static String _cipherAlgorithm = "TripleDES";

        public static void main(String[] args) {
            String de =CommonUtil.Encrypt.encrypt("heeye");
            System.out.println(de);
            System.out.println(CommonUtil.Encrypt.decrypt(de));
        }

        static {
            InputStream inStream = null;
            try {
                inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(CommonUtil.class.getName().replace('.', '/') + ".class");
                DataInputStream din = new DataInputStream(inStream);
                int keyvalue = din.readInt();
                key = Integer.toHexString(keyvalue) + "ran!dom$21-hee#;";
            } catch (Throwable th) {
            } finally {
                if (inStream != null) try {
                    inStream.close();
                } catch (Exception e) {
                }
            }
        }

        public static String encrypt(String plain) {
            try {
                SecretKeySpec ks = new SecretKeySpec(key.getBytes(), _cipherAlgorithm);
                Cipher cipher = Cipher.getInstance(_cipherAlgorithm);
                cipher.init(Cipher.ENCRYPT_MODE, ks);
                byte[] encd = cipher.doFinal(plain.getBytes());
                String encdString = new sun.misc.BASE64Encoder().encode(encd);
                return encdString;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return plain;
            }
        }

        public static String decrypt(String encrypted) {
            try {
                SecretKeySpec ks = new SecretKeySpec(key.getBytes(), _cipherAlgorithm);
                Cipher cipher = Cipher.getInstance(_cipherAlgorithm);
                cipher.init(Cipher.DECRYPT_MODE, ks);
                byte[] decryptedBytes = cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(encrypted));
                String decrypted = new String(decryptedBytes);
                return decrypted;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return encrypted;
            }
        }

    }

}
