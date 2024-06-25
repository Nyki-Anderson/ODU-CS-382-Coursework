package perks;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


/**
 * 
 */

/**
 * @author zeil
 *
 */
public class PerkSelection {

    private Requirements reqts;


    public PerkSelection(Requirements r) {
        reqts = r;
    }

    Perk[] sortedPerks;

    int[] solutionState;
    int currentScore;
    
    
    

    /**
     * @param args
     * @throws IOException 
     */
    public static void main (String[] argv) throws IOException
    {
        BufferedReader in;
        if (argv.length > 0)
        {
            in = new BufferedReader(new FileReader(argv[0]));
        }
        else {
            in = new BufferedReader (new InputStreamReader(System.in));
        }
        run (in);
        //return 0;
    }




    private static void run(BufferedReader in) throws IOException {
        String line = in.readLine();
        int maxPerksPerPlayer = Integer.parseInt(line);

        Requirements reqts = new Requirements();

        line = in.readLine();
        while (line != null) {
            if (line.trim().length() > 0) {
                Scanner input = new Scanner(line);
                String newPerkName = input.next();
                int value = input.nextInt();
                Perk p = reqts.getPerk(newPerkName);
                if (p == null) {
                    p = new Perk(newPerkName, value);
                    reqts.addPerk(p);
                } else {
                    p.setValue(value);
                }

                while (input.hasNext()) {
                    String prereqName = input.next();
                    Perk q = reqts.getPerk(prereqName);
                    if (q == null) {
                        q = new Perk(prereqName, 0);
                        reqts.addPerk(q);
                    }
                    reqts.addDependency(p, q);
                }
            }
            line = in.readLine();
        }
        PerkSelection problem = new PerkSelection(reqts);
        problem.solve(maxPerksPerPlayer);
    }







    private void solve(int maxPerksPerPlayer)
    {
        // Solution technique is pruned backtracking.
        //    (For timing purposes, backtracking can be turned off.)
        // We know that we want to choose numPerks perks. The current problem state
        // will therefore be represented as an array of numPerks integers, each
        // giving the index of a currently selected perk. These indices index into
        // the byOrder array. 

        sortedPerks = new Perk[reqts.numberOfPerks()];
        int i0 = 0;
        for (Perk p: reqts) {
            sortedPerks[i0] = p;
            ++i0;
        }
        Arrays.sort(sortedPerks);


        int bestValueSoFar = 0;
        int[] bestSolutionSoFar = null;

        solutionState = new int[maxPerksPerPlayer];
        for (int i = 0; i < maxPerksPerPlayer; ++i) {
            solutionState[i] = 0; 
        }
        solutionState[maxPerksPerPlayer-1] = reqts.numberOfPerks();
        currentScore = 0;

        boolean quit = advance(bestValueSoFar);
        //printState(currentScore, System.err);
        while (quit) {
            if (currentScore > bestValueSoFar
                    || currentScore == bestValueSoFar) {
                bestValueSoFar = currentScore;
                bestSolutionSoFar = Arrays.copyOf(solutionState, solutionState.length);
            }
            quit = advance(bestValueSoFar);
        }
        solutionState = bestSolutionSoFar;
        printState(bestValueSoFar, System.out);
    }


    private void printState (int currentValue, PrintStream out) {
        out.print(currentValue + ":");
        for (int p: solutionState) {
            out.print("  " + sortedPerks[p]);
        }
        out.println ();
    }

    // Advance to the next legal state. Return false if unable to do so.
    private boolean advance(int bestScoreSoFar) {
        int maxPerksPerPlayer = solutionState.length;

        boolean repeatThis = true;
        while (repeatThis) {
            // System.err.println ("Advancing from " + Arrays.toString(solutionState) + ": " + getSolution());
            repeatThis = false;
            int perkNumToChange = 0;
            while (perkNumToChange < maxPerksPerPlayer && solutionState[perkNumToChange] <= perkNumToChange)
                ++perkNumToChange;
            // System.err.println ("  change selection #" + perkNumToChange);
            if (perkNumToChange >= maxPerksPerPlayer)
                return false;
            int nextSelection = solutionState[perkNumToChange]-1;

            // Decrement the selection to be changed and set the following selections
            // to the highest possible values.
            solutionState[perkNumToChange] = nextSelection;
            for (int i = perkNumToChange-1; i >= 0; --i) {
                solutionState[i] = solutionState[i+1] - 1;
            }
            
            Set<Perk> selected = new HashSet<Perk>();
            for (int perkNum: solutionState) {
                selected.add(sortedPerks[perkNum]);
            }

            repeatThis = false;
            for (Perk p: selected) {
                if (!reqts.canSelect(p, selected)) {
                    repeatThis = true;
                    break;
                }
            }
            //printState(-1, System.err);
        }

        currentScore = 0;
        for (int i = 0; i < maxPerksPerPlayer; ++i)
            currentScore += sortedPerks[solutionState[i]].getValue();
        // System.err.println ("Advanced to " + Arrays.toString(solutionState) + ": " + getSolution() + " " + currentScore);
        return true;
    }




 

}
