package tacoMiner.mine.cuda;

import tacoMiner.mine.GPU;

public class Cuda implements GPU {

	// Singleton
	private Cuda () {
		//TODO: Init modules
	} 
	
	private Cuda instance = new Cuda();
	
	public Cuda getInstance() {
		return instance;
	}
	
}
