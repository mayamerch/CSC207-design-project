package MessagePackage;

import java.util.ArrayList;

public class Message { // storing all messages
    public String content;
    public String from;

    /**
     * Constructs a new Message object
     * @param content: content of the message (String)
     * @param from: name of the sender of the message (String)
     */
    public Message(String content, String from){
        this.content = content;
        this.from = from;
    }

    //public isUser

    public void setContent(String c){
        this.content = c;
    }
    public void setFrom(String f){
        this.from = f;
    }

    public String getContent(){
        return content;
    }
    public String getFrom() {
        return from;
    }

}
