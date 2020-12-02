package EventPackage.EventOuterLayer;


import EventPackage.EventEntities.Event;
import EventPackage.EventGateways.EventGateway;
import EventPackage.EventGateways.RoomGateway;
import EventPackage.EventUseCases.EventManager;
import EventPackage.EventUseCases.RoomManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class EventController {

    private EventManager eventManager;
    private RoomManager roomManager;
    private EventGateway eventGateway;
    private RoomGateway roomGateway;
    private int userId;
    private boolean userVIP;
    private ArrayList<Integer> speakerList;


    /**
     * Creates an instance of EventController and loads in all the saved data
     */
    public EventController(int userId, boolean userVIP, ArrayList<Integer> speakerList) {
        eventGateway = new EventGateway();
        roomGateway = new RoomGateway();
        eventManager = eventGateway.getEventManager();
        roomManager = roomGateway.getRoomManager();

        this.userId = userId;
        this.userVIP = userVIP;
        this.speakerList = speakerList;
    }




    public boolean signUp(int eventId) {

        boolean status = eventManager.enroll(eventId, userId, userVIP);
        return status;
    }




    public Object[][] getAllEvents() {

        Object[][] data = new Object[eventManager.getEventList().size()][];

        for (Event event: eventManager.getEventList()) {
            int availableSpace = event.getEventCapacity() - event.getEventAttendees().size();
            Object[] eventInfo = {event.getEventId(), event.getEventName(), event.getEventType(), event.getEventRoom(),
                    event.getEventDate(), event.getEventDuration(), event.getEventCapacity(), availableSpace,
                    event.getVIPStatus()};
            data[0] = eventInfo;
        }

        return data;
    }


    public Object[][] getEventsAttending() {
        Object[][] data = new Object[eventManager.myEvents(userId).size()][];

        for (Event event: eventManager.myEvents(userId)) {
            int availableSpace = event.getEventCapacity() - event.getEventAttendees().size();
            Object[] eventInfo = {event.getEventId(), event.getEventName(), event.getEventType(), event.getEventRoom(),
                    event.getEventDate(), event.getEventDuration(), event.getEventCapacity(), availableSpace,
                    event.getVIPStatus()};
            data[0] = eventInfo;
        }

        return data;
    }

/*
    private void cancelAttend(int UserId) {
        Scanner reader = new Scanner(System.in);

        ep.chooseEvent();
        String UserInput = reader.nextLine();
        while (!checkInput(UserInput)) {
            UserInput = reader.nextLine();
        }
        int UserInputInt = Integer.parseInt(UserInput);

        int status = -1;
        for (Event event: em.myEvents(UserId)) {
            if (event.getEventId() == UserInputInt) {
                em.unenroll(UserInputInt, UserId);
                status = 0;
            }
        }

        ep.printStatus(status);
        ep.goBack();
    }


    private int createEvent(String UserInput, ArrayList<Integer> speakerIds) {
        String eventName;
        int eventRoom;
        Date eventDate;
        int eventSpeaker;
        int eventDuration = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        int status;

        String[] splitInput = UserInput.split(";");
        if (splitInput.length != 4)
            return -2;

        eventName = splitInput[0];
        try {
            eventRoom = Integer.parseInt(splitInput[1]);
            eventDate = sdf.parse(splitInput[2]);
            eventSpeaker = Integer.parseInt(splitInput[3]);
        }
        catch (Exception e) {
            return -3;
        }

        if (!(speakerIds.contains(eventSpeaker)) || !(rm.roomExists(eventRoom))) {
            return -4;
        }
        status = em.createEvent(eventName, eventRoom, eventDate, eventSpeaker, eventDuration);


        return status;
    }

    private void createRoom(int input) {
        int id = rm.createRoom(input);
        ep.printStatus(0);
        ep.displayRoom(id);
    }
    */

}
