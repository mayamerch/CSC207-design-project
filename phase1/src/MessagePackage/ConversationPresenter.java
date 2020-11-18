package MessagePackage;

import EventPackage.EventManager;

import java.util.ArrayList;
import java.util.Scanner;

public class ConversationPresenter {

    public String returnChats(ChatroomController cc, int userID){
        return cc.myChats(userID);
    }

    public String returnBroadcasts(BroadcastController bc, int userID){
        return bc.myBroadcasts(userID);
    }

    public int printOptions(){
        Scanner kb = new Scanner(System.in);
        System.out.println("What would you like to do?\n" +
                "1. Check Messages\n" +
                "2. Send Messages\n" +
                "3. Check Broadcasts\n" +
                "4. Send Broadcasts\n");

        return kb.nextInt();
    }

    public void run(ChatroomController cc, BroadcastController bc, EventManager em) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter your userID:");
        int yourUserID = kb.nextInt();

        int option = printOptions();

        while (option != 0) {

            switch (option) {
                case 1: // print chats
                    System.out.println("Here are your chats:");
                    System.out.println(returnChats(cc, yourUserID));
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
                    int sendto = -2;
                    while (!(kb.nextInt() == -1)) {
                        recipients.add(kb.nextInt());
                    }

                    cc.sendNewChat(recipients, yourUserID, chat);
                    option = -1;
                    break;

                case 3: // print broadcasts
                    System.out.println("Here are your broadcasts:");
                    System.out.println(returnBroadcasts(bc, yourUserID));
                    option = -1;
                    break;

                case 4: // send broadcasts
                    System.out.println("Enter the message to send as a broadcast. Enter DONE when finished:");
                    String broadcast = "";
                    while(!kb.nextLine().equals("DONE")){
                        broadcast = kb.nextLine();
                    }

                    System.out.println("Enter the ID of the event you want to broadcast to:");
                    int eventID = kb.nextInt();

                    bc.sendBroadcast(yourUserID, em.getEvent(eventID), broadcast);
                    option = -1;
                    break;

                default:
                    System.out.println("Please enter a number 1-4, or 0 if you are finished:");
                    option = kb.nextInt();
            }
        }
    }
}