package channelpopularity.context;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.Video;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.util.FileDisplayInterface;
import channelpopularity.util.StdoutDisplayInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rashmi Badadale
 * Class that implements the context Interface, ContextI
 */
public class Context implements ContextI {

    private final Map<StateName, StateI> availableStates;
    Map<String, Video> videos;
    private StateI currentState;
    private final StateI unpopularState;
    private final StateI highlyPopularState;
    private final StateI mildlyPopularState;
    private final StateI ultraPopularState;
    private final FileDisplayInterface fileDisplayInterface;
    private final StdoutDisplayInterface stdoutDisplayInterface;

    /**
     *
     * Constructor for the Context class that uses the simple factory design to fetch the required state
     *
     * @param simpleStateFactory instance of SimpleStateFactory class
     * @param fileDisplayInterfaceIn instance of Results class of FileDisplayInterface type
     * @param stdoutDisplayInterfaceIn instance of Results class of stdoutDisplayInterface type
     */
    public Context(SimpleStateFactory simpleStateFactory, FileDisplayInterface fileDisplayInterfaceIn, StdoutDisplayInterface stdoutDisplayInterfaceIn) {
        fileDisplayInterface = fileDisplayInterfaceIn;
        stdoutDisplayInterface = stdoutDisplayInterfaceIn;
        videos = new HashMap<>();
        availableStates = new HashMap<>();
        unpopularState = simpleStateFactory.create(StateName.UNPOPULAR, this);
        availableStates.put(StateName.UNPOPULAR, unpopularState);
        mildlyPopularState = simpleStateFactory.create(StateName.MILDLY_POPULAR, this);
        availableStates.put(StateName.MILDLY_POPULAR, mildlyPopularState);
        highlyPopularState = simpleStateFactory.create(StateName.HIGHLY_POPULAR, this);
        availableStates.put(StateName.HIGHLY_POPULAR, highlyPopularState);
        ultraPopularState = simpleStateFactory.create(StateName.ULTRA_POPULAR, this);
        availableStates.put(StateName.ULTRA_POPULAR, ultraPopularState);
        currentState = unpopularState;
    }

    /**
     * Method to get the hashmap where all the videos are stored.
     *
     * @return Hashmap of stored videos
     */

    public Map<String, Video> getVideos() {
        return videos;
    }

    /**
     * Overriding the toString() method
     * @return String
     */

    public String toString() {
        return "Uses Simple factory design pattern to fetch required state instance and initialize current state";
    }

    /**
     * Method to set the current state from the hashmap of available states
     * @param stateToChangeTo The state that needs to be changed to
     */

    public void setCurrentState(StateName stateToChangeTo) {
        if (availableStates.containsKey(stateToChangeTo)) {
            currentState = availableStates.get(stateToChangeTo);
        }
    }

    /**
     * Function to call addVideo() method of the current state
     *
     * @param videoName Name of the video to be added
     */

    @Override
    public void addVideo(String videoName) {
        try {
            currentState.addVideo(videoName, fileDisplayInterface, stdoutDisplayInterface);
        }catch(IllegalArgumentException illegalArgumentException){
            illegalArgumentException.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Function to call the removeVideo() method of the current state.
     *
     * @param videoName Name of the video to be removed
     */
    @Override
    public void removeVideo(String videoName) {
        try {
            currentState.removeVideo(videoName, fileDisplayInterface, stdoutDisplayInterface);
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
            System.exit(0);
        }
    }

    /**
     *
     * Function to call the calculateMetrics() method of the current state
     * @param videoName Name of the video whose metrics needs to be updated
     * @param views Number of views increased
     * @param likes Number of likes increased/decreased
     * @param dislikes Number of dislikes increased/decreased
     */
    @Override
    public void calculateMetrics(String videoName, int views, int likes, int dislikes) {
        currentState.calculateMetrics(videoName, views, likes, dislikes, fileDisplayInterface, stdoutDisplayInterface);
    }

    /**
     * Function to call the adRequest() method of the current state
     * @param videoName Name of the video in which the advertisement should be added
     * @param length Length of the video
     */

    @Override
    public void adRequest(String videoName, int length) {
        currentState.adRequest(videoName, length, fileDisplayInterface, stdoutDisplayInterface);
    }
}
