package tacoMiner.process.cpu;

import tacoMiner.process.Device;
import tacoMiner.process.DeviceMonitor;
import tacoMiner.process.Processor;
import tacoMiner.process.SubSystem;

public class CPU implements Processor {
	
	private boolean active = false;
	
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
	
	public void start() {
		active = true;
		// Init subsystems:
		
		// CPU monitor:
			cpumonitor = new CPUmonitor();
			cpumonitor.start();
			
		// 
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
	

}
