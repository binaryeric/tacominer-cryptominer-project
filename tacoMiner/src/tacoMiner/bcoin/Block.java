package tacoMiner.bcoin;

import tacoMiner.main.tacoInit;
import tacoMiner.util.SHA256;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.time.Instant;

public class Block extends Thread {
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public String version;
    public String previous_block_header_hash; //char[32]
    public String merkle_root_hash;
    public String time;
    public String nBits;
    public int nonce;
    String savedHeader = "";
    byte[] savedHeaderBytes;
    byte[] nbitsByte;
    private String plain; // if performance is bad then you can use char array
    private ByteBuffer plainByteArray;
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

    public static final byte[] intToByteArray(int value) {
        return new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value};
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public void run() {
        time = Long.toString(Instant.EPOCH.getEpochSecond());
        tacoInit.setTime(ByteBuffer.allocate(4).putInt((int) Instant.EPOCH.getEpochSecond()).array());
        while (true) {
            try {
                Thread.sleep(950);
                System.out.println(tacoInit.hashesCount + " : H/pS");
                tacoInit.hashesCount = 0;
                time = Long.toString(Instant.EPOCH.getEpochSecond());
                tacoInit.setTime(ByteBuffer.allocate(4).putInt((int) Instant.EPOCH.getEpochSecond()).array());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int intEndian(int i) {
        return (i & 0xff) << 24 | (i & 0xff00) << 8 | (i & 0xff0000) >> 8 | (i >> 24) & 0xff;
    }

    public int abs(int n) {
        return (n ^ (n >> 31)) + (n >>> 31);
    }

    public void saveHeader() {
        savedHeader = version + previous_block_header_hash + merkle_root_hash;
    }

    public void convertValsToBytes() {
        nbitsByte = hexStringToByteArray(nBits);
        savedHeaderBytes = hexStringToByteArray(savedHeader);
    }

    public byte[] getHash(int _nonce) throws UnsupportedEncodingException {
        nonce = _nonce;
        // System.out.println("Old " + _nonce);
        _nonce = abs(intEndian(_nonce));
        // System.out.println("New " + _nonce);
        int neededMem = savedHeaderBytes.length + nbitsByte.length + 8; //8 = time + nonce
        plainByteArray = plainByteArray.allocate(neededMem).put(savedHeaderBytes).
                put(tacoInit.timeBytes()).
                put(nbitsByte).
                putInt(_nonce);
        //plain = savedHeader + tacoInit.time() + nBits + nonce;

        /*
        System.out.println("PLAIN --===========--");
        for(Byte b : plainByteArray.array()){
            System.out.print(String.format("%02X", b));
        }
        System.out.println("\n===========");
        */

        return SHA256.hasher(plainByteArray.array());//SHA256.EndianReverse(hasher(hasher(plain)));
    }
}
