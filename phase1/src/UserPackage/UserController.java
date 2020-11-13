package UserPackage;
import EventPackage.EventManager;

import java.util.Scanner;

public abstract class UserController {
    protected int currentUserId;
    protected UserManager userManager;
    protected EventManager eventManager;
    // private AttendanceManager;?
    Scanner scanner = new Scanner(System.in);

    public UserController(UserManager userManager, EventManager eventManager){
        this.userManager = userManager;
        this.eventManager = eventManager;
        // the user manager and event manager are formed in the system controller
    }


    /**
     TODO: change this so username or ID entered, and function will get User By ID to validate
     */
    public void UserLogin() {
        System.out.println("Enter Username");
        String username = scanner.nextLine();
        System.out.println("Enter Password");
        String password = scanner.nextLine();
        int potentialID = userManager.validateLogin(username, password);
        if (potentialID >= 0 ) {
            currentUserId = potentialID;
            System.out.println("Login Successful");
        } else {
            System.out.println("Invalid email or password");
        }
    }
    public int validateUserIDInput(){
        int userID;
        System.out.println("Enter ID of User");
        userID = scanner.nextInt();
        if (userManager.getUserByID(userID) != null){
            return userID;}
        else{
            System.out.println("That is not a Valid UserID. Please try Again.");
            return validateUserIDInput();
        }
    }

    public int validateEventInput(){
        int eventID;
        System.out.println("Enter ID of Event");
        eventID = scanner.nextInt();
        if (eventManager.getEvent(eventID) != null){
            return eventID;}
        else{
            System.out.println("Invalid Event ID, please Try again");
            return validateEventInput();
        }
    }
}
