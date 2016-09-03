package tacoMiner.main;

import org.json.JSONException;
import tacoMiner.bcoin.*;
import tacoMiner.debug.Log;
import tacoMiner.merkle.MerkleRoot;
import tacoMiner.util.*;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.SplittableRandom;

public class tacoInit {
    private static final int PROTOCOL_VERSION = 70014;
    private static final int NUMBER_OF_THREADS = 10;
    public static String time;
    public static byte[] timeBytes;
    public static int hashesCount = 0;
    public static Block blocks[];
    private static Log logger = Log.getInstance();

    public static synchronized String time() {
        return time;
    }

    public static byte[] timeBytes() {
        return timeBytes;
    }

    public static synchronized void setTime(String t) {
        time = t;
    }

    public static synchronized void setTime(byte[] t) {
        timeBytes = t;
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

        SHA256old.InitMD();

        GetUnconfTX tx = new GetUnconfTX((short) 8);
        MerkleRoot merk = new MerkleRoot(MerkleRoot.convArray(tx.getTXArray()));

        //MerkleRoot merk = new MerkleRoot(new String[]{"8c14f0db3df150123e6f3dbbf30f8b955a8249b62ac1d1ff16284aefa3d06d87", "fff2525b8931402dd09222c50775608f75787bd2b87e56995a7bdd30f79702c4", "6359f0868171b1d194cbee1af2f16ea598ae8fad666d9b012c8ed2b79a236ec4", "e9a66845e05d5abc0ad04ec80f774a7e585c6e8db975962d069a522137b80c1d"});

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


        /*
        String vers = "00000001";
        String previous = "000000000002d01c1fccc21636b607dfd930d31d01c3a62104612a1719011250";
        String merkle = merk.calculateRoot();
        String time = "0x4D1B2237";
        String nbits = Integer.toHexString(DifficultyExchange.DifficultyToNBits(diff));
        //String nonce = "810C1A07";
        */

        time = Integer.toHexString(Integer.decode(time));

        logger.log("Calculating Merkle Root . . .");
        System.out.println(merkle);

        vers = SHA256old.EndianReverse(vers);
        previous = SHA256old.EndianReverse(previous);
        merkle = SHA256old.EndianReverse(merkle);
        time = SHA256old.EndianReverse(time);
        nbits = SHA256old.EndianReverse(nbits);

        BlockMultithread.init(vers, previous, merkle, time, nbits);
        blocks = BlockMultithread.multiThreadBlocksGenerate(NUMBER_OF_THREADS);

        logger.log("Starting miner epoch timer");
        startClock(blocks[0]); // just need to start 1 of the blocks timer

        logger.log("Initializing header prefix");
        BlockMultithread.saveHeaders(blocks);

        System.out.println("====");
        System.out.print(vers);
        System.out.print(previous);
        System.out.print(merkle);
        System.out.print(time);
        System.out.print(nbits);
        System.out.println("====");

        System.out.println("Converting header values into bytes");
        BlockMultithread.initBytes(blocks);

        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------");

        BlockMultithread.startMine(random, blocks, target);

        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("Starting Stats Daemon");

        Stats stats = new Stats();
        stats.run();

    }

}
