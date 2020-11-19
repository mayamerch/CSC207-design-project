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
        while (checkInput(input, new String[]{"1", "2"})) {
            System.out.println("Please try again");
            input = scanner.nextLine();
        }
        if (input.equals("1")) {
            return signIn();
        } else {
            if(createNewAccount()) {
                return signIn();
            } else {
                return false;
            }
        }
    }

    public Integer secondMenu(){
        System.out.println("What would you like to do?\n" +
                "1. Manage Events\n" +
                "2. Manage Conversations\n" +
                "3. Manage Friends\n" +
                "4. Exit" +
                "Please input a number: ");
        String input = scanner.nextLine();
        while (checkInput(input, new String[]{"1", "2", "3", "4"})) {
            System.out.println("Please try again");
            input = scanner.nextLine();
        }
        return Integer.parseInt(input);
    }

    private boolean checkInput(String s, String[] options) {
        for (String option: options) {
            if (s.equalsIgnoreCase(option)) {
                return true;
            }
        }
        return false;
    }

    public boolean signIn() {
        // Is some kind of "go back" functionality needed?
        char userType = uc.UserLogin();
        while (userType == 'N') {
            userType = uc.UserLogin();
        }
        return true;
    }

    public boolean createNewAccount() {
        return uc.createUser();
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
        boolean loggedIn = boot.firstMenu();
        while (!loggedIn) {
            loggedIn = boot.firstMenu();
        }
        //once logged in
        int currId = uc.currentUserId;
        EventController ec = new EventController();
        int op = boot.secondMenu();
        do {
            System.out.println("The menu chosen is " + op);
            switch (op) {
                case 1:
                    ec.run(currId, uc.getUserType(), boot.LLtoAL(uc.getSpeakerList()));
                    break;
                case 2:
                    // ConversationPresenter called here
                    break;
                case 3:
                    //UserPresenter called here
                    break;
                case 4:
                    uc.logOut();
                    break;
            }
            op = boot.secondMenu();
        } while (op != 4);
    }
}
