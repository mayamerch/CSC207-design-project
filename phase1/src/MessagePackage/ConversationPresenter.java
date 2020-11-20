package MessagePackage;

import java.util.ArrayList;
import java.util.Scanner;

public class ConversationPresenter { // User.java to get friends

    public void printChats(ChatroomController cc, int userID){
        System.out.println(cc.myChats(userID));
    }

    public void printBroadcasts(BroadcastController bc, int userID){
        System.out.println(bc.myBroadcasts(userID));
    }

    public int printOptions(){
        Scanner kb = new Scanner(System.in);
        System.out.println("What would you like to do?\n" +
                "1. Check Messages\n" +
                "2. Send Messages\n" +
                "3. Check Broadcasts\n" +
                "4. Send Broadcasts\n"); // should only show up for organizer and speakers

        return kb.nextInt();
    }

    public int printOptionsAttendees(){
        Scanner kb = new Scanner(System.in);
        System.out.println("What would you like to do?\n" +
                "1. Check Messages\n" +
                "2. Send Messages\n" +
                "3. Check Broadcasts\n");
        return kb.nextInt();
    }

    public void run(ChatroomController cc, BroadcastController bc) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter your userID:");
        int yourUserID = kb.nextInt();

        char userType = bc.getUm().getUserByID(yourUserID).getType();

        int option = 0;

        if(userType == 'O' || userType == 'S'){
            option = printOptions();
        }
        else if(userType == 'A'){
            option = printOptionsAttendees();
        }


        while (option != 0) {
            switch (option) {


                case 1: // print chats
                    System.out.println("Here are your chats:");
                    printChats(cc, yourUserID);
                    option = -1;
                    break;

                case 2: // send chats
                    System.out.println("Enter the message to send as a chat. Enter DONE when finished:");
                    String chat = "";
                    while(!kb.nextLine().equals("DONE")){
                        chat = kb.nextLine();
                    }
                    ArrayList<Integer> recipients = new ArrayList<>();
                    System.out.println("Enter the ID(s) of the user(s) you want to message. Enter -1 twice when finished:");
                    while (!(kb.nextInt() == -1)) {
                        recipients.add(kb.nextInt());
                    }

                    cc.sendChat(recipients, yourUserID, chat);
                    option = -1;
                    break;



                case 3: // print broadcasts
                    System.out.println("Here are your broadcasts:");
                    printBroadcasts(bc, yourUserID);
                    option = -1;
                    break;



                case 4: // send broadcasts SPEAKER
                    System.out.println("Enter the message to send as a broadcast. Enter DONE when finished:");
                    String speakerBroadcast = "";
                    while(!kb.nextLine().equals("DONE")){
                        speakerBroadcast = kb.nextLine();
                    }

                    System.out.println("Enter the ID of the event you want to broadcast to:");
                    int speakerEventID = kb.nextInt();
                    bc.sendBroadcast(yourUserID, speakerEventID, speakerBroadcast);
                    option = -1;
                    break;


                case 5: // send broadcasts ORGANIZER
                    System.out.println("Enter the message to send as a broadcast. Enter DONE when finished:");
                    String organizerBroadcast = "";
                    while(!kb.nextLine().equals("DONE")){
                        organizerBroadcast = kb.nextLine();
                    }

                    System.out.println("Enter 0 to broadcast to entire conference, or enter -1 to send a broadcast to a single event:");
                    int organizer = kb.nextInt();
                    if(organizer == 0){
                        bc.broadcastConference(yourUserID, organizerBroadcast);
                    }
                    else if(organizer == 1){
                        System.out.println("Enter the ID of the event you want to broadcast to:");
                        int organizerEventID = kb.nextInt();
                        bc.sendBroadcast(yourUserID, organizerEventID, organizerBroadcast);
                    }

                    option = -1;
                    break;


                default:
                    System.out.println("Enter a menu number 1-4, or 0 to exit:");
                    option = kb.nextInt();
            }
        }
    }
}