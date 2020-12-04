package EventPackage.EventOuterLayer;


import EventPackage.EventEntities.*;
import EventPackage.EventGateways.EventGateway;
import EventPackage.EventGateways.RoomGateway;
import EventPackage.EventUseCases.EventManager;
import EventPackage.EventUseCases.RoomManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
     * @param userId Id of the current user
     * @param userVIP true is the current user is a vip, false if not
     * @param speakerList List of speakers ids in this program
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


    /**
     * return the list of speakers
     * @return the list of speakers
     */
    public ArrayList<Integer> getSpeakerList() {
        return speakerList;
    }

    /**
     * returns the RoomManager
     * @return the RoomManger
     */
    public RoomManager getRoomManager() {
        return roomManager;
    }

    /**
     * Returns the EventManager
     * @return the EventManager
     */
    public EventManager getEventManager() {
        return eventManager;
    }

    /**
     * returns the Users Id
     * @return the Users Id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * returns if this user is a VIP or not
     * @return whether user is VIP or not
     */
    public boolean isUserVIP() {
        return userVIP;
    }

    /**
     * Signs the current user up for an event
     * @param eventId Id of the event to join
     * @return true if user was added
     *          false if he wasn't
     */
    public boolean signUp(int eventId) {

        boolean status = eventManager.enroll(eventId, userId, userVIP);

        eventGateway.write(eventManager);
        roomGateway.write(roomManager);

        return status;

    }


    /**
     * Creates a party event if all the information is suitable
     * @param EventName Name of the event
     * @param EventCapacity capacity of the event
     * @param EventDate Date of the event
     * @param EventRoom Room id of the event
     * @param EventDuration duration of the event
     * @param EventVIP VIP status of the event
     * @return status of process, 0 is success
     */
    public int createParty(String EventName, int EventCapacity, Date EventDate, int EventRoom,
                           int EventDuration, boolean EventVIP) {



        if (!roomManager.roomExists(EventRoom))
            return -4;

        if (EventCapacity > roomManager.findRoom(EventRoom).getRoomCapacity())
            return -3;

        if (eventManager.createParty(EventName, EventCapacity, EventDate, EventRoom, EventDuration,
                EventVIP) == -1)
            return -1;
        else {
            eventGateway.write(eventManager);
            roomGateway.write(roomManager);
            return 0;
        }
    }

    /**
     * Creates a Single Speaker event if all the information is suitable
     * @param EventName Name of the event
     * @param capacity capacity of the event
     * @param date Date of the event
     * @param room Room id of the event
     * @param duration duration of the event
     * @param vip VIP status of the event
     * @param speaker speaker of this event
     * @return status of process, 0 is success
     */
    public int createSingleSpeakerEvent(String EventName, int capacity, Date date, int room,
                                        int duration, boolean vip, int speaker) {

        if (capacity > roomManager.findRoom(room).getRoomCapacity())
            return -3;

        if (!speakerList.contains(speaker) || !roomManager.roomExists(room))
            return -4;

        if (eventManager.createSingleSpeakerEvent(EventName, capacity, date, room, duration, vip, speaker) == -1)
            return -1;
        else {
            eventGateway.write(eventManager);
            roomGateway.write(roomManager);
            return 0;
        }
    }


    /**
     * Creates a Multi-Speaker event if all the information is suitable
     * @param EventName Name of the event
     * @param capacity capacity of the event
     * @param date Date of the event
     * @param room Room id of the event
     * @param duration duration of the event
     * @param vip VIP status of the event
     * @param speakers list of speakers of this event
     * @return status of process, 0 is success
     */
    public int createMultiSpeakerEvent(String EventName, int capacity, Date date, int room,
                                       int duration, boolean vip, ArrayList<Integer> speakers) {

        if (!roomManager.roomExists(room))
            return -4;

        for (int Speaker: speakers)
            if (!speakerList.contains(Speaker))
                return -4;

        if (capacity > roomManager.findRoom(room).getRoomCapacity())
            return -3;


        int status = eventManager.createMultiSpeakerEvent(EventName, capacity, date, room, duration, vip, speakers);
        eventGateway.write(eventManager);
        roomGateway.write(roomManager);
        return status;

    }

    /**
     * stops current user from attending an event
     * @param eventId id of the event
     * @return true if successful, false if not
     */
    public int cancelAttend(int eventId) {
        int status = eventManager.unenroll(eventId, userId);
        eventGateway.write(eventManager);
        roomGateway.write(roomManager);
        return status;
    }


    /**
     * Create a room
     * @param capacity capacity of the room
     * @return true
     */
    public boolean createRoom(int capacity){
        roomManager.createRoom(capacity);
        eventGateway.write(eventManager);
        roomGateway.write(roomManager);
        return true;
    }


    /**
     * returns type an event
     * @param eventId id of the event
     * @return 0 if event doesnt exist
     *         1 if its a party
     *         2 if its a SingleSpeaker event
     *         3 if its a MultiSpeaker event
     */
    public int findType(int eventId) {
        if (eventManager.isParty(eventId))
            return 1;
        else if (eventManager.isSingleSpeakerEvent(eventId))
            return 2;
        else if (eventManager.isMultiSpeakerEvent(eventId))
            return 3;
        else
            return 0;
    }


    /**
     * Edits a Party event if information is suitable
     * @param eventId id of the event
     * @param EventName Name of event
     * @param capacity capacity of event
     * @param date date of event
     * @param room room id of event
     * @param duration duration of event
     * @param vip vip status of event
     * @return status of process, 0 is a success
     */
    public int editParty(int eventId, String EventName, int capacity,
                         Date date, int room, int duration, boolean vip) {

        if (!roomManager.roomExists(room))
            return -4;

        if (eventManager.rescheduleParty(eventId, EventName, capacity, date, room, duration, vip)) {
            eventGateway.write(eventManager);
            roomGateway.write(roomManager);
            return 0;
        }
        else
            return -1;
    }


    /**
     * Edits a Single Speaker event if information is suitable
     * @param eventId id of the event
     * @param EventName Name of event
     * @param capacity capacity of event
     * @param date date of event
     * @param room room id of event
     * @param duration duration of event
     * @param vip vip status of event
     * @param speaker speaker at the event
     * @return status of process, 0 is a success
     */
    public int editSingleSpeaker(int eventId, String EventName, int capacity,
                         Date date, int room, int duration, boolean vip,
                                 int speaker) {

        if (!speakerList.contains(speaker) || !roomManager.roomExists(room))
            return -4;

        if (eventManager.rescheduleSingleSpeaker(eventId, EventName, capacity, date, room, duration, speaker, vip)) {
            eventGateway.write(eventManager);
            roomGateway.write(roomManager);
            return 0;
        }
        else
            return -1;
    }


    /**
     * Edits a Multi-Speaker event if information is suitable
     * @param eventId id of the event
     * @param EventName Name of event
     * @param capacity capacity of event
     * @param date date of event
     * @param room room id of event
     * @param duration duration of event
     * @param vip vip status of event
     * @param speakers list of speakers at the event
     * @return status of process, 0 is a success
     */
    public int editMultiSpeaker(int eventId, String EventName, int capacity,
                                Date date, int room, int duration, boolean vip,
                                ArrayList<Integer> speakers) {

        if (!roomManager.roomExists(room))
            return -4;

        for (int Speaker: speakers)
            if (!speakerList.contains(Speaker))
                return -4;

        if (eventManager.rescheduleMultiSpeaker(eventId, EventName, capacity, date, room, duration, speakers, vip)) {
            eventGateway.write(eventManager);
            roomGateway.write(roomManager);
            return 0;
        }
        else
            return -1;
    }

    /**
     * Cancels an event if it exists
     * @param eventId id of the event
     * @return true if it was deleted, false otherwise
     */
    public boolean cancelEvent(int eventId) {
        boolean status = eventManager.cancelEvent(eventId);
        eventGateway.write(eventManager);
        roomGateway.write(roomManager);
        return status;
    }
}
