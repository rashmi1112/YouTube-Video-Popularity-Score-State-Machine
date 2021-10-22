package channelpopularity.operation;

/**
 * @author Rashmi Badadale
 * Enum to store the operations performed by the states
 */
public enum Operation {

    ADD_VIDEO ("ADD_VIDEO"),
    REMOVE_VIDEO("REMOVE_VIDEO") ,
    METRICS("METRICS"),
    AD_REQUEST("AD_REQUEST"),
    VIDEO_ADDED("__VIDEO_ADDED::") ,
    VIDEO_REMOVED("__VIDEO_REMOVED::"),
    POPULARITY_SCORE_UPDATE("__POPULARITY_SCORE_UPDATE::"),
    AD_REQUEST_UPDATE("__AD_REQUEST::"),
    AD_REQUEST_APPROVED("APPROVED"),
    AD_REQUEST_REJECTED("REJECTED");

    private String operations;

    /**
     * Constructor to initialize the String
     * @param string String value
     */
    Operation(String string) {
        this.operations = string;
    }

    /**
     * Function to get the operation string
     * @return String operation
     */

    public String getOperationString(){
        return operations;
    }
}
