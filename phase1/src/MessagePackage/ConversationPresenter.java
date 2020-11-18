package MessagePackage;

import java.util.ArrayList;
import java.util.Scanner;

public class ConversationPresenter {

    /**
     * Prints first menu for user logging in to manage their messages
     * a specific user
     */
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

    public void loginMessages() {
        Scanner kb = new Scanner(System.in);
        System.out.println("What would you like to do?\n" +
                "1. Manage Events\n" +
                "2. Check Messages\n" +
                "3. Check Broadcasts\n" +
                "Please input a number: ");
        int i;
        do {
            int input = kb.nextInt();
            i = firstMenu(input);
        }
        while (i == -1);
        System.out.println("The menu chosen is " + i);
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
    }

    public void printChats(ChatroomController cc, int userID){
        System.out.println(cc.myChats(userID));
    }

    public void printBroadcasts(BroadcastController bc, int userID){
        System.out.println(bc.myBroadcasts(userID));
    }

}
