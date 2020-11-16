package MessagePackage;

import java.util.Scanner;

public class ConversationPresenter {
    public ChatroomController cm;
    public BroadcastController bm;

    /**
     * Creates an instance of ConversationController that contains all the recorded conversations (empty at first)

     * @param cm a ChatroomManager object
     * @param bm a BroadcastManager object
     */
    public ConversationPresenter(ChatroomController cm, BroadcastController bm) {
        this.cm = cm;
        this.bm = bm;
    }

    public Integer firstMenu(int input){
        if(input == 1){
            System.out.println("manage events");
            return 1;
        }
        else if (input == 2){
            System.out.println("messages");
            return 3;
        }
        else if (input == 3){
            System.out.println("broadcasts");
            return 4;
        }
        else{
            System.out.println("try again");
            return -1;
        }

    }

    public void loginMessages() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login");
        System.out.println("Enter your goddamn Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        // TODO: check if credentials are valid - aren't logins done in the user controller?

        //Assuming password is correct
        System.out.println("What would you like to do?\n" +
                "1. Manage Events\n" +
                "2. Check Messages\n" +
                "3. Check Broadcasts\n" +
                "Please input a number: ");
        int i;
        do {
            int input = scanner.nextInt();
            i = firstMenu(input);
        }
        while (i == -1);
        System.out.println("the menu chosen is " + i);
    }

    public void secondMenu(int input1, int input2){
        System.out.println("What would you like to do?\n" +
                "1. Read\n" +
                "2. Send\n" +
                "Please input a number: ");
        Scanner kb = new Scanner(System.in);
        input2 = kb.nextInt();

        input1 = firstMenu(input1);
        if (input1 == 2){ // messages
            if(input2 == 1){
                System.out.println("read chats");
                //System.out.println(cm.returnChatsforUserID());
            }
            if(input2 == 2){
                System.out.println("send chats");
            }
        }
        else if (input1 == 3){ // broadcasts
            if(input2 == 1){
                System.out.println("read broadcasts");
                //System.out.println(bm.returnBroadcastsforUserID(this.message.getUserId()));
            }
            if(input2 == 2){
                System.out.println("send broadcasts");
            }
        }
        else{
            System.out.println("try again");
        }
    }

}
