package UserPackage;

import EventPackage.Event;
import EventPackage.EventManager;

import java.util.ArrayList;

public class AttendeeController extends UserController {
    private EventManager event_manager;
    private UserManager user_manager;
    // private AttendanceManager;
    // Since this controller has all the individual user functions, we have an
    // attribute for


    AttendeeController(UserManager user_manager, EventManager event_manager){

        super(user_manager, event_manager);
    }

    // current implementation of event manager signs up and removes people through event manager
    // if it were done through an Attendance manager it could be an attribute here


    public boolean eventSignUp(){
     // needs to call on Attendance manager and user manager to find user then
     // put in the user through Attendance manager
        System.out.println("Enter ID of Event");
        int eventID = validateEventInput();
        if (event_manager.enroll(eventID, currentUserId) == 1){
            return true;
        }
        else if (event_manager.enroll(eventID, currentUserId) == 0)
        {System.out.println("Event full");
            return false; }
        else{
            System.out.println("Event ID invalid");
            // This should never happen if eventInput function works properly
            return false;
        }
     }


    public boolean eventUnenroll(){
        System.out.println("Enter ID of Event");
        int eventID = validateEventInput();
        if (event_manager.unenroll(eventID, currentUserId) == 1){
            return true;
        }
        else if (event_manager.unenroll(eventID, currentUserId) == 0)
        {System.out.println("You are already un enrolled");
            return false; }
        else{
            System.out.println("Event ID invalid");
            return false;
        }
    }

    public ArrayList<Event> see_my_events(){
        return event_manager.myEvents(currentUserId);
    }

    // Remove the return false if attendee list is added
    public boolean addFriend(){
        System.out.println("Enter ID of friend");
        int friendID = validateUserIDInput();
        if (friendID == currentUserId){
            System.out.println("Cannot input your ID here");
            return false;
        }
        return user_manager.AddFriend(currentUserId, friendID);
    }
}
