package tacoMiner.bcoin;

import tacoMiner.util.SHA256;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.time.Instant;

public class Block implements Runnable {
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public String version;
    public String previous_block_header_hash; //char[32]
    public String merkle_root_hash;
    public String time;
    public String nBits;
    public String nonce;
    String savedHeader = "";
    private String plain; // if performance is bad then you can use char array
    public Block(String _ver, String pbhh, String mrh, String _time, String _nBits) {
        version = _ver;
        previous_block_header_hash = pbhh;
        merkle_root_hash = mrh;
        time = _time;
        nBits = _nBits;
    }

    public static void reverse(byte[] array) {
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            byte b = array[i];
            array[i] = array[j];
            array[j] = b;
        }
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public void startClock() {
        Thread t = new Thread();
        t.start();
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(800);
                time = Long.toString(Instant.EPOCH.getEpochSecond());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String hasher(String plain) {
        return DatatypeConverter.printHexBinary(SHA256.RawHash256(plain));
    }

    private byte[] hasher2(String plain) {
        return SHA256.RawHash256(plain);
    }

    public void saveHeader() {
        savedHeader = version + previous_block_header_hash + merkle_root_hash;
    }

    public String getHash(String _nonce) throws UnsupportedEncodingException {
        nonce = _nonce;
        StringBuilder bnonce = new StringBuilder(nonce);
        for (int i = 0; i < ((nonce.length() % 2 == 1 ? 1 : 0) + nonce.length() - nonce.length()); i++) {
            bnonce = bnonce.insert(0, "0");
            //pad hex: example:
            //f
            //turns into
            //0f
        }
        nonce = SHA256.EndianReverse(bnonce.toString());

        plain = savedHeader + time + nBits + nonce; //compiler will auto-optimize by using +
        //returns bytearray for performance, so instead of strng-bytearray it just returns byte-array
        System.out.println(plain);
        System.out.println(SHA256.EndianReverse(hasher(hasher(plain))));
        return SHA256.EndianReverse(hasher(hasher(plain)));
    }
}
