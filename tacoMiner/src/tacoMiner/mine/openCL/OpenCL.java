package tacoMiner.mine.openCL;

import tacoMiner.mine.GPU;

public class OpenCL implements GPU {

	// Singleton
	private OpenCL() {
		// TODO: Init modules
	} 
	
	private OpenCL instance = new OpenCL();
	
	public OpenCL getInstance() {
		return instance;
	}
	
	
	
}
