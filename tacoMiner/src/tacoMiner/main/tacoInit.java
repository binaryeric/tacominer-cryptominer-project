package tacoMiner.main;

import org.json.JSONException;
import tacoMiner.bcoin.Block;
import tacoMiner.debug.Log;
import tacoMiner.util.DifficultyExchange;
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
        //BcoinDaemon.Start(); takes too long to start, so il just do it manually

        //System.out.println(BcoinCLI.Run("getdifficulty"));

        //Examples
        SHA256.InitMD();
        //System.out.println(SHA256.Hash256("Hello, World"));

        System.out.println(DifficultyExchange.DifficultyToTarget(6695826));

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
