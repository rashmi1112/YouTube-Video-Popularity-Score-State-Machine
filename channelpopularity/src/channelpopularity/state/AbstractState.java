package channelpopularity.state;

import channelpopularity.operation.Operation;

import java.util.Map;

/**
 * Abstract class implementing the StateI interface. Contains all the common functionalities among all
 *      the state classes.
 */
public abstract class AbstractState implements StateI {
    static public float popularityScoreOfChannel;
    private String outputLine;
    static public Map<String,Video> videos ;

    /**
     * Overriding the toString() method
     * @return String
     */

    public String toString(){
        return "Implements the common state operations and used to calculate metrics for the video and channel." +
                " Also identifies state based on the popularity score of the channel";
    }

    /**
     * Method to add the given video to the hashmap of stored video instances.
     *
     * @param videoName Name of the video to be added
     * @param currentStateName current state name of the type StateName Enum
     * @param videosIn Hashmap of the video names and video instances
     * @return String   Output line to be sent to the Results class.
     */

    public String addVideo(String videoName, StateName currentStateName, Map<String, Video> videosIn) {
            Video video = new Video(videoName);
            videosIn.put(videoName,video);
            popularityScoreOfChannel = calculatePopularityScoreForChannel(videosIn);
            outputLine = currentStateName + Operation.VIDEO_ADDED.getOperationString() + videoName+ "\n";
        return outputLine;
    }

    /**
     * Method to remove the given video to the hashmap of stored video instances.
     * @param videoName Name of the video to be removed
     * @param currentStateName current state name of the type StateName Enum
     * @param videosIn Hashmap of the video names and video instances
     * @return String   Output line to be sent to the Results class.
     */

    public String removeVideo(String videoName,StateName currentStateName, Map<String, Video> videosIn) {
        videosIn.remove(videoName);
        popularityScoreOfChannel = calculatePopularityScoreForChannel(videosIn);
        outputLine = currentStateName + Operation.VIDEO_REMOVED.getOperationString() + videoName+ "\n";
        return outputLine;
    }

    /**
     * Method to approve/reject the advertisement for the given video
     * @param length Length of the advertisement
     * @param currentStateName current state name of the type StateName Enum
     * @return String   Output line to be sent to the Results class.
     */

    public String adRequest(int length, StateName currentStateName) {
        String adRequest;
        adRequest = checkLength(length, currentStateName);
        outputLine = currentStateName + Operation.AD_REQUEST_UPDATE.getOperationString() + adRequest+ "\n";
        return outputLine;
    }

    /**
     * Method to update the metrics of the video and the channel
     * @param videoName Name of the video whose metrics needs to be calculated
     * @param views Number of increased views
     * @param likes Number of likes increased/decreased
     * @param dislikes Number of dislikes increased/decreased
     * @param currentStateName current state name of the type StateName Enum
     * @param videosIn Hashmap of the video names and video instances
     * @return String   Output line to be sent to the Results class.
     */

    public String calculateMetrics(String videoName, int views, int likes, int dislikes, StateName currentStateName, Map<String, Video> videosIn){
        Video video = videosIn.get(videoName);

        video.setViews(views);
        video.setLikes(likes);
        video.setDislikes(dislikes);
        video.setPopularityScore(updatePopularityScoreForVideo(video));
        popularityScoreOfChannel = calculatePopularityScoreForChannel(videosIn);
        outputLine = currentStateName + Operation.POPULARITY_SCORE_UPDATE.getOperationString() + popularityScoreOfChannel+ "\n";
        return outputLine;
    }

    /**
     * Method to calculate the popularity score of the video
     * @param videoIn Instance video class
     * @return int  popularity score of the video. Returns 0 is negative.
     */

    public int updatePopularityScoreForVideo(Video videoIn)  {
        int popularityScore;
        popularityScore = videoIn.getViews() + 2 * (videoIn.getLikes() - videoIn.getDislikes());
        if(popularityScore <0){
            popularityScore = 0;
        }
        return popularityScore;
    }

    /**
     * Method to calculate the popularity score of the channel
     * @param videos Instance of the video class
     * @return float   popularity score of the channel
     */

    public float calculatePopularityScoreForChannel(Map<String, Video> videos) {
        int sumOfScore = 0;
        int numberOfVideos;
        float popularityScoreForChannel;
        numberOfVideos = videos.size();
        for (String key : videos.keySet()) {
            int valuePopularityScore = videos.get(key).getPopularityScore();
            sumOfScore = sumOfScore + valuePopularityScore;
        }
        if (sumOfScore == 0) {
            return 0;
        }
        popularityScoreForChannel = (float) sumOfScore / numberOfVideos;
        return popularityScoreForChannel;
    }

    /**
     * Method to identify the state of the channel based on the popularity score of channel.
     * @param popularityScoreOfChannel Float    popularity score of the channel
     * @return StateName state name based on the condition mentioned
     */

    public StateName stateIdentification(float popularityScoreOfChannel) {
        if (popularityScoreOfChannel >= 0 && popularityScoreOfChannel <= 1000) {

            return StateName.UNPOPULAR;
        }
        else if (popularityScoreOfChannel > 1000 && popularityScoreOfChannel <= 10000) {
            return StateName.MILDLY_POPULAR;
        }
        else if (popularityScoreOfChannel > 10000 && popularityScoreOfChannel <= 100000) {
            return StateName.HIGHLY_POPULAR;
        }
        else if (popularityScoreOfChannel > 100000) {
            return StateName.ULTRA_POPULAR;
        }
        return null;

    }

    /**
     * Method to check whether an advertise should be approved or rejected for a video.
     * @param length Length of the advertisement
     * @param currentStateName Name of the current state of type StateName Enum
     * @return String   Ad_Request Approved / Rejected
     */

    public String checkLength(int length, StateName currentStateName) {
        String approvedRequest = Operation.AD_REQUEST_APPROVED.getOperationString();
        String rejectedRequest = Operation.AD_REQUEST_REJECTED.getOperationString();

        try {
            if (!(length > 0)) {
                throw new IllegalArgumentException("Length cannot be negative!");
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
            System.exit(0);
        }

        if (currentStateName == StateName.UNPOPULAR && length <= 10) {
            return approvedRequest;
        }
        if (currentStateName == StateName.MILDLY_POPULAR && length <= 20) {
            return approvedRequest;
        }
        if (currentStateName == StateName.HIGHLY_POPULAR && length <= 30) {
            return approvedRequest;
        }
        if (currentStateName == StateName.ULTRA_POPULAR && length <= 40) {
            return approvedRequest;
        } else {
            return rejectedRequest;
        }
    }
}
