package tacoMiner.process.openCL;

import tacoMiner.process.GPU;

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
