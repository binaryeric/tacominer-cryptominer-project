package tacoMiner.main;

import tacoMiner.debug.Log;
import tacoMiner.util.HTTP;

public class tacoInit {
	
	private static Log logger = Log.getInstance();

	public static void main(String[] args) {
		logger.print("Starting tacoMiner");
        System.out.println(HTTP.getAsync(HTTP.formatURL("http://www.example.com/")));
    }

}
