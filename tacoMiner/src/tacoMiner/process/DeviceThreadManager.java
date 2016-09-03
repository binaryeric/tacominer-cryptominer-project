package tacoMiner.process;

import java.util.SplittableRandom;

public interface DeviceThreadManager {

	public void init(String vers, String pb, String mroot, String t, String bits);
	
	public Miner[] threadGenerator(int numThreads);
	public void initBytes(Miner[] b);
	public void saveHeaders(Miner[] b);
	
	public void startMiners(SplittableRandom sr, Miner[] b, byte[] tgt);

}
