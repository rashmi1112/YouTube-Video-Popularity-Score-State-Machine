package channelpopularity.context;

import channelpopularity.state.StateName;
import channelpopularity.state.Video;

import java.util.Map;

/**
 * Interface for Context class
 */

public interface ContextI {

    void setCurrentState(StateName stateName);
    Map<String, Video> getVideos();
    void addVideo(String videoName);
    void removeVideo(String videoName);
    void calculateMetrics(String videoName, int views, int likes, int dislikes);
    void adRequest(String videoName, int length);
}
