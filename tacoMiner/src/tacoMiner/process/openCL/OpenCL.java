package tacoMiner.process.openCL;

import tacoMiner.process.Device;
import tacoMiner.process.DeviceMonitor;
import tacoMiner.process.Processor;
import tacoMiner.process.SubSystem;

public class OpenCL implements Processor {

	// Singleton
	private OpenCL() {
		// TODO: Init modules
	} 
	
	private static OpenCL instance = new OpenCL();
	
	public static OpenCL getInstance() {
		return instance;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Device getDevice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hash() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getHashRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DeviceMonitor getDeviceMonitor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubSystem getMineController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetHashRate() {
		// TODO Auto-generated method stub
		
	}
	
}
