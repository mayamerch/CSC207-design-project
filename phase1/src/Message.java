import java.util.ArrayList;

public class Message { // storing all messages
    public String content;
    public String from;
    public ArrayList<String> recipients; // for a single message, length 1

    /**
     * Constructs a new Message object
     * @param content: content of the message (String)
     * @param from: name of the sender of the message (String)
     * @param recipients: name of the receiver of the message (ArrayList)
     */
    public Message(String content, String from, ArrayList<String> recipients){
        this.content = content;
        this.from = from;
        this.recipients = recipients;
    }

    public void setContent(String c){
        this.content = c;
    }
    public void setFrom(String f){
        this.from = f;
    }
    public void setTo(ArrayList<String> r){
        this.recipients = r;
    }

    public String getContent(){
        return content;
    }
    public String getFrom() {
        return from;
    }
    public ArrayList<String> getTo() {
        return recipients;
    }

}
