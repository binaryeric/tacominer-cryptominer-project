package tacoMiner.process.cpu;

import tacoMiner.process.*;

public class CPU implements Processor {
	
	private int hashes;
	private boolean active;
	
	//
	private void CPU() {
	}
	
	private static CPU instance = new CPU();
	//
	public static CPU getInstance() {
		return instance;
	}
	
	// Subsystems:
	
	private DeviceMonitor cpumonitor;
	private SubSystem mineController;
	
	public void start() {
		active = true;
		hashes = 0;
		
		// Init subsystems:
		
		// CPU monitor:
			cpumonitor = new CPUmonitor();
			cpumonitor.start();
			
			
		// Mine Controller:
			mineController = new MinerController();
			mineController.start();
			
		// After everything is started, begin the output logger:
			cpumonitor.logOutput();
	}
	
	@Override
	public void hash() {
		hashes++;
	}

	@Override
	public int getHashRate() {
		return hashes;
	}
	
	@Override
	public void resetHashRate() {
		hashes = 0;
	}

	public void stop() {
		active = false;
	}

	public boolean isAlive() {
		return active;
	}
	
	// 
	public Device getDevice() {
		// TODO Auto-generated method stub
		return null;
	}

	// --------------------- get the subsystems --------------------- \\
	
	public DeviceMonitor getDeviceMonitor() {
		return cpumonitor;
	}
	
	public SubSystem getMineController() {
		return mineController;
	}
}
