public class Message { // storing all messages
    public String content;
    public String from;
    public String to;

    public Message(String content, String from, String to){
        this.content = content;
        this.from = from;
        this.to = to;
    }

    public void setContent(String c){
        this.content = c;
    }
    public void setFrom(String f){
        this.from = f;
    }
    public void setTo(String t){
        this.to = t;
    }

    public String getContent(){
        return content;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }

}
