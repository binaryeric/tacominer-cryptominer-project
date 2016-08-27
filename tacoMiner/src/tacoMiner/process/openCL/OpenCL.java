package tacoMiner.process.openCL;

import tacoMiner.process.Device;
import tacoMiner.process.GPU;

public class OpenCL implements GPU {

	// Singleton
	private OpenCL() {
		// TODO: Init modules
	} 
	
	private static OpenCL instance = new OpenCL();
	
	public static OpenCL getInstance() {
		return instance;
	}

	@Override
	public Device getDevice() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
