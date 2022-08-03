import java.io.IOException;

/**
 * 
 */



/**
 * @author jsmith
 *
 */
public class ShutdownTest {

	/**
	 * 
	 */
	public static final String shutdownCommand = "shutdown -h now";
	
	public static final String resetCommand = "shutdown -r now";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	    try {
			Runtime.getRuntime().exec(resetCommand);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.exit(0);
		
	}

}
