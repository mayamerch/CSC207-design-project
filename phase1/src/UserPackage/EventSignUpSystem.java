package UserPackage;
import EventPackage.Event;
import EventPackage.EventManager;

import java.util.Scanner;

public abstract class EventSignUpSystem {
   protected UserManager user_manager;
   protected EventManager event_manager;
   // private AttendanceManager;?
   Scanner scanner = new Scanner(System.in);

    public EventSignUpSystem(UserManager user_manager, EventManager event_manager){
        this.user_manager = user_manager;
        this.event_manager = event_manager;
        // the user manager and event manager are formed in the system controller
    }


    /**
     TODO: change this so username or ID entered, and function will get User By ID to validate
     */
    public void UserLogin (User user) {
        System.out.println("Enter Username");

        String username = scanner.nextLine();
        System.out.println("Enter Password");
        String password = scanner.nextLine();
        if (user_manager.validate_login(user, username, password)) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Invalid email or password");
        }
    }
    public int validateUserIDInput(){
        int userID;
        System.out.println("Enter ID of User");
        userID = scanner.nextInt();
        if (user_manager.getUserByID(userID) != null){
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
        if (event_manager.getEvent(eventID) != null){
            return eventID;}
        else{
            System.out.println("Invalid Event ID, please Try again");
            return validateEventInput();
        }
    }
}
