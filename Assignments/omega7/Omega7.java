\import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Omega7 {

    public Omega7()
    {
	x = y = 0.0;
    }
	
    /**
     * The main function sets up the input source and calls execute().
     * 
     * If the program is run with no command line arguments, then input is taken
     * from standard input (System.in), usually the keyboard. If a command line
     * argument is supplieed, it is treated as the name of a file to use for input.  
     * 
     * @param args If non-empty, the name of a file to use for input.
     * @throws FileNotFoundException 
     */
    
    public static void main(String[] args) throws FileNotFoundException {
	BufferedReader input = null;
        if (args.length == 0) {
            input = new BufferedReader(new InputStreamReader(System.in));
        } else {
            input = new BufferedReader (new FileReader (args[0]));
        }
        new Omega7().execute(input);
    }
    
    // Current position of the boat
    // (0,0) is a point halfway between the beacons
    public double x, y;
    
    /**
     * Runs the main algorithm
     * 
     * @param input
     */
    private void execute(BufferedReader input) {
	Scanner in = new Scanner (input);
	int numSteps = in.nextInt();
	
	// starting position
	x = 0.0;
	y = 0.0;
	
	for (int step = 0; step < numSteps; ++step) {
	    //Read the next leg
	    String bearing = in.next();
	    double distance = in.nextDouble();
	    
	    double degrees = convertBearingToDegrees(bearing);
	    computePositionAtEndOfLeg (degrees, distance);
	    int angle1 = angleToBeacon (-50.0, 0.0);
	    int angle2 = angleToBeacon (50.0, 0.0);
	    System.out.println (angle1 + " " + angle2);
	    
	}
    }
    
    
    
    String[] CompassRoseBearings = {"N", "NNE", "NE", "ENE", "E", 
				    "ESE", "SE", "SSE", "S",
				    "SSW", "SW", "WSW", "W",
				    "WNW", "NW", "NNW"};
    
    /**
     * Given a bearing (one of the CompassRoseBearings above), return the 
     * corresponding angle in degrees, assuming that N is 0 degrees and
     * degrees increase clockwise.
     * 
     * @param bearing name of a bearing on the compass rose
     * @return corresponding angle in degrees, measured clockwise from North
     */
    public double convertBearingToDegrees(String bearing) {
	// ToDo
	return 0.0;
    }
    
    /**
     * Compute the angle, measured clockwise form the North, of the point
     * (targetX, targetY) as seen from the current position of the boat.
     * 
     * @param targetX   x-coordinate of target position
     * @param targetY   y-coordinate of target position
     * @return angle of that target position, rounded to the closest integer degree
     */
    private int angleToBeacon(double targetX, double targetY) {
	// ToDo
	return 0;
    }
    
    
    /**
     * Update the (x,y) position of the boat given that it travels a given distance
     * and direction
     * 
     * @param direction expressed in degrees, measured clockwise from the North
     * @param distance  a distance in stadia
     */
    public void computePositionAtEndOfLeg(double direction, double distance) {
	// ToDo
    }
}
