package tacoMiner.process.cpu;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

import tacoMiner.debug.Log;
import tacoMiner.process.*;
import tacoMiner.util.SHA256;

public class BlockMiner implements Miner {

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    byte[] savedHeaderBytes, nbitsByte;
    private Processor cpu;
    private Log logger;
    private String _ver, pbhh, mrh, _time, _nBits, savedHeader;
    private int _nonce;
    private MessageDigest md;
    private SHA256 SHA;

    private ByteBuffer plainByteArray;

    public BlockMiner(String ver, String pb, String mroot, String t, String bit) {
        _ver = ver;
        pbhh = pb; // Previous block header hash
        mrh = mroot; // merkle root hash
        _time = t;
        _nBits = bit;

        cpu = CPU.getInstance();
        logger = Log.getInstance();

        SHA = new SHA256();

        initMD();
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

    private void initMD() {
        try {
            md = MessageDigest.getInstance("SHA-256");
            SHA.InitMD(md);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void mine() {
        new Thread(new Runnable() {
            public void run() {
                while (cpu.isAlive()) {
                    try {
                        Thread.sleep(950);
                        _time = Long.toString(Instant.EPOCH.getEpochSecond());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    @Override
    public void saveHeader() {
        savedHeader = _ver + pbhh + mrh;  // + blockheader + merkleroothash;
    }

    @Override
    public void convertValsToBytes() {
        nbitsByte = hexStringToByteArray(_nBits);
        savedHeaderBytes = hexStringToByteArray(savedHeader);
    }

    @Override
    public void allocateByteArray() {
        int neededMem = savedHeaderBytes.length + nbitsByte.length + 8;
        plainByteArray = ByteBuffer.allocateDirect(neededMem);
    }

    @Override
    public byte[] getHash(int nonce) {
        _nonce = nonce;
        nonce = abs(intEndian(nonce));

        plainByteArray.position(0);
        plainByteArray = plainByteArray.put(savedHeaderBytes).
                put(MinerController.getTime()).
                put(nbitsByte).
                putInt(nonce);

        return SHA.hasher(plainByteArray);//SHA256.EndianReverse(hasher(hasher(plain)));
    }

    private int intEndian(int i) {
        return (i & 0xff) << 24 | (i & 0xff00) << 8 | (i & 0xff0000) >> 8 | (i >> 24) & 0xff;
    }

    private int abs(int n) {
        return (n ^ (n >> 31)) + (n >>> 31);
    }

    private void reverse(byte[] array) {
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            byte b = array[i];
            array[i] = array[j];
            array[j] = b;
        }
    }
}
