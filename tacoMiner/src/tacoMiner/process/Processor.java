package tacoMiner.process;

public interface Processor {
	
	public void start();
	public void stop();
	public boolean isAlive();
	public Device getDevice();
	
	public void hash();
	public int getHashRate();
	public void resetHashRate();
	
	// manditory subsystems
	public DeviceMonitor getDeviceMonitor();
	public SubSystem getMineController();
	

}
