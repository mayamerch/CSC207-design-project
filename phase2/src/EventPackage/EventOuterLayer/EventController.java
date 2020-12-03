package EventPackage.EventOuterLayer;


import EventPackage.EventEntities.Event;
import EventPackage.EventEntities.Room;
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


    private boolean checkInput(String Input) {
        try {
            Integer.parseInt(Input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public boolean signUp(String eventId) {
        if (!checkInput(eventId))
            return false;

        int id = Integer.parseInt(eventId);

        boolean status = eventManager.enroll(id, userId, userVIP);
        return status;
    }




    public Object[][] getAllEvents() {

        Object[][] data = new Object[eventManager.getEventList().size()][];

        for (int i = 0; i < eventManager.getEventList().size(); i++) {

            Event event = eventManager.getEventList().get(i);
            int availableSpace = event.getEventCapacity() - event.getEventAttendees().size();

            Object[] eventInfo = {event.getEventId(), event.getEventName(), event.getEventType(), event.getEventRoom(),
                    event.getEventDate(), event.getEventDuration(), event.getEventCapacity(), availableSpace,
                    event.getVIPStatus()};
            data[i] = eventInfo;
        }

        return data;
    }


    public Object[][] getEventsAttending() {
        Object[][] data = new Object[eventManager.myEvents(userId).size()][];

        for (int i = 0; i < eventManager.myEvents(userId).size(); i++) {

            Event event = eventManager.myEvents(userId).get(i);
            int availableSpace = event.getEventCapacity() - event.getEventAttendees().size();

            Object[] eventInfo = {event.getEventId(), event.getEventName(), event.getEventType(), event.getEventRoom(),
                    event.getEventDate(), event.getEventDuration(), event.getEventCapacity(), availableSpace,
                    event.getVIPStatus()};
            data[i] = eventInfo;
        }

        return data;
    }


    public Object[][] getAvailEvents() {
        Object[][] data = new Object[eventManager.availEvents(userId, userVIP).size()][];

        for (int i = 0; i < eventManager.availEvents(userId, userVIP).size(); i++) {

            Event event = eventManager.availEvents(userId, userVIP).get(i);
            int availableSpace = event.getEventCapacity() - event.getEventAttendees().size();

            Object[] eventInfo = {event.getEventId(), event.getEventName(), event.getEventType(), event.getEventRoom(),
                    event.getEventDate(), event.getEventDuration(), event.getEventCapacity(), availableSpace,
                    event.getVIPStatus()};
            data[i] = eventInfo;
        }

        return data;
    }

    public Object[][] getRooms() {
        Object[][] data = new Object[roomManager.getRoomList().size()][];

        for (int i = 0; i < roomManager.getRoomList().size(); i++) {

            Room room = roomManager.getRoomList().get(i);

            Object[] roomInfo = {room.getRoomNumber(), room.getRoomCapacity()};
            data[i] = roomInfo;
        }

        return data;
    }



    public int createParty(String EventName, String EventCapacity, String EventDate, String EventRoom,
                           String EventDuration, String EventVIP) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        int capacity;
        Date date;
        int room;
        int duration;
        boolean vip;

        try {
            capacity = Integer.parseInt(EventCapacity);
            date = sdf.parse(EventDate);
            room = Integer.parseInt(EventRoom);
            duration = Integer.parseInt(EventDuration);
            vip = Boolean.parseBoolean(EventVIP);
        }
        catch (Exception e) {
            return -2;
        }

        if (capacity > roomManager.findRoom(room).getRoomCapacity())
            return -3;

        return eventManager.createParty(EventName, capacity, date, room, duration, vip);

    }

    public int createSingleSpeakerEvent(String EventName, String EventCapacity, String EventDate, String EventRoom,
                                        String EventDuration, String EventVIP, String EventSpeaker) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        int capacity;
        Date date;
        int room;
        int duration;
        boolean vip;
        int speaker;

        try {
            capacity = Integer.parseInt(EventCapacity);
            date = sdf.parse(EventDate);
            room = Integer.parseInt(EventRoom);
            duration = Integer.parseInt(EventDuration);
            vip = Boolean.parseBoolean(EventVIP);
            speaker = Integer.parseInt(EventSpeaker);
        }
        catch (Exception e) {
            return -2;
        }

        if (capacity > roomManager.findRoom(room).getRoomCapacity())
            return -3;

        return eventManager.createSingleSpeakerEvent(EventName, capacity, date, room, duration, vip, speaker);
    }


    public int createMultiSpeakerEvent(String EventName, String EventCapacity, String EventDate, String EventRoom,
                                       String EventDuration, String EventVIP, String[] EventSpeaker) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        int capacity;
        Date date;
        int room;
        int duration;
        boolean vip;
        ArrayList<Integer> speakers = new ArrayList<>();

        try {
            capacity = Integer.parseInt(EventCapacity);
            date = sdf.parse(EventDate);
            room = Integer.parseInt(EventRoom);
            duration = Integer.parseInt(EventDuration);
            vip = Boolean.parseBoolean(EventVIP);
            for (String num: EventSpeaker)
                speakers.add(Integer.parseInt(num));
        }
        catch (Exception e) {
            return -2;
        }

        if (capacity > roomManager.findRoom(room).getRoomCapacity())
            return -3;

        return eventManager.createMultiSpeakerEvent(EventName, capacity, date, room, duration, vip, speakers);
    }


    public int cancelAttend(String eventId) {
        if (!checkInput(eventId))
            return -2;

        return eventManager.unenroll(Integer.parseInt(eventId), userId);
    }


    public boolean createRoom(String RoomCapacity){
        int capacity;

        try {
           capacity = Integer.parseInt(RoomCapacity);
        }
        catch (Exception e) {
            return false;
        }

        roomManager.createRoom(capacity);
        return true;
    }
}
