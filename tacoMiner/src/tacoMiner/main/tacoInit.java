package tacoMiner.main;

import org.json.JSONException;
import tacoMiner.bcoin.Block;
import tacoMiner.debug.Log;
import tacoMiner.merkle.MerkleRoot;
import tacoMiner.util.GetUnconfTX;
import tacoMiner.util.SHA256;

import java.io.UnsupportedEncodingException;

public class tacoInit {
	
	private static Log logger = Log.getInstance();

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
        //double diff = Double.valueOf(BcoinCLI.Run("getdifficulty"));
        //System.out.println(diff);

        //Examples
        SHA256.InitMD();
        //System.out.println(SHA256.Hash256("Hello, World"));

        //GetUnconfTX tx = new GetUnconfTX((short) 7);
        String[] txExample = new String[]{"8c14f0db3df150123e6f3dbbf30f8b955a8249b62ac1d1ff16284aefa3d06d87", "fff2525b8931402dd09222c50775608f75787bd2b87e56995a7bdd30f79702c4", "6359f0868171b1d194cbee1af2f16ea598ae8fad666d9b012c8ed2b79a236ec4", "e9a66845e05d5abc0ad04ec80f774a7e585c6e8db975962d069a522137b80c1d"};
        MerkleRoot merk = new MerkleRoot(txExample);
        merk.calculateRoot();

        System.out.println("--------------------");

        //System.out.println(DifficultyExchange.DifficultyToTarget(diff));

        System.out.println("--------------------");

        String vers = "00000001";
        String previous = "00000000000000001E8D6829A8A21ADC5D38D0A473B144B6765798E61F98BD1D";
        String merkle = "53fb6ea244d5f501a22c95c4c56701d70a6e115c5476ed95280cb22149c171b3";
        String time = "4DD7F6B4";
        String nbits = "1a44b9f2";
        String nonce = "810C1A07";

        vers = SHA256.EndianReverse(vers);
        previous = SHA256.EndianReverse(previous);
        merkle = SHA256.EndianReverse(merkle);
        time = SHA256.EndianReverse(time);
        nbits = SHA256.EndianReverse(nbits);
        nonce = SHA256.EndianReverse(nonce);

        Block b = new Block(vers, previous, merkle, time, nbits);
        System.out.println(SHA256.EndianReverse(b.getHash(nonce))); //9546a142
        //hash needs to be put into little-endian

        //BcoinDaemon.Stop();


    }

}
