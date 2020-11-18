import EventPackage.EventController;
import EventPackage.EventManager;
import EventPackage.RoomManager;
import UserPackage.UserController;

import java.util.Scanner;

public class Boot {

    Scanner scanner = new Scanner(System.in);

    public Integer firstMenu(int input){
        if(input == 1){ return 1; }
        else if (input == 2){ return 2; }
        else if (input == 3){ return 3; }
        else{
            System.out.println("Try again");
            return -1;
        }

    }

    public static void main(String[] args){
        Boot boot = new Boot();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login");
        System.out.println("Enter your  Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        // TODO: check if credentials are valid
        EventController ec = new EventController();
        //Assuming password is correct
        System.out.println("What would you like to do?\n" +
                "1. Manage Events\n" +
                "2. Manage Conversations\n" +
                "3. Manage Friends\n" +
                "Please input a number: ");
        int i;
        do {
            int input = scanner.nextInt();
            i = boot.firstMenu(input);
        }
        while (i == -1);
        System.out.println("The menu chosen is "+ i);
        switch (i) {
            case 1:
                // ec.run(userID, userType, speakerIDS); Add when everything is implemented
            case 2:
                // ConversationPresenter called here
            case 3:
                //UserPresenter called here
        }
    }
}
