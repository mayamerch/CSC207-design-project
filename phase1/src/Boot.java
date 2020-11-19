import EventPackage.EventController;
import EventPackage.EventManager;
import EventPackage.RoomManager;
import UserPackage.User;
import UserPackage.UserController;
import UserPackage.UserGateway;
import UserPackage.UserManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Boot {

    UserController uc = new UserController();
    Scanner scanner = new Scanner(System.in);

    public boolean firstMenu(){
        String input;
        System.out.println("Please choose an option\n" +
                "1. Sign in\n" +
                "2. Create a new account");
        input = scanner.nextLine();
        while (!input.equals("1") && !input.equals("2")) {
            System.out.println("Please try again");
            input = scanner.nextLine();
        }
        if (input.equals("2")) {
            creatNewAccount();
        }
        signIn();
        return true;
    }

    public Integer secondMenu(String input){
        if(input.equals("1")){ return 1; }
        else if (input.equals("2")){ return 2; }
        else if (input.equals("3")){ return 3; }
        else{
            System.out.println("Try again");
            return -1;
        }

    }

    public boolean signIn() {
        // Is some kind of "go back" functionality needed?
        char userType = uc.UserLogin();
        while (userType == 'N') {
            userType = uc.UserLogin();
        }
        return true;
    }

    public boolean creatNewAccount() {

    }

    public ArrayList<Integer> LLtoAL(LinkedList<User> ll) {
        ArrayList<Integer> userIDs = new ArrayList<>();
        for (User user : ll) {
            userIDs.add(user.getUserID());
        }
        return userIDs;
    }



    public static void main(String[] args){
        Boot boot = new Boot();
        UserController uc = boot.uc;
        Scanner scanner = new Scanner(System.in);
        boot.firstMenu();
        //once logged in
        int currId = uc.currentUserId;
        EventController ec = new EventController();
        System.out.println("What would you like to do?\n" +
                "1. Manage Events\n" +
                "2. Manage Conversations\n" +
                "3. Manage Friends\n" +
                "Please input a number: ");
        do {
            String input = scanner.nextLine();
            i = boot.secondMenu(input);
        }
        while (i == -1);
        System.out.println("The menu chosen is "+ i);
        switch (i) {
            case 1:
                ec.run(currId, userType, boot.LLtoAL(uc.getUserManager().getSpeakerList()));
            case 2:
                // ConversationPresenter called here
            case 3:
                //UserPresenter called here
        }
    }
}
