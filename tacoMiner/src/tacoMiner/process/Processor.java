package tacoMiner.process;

public interface Processor {
	
	public void start();
	public void stop();
	public boolean isAlive();
	public Device getDevice();
	
	// manditory subsystems
	public DeviceMonitor getDeviceMonitor();
	

}
