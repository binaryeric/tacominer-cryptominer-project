package tacoMiner.main;

import org.json.JSONException;
import tacoMiner.debug.Log;
import tacoMiner.util.DifficultyToTarget;

public class tacoInit {
	
	private static Log logger = Log.getInstance();

	public static void main(String[] args) throws JSONException {
		logger.print("Starting tacoMiner");

        /*
        //Examples
        System.out.println(HTTP.getAsync(HTTP.formatURL("http://www.example.com/")));

        GetUnconfTX tx = new GetUnconfTX((short) 5);
        String[][] txArray = tx.getTXArray();
        for (String[] a : txArray) {
            System.out.println(a[0] + " : Size : " + a[1]);
        }
        //Examples
        SHA256.InitMD();
        System.out.println(SHA256.Hash256("Hello, World"));
        */

        System.out.println(DifficultyToTarget.DifficultyToNBits(217375482757.23764));


    }

}
