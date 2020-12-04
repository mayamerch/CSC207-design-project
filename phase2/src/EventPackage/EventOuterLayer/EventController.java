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


    public ArrayList<Integer> getSpeakerList() {
        return speakerList;
    }

    public RoomManager getRoomManager() {
        return roomManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }


    public int getUserId() {
        return userId;
    }

    public boolean isUserVIP() {
        return userVIP;
    }

    public boolean signUp(int eventId) {

        boolean status = eventManager.enroll(eventId, userId, userVIP);

        eventGateway.write(eventManager);
        roomGateway.write(roomManager);

        return status;

    }



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


    public int cancelAttend(int eventId) {
        int status = eventManager.unenroll(eventId, userId);
        eventGateway.write(eventManager);
        roomGateway.write(roomManager);
        return status;
    }


    public boolean createRoom(int capacity){
        roomManager.createRoom(capacity);
        eventGateway.write(eventManager);
        roomGateway.write(roomManager);
        return true;
    }


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
}
