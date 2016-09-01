package tacoMiner.util;

import java.security.MessageDigest;

/**
 * Static utility class that just does sha256 hashing.
 */
public class SHA256 {
    private static MessageDigest md;

    public static String EndianReverse(String s) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < s.length() - 1; i += 2) {
            str.insert(0, s.substring(i, i + 2));
        }
        return str.toString();
    }

    public static String convertHexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);

            temp.append(decimal);
        }
        return sb.toString();
    }

    public static void InitMD() {
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            System.out.println("MessageDigest Init Exception");
        }
    }

    public static String Hash256(String plain) {
        //Converts String to Byte Array then Hash
        try {
            md.update(plain.getBytes("ASCII"));
            byte[] dg = md.digest();
            StringBuilder digest = new StringBuilder();
            for (byte a : dg) {
                digest.append(String.format("%02x", a));
            }
            return digest.toString();
        } catch (Exception e) {
            System.out.println("Hash Exception: Plaintext ASCII Byte Exception");
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] RawHash256(String plain) {
        //Does Direct Byte Hash
        try {
            byte[] bytebuff = new byte[plain.length() / 2];
            int z = 0;

            System.out.println(plain);
            for (int i = 0; i < plain.length(); i = i + 2) {
                bytebuff[z] = Integer.decode("0x" + plain.substring(i, i + 2)).byteValue();
                z = z + 1;
            }
            md.update(bytebuff);
            return md.digest();
        } catch (Exception e) {
            System.out.println("Hash Exception: Plaintext ASCII Byte Exception");
            e.printStackTrace();
            Runtime.getRuntime().halt(1);
        }
        return null;
    }

    public static byte[] ByteRawHash256(byte[] plain) {
        //Does Direct Byte Hash
        try {
            md.update(plain);
            return md.digest();
        } catch (Exception e) {
            System.out.println("Hash Exception: Raw Byte Hash");
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] ByteArrayHash256(byte[] plain) {
        /*
        Returns a byte array of the SHA256 Hash.
         */
        return md.digest(plain);
    }
}
