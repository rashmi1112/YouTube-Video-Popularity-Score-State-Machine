package channelpopularity.state;

public class Video {
    private String videoName;
    private int views;
    private int likes;
    private int dislikes;
    private int popularityScore;

    public Video(String videoNameIn){
        videoName = videoNameIn;
        likes = 0;
        dislikes = 0;
        views = 0;
        popularityScore = 0;
    }

    public void setViews(int viewsIn) {
        views = views + viewsIn;
    }

    public void setLikes(int likesIn) {

            likes = likes + likesIn;
            try{
                if(likesIn > likes){
                    throw new IllegalArgumentException("Decrease in likes is more than total number of likes.");
                }
            }catch (IllegalArgumentException illegalArgumentException){
                illegalArgumentException.printStackTrace();
                System.exit(0);
            }

    }

    public void setDislikes(int dislikesIn) {
        dislikes = dislikes + dislikesIn;
        try{
            if(dislikesIn > dislikes){
                throw new IllegalArgumentException("Decrease in dislikes is more than total number of dislikes.");
            }
        }catch (IllegalArgumentException illegalArgumentException){
            illegalArgumentException.printStackTrace();
            System.exit(0);
        }

    }

    public String toString(){
        return "Video class for storing the metadata about the video. ";
    }

    public void setVideoName(String videoNameIn) {
        videoName = videoNameIn;
    }

    public void setPopularityScore(int popularityScoreIn) {
        popularityScore = popularityScoreIn;
    }

    public int getViews() {
        return views;
    }

    public int getLikes(){
        return likes;
    }

    public int getDislikes(){
        return dislikes;
    }

    public int getPopularityScore(){
        return popularityScore;
    }


}
