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
                    event.getVIPStatus(), getSpeakerString(event.getEventId())};
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
                    event.getVIPStatus(), getSpeakerString(event.getEventId())};
            data[i] = eventInfo;
        }

        return data;
    }


    public Object[][] getEventsSpeakingAt() {
        Object[][] data = new Object[eventManager.speakingAt(userId).size()][];

        for (int i = 0; i < eventManager.speakingAt(userId).size(); i++) {

            Event event = eventManager.speakingAt(userId).get(i);
            int availableSpace = event.getEventCapacity() - event.getEventAttendees().size();

            Object[] eventInfo = {event.getEventId(), event.getEventName(), event.getEventType(), event.getEventRoom(),
                    event.getEventDate(), event.getEventDuration(), event.getEventCapacity(), availableSpace,
                    event.getVIPStatus(), getSpeakerString(event.getEventId())};
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
                    event.getVIPStatus(), getSpeakerString(event.getEventId())};
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

        if (!roomManager.roomExists(room))
            return -4;

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

        if (!speakerList.contains(speaker) || !roomManager.roomExists(room))
            return -4;

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

        if (!roomManager.roomExists(room))
            return -4;

        for (int Speaker: speakers)
            if (!speakerList.contains(Speaker))
                return -4;

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


    public int findType(String EventId) {
        int eventId;

        try {
            eventId = Integer.parseInt(EventId);
        }
        catch (Exception e) {
            return -1;
        }

        if (eventManager.isParty(eventId))
            return 1;
        else if (eventManager.isSingleSpeakerEvent(eventId))
            return 2;
        else if (eventManager.isMultiSpeakerEvent(eventId))
            return 3;
        else
            return 0;
    }


    public String[] getPartyInfo(String EventId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        String[] eventInfo = new String[6];
        int eventId;

        eventId = Integer.parseInt(EventId);
        Party event = (Party) eventManager.getEvent(eventId);

        eventInfo[0] = event.getEventName();
        eventInfo[1] = String.valueOf(event.getEventCapacity());
        eventInfo[2] = sdf.format(event.getEventDate());
        eventInfo[3] = String.valueOf(event.getEventRoom());
        eventInfo[4] = String.valueOf(event.getEventDuration());
        eventInfo[5] = String.valueOf(event.getVIPStatus());

        return eventInfo;
    }

    public String[] getSingleSpeakerInfo(String EventId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        String[] eventInfo = new String[7];
        int eventId;

        eventId = Integer.parseInt(EventId);
        SingleSpeakerEvent event = (SingleSpeakerEvent) eventManager.getEvent(eventId);

        eventInfo[0] = event.getEventName();
        eventInfo[1] = String.valueOf(event.getEventCapacity());
        eventInfo[2] = sdf.format(event.getEventDate());
        eventInfo[3] = String.valueOf(event.getEventRoom());
        eventInfo[4] = String.valueOf(event.getEventDuration());
        eventInfo[5] = String.valueOf(event.getVIPStatus());
        eventInfo[6] = String.valueOf(event.getEventSpeaker());

        return eventInfo;
    }


    public String[] getMultiSpeakerInfo(String EventId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        String[] eventInfo = new String[7];
        int eventId;

        eventId = Integer.parseInt(EventId);
        MultiSpeakerEvent event = (MultiSpeakerEvent) eventManager.getEvent(eventId);

        eventInfo[0] = event.getEventName();
        eventInfo[1] = String.valueOf(event.getEventCapacity());
        eventInfo[2] = sdf.format(event.getEventDate());
        eventInfo[3] = String.valueOf(event.getEventRoom());
        eventInfo[4] = String.valueOf(event.getEventDuration());
        eventInfo[5] = String.valueOf(event.getVIPStatus());
        eventInfo[6] = String.valueOf(event.getEventSpeakers());

        return eventInfo;
    }


    public int editParty(String EventId, String EventName, String EventCapacity,
                         String EventDate, String EventRoom, String EventDuration, String EventVIP) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        int eventId = Integer.parseInt(EventId);
        int capacity;
        Date date;
        int room;
        int duration;
        boolean vip;

        Party event = (Party) eventManager.getEvent(eventId);

        try {
            if (EventCapacity.equals(""))
                capacity = event.getEventCapacity();
            else
                capacity = Integer.parseInt(EventCapacity);

            if (EventDate.equals(""))
                date = event.getEventDate();
            else
                date = sdf.parse(EventDate);

            if (EventCapacity.equals(""))
                room = event.getEventRoom();
            else
                room = Integer.parseInt(EventRoom);

            if (EventCapacity.equals(""))
                duration = event.getEventDuration();
            else
                duration = Integer.parseInt(EventDuration);

            vip = Boolean.parseBoolean(EventVIP);
        }
        catch (Exception e) {
            return -2;
        }

        if (!roomManager.roomExists(room))
            return -4;

        if (eventManager.rescheduleParty(eventId, EventName, capacity, date, room, duration, vip))
            return 0;
        else
            return -1;
    }


    public int editSingleSpeaker(String EventId, String EventName, String EventCapacity,
                         String EventDate, String EventRoom, String EventDuration, String EventVIP,
                                 String EventSpeaker) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        int eventId = Integer.parseInt(EventId);
        int capacity;
        Date date;
        int room;
        int duration;
        boolean vip;
        int speaker;

        SingleSpeakerEvent event = (SingleSpeakerEvent) eventManager.getEvent(eventId);

        try {
            if (EventCapacity.equals(""))
                capacity = event.getEventCapacity();
            else
                capacity = Integer.parseInt(EventCapacity);

            if (EventDate.equals(""))
                date = event.getEventDate();
            else
                date = sdf.parse(EventDate);

            if (EventCapacity.equals(""))
                room = event.getEventRoom();
            else
                room = Integer.parseInt(EventRoom);

            if (EventCapacity.equals(""))
                duration = event.getEventDuration();
            else
                duration = Integer.parseInt(EventDuration);

            if (EventSpeaker.equals(""))
                speaker = event.getEventSpeaker();
            else
                speaker = Integer.parseInt(EventSpeaker);

            vip = Boolean.parseBoolean(EventVIP);
        }
        catch (Exception e) {
            return -2;
        }

        if (!speakerList.contains(speaker) || !roomManager.roomExists(room))
            return -4;

        if (eventManager.rescheduleSingleSpeaker(eventId, EventName, capacity, date, room, duration, speaker, vip))
            return 0;
        else
            return -1;
    }


    public int editMultiSpeaker(String EventId, String EventName, String EventCapacity,
                         String EventDate, String EventRoom, String EventDuration, String EventVIP,
                                String EventSpeakers) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        String[] Speakers = EventSpeakers.split(",");
        int eventId = Integer.parseInt(EventId);
        int capacity;
        Date date;
        int room;
        int duration;
        boolean vip;
        ArrayList<Integer> speakers = new ArrayList<>();

        MultiSpeakerEvent event = (MultiSpeakerEvent) eventManager.getEvent(eventId);

        try {
            if (EventCapacity.equals(""))
                capacity = event.getEventCapacity();
            else
                capacity = Integer.parseInt(EventCapacity);

            if (EventDate.equals(""))
                date = event.getEventDate();
            else
                date = sdf.parse(EventDate);

            if (EventCapacity.equals(""))
                room = event.getEventRoom();
            else
                room = Integer.parseInt(EventRoom);

            if (EventCapacity.equals(""))
                duration = event.getEventDuration();
            else
                duration = Integer.parseInt(EventDuration);

            if (EventSpeakers.equals(""))
                speakers = event.getEventSpeakers();
            else
                for (String num: Speakers)
                    speakers.add(Integer.parseInt(num));

            vip = Boolean.parseBoolean(EventVIP);
        }
        catch (Exception e) {
            return -2;
        }

        if (!roomManager.roomExists(room))
            return -4;

        for (int Speaker: speakers)
            if (!speakerList.contains(Speaker))
                return -4;

        if (eventManager.rescheduleMultiSpeaker(eventId, EventName, capacity, date, room, duration, speakers, vip))
            return 0;
        else
            return -1;
    }


    private String getSpeakerString(int eventId) {
        Event event = eventManager.getEvent(eventId);

        if (event.getEventType().equals("Single Speaker"))
            return String.valueOf(((SingleSpeakerEvent) event).getEventSpeaker());
        else if (event.getEventType().equals("Multi-Speaker"))
            return String.valueOf(((MultiSpeakerEvent) event).getEventSpeakers());
        else
            return "None";
    }
}
