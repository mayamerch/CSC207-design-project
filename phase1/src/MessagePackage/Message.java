package MessagePackage;

import java.util.ArrayList;

public class Message { // storing all messages
    // TODO: make these private?
    private String content;  // TODO: NEED TO ENSURE CONTENT DOES NOT INCLUDE THE FOLLOWING: ~ , \t, \n, \n\n
    private Integer userId;

    /**
     * Constructs a new Message object
     * @param content: content of the message (String)
     * @param userId: id of the sender of the message (String)
     */
    public Message(String content, Integer userId){
        this.content = content;
        this.userId = userId;
    }

    /**
     * @return the content of this message
     */
    public String getContent(){
        return content;
    }

    /**
     * Sets the message content to another String
     */
    public void setContent(String c){
        this.content = c;
    }

    /**
     * @return the userID of the sender of the message
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the sender's userID to another userID
    * TODO: is this necessary since the userId doesn't change (at least not here)?
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    /**
     * To use in Gateway class for saving Message as a string to write to file.
     * @return string of form "userID~message content"
     */
    @Override
    public String toString() {
        return this.userId.toString()+ "~" + this.content;
    }


    /**
     * To use for Presenter class for presenting Messages.
     * @return
     */
    public String format(){
        return "From userID " + this.userId + ": " + this.content;
    }


}
