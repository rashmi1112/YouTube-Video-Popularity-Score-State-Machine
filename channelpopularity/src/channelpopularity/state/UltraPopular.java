package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.util.FileDisplayInterface;
import channelpopularity.util.StdoutDisplayInterface;

import java.util.Map;

/**
 * @author Rashmi Badadale
 * Class for the ultra popular state extending the AbstractState class
 */

public class UltraPopular extends AbstractState{
    private StateName currentStateName;
    private StateName retrievedStateName;
    private ContextI contextI;
    private String outputLine;


    /**
     * Constructor for initializing the current state name, context class reference variable and hashmap of video
     *      names and video instances.
     * @param stateName StateName Enum  current state name
     * @param context ContextI  reference variable of the context class.
     */

    public UltraPopular(StateName stateName, ContextI context){
        currentStateName = stateName;
        contextI = context;
        videos = contextI.getVideos();
    }

    /**
     * Overriding the toString() method
     * @return String
     */

    public String toString(){
        return "Implements the Ultra popular state methods.";
    }

    /**
     * Method to add video when the channel is in ultra popular state.
     * @param videoName Name of the video to be added
     * @param fileDisplayInterfaceIn Instance of the Results class of FileDisplayInterface interface type
     * @param stdoutDisplayInterfaceIn Instance of the Results class of stdoutDisplayInterface interface type
     * @throws IllegalArgumentException When the video already exists
     */

    @Override
    public void addVideo(String videoName, FileDisplayInterface fileDisplayInterfaceIn, StdoutDisplayInterface stdoutDisplayInterfaceIn) {
        if (!videos.containsKey(videoName)) {
            outputLine = addVideo(videoName, currentStateName, videos);
            fileDisplayInterfaceIn.storeResults(outputLine);
            stdoutDisplayInterfaceIn.storeResults(outputLine);
            retrievedStateName = stateIdentification(popularityScoreOfChannel);
            if (currentStateName != retrievedStateName) {
                contextI.setCurrentState(retrievedStateName);
            }
        }else  {
            throw new IllegalArgumentException("Video " + videoName + " already exists!");
        }
    }

    /**
     * Method to remove video when the channel is in ultra popular state.
     * @param videoName Name of the video to be removed
     * @param fileDisplayInterfaceIn Instance of the Results class of FileDisplayInterface interface type
     * @param stdoutDisplayInterfaceIn Instance of the Results class of stdoutDisplayInterface interface type
     * @throws IllegalArgumentException When the video does not exists.
     */

    @Override
    public void removeVideo(String videoName, FileDisplayInterface fileDisplayInterfaceIn, StdoutDisplayInterface stdoutDisplayInterfaceIn) {

        if(videos.containsKey(videoName)) {
            outputLine = removeVideo(videoName, currentStateName, videos);
            fileDisplayInterfaceIn.storeResults(outputLine);
            stdoutDisplayInterfaceIn.storeResults(outputLine);
            retrievedStateName = stateIdentification(popularityScoreOfChannel);
            if (currentStateName != retrievedStateName) {
                contextI.setCurrentState(retrievedStateName);
            }
        }
        else{
            throw new IllegalArgumentException("Video " + videoName + " does not exist!");
        }
    }

    /**
     * Method calculate the metrics when the channel is in ultra popular state.
     * @param videoName Name of the video
     * @param views Number of increased views
     * @param likes Number of increased/decreased likes
     * @param dislikes Number of increased/decreased dislikes
     * @param fileDisplayInterfaceIn Instance of the Results class of FileDisplayInterface interface type
     * @param stdoutDisplayInterfaceIn Instance of the Results class of stdoutDisplayInterface interface type
     * @throws IllegalArgumentException When the video does not exists.
     */

    @Override
    public void calculateMetrics(String videoName, int views, int likes, int dislikes, FileDisplayInterface fileDisplayInterfaceIn, StdoutDisplayInterface stdoutDisplayInterfaceIn) {

        if(videos.containsKey(videoName)) {
            outputLine = calculateMetrics(videoName,views,likes,dislikes,currentStateName, videos);
            fileDisplayInterfaceIn.storeResults(outputLine);
            stdoutDisplayInterfaceIn.storeResults(outputLine);
            retrievedStateName = stateIdentification(popularityScoreOfChannel);
            if (currentStateName != retrievedStateName) {
                contextI.setCurrentState(retrievedStateName);
            }
        }
        else{
            throw new IllegalArgumentException("Video " + videoName + " does not exist!");
        }
    }

    /**
     * Method to process the ad_request when the channel is in ultra popular state
     * @param videoName Name of the video
     * @param length Length of the video
     * @param fileDisplayInterfaceIn Instance of the Results class of FileDisplayInterface interface type
     * @param stdoutDisplayInterfaceIn Instance of the Results class of stdoutDisplayInterface interface type
     * @throws IllegalArgumentException When the video does not exists.
     */

    @Override
    public void adRequest(String videoName, int length, FileDisplayInterface fileDisplayInterfaceIn, StdoutDisplayInterface stdoutDisplayInterfaceIn) {
        if(videos.containsKey(videoName)) {
            outputLine = adRequest(length, currentStateName);
            fileDisplayInterfaceIn.storeResults(outputLine);
            stdoutDisplayInterfaceIn.storeResults(outputLine);
        }
        else{
            throw new IllegalArgumentException("Video " + videoName + " does not exist!");
        }
    }
}
