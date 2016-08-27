package tacoMiner.mine.openCL;

public class OpenCL {

	// Singleton
	private OpenCL() {
		// TODO: Init modules
	} 
	
	private OpenCL instance = new OpenCL();
	
	public OpenCL getInstance() {
		return instance;
	}
	
	
	
}
