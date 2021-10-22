package channelpopularity.state;

import channelpopularity.util.FileDisplayInterface;
import channelpopularity.util.StdoutDisplayInterface;

/**
 * Interface to be implemented by the AbstractState and hence by all the state classes
 */
public interface StateI {



    void addVideo(String videoName, FileDisplayInterface fileDisplayInterfaceIn, StdoutDisplayInterface stdoutDisplayInterface);

    void removeVideo(String videoName, FileDisplayInterface fileDisplayInterfaceIn, StdoutDisplayInterface stdoutDisplayInterfaceIn);

    void calculateMetrics(String videoName, int views, int likes, int dislikes, FileDisplayInterface fileDisplayInterfaceIn, StdoutDisplayInterface stdoutDisplayInterfaceIn);

    void adRequest(String videoName, int length, FileDisplayInterface fileDisplayInterfaceIn, StdoutDisplayInterface stdoutDisplayInterfaceIn);
}
