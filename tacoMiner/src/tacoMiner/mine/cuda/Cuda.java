package tacoMiner.mine.cuda;

public class Cuda {

	// Singleton
	private Cuda () {
		//TODO: Init modules
	} 
	
	private Cuda instance = new Cuda();
	
	public Cuda getInstance() {
		return instance;
	}
	
}
