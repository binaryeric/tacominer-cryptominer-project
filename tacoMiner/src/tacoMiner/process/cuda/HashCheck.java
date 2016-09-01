package tacoMiner.process.cuda;

import tacoMiner.process.SubSystem;
import jcuda.driver.JCudaDriver;
import jcuda.driver.*;
import static jcuda.driver.JCudaDriver.*;


public class HashCheck implements SubSystem {

	@Override
	public void start() {
		// TODO Auto-generated method stub
	}
	
	public String fromTargetHash(String input, String targetSHA256) {
		CudaContext context = (CudaContext) Cuda.getInstance().getContext();
		CUdevice gpu = context.getGPU();
		
		// todo: create pointers to new hash and create sub-contexts to process in parallels
		
		
		long nonce = 0;
		
		
		
		
		return "";
	}
}
