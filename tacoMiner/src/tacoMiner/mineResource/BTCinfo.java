package tacoMiner.mineResource;

import tacoMiner.util.*;

public class BTCinfo {

	public static String getPreviousHash() {
		return HTTP.getAsync(HTTP.formatURL("https://blockchain.info/q/latesthash"));
	}
	
}
