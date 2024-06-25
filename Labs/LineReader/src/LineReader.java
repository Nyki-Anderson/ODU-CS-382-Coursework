import java.io.*;

public class LineReader {

    private File file;

    public LineReader(String filename) {
        file = new File(filename);
    }

    public static void main(String[] args) 
    {
        if (args.length != 1) {
            System.err.println("You must supply a file name (path) as a command argument.");
            return;
        }
    
        String filename = args[0];
        try {
            new LineReader(filename).listFile();
        } catch (IOException e) {
            System.err.println("Something went wrong.");
        }
        
    }

    /**
     * Open the file (supplied in the constructor) and print one or more lines from it.
     */
    private void listFile() throws IOException {
        try {
            FileReader freader = new FileReader(file);
            BufferedReader in = new BufferedReader(freader);
            String line = in.readLine();
            System.out.println(line);
            in.close(); //also closes freader
        } catch (FileNotFoundException e) {
            System.err.println("Could not find file " + file);
            System.exit(1);
        }

        System.out.println("Listing; " + file);
    }
}
