package tacoMiner.main;

import tacoMiner.debug.Log;
import tacoMiner.util.GetUnconfTX;
import tacoMiner.util.HTTP;

public class tacoInit {
	
	private static Log logger = Log.getInstance();

	public static void main(String[] args) {
		logger.print("Starting tacoMiner");

        //Examples
        System.out.println(HTTP.getAsync(HTTP.formatURL("http://www.example.com/")));

        GetUnconfTX tx = new GetUnconfTX((short) 5);
        String[][] txArray = tx.getTXArray();
        for (String[] a : txArray) {
            System.out.println(a[0] + " : Size : " + a[1]);
        }
        //Examples
    }

}
