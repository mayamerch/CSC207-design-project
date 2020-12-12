package MessagePackage;

import EventPackage.EventUseCases.EventManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BroadcastGateway {
    private File broadcastDataFile;
    private EventManager eventManager;
    private boolean serialize = true; //if set to true, file is set to ser file in constructor, feel free to delete if we only serialize

    public BroadcastGateway(EventManager eventManager) {
        this.eventManager = eventManager;
        if (serialize)
            this.broadcastDataFile = new File("src/MessagePackage/BroadcastDataFile.ser");
        else
            this.broadcastDataFile = new File("src/MessagePackage/BroadcastDataFile.txt");
        try {
            broadcastDataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveBroadcastsObject(ArrayList<Broadcast> broadcasts) {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.broadcastDataFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(broadcasts);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in "+this.broadcastDataFile.getPath());
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public ArrayList<Broadcast> readBroadcastsObject(){
        ArrayList<Broadcast> broadcasts = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(this.broadcastDataFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            broadcasts = (ArrayList<Broadcast>) in.readObject();
            in.close();
            fileIn.close();
            return broadcasts;
        } catch (IOException f) {
            f.printStackTrace();
            return broadcasts;
        } catch (ClassNotFoundException c) {
            System.out.println("List class not found");
            c.printStackTrace();
            return broadcasts;
        }
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
     * Make the messages instance variable for a Chatroom
     * @param s String of data for the messages variable
     * @return ArrayList of Messages
     */

    private ArrayList<Message> stringToMessages(String s){
        ArrayList<Message> messages = new ArrayList<>();
        if (!s.equals("[]")) {
            String[] stuff = s.substring(1, s.length() - 1).split("\t");
            for (String messageStr : stuff){
                messages.add(stringToMessage(messageStr));
            }
        }
        return messages;
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
        String[] stuff = s.split("\n");
        ArrayList<Integer> broadcasters = stringToBroadcasters(stuff[0]);
        ArrayList<Message> messages = stringToMessages(stuff[1]);
        int eventID = Integer.parseInt(stuff[2]);

        return new Broadcast(broadcasters, messages, eventID, this.eventManager);
    }
}
