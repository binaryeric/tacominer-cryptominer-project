package tacoMiner.process.cuda;


import tacoMiner.process.SubSystem;

import jcuda.driver.JCudaDriver;
import jcuda.driver.*;
import static jcuda.driver.JCudaDriver.*;


//

public class CudaContext implements SubSystem {
	
	private CUdevice gpu;
	
	@Override
	public void start() {
		JCudaDriver.setExceptionsEnabled(true);
		cuInit(0);
		CUdevice gpu = new CUdevice();
		cuDeviceGet(gpu,0);
		
		// Everything from gpu CUdevice object will attach to this context.
		CUcontext context = new CUcontext();
		cuCtxCreate(context,0,gpu);
	}
	
	public CUdevice getGPU() {
		return gpu;
	}
	
	public void clean(CUdeviceptr[] devices) {
		for(int i=0;i < devices.length; i++) {
			cuMemFree(devices[i]);
		}
	}

}
