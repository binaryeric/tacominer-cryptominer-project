package tacoMiner.process.cpu;


import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

import tacoMiner.debug.Log;
import tacoMiner.process.*;

public class CPUmonitor implements DeviceMonitor {

	private Processor cpu;
	private Log logger;
	private OperatingSystemMXBean sysMonitor;
	
	//
	private int hashRate = 0;

	@Override
	public void start() {
		sysMonitor = ManagementFactory.getOperatingSystemMXBean();
		//
		cpu = CPU.getInstance();
		logger = Log.getInstance();
	}
	
	// CPU usage:
	
	public double getUsage() {
		double cpuUsage = sysMonitor.getSystemLoadAverage();
		if(cpuUsage < 0) 
			cpuUsage = 0;
		return cpuUsage;
	}
	
	// Hash rate:
	
	public void setHashRate(int rate) {
		hashRate = rate;
	}
	
	public int getHashRate() {
		return hashRate;
	}
	
	// logger output:
	public void logOutput() {
		new Thread(new Runnable() {
			public void run() {
				while(cpu.isAlive()) {
					// Print to output:
					logger.printHighlight("CPU Usage: " + Double.toString(getUsage()) + "%");
					logger.printHighlight("Hash rate: " + Integer.toString(getHashRate()) + "/t per second");
	
					// Wait a second
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}).start();
	}

}
