package MessagePackage;

import EventPackage.Event;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class ConversationGateway {
    private File chatroomDataFile;
    private File broadcastDataFile;
    private ArrayList<StringBuilder> chatroomData;
    private ArrayList<StringBuilder> broadcastData;

    /**
     * create an instance of ConversationGateway
     */
    public ConversationGateway(){
        try {
            this.chatroomDataFile = new File("phase1/src/EventPackage/chatroomData.txt");
            if (this.chatroomDataFile.createNewFile()) {
                this.chatroomData = new ArrayList<>();
            } else {
                this.chatroomData = conversationsReader(chatroomDataFile);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            this.broadcastDataFile = new File("phase1/src/EventPackage/broadcastData.txt");
            if (this.broadcastDataFile.createNewFile()) {
                this.broadcastData= new ArrayList<>();
            } else {
                this.broadcastData = conversationsReader(broadcastDataFile);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    /**
     * Write the data for conversations currently in ConversationController to chatroomDataFile and broadcastDataFile
     */
    private void conversationsWriter(){
        try {
            FileWriter eventWriter = new FileWriter(this.chatroomDataFile, true);
            for (Event e : eventList) {
                eventWriter.write(e.toString() + System.lineSeparator());
                eventWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    /**
     * Takes data from chatroomData.txt and broadcastData.txt and tries to write into chatroomData and broadcastData
     * Prints error message to console if attempt failed.
     */
    private ArrayList<StringBuilder> conversationsReader(File file){

        return null;
    }

    /**
     * @param readStrings an ArrayList of StringBuilder that has information on each conversation
     * @return an ArrayList of Chatroom or Broadcast
     */

    public ArrayList<Conversation> conversationParser(ArrayList<StringBuilder> readStrings){
        ArrayList<StringBuilder> readText = new ArrayList<>();
        try {

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }

}
