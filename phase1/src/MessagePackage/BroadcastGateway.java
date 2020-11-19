package MessagePackage;

import EventPackage.Event;
import EventPackage.EventManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BroadcastGateway {
    private File broadcastDataFile;
    private EventManager em;

    public BroadcastGateway(EventManager em) {
        this.em = em;
        this.broadcastDataFile = new File("src/MessagePackage/BroadcastDataFile.txt");
    }

    /**
     * saves instance variable broadcasts of BroadcastController chats to BroadcastDataFile
     * @param broadcasts ArrayList of Broadcasts in BroadcastController
     * @throws IOException in case file is not written to
     */

    public void writeBroadcastsToFile(ArrayList<Broadcast> broadcasts) throws IOException {
        StringBuilder s = new StringBuilder("");
        for (Broadcast b: broadcasts){
            s.append(b.toString());
            s.append("\n\n") ;
        }
        FileWriter writer = new FileWriter(this.broadcastDataFile);
        writer.write(s.toString());
        writer.close();
    }

    /**
     * Make an ArrayList of Broadcasts from BroadcastDataFile
     * @return Arraylist of Broadcasts for BroadcastController
     * @throws FileNotFoundException if BroadcastDataFile.txt does not exist
     */

    public ArrayList<Broadcast> makeBroadcasts() throws FileNotFoundException {
        ArrayList<Broadcast> broadcasts = new ArrayList<>();
        Scanner scan = new Scanner(this.broadcastDataFile);
        scan.useDelimiter("\n\n");
        while (scan.hasNext()) {
            Broadcast b = stringToBroadcast(scan.next());
            broadcasts.add(b);
        }
        return broadcasts;
    }

    /**
     * Make a Message from a string
     * @param s a String of data for a Message
     * @return instance of message constructed with data from String s
     */
    private Message stringToMessage(String s){
        String[] stuff = s.split("~");
        String content = stuff[1];
        Integer sender = Integer.parseInt(stuff[0]);
        return new Message(content, sender);
    }


    /**
     * Make a MessageQueue from a string
     * @param s a String of data for a MessageQueue
     * @return a MessageQueue with data from String s
     */
    private MessageQueue stringToMessageQueue(String s){
        String[] stuff = s.split("\t");
        MessageQueue mq = new MessageQueue();
        for (String messageStr : stuff){
            mq.pushMessage(stringToMessage(messageStr));
        }
        return mq;
    }

    /**
     * Make a list of userIDs for broadcasts from a string
     * @param s a String representing an ArrayList of userIDs
     * @return return the broadcasters instance variable for Broadcast
     */
    private ArrayList<Integer> stringToBroadcasters(String s){
        ArrayList<Integer> broadcasters = new ArrayList<Integer>();
        if (!s.equals("[]")) {
            String[] stuff = s.substring(1, s.length() - 1).split(", ");
            for (String userIDStr : stuff){
                broadcasters.add(Integer.parseInt(userIDStr));
            }
        }
        return broadcasters;
    }

    /**
     * Make a Broadcast from a string from BroadcastDataFile
     * @param s a String of data for a Broadcast
     * @return an instance of Chatroom with data from String s
     */
    private Broadcast stringToBroadcast(String s) {
        // Broadcast.toString() broadcasters.toString() + "\n" + messageQueue.toString() + "\n" + eventID
        String[] stuff = s.split("\n");
        ArrayList<Integer> broadcasters = stringToBroadcasters(stuff[0]);
        MessageQueue mq = stringToMessageQueue(stuff[1]);
        int eventID = Integer.parseInt(stuff[2]);

        return new Broadcast(broadcasters, mq, eventID, this.em);
    }


}
