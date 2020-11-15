package MessagePackage;

import java.util.Scanner;

public class ConversationController {
    public Message message;
    public ChatroomManager cm;
    public BroadcastManager bm;
    Scanner kb = new Scanner(System.in);

    /**
     * Creates an instance of ConversationController that contains all the recorded conversations (empty at first)
     *
     * @param message a message being sent in the conversation (either a chatroom or a broadcast)
     * @param cm a ChatroomManager object
     * @param bm a BroadcastManager object
     */
    public ConversationController(Message message, ChatroomManager cm, BroadcastManager bm) {
        this.message = message;
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

}
