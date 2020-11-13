package UserPackage;

import EventPackage.EventManager;
import EventPackage.EventRoomGateway;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrganiserController extends EventSignUpSystem{
    private EventRoomGateway gateway;
    // add an attribute for the organiser currently logged in here
    // for when we implement chat functionality

    OrganiserController(UserManager user_manager, EventManager event_manager){
        super(user_manager, event_manager);
        // by making this an attribute of the organiser controller we ensure there is only one and
        // it can be accessed by the organiser controller
        this.gateway = new EventRoomGateway();
    }


    public void cancel_event(){
        System.out.println("Enter ID of Event to be cancelled");

        int eventID = validateEventInput();
        if (event_manager.cancelEvent(eventID)){
            System.out.println("The event is cancelled");
        }
        else{
            System.out.println("Event not found");
        }
        // This method removes the event at index i if the event has the same ID
        // however if an event is removed, the event after it has a different
        // index than its ID and the cancel event method returns false since
        // it cant find it
    }
    public boolean reschedule_event() throws ParseException {
        // Could also have the user input all the data at once and have the
        // method split the string
        System.out.println("Enter ID of Event to be rescheduled");

        int eventID = validateEventInput();
        System.out.println("Enter the new time of the event: \"dd-MMM-yyyy HH:mm:ss\"");
        SimpleDateFormat formatter6=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        // add a regular expression here?
        String eventtime = scanner.nextLine();
        Date date6=formatter6.parse(eventtime);
        //check if there is a room conflict
        System.out.println("Enter Room Number for the rescheduled event");
        // Validate this?
        int RoomNumber = scanner.nextInt();
        System.out.println("Enter ID of speaker for the event");
        // add special input -1 for same speaker? may introduce bugs in other functions
        int SpeakerID = validateUserIDInput();
        System.out.println("Enter new duration for the event");
        int duration = scanner.nextInt();
        return event_manager.reschedule(eventID, date6, RoomNumber, SpeakerID, duration);
        }

    public void create_speaker(){
        System.out.println("Enter Username of speaker");

        String username = scanner.nextLine();
        System.out.println("Enter Password for speaker");
        String password = scanner.nextLine();
        user_manager.create_account(username, password, "Speaker");
    }

    /** TODO: implement this when scanner implemented
     */

    public ArrayList<StringBuilder> read_schedule_text(){
        System.out.println("Enter Pathway of File to be scanned");
        String pathway = scanner.nextLine();
        File NewFile;
        NewFile = new File(pathway);
        return gateway.read(NewFile);
    }

    /** TODO: implement this when Broadcast implemented
     */
    public void send_broadcast(){}

    // This controller would likely assign speakers to talks using a method in event manager




}
