package tacoMiner.bcoin;

import tacoMiner.util.SHA256;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;

public class Block {
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public String version;
    public String previous_block_header_hash; //char[32]
    public String merkle_root_hash;
    public String time;
    public String nBits;
    public String nonce;
    private String plain; // if performance is bad then you can use char array

    public Block(String _ver, String pbhh, String mrh, String _time, String _nBits) {
        version = _ver;
        previous_block_header_hash = pbhh;
        merkle_root_hash = mrh;
        time = _time;
        nBits = _nBits;
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

    private String hasher(String plain) {
        return DatatypeConverter.printHexBinary(SHA256.RawHash256(plain));
    }

    public String getHash(String _nonce) throws UnsupportedEncodingException {
        nonce = _nonce;
        plain = version + previous_block_header_hash + merkle_root_hash + time + nBits + nonce;
        System.out.println(plain);
        return hasher(hasher(plain));
    }
}
