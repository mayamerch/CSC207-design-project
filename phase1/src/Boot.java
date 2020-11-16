import EventPackage.EventController;
import EventPackage.EventManager;
import EventPackage.RoomManager;
import UserPackage.UserController;

import java.util.Scanner;

public class Boot {

    Scanner scanner = new Scanner(System.in);

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

    public static void main(String[] args){
        Boot boot = new Boot();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login");
        System.out.println("Enter your goddamn Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        // TODO: check if credentials are valid
        EventController ec = new EventController();
        //Assuming password is correct
        System.out.println("What would you like to do?\n" +
                "1. Manage Events\n" +
                "2. Check Messages\n" +
                "3. Check Broadcasts\n" +
                "Please input a number: ");
        int i;
        do {
            int input = scanner.nextInt();
            i = boot.firstMenu(input);
        }
        while (i == -1);
        System.out.println("the menu chosen is "+ i);
        switch (i) {
            case 1:
                ec.run(userID, userType, speakerIDS);
            case 2:
                // Run method for messages
            case 3:
                //Run method for broadcasts
        }
    }
}
