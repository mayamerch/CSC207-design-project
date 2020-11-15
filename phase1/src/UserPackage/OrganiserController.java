package UserPackage;

import EventPackage.EventManager;
import EventPackage.EventRoomGateway;
import EventPackage.Room;
import EventPackage.RoomManager;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrganiserController extends UserController {
    private EventRoomGateway gateway;
    private RoomManager roomManager;
    // add an attribute for the organiser currently logged in here
    // for when we implement chat functionality

    OrganiserController(UserManager user_manager, EventManager event_manager){
        super(user_manager, event_manager);
        // by making this an attribute of the organiser controller we ensure there is only one and
        // it can be accessed by the organiser controller
        this.gateway = new EventRoomGateway();
        this.roomManager = new RoomManager();
    }


    public void cancelEvent(){
        System.out.println("Enter ID of Event to be cancelled");
        int eventID = validateEventInput();
        cancelEvent(eventID);}

    public boolean cancelEvent(int eventID){
        if (eventManager.cancelEvent(eventID)){
            System.out.println("The event is cancelled");
            return true;
        }
        else{
            System.out.println("Event not found");
            return false;
            // Should not run if validate event input works properly
        }
        // This method removes the event at index i if the event has the same ID
        // however if an event is removed, the event after it has a different
        // index than its ID and the cancel event method returns false since
        // it cant find it
    }

    public Date enterDate(){
        try {
            System.out.println("Enter Date Object Here in format \"dd-MMM-yyyy HH:mm:ss\"");
            SimpleDateFormat formatter6=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            String eventTime = scanner.nextLine();
            return formatter6.parse(eventTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Not sure how to make this recursive so it continues until you put in a date time.

    /**
     TODO: Idieally we would have a function in RoomManager that displays availible rooms somehow?
     */
    public int enterRoom(){
        System.out.println(" Enter the room number here");
        int roomNumber = scanner.nextInt();
        if (roomManager.findRoom(roomNumber) != null){
           return roomNumber;
        }
        else{
            System.out.println("This is not a valid room number");
            return enterRoom();
        }
    }

    public int enterSpeaker(){
        int speakerID;
        boolean isSpeaker = false;
        do {
            System.out.println("Enter ID of speaker for the event");
            // add special input -1 for same speaker? may introduce bugs in other functions
            speakerID = validateUserIDInput();
            isSpeaker = userManager.getUserByID(speakerID) instanceof Speaker;
            if (!isSpeaker)
                System.out.println("Invalid speaker ID, please enter again.");
        } while (!isSpeaker);
        return speakerID;
    }

    public boolean createEvent(){
        Date eventDate;
        boolean isaDate;
        do {
            System.out.println("Enter the date of the new event");
            eventDate = enterDate();
            isaDate = eventDate != null;
            if (!isaDate) {
                System.out.println(" Invalid date, Try again");}
        } while (!isaDate);
        System.out.println("Enter the name of the new event");
        String eventName = scanner.nextLine();
        System.out.println("Enter the room number of the event");
        int roomNumber = enterRoom();
        int speakerID = enterSpeaker();
        System.out.println(" Enter the duration of the event");
        int durationInput = scanner.nextInt();
        if (eventManager.createEvent(eventName, roomNumber, eventDate,speakerID,durationInput) == -1){
            System.out.println(" The room or speaker is not availible at the date you selected");
            // Is that what this function checks for?
            return false;
        }
        else{
            System.out.println("The event was created sucessfully");
            return true;}
    }

    public void createRoom(){
        System.out.println(" Enter the capacity of the room you wish to create");
        int roomcapacity = scanner.nextInt();
        roomManager.createRoom(roomcapacity);
    }

    public boolean rescheduleEvent(){
        // Could also have the user input all the data at once and have the
        // method split the string

        System.out.println("Enter ID of Event to be rescheduled");

        int eventID = validateEventInput();
        Date date = enterDate();
        if (date == null){return false;}
        //check if there is a room conflict
        System.out.println("Enter Room Number for the rescheduled event");
        // Validate this?
        int RoomNumber = enterRoom();
        int speakerID = enterSpeaker();
        System.out.println("Enter new duration for the event");
        int duration = scanner.nextInt();
        return eventManager.reschedule(eventID, date, RoomNumber, speakerID, duration);
    }

    public void create_speaker(){
        System.out.println("Enter Username of speaker");
        String username = scanner.nextLine();
        System.out.println("Enter Password for speaker");
        String password = scanner.nextLine();
        userManager.createAccount(username, password, "Speaker");
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
