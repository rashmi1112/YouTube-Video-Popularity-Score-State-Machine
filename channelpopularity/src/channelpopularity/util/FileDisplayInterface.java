package channelpopularity.util;

import java.io.IOException;

/**
 * Interface to be implemented by the Results class
 */
public interface FileDisplayInterface {
    void storeResults(String writeLine);
    void writeToFile() throws IOException;
}
