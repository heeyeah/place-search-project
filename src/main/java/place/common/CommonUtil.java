package place.common;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.DataInputStream;
import java.io.InputStream;

public class CommonUtil {

    public static class Encrypt {

        private static String key = null;
        private static String _cipherAlgorithm = "TripleDES";

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
                String encdString = new BASE64Encoder().encode(encd);
                return encdString;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return plain;
            }
        }

//        public static String decrypt(String encrypted) {
//            try {
//                SecretKeySpec ks = new SecretKeySpec(key.getBytes(), _cipherAlgorithm);
//                Cipher cipher = Cipher.getInstance(_cipherAlgorithm);
//                cipher.init(Cipher.DECRYPT_MODE, ks);
//                byte[] decryptedBytes = cipher.doFinal(new BASE64Decoder().decodeBuffer(encrypted));
//                String decrypted = new String(decryptedBytes);
//                return decrypted;
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                return encrypted;
//            }
//        }
    }

}
