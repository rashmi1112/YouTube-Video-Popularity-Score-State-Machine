package channelpopularity.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Results stores the individual processed input from each state methods
 * and stores it into a StringBuilder object and prints the output on standard output
 * and output file.
 *
 * Implements the FileDisplayInterface and StdoutDisplayInterface
 *
 * @author Rashmi Badadale
 */

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    private String filename;
    private StringBuilder stringBuilder;
    private BufferedWriter bufferedWriter;

    /**
     * Constructor for initializing the StringBuilder and the output filename.
     * @param outputFileName output filename
     */
    public Results(String outputFileName) {
        filename = outputFileName;
        stringBuilder = new StringBuilder();
    }


    /**
     * Overriding the toString() method
     * @return String
     */

    public String toString() {
        return "Implements the FileDisplayInterface and StdoutDisplayInterface for storing the results from the state class" +
                "into a data structure";
    }

    /**
     *Stores the processed input from the state operation methods into StringBuilder object
     * @param writeLine output line to be written to the output file and the standard console.
     */
    @Override
    public void storeResults(String writeLine) {
        stringBuilder.append(writeLine);
    }

    /**
     * Function to create the buffered writer in order to write the output to the file.
     * @throws IOException On any I/O errors while writing lines to the output file
     */
    @Override
    public void writeToFile() throws IOException {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filename));
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.err.println("Writing to file unsuccessful!");
        } finally {
            bufferedWriter.close();
        }
    }

    /**
     * Function to write the output to the standard console.
     */

    @Override
    public void writeToStdOut() {
        System.out.println(stringBuilder.toString());
    }
}
