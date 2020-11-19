package MessagePackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BroadcastGateway {
    private File broadcastDataFile;

    public BroadcastGateway() {

    }

    private ArrayList<StringBuilder> reader(File broadcastDataFile) {
        return null;
    }

    private Message stringToMessage(String s){
        String[] stuff = s.split("~");
        String content = stuff[1];
        Integer sender = Integer.parseInt(stuff[0]);
        return new Message(content, sender);
    }

    private MessageQueue stringToMessageQueue(String s){
        String[] stuff = s.split("\t");
        MessageQueue mq = new MessageQueue();
        for (String messageStr : stuff){
            mq.pushMessage(stringToMessage(messageStr));
        }
        return mq;
    }

}
