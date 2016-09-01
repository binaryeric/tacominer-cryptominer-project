package tacoMiner.main;

import org.json.JSONException;
import tacoMiner.bcoin.BcoinCLI;
import tacoMiner.bcoin.Block;
import tacoMiner.debug.Log;
import tacoMiner.merkle.MerkleRoot;
import tacoMiner.util.DifficultyExchange;
import tacoMiner.util.GetUnconfTX;
import tacoMiner.util.HTTP;
import tacoMiner.util.SHA256;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.time.Instant;
import java.util.SplittableRandom;
/*
WAYPOINT
WAYPOINT
WAYPOINT
WAYPOINT
WAYPOINT
WAYPOINT
WAYPOINT
WAYPOINT
WAYPOINT
WAYPOINT
WAYPOINT
WAYPOINT

 */

public class tacoInit {

    private static final int PROTOCOL_VERSION = 70014;
    private static Log logger = Log.getInstance();

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
        double diff = (double) 14484.16236122;
        //System.out.println(diff);

        SHA256.InitMD();
        GetUnconfTX tx = new GetUnconfTX((short) 8);

        MerkleRoot merk = new MerkleRoot(new String[]{"8c14f0db3df150123e6f3dbbf30f8b955a8249b62ac1d1ff16284aefa3d06d87", "fff2525b8931402dd09222c50775608f75787bd2b87e56995a7bdd30f79702c4", "6359f0868171b1d194cbee1af2f16ea598ae8fad666d9b012c8ed2b79a236ec4", "e9a66845e05d5abc0ad04ec80f774a7e585c6e8db975962d069a522137b80c1d"});


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

        String vers = "00000001";
        String previous = "000000000002d01c1fccc21636b607dfd930d31d01c3a62104612a1719011250";
        String merkle = merk.calculateRoot();
        String time = "1293623863";
        String nbits = Integer.toHexString(DifficultyExchange.DifficultyToNBits(diff));
        //String nonce = "0";

        time = Integer.toHexString(Integer.decode(time));

        logger.log("Calculating Merkle Root . . .");
        System.out.println(merkle);

        vers = SHA256.EndianReverse(vers);
        previous = SHA256.EndianReverse(previous);
        merkle = SHA256.EndianReverse(merkle);
        time = SHA256.EndianReverse(time);
        nbits = SHA256.EndianReverse(nbits);
        //nonce = SHA256.EndianReverse(nonce);

        Block b = new Block(vers, previous, merkle, time, nbits);
        int nonce = 274141000;
        String hash;

        logger.log("Starting miner epoch timer");
        //b.startClock(); //updates time
        logger.log("Initializing header prefix");
        b.saveHeader();

        System.out.println("====");
        System.out.print(vers);
        System.out.print(previous);
        System.out.print(merkle);
        System.out.print(time);
        System.out.print(nbits);
        System.out.println("====");


        BigInteger targetBig = new BigInteger(target);

        while (true) {
            //nonce = random.nextInt(0, Integer.MAX_VALUE);
            //System.out.println(nonce);
            nonce = nonce + 1;
            hash = b.getHash(Integer.toHexString(nonce));
            System.out.println("NONCE: " + nonce);
            System.out.println(targetString);
            System.out.println("~~~~~~~~~~~~~~~");
            System.out.println(new BigInteger(hash, 16).compareTo(targetBig));
            if (new BigInteger(hash, 16).compareTo(targetBig) == -1) {
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
        //System.out.println(SHA256.EndianReverse(b.getHash(nonce))); //9546a142
        //hash needs to be put into little-endian

        //BcoinDaemon.Stop();


    }

}
