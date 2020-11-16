package MessagePackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BroadcastGateway {
    private File broadcastDataFile;
    private ArrayList<StringBuilder> broadcastData;

    public BroadcastGateway() {
        try {
            this.broadcastDataFile = new File("phase1/src/EventPackage/chatroomData.txt");
            if (this.broadcastDataFile.createNewFile()) {
                this.broadcastData = new ArrayList<>();
            } else {
                this.broadcastData = reader(broadcastDataFile);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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
