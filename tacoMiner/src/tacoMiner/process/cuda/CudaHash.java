package tacoMiner.process.cuda;

import tacoMiner.process.SubSystem;
import jcuda.driver.JCudaDriver;
import jcuda.driver.*;
import static jcuda.driver.JCudaDriver.*;


public class CudaHash implements SubSystem {

    @Override
    public void start() {
        // TODO Auto-generated method stub
    }
	// more todo qq
	public String fromTargetHash(String input, String targetSHA256) {
		CudaContext context = (CudaContext) Cuda.getInstance().getContext();
		CUdevice gpu = context.getGPU();
		
		// todo: create pointers to new hash and create sub-contexts to process in parallels
		// todo: load ptx file 
		
		
		long nonce = 0;
		
		
		
		
		return "";
	}
}
