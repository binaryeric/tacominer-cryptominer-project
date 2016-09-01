package tacoMiner.process.cuda;

import tacoMiner.process.GPU;
import tacoMiner.process.Device;
import tacoMiner.process.SubSystem;



public class Cuda implements GPU {

	private SubSystem cudaContext;
	
	// Singleton
	private Cuda () {
		//TODO: Init modules
		cudaContext =  new CudaContext();
		cudaContext.start();
		
		//
	} 
	
	private static Cuda instance = new Cuda();
	
	public static Cuda getInstance() {
		return instance;
	}

	public SubSystem getContext() {
		return cudaContext;
	}

	@Override
	public Device getDevice() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
