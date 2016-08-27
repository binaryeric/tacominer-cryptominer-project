package tacoMiner.util;


import java.security.MessageDigest;

/**
 * Static utility class that just does sha256 hashing.
 */
public class SHA256 {
    private static MessageDigest md;

    public static void InitMD() {
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            System.out.println("MessageDigest Init Exception");
        }
    }

    public static String Hash256(String plain) {
        try {
            md.update(plain.getBytes("UTF-8"));
            byte[] dg = md.digest();
            StringBuilder digest = new StringBuilder();
            for (byte a : dg) {
                digest.append(String.format("%02x", a));
            }
            return digest.toString();
        } catch (Exception e) {
            System.out.println("Hash Exception: Plaintext UTF-8 Byte Exception");
        }
        return null;
    }

    public static byte[] ByteArrayHash256(String plain) {
        /*
        Returns a byte array of the SHA256 Hash.
         */
        try {
            md.update(plain.getBytes("UTF-8"));
            return md.digest();
        } catch (Exception e) {
            System.out.println("Hash Exception: Plaintext UTF-8 Byte Exception");
        }
        return null;
    }
}
