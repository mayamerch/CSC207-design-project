package MessagePackage;

import java.util.ArrayList;

public class Message { // storing all messages
    public String content;
    public Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Constructs a new Message object
     * @param content: content of the message (String)
     * @param userId: id of the sender of the message (String)
     */
    public Message(String content, Integer userId){
        this.content = content;
        this.userId = userId;
    }

    //public isUser

    public void setContent(String c){
        this.content = c;
    }

    public String getContent(){
        return content;
    }

}
