package tacoMiner.main;

import org.json.JSONException;
import tacoMiner.bcoin.BcoinCLI;
import tacoMiner.bcoin.Block;
import tacoMiner.debug.Log;
import tacoMiner.merkle.MerkleRoot;
import tacoMiner.util.*;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.time.Instant;
import java.util.SplittableRandom;

public class tacoInit {
    private static final int PROTOCOL_VERSION = 70014;
    public static String time;
    public static int hashesCount = 0;
    private static Log logger = Log.getInstance();

    public static synchronized String time() {
        return time;
    }

    public static synchronized void setTime(String t) {
        time = t;
    }

    public static boolean checkByteArray(byte[] a, byte[] b) {
        for (int i = 0; i < b.length; i++) {
            System.out.println(a[i]);
            System.out.println(b[i]);
            if (a[i] > b[i]) {
                return false;
            }
        }
        return true;
    }

    public static byte[] hexStringToByteArray(String s) {
        System.out.println(s);
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static void startClock(Block b) {
        long waitT = Long.parseLong(Long.toString(Instant.now().toEpochMilli()).replace(Long.toString(Instant.now().getEpochSecond()), ""));
        System.out.println("Synch Clock: " + waitT);
        try {
            Thread.sleep(waitT);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Sync Clock Exception");
        }
        System.out.println("Epoch Clock Starting");
        b.start();
        System.out.println(b.toString());
    }

    public static void main(String[] args) throws JSONException, UnsupportedEncodingException {
        logger.print("Starting tacoMiner");



        /*
        //Examples
        System.out.println(HTTP.getAsync(HTTP.formatURL("http://www.example.com/")));

        GetUnconfTX tx = new GetUnconfTX((short) 5);
        String[][] txArray = tx.getTXArray();
        for (String[] a : txArray) {
            System.out.println(a[0] + " : Size : " + a[1]);
        }
        */

        //BcoinCLI.Run("getdifficulty")

        //BcoinDaemon.Start(); takes too long to start, so il just do it manually
        double diff = Double.valueOf(HTTP.getAsync(HTTP.formatURL("https://blockchain.info/q/getdifficulty")));//Double.valueOf(BcoinCLI.Run("getdifficulty"));
        //System.out.println(diff);

        SHA256.InitMD();
        SHA256old.InitMD();

        GetUnconfTX tx = new GetUnconfTX((short) 8);
        MerkleRoot merk = new MerkleRoot(MerkleRoot.convArray(tx.getTXArray()));

        //String vers = "00000001";
        //String previous = "00000000000000001E8D6829A8A21ADC5D38D0A473B144B6765798E61F98BD1D";
        //String merkle = "53fb6ea244d5f501a22c95c4c56701d70a6e115c5476ed95280cb22149c171b3";
        //String time = "4DD7F6B4";
        //String nbits = "1a44b9f2";
        //String nonce = "810C1A07";

        SplittableRandom random = new SplittableRandom();


        logger.log("Calculating Target . . .");

        String targetString = new BigInteger(DifficultyExchange.DifficultyToTarget(diff)).toString(16);
        for (int i = 0; i < ((targetString.length() % 2 == 1 ? 1 : 0) + targetString.length() - targetString.length()); i++) {
            targetString = "0" + targetString;
            //pad hex: example:
            //f
            //turns into
            //0f
        }
        System.out.println(targetString);
        byte[] target = hexStringToByteArray(targetString);

        String vers = Integer.toString(PROTOCOL_VERSION);
        String previous = HTTP.getAsync(HTTP.formatURL("https://blockchain.info/q/latesthash"));
        String merkle = merk.calculateRoot();
        time = Long.toString(Instant.now().getEpochSecond());
        String nbits = Integer.toHexString(DifficultyExchange.DifficultyToNBits(diff));
        //String nonce = "0";

        time = Integer.toHexString(Integer.decode(time));

        logger.log("Calculating Merkle Root . . .");
        System.out.println(merkle);

        vers = SHA256old.EndianReverse(vers);
        previous = SHA256old.EndianReverse(previous);
        merkle = SHA256old.EndianReverse(merkle);
        time = SHA256old.EndianReverse(time);
        nbits = SHA256old.EndianReverse(nbits);
        //nonce = SHA256.EndianReverse(nonce);

        Block b = new Block(vers, previous, merkle, time, nbits);
        int nonce = 0;
        byte[] hash;

        logger.log("Starting miner epoch timer");
        startClock(b);
        logger.log("Initializing header prefix");
        b.saveHeader();

        System.out.println("====");
        System.out.print(vers);
        System.out.print(previous);
        System.out.print(merkle);
        System.out.print(time);
        System.out.print(nbits);
        System.out.println("====");

        System.out.println("Converting header values into bytes");
        b.convertValsToBytes();

        BigInteger targetBig = new BigInteger(target);


        while (true) {

            hashesCount++;

            nonce = random.nextInt(0, Integer.MAX_VALUE);
            hash = b.getHash(nonce);

            if (SHA256.ArrayCompare(hash, target)) {
                System.out.println("WE MINED A BLOCK");
                System.out.println("WE MINED A BLOCK");
                System.out.println("WE MINED A BLOCK");
                System.out.println("WE MINED A BLOCK");
                System.out.println("WE MINED A BLOCK");
                System.out.println("WE MINED A BLOCK");
                System.out.println("Mined: Nonce: " + nonce);
                break;
            }
        }


    }

}
