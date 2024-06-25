import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author zeil
 *
 */
public class SATScores {

    private String[] names;
    private double[] scores;
    private final int MaxScores = 1000;
    private int numScores;

    private double average;
    private double stddev;

    public SATScores()
    {
        names = new String[MaxScores];
        scores = new double[MaxScores];
        numScores = 0;
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.err.println ("Usage: java SATScores inputFileName");
            System.exit(1);
        }
        BufferedReader input = new BufferedReader (
                new FileReader(args[0]));
        SATScores s = new SATScores();
        s.processScores(input);
    }


    public void processScores(BufferedReader input) {
        readScores(input);
        computeStats();
        writeReport();
    }


    /**
     * Computes the cumulative normal function of z, where z is a normalized
     * value. 
     * 
     * @param z
     * @return cumulative normal of z
     */
    public double cumulativeNormal (double z)
    {
        if (z >= 0.0) {
            double sum = 0.0;
            double c = 1.0 / Math.sqrt(2.0 * Math.PI);
            for (int i = 0; i < 1000; ++i) {
                double x = ((double)i) * z / 1000.0;
                double y = Math.exp(-0.5 * x*x);
                sum += y;
            }
            sum /= 1000.0;
            sum *= c * z;
            return 0.5 + sum;
        }
        else
            return 1.0 - cumulativeNormal(-z);
    }
    

    /**
     * Compute the average and standard deviation for the
     * accumulated scores.
     */
    private void computeStats() {
	// ToDo
    }

    /**
     * Read the names and raw scores into the arrays
     * 
     * @param input  input stream from which to read
     */
    private void readScores(BufferedReader input) {
	// ToDo
    }

   /**
    * Print the report of normalized scores and percentiles for each student. 
    */
    private void writeReport()
    {
	// ToDo
    }
}