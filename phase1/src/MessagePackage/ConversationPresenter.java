package MessagePackage;

import java.util.ArrayList;
import java.util.Scanner;

public class ConversationPresenter {

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
                "4. Send Broadcasts\n" +
                "Please enter 0 when finished.: ");

        return kb.nextInt();
    }

    public void run(ChatroomController cc, BroadcastController bc) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter your userID:");
        int yourUserID = kb.nextInt();

        int option = printOptions();

        while (option != 0) {

            switch (option) {
                case 1: // print chats
                    printChats(cc, yourUserID);
                    break;

                case 2: // send chats
                    System.out.println("Enter the message to send as a chat:");
                    String chat = kb.nextLine();

                    ArrayList<Integer> recipients = new ArrayList<>();
                    System.out.println("Enter the ID of the user you want to message. Enter -1 when finished:");
                    while (kb.nextInt() != -1) {
                        recipients.add(kb.nextInt());
                    }

                    cc.sendNewChat(recipients, yourUserID, chat);
                    break;

                case 3: // print broadcasts
                    printBroadcasts(bc, yourUserID);
                    break;

                case 4: // send broadcasts
                    System.out.println("Enter the message to send as a broadcast:");
                    String broadcast = kb.nextLine();
                    System.out.println("Enter the ID of the event you want to broadcast to:");
                    int eventID = kb.nextInt();

                    bc.sendNewBroadcast(yourUserID, eventID, broadcast);

                default:
                    System.out.println("Please enter a number 1-4, or 0 if you are finished:");
                    option = kb.nextInt();
            }
        }
    }


/*    public void loginMessages() {
        Scanner kb = new Scanner(System.in);
        System.out.println("What would you like to do?\n" +
                "1. Check Messages\n" +
                "2. Check Broadcasts\n" +
                "Please input a number: ");
        int i;
        do {
            int input = kb.nextInt();
            i = firstMenu(input);
        }
        while (i == -1);
        System.out.println("The menu chosen is " + i);
    }




    public Integer firstMenu(int input){
        if (input == 1){
            System.out.println("Messages");
            return 2;
        }
        else if (input == 2){
            System.out.println("Broadcasts");
            return 3;
        }
        else{
            System.out.println("Enter a valid number");
            return -1;
        }

    }


    public void secondMenu(int input1, BroadcastController bc, ChatroomController cc){
        Scanner kb = new Scanner(System.in);
        input1 = firstMenu(input1);

        System.out.println("Enter your userID: ");
        int userID = kb.nextInt();

        System.out.println("What would you like to do?\n" +
                "1. Read\n" +
                "2. Send\n" +
                "Please input a number: ");
        int input2 = kb.nextInt();

        if (input1 == 2){ // messages
            if(input2 == 1){
                System.out.println("Read chats");
                printChats(cc, userID);
            }
            if(input2 == 2){
                System.out.println("Send chats");
            }
        }
        else if (input1 == 3){ // broadcasts
            if(input2 == 1){
                System.out.println("Read broadcasts");
                printBroadcasts(bc, userID);
            }
            if(input2 == 2){
                System.out.println("Send broadcasts");
            }
        }
        else{
            System.out.println("Enter a valid number: ");
            secondMenu(input1, bc, cc);
        }
    }*/

}
