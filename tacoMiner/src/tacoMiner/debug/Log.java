package tacoMiner.debug;
import java.util.*;
import java.text.*;

public class Log {

	// Singleton class for error logging/command line communication.
	private Log() {} 
	// 
	private static Log instance = new Log(); // Create instance
	
	// Get the class
	public static Log getInstance() {
		return instance;
	}
	
	
	// Actual functions.
	private String getDate() {
		// Get today's date:
		DateFormat dFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dFormat.format(date.getTime());
	}
	
	
	// I'm sry, I like my terminal colors ok.
	
	public void reportError(String err) {
		System.out.println(getDate() + " | " + ANSIcodes.red +  err + ANSIcodes.reset);
	}
	
	public void log(String m) {
		System.out.println(getDate() + " | " + ANSIcodes.cyan + m + ANSIcodes.reset);
	}
	
	public void print(String m) {
		System.out.println(ANSIcodes.green + m + ANSIcodes.reset);
	}
	
}
