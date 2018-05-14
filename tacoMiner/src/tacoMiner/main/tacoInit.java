package tacoMiner.main;

import tacoMiner.process.Processor;
import tacoMiner.process.cpu.CPU;

public class tacoInit {

	private static Processor gpu;

	
	public static void main(String[] args) {
		gpu = CPU.getInstance();
		gpu.start();

	}

}
