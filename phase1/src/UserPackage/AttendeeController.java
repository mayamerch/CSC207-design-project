package UserPackage;

import EventPackage.Event;
import EventPackage.EventManager;

import java.util.ArrayList;

public class AttendeeController extends EventSignUpSystem{
    private EventManager event_manager;
    private UserManager user_manager;
    // private AttendanceManager;
    private int using_userID;
    // Since this controller has all the individual user functions, we have an
    // attribute for


    AttendeeController(UserManager user_manager, EventManager event_manager){
        super(user_manager, event_manager);
        this.using_userID = 0;
    }

    // current implementation of event manager signs up and removes people through event manager
    // if it were done through an Attendance manager it could be an attribute here

    public boolean set_user_using(int userID){
        if (user_manager.validate_id(userID)){
            this.using_userID = userID;
            return true;
        }
        return false;
    }

    public boolean event_sign_up(){
     // needs to call on Attendance manager and user manager to find user then
     // put in the user through Attendance manager
        System.out.println("Enter ID of Event");
        int eventID = scanner.nextInt();
        if (event_manager.enroll(eventID, this.using_userID) == 1){
            return true;
        }
        else if (event_manager.enroll(eventID, this.using_userID) == 0)
        {System.out.println("Event full");
            return false; }
        else{
            System.out.println("Event ID invalid");
            return false;
        }
     }

*
     TODO: Duplicate code?



    public boolean event_unenroll(){
        System.out.println("Enter ID of Event");
        int eventID = scanner.nextInt();
        if (event_manager.unenroll(eventID, this.using_userID) == 1){
            return true;
        }
        else if (event_manager.enroll(eventID, this.using_userID) == 0)
        {System.out.println("You are already un enrolled");
            return false; }
        else{
            System.out.println("Event ID invalid");
            return false;
        }
    }

    public ArrayList<Event> see_my_events(){
        return event_manager.myEvents(using_userID);
    }

}
