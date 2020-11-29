package MessagePackage;

import UserPackage.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConversationPresenter { // User.java to get friends

    public void printChats(ChatroomController cc, int userID){
        System.out.println(cc.myChats(userID));
    }

    public void printBroadcasts(BroadcastController bc, int userID){
        System.out.println(bc.myBroadcasts(userID));
    }

    public int printSpeakerOptions(){
        Scanner kb = new Scanner(System.in);
        System.out.println("Manage Conversations\n"+
                "---------------------------------\n"+
                "What would you like to do?\n" +
                "1. Check Messages\n" +
                "2. Send Messages\n" +
                "3. Check Broadcasts\n" +
                "4. Send Broadcast to one Event\n" +
                "6. Send Broadcast to all your Events\n");

        return kb.nextInt();
    }

    public int printAttendeeOptions(){
        Scanner kb = new Scanner(System.in);
        System.out.println("Manage Conversations\n"+
                "---------------------------------\n"+
                "What would you like to dxo?\n" +
                "1. Check Messages\n" +
                "2. Send Messages\n" +
                "3. Check Broadcasts\n");
        return kb.nextInt();
    }

    public int printOrganizerOptions(){
        Scanner kb = new Scanner(System.in);
        System.out.println("Manage Conversations\n"+
                "---------------------------------\n"+
                "What would you like to do?\n" +
                "1. Check Messages\n" +
                "2. Send Messages\n" +
                "3. Check Broadcasts\n" +
                "7. Send Broadcast to Speakers\n" +
                "5. Send Broadcast to Attendees\n");

        return kb.nextInt();
    }

    public void run(int currID, char userType, ChatroomController cc, BroadcastController bc) throws IOException {
        Scanner kb = new Scanner(System.in);

        int option = 0;

        if(userType == 'O'){
            option = printOrganizerOptions();
        }
        else if(userType == 'S'){
            option = printSpeakerOptions();
        }
        else if(userType == 'A'){
            option = printAttendeeOptions();
        }


        while (option != 0) {
            switch (option) {


                case 1: // print chats
                    System.out.println("Here are your chats:");
                    printChats(cc, currID);
                    option = -1;
                    break;

                case 2: // send chats
                    System.out.println("Enter the message to send as a chat. Enter DONE when finished:");
                    String chat = "";
                    while(!kb.nextLine().equals("DONE")){
                        chat = kb.nextLine();
                    }
                    ArrayList<Integer> recipients = new ArrayList<>();
                    System.out.println("Enter the ID(s) of the user(s) you want to message. Enter -1 when finished:");
                    while (!(kb.nextInt() == -1)) {
                        recipients.add(kb.nextInt());
                    }

                    cc.sendChat(recipients, currID, chat);
                    cc.saveChats();
                    option = -1;
                    break;



                case 3: // print broadcasts
                    System.out.println("Here are your broadcasts:");
                    printBroadcasts(bc, currID);
                    option = -1;
                    break;



                case 4: // Send Broadcast to one Event speaker
                    System.out.println("Enter the message to send as a broadcast. Enter DONE when finished:");
                    String speakerBroadcast = "";
                    while(!kb.nextLine().equals("DONE")){
                        speakerBroadcast = kb.nextLine();
                    }

                    System.out.println("Enter the ID of the event you want to broadcast to:");
                    int speakerEventID = kb.nextInt();
                    bc.sendBroadcast(currID, speakerEventID, speakerBroadcast);
                    bc.saveBroadcasts();
                    option = -1;
                    break;


                case 5: // Send Broadcast to Attendees organizer
                    System.out.println("Enter the message to send as a broadcast. Enter DONE when finished:");
                    String organizerBroadcast = "";
                    while(!kb.nextLine().equals("DONE")){
                        organizerBroadcast = kb.nextLine();
                    }
                    bc.broadcastConference(currID, organizerBroadcast);
                    bc.saveBroadcasts();
                    option = -1;
                    break;

                case 6: // Send Broadcast to all your Events speaker
                    System.out.println("Enter the message to send to all your events. Enter DONE when finished:");
                    String speakerAllEventsBroadcast = "";
                    while(!kb.nextLine().equals("DONE")){
                        speakerAllEventsBroadcast = kb.nextLine();
                    }
                    bc.sendBroadcastInAllSpeakerEvents((Speaker)bc.getUm().getUserByID(currID), speakerAllEventsBroadcast);
                    bc.saveBroadcasts();
                    option = -1;
                    break;


                case 7: //Send Broadcast to Speakers from organizer
                    System.out.println("Enter the message to send to all Speakers. Enter DONE when finished:");
                    String messageToSpeakers = "";
                    while(!kb.nextLine().equals("DONE")){
                        messageToSpeakers = kb.nextLine();
                    }
                    cc.sendMessageToAllSpeakers(currID, messageToSpeakers);
                    cc.saveChats();
                    option = -1;
                    break;


                default:
                    cc.saveChats();
                    bc.saveBroadcasts();
                    System.out.println("Enter a menu number, or 0 to exit:");
                    option = kb.nextInt();
            }
        }
    }
}