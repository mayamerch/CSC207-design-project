import EventPackage.EventController;
import EventPackage.EventManager;
import EventPackage.RoomManager;
import UserPackage.UserController;
import UserPackage.UserGateway;
import UserPackage.UserManager;

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

        UserController uc = new UserController();

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Login");
//        System.out.println("Enter your  Username: ");
//        String username = scanner.nextLine();
//        System.out.println("Password: ");
//        String password = scanner.nextLine();
        char userType = uc.UserLogin();
        while (userType == 'N') {
            userType = uc.UserLogin();
        }
        int currId = uc.currentUserId;
        Scanner scanner = new Scanner(System.in);
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
                //ec.run(currId, userType, uc.getUserManager().getSpeakerList());
            case 2:
                // ConversationPresenter called here
            case 3:
                //UserPresenter called here
        }
    }
}
