package tacoMiner.process.cuda;

import tacoMiner.process.GPU;

public class Cuda implements GPU {

	// Singleton
	private Cuda () {
		//TODO: Init modules
	} 
	
	private static Cuda instance = new Cuda();
	
	public static Cuda getInstance() {
		return instance;
	}
	
}
