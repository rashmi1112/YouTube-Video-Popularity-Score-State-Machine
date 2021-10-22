package channelpopularity.util;

import channelpopularity.context.ContextI;
import channelpopularity.operation.Operation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

/**
 * @author Rashmi Badadale
 *
 * Utility to read a single line of the input file at a time using the FileProcessor instance and parsing the required
 *      string from the read line. The parsed string determines the respective method to be called from the context
 *      class.
 */
public class HelperClass {
    private FileProcessor fileProcessor;
    private String filename;
    private String currentLine;
    private ContextI contextInstance;

    /**
     * Constructoir to initialize the FileProcessor and Context class instances.
     *
     * @param filenameIn Input filename
     * @param contextI Context instance of contextI type
     */

    public HelperClass(String filenameIn, ContextI contextI) {
        try {
            fileProcessor = new FileProcessor(filenameIn);
        } catch (InvalidPathException | SecurityException | IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            System.exit(0);
        }
        filename = filenameIn;
        contextInstance = contextI;
    }

    /**
     * Overriding the toString() method
     * @return String
     */
    public String toString() {
        return "Helper class for retrieving the words from the input file and sending them to respective context methods";
    }

    /**
     * Method to extract the required string from the input line and sending it as a parameter to the respective
     *      context class function.
     *
     * @throws IOException On any I/O errors while reading lines from input file
     */
    public void getWords() throws IOException {
        String extractedWord;
        currentLine = fileProcessor.poll();
        if (null == currentLine) {
            throw new IllegalArgumentException("Empty Input File!");
        }
        while (null != currentLine) {
            if (currentLine.contains(Operation.ADD_VIDEO.toString())) {
                if(!currentLine.matches(Operation.ADD_VIDEO.toString() + "::([a-zA-Z0-9[. ]?]+)" )){
                    System.err.println("Incorrect input format! Required format is ADD_VIDEO::<video name> ");
                    throw new IllegalArgumentException();
                }
                extractedWord = currentLine.substring(currentLine.lastIndexOf(":") + 1);
                contextInstance.addVideo(extractedWord);

            } else if (currentLine.contains(Operation.REMOVE_VIDEO.toString())) {
                if (!currentLine.matches(Operation.REMOVE_VIDEO.toString() + "::([a-zA-Z0-9[. ]?]+)")) {
                    throw new IllegalArgumentException("Incorrect input format! Required format is REMOVE_VIDEO::<video name> ");
                }
                extractedWord = currentLine.substring(currentLine.lastIndexOf(":") + 1);
                contextInstance.removeVideo(extractedWord);

            } else if (currentLine.contains(Operation.METRICS.toString())) {
                String tempString1 = Operation.METRICS.toString() +"__([a-zA-Z0-9[. ]?]+)::\\[VIEWS=([0-9]+)\\,LIKES=(-?[0-9]+)\\,DISLIKES=(-?[0-9]+)\\]";
                if(!currentLine.matches(tempString1)){
                    throw new IllegalArgumentException("\n 1.Incorrect input format! Required format is METRICS__<video name>::[VIEWS=<delta in #views>,LIKES=<delta in #likes>,DISLIKES=<delta in #dislikes>] \n 2.Views " +
                            "cannot be a negative value. \n 3.Views,Likes and Dislikes shall be integer values. \n 4.There" +
                            " should be no spaces before or after comma. ");
                }
                int extractViews, extractLikes, extractDislikes, startIndexForLikesParsing, endIndexForLikesParsing;
                int startIndexForVideoName, endIndexForVideoName;
                String tempString;
                startIndexForVideoName = currentLine.lastIndexOf("_") + 1;
                endIndexForVideoName = currentLine.indexOf(":");
                extractedWord = currentLine.substring(startIndexForVideoName, endIndexForVideoName);
                extractDislikes = Integer.parseInt((currentLine.substring(currentLine.lastIndexOf("=") + 1)).replace("]", ""));
                tempString = currentLine.substring(currentLine.indexOf("=") + 1);
                extractViews = Integer.parseInt(tempString.substring(0, tempString.indexOf(",")));
                startIndexForLikesParsing = tempString.indexOf("=") + 1;
                endIndexForLikesParsing = tempString.lastIndexOf(",");
                extractLikes = Integer.parseInt(tempString.substring(startIndexForLikesParsing, endIndexForLikesParsing));
                contextInstance.calculateMetrics(extractedWord, extractViews, extractLikes, extractDislikes);
            }
            else if (currentLine.contains(Operation.AD_REQUEST.toString())) {
                if (!currentLine.matches(Operation.AD_REQUEST.toString() +"__([a-zA-Z0-9[. ]?]+)::LEN=([0-9]+)")) {
                    System.err.println();
                    throw new IllegalArgumentException("\n 1.Incorrect input format! Required format is AD_REQUEST__<video name>::LEN=<length> \n " +
                            "2.Advertisement length MUST be an integer. \n 3.Advertisement length should not be negative.");
                }
                int startIndexForNameParsing, endIndexForNameParsing, length;
                length = Integer.parseInt(currentLine.substring(currentLine.lastIndexOf("=") + 1));
                startIndexForNameParsing = currentLine.lastIndexOf("_") + 1;
                endIndexForNameParsing = currentLine.indexOf(":");
                extractedWord = currentLine.substring(startIndexForNameParsing, endIndexForNameParsing);
                contextInstance.adRequest(extractedWord, length);
            }

        }
    }

    /**
     * Function to close the fileProcessor after finishing the reading from the input file.
     *
     * @throws IOException On any I/O errors while reading lines from input file
     */

    public void close() throws IOException {
        fileProcessor.close();
    }

}
