package tacoMiner.main;

import org.json.JSONException;
import tacoMiner.bcoin.Block;
import tacoMiner.debug.Log;
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
        //Examples
        SHA256.InitMD();
        //System.out.println(SHA256.Hash256("Hello, World"));

        Block b = new Block("01000000", "81cd02ab7e569e8bcd9317e2fe99f2de44d49ab2b8851ba4a308000000000000", "e320b6c2fffc8d750423db8b1eb942ae710e951ed797f7affc8892b0f1fc122b", "c7f5d74d", "f2b9441a");
        System.out.println(b.getHash("42a14695"));
        //hash needs to be put into little-endian

    }

}
