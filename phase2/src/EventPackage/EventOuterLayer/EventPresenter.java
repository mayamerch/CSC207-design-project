package EventPackage.EventOuterLayer;

import EventPackage.EventEntities.*;
import EventPackage.EventGUI.Creators.PartyCreator;
import EventPackage.EventUseCases.EventManager;
import EventPackage.EventUseCases.RoomManager;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventPresenter {

    private EventController eventController;

    public EventPresenter(EventController eventController) {
        this.eventController = eventController;
    }


    public boolean signUp(String eventId) {
        int id;

        try {
            id = Integer.parseInt(eventId);
        }
        catch (Exception e) {
            return false;
        }

        return eventController.signUp(id);
    }


    public String[][] getAllEvents() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        EventManager eventManager = eventController.getEventManager();

        String[][] data = new String[eventManager.getEventList().size()][];

        for (int i = 0; i < eventManager.getEventList().size(); i++) {

            Event event = eventManager.getEventList().get(i);
            int availableSpace = event.getEventCapacity() - event.getEventAttendees().size();

            String[] eventInfo = {String.valueOf(event.getEventId()), event.getEventName(), event.getEventType(),
                    String.valueOf(event.getEventRoom()), dateFormat.format(event.getEventDate()),
                    timeFormat.format(event.getEventDate()),
                    String.valueOf(event.getEventDuration()), String.valueOf(event.getEventCapacity()),
                    String.valueOf(availableSpace), String.valueOf(event.getVIPStatus()),
                    getSpeakerString(event.getEventId())};
            data[i] = eventInfo;
        }

        return data;
    }

    public String[][] getEventsAttending() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        EventManager eventManager = eventController.getEventManager();
        int userId = eventController.getUserId();

        String[][] data = new String[eventManager.myEvents(userId).size()][];

        for (int i = 0; i < eventManager.myEvents(userId).size(); i++) {

            Event event = eventManager.myEvents(userId).get(i);
            int availableSpace = event.getEventCapacity() - event.getEventAttendees().size();

            String[] eventInfo = {String.valueOf(event.getEventId()), event.getEventName(), event.getEventType(),
                    String.valueOf(event.getEventRoom()), dateFormat.format(event.getEventDate()),
                    timeFormat.format(event.getEventDate()),
                    String.valueOf(event.getEventDuration()), String.valueOf(event.getEventCapacity()),
                    String.valueOf(availableSpace), String.valueOf(event.getVIPStatus()),
                    getSpeakerString(event.getEventId())};
            data[i] = eventInfo;
        }

        return data;
    }


    public String[][] getEventsSpeakingAt() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        EventManager eventManager = eventController.getEventManager();
        int userId = eventController.getUserId();

        String[][] data = new String[eventManager.speakingAt(userId).size()][];

        for (int i = 0; i < eventManager.speakingAt(userId).size(); i++) {

            Event event = eventManager.speakingAt(userId).get(i);
            int availableSpace = event.getEventCapacity() - event.getEventAttendees().size();

            String[] eventInfo = {String.valueOf(event.getEventId()), event.getEventName(), event.getEventType(),
                    String.valueOf(event.getEventRoom()), dateFormat.format(event.getEventDate()),
                    timeFormat.format(event.getEventDate()),
                    String.valueOf(event.getEventDuration()), String.valueOf(event.getEventCapacity()),
                    String.valueOf(availableSpace), String.valueOf(event.getVIPStatus()),
                    getSpeakerString(event.getEventId())};
            data[i] = eventInfo;
        }

        return data;
    }

    public String[][] getAvailEvents() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        EventManager eventManager = eventController.getEventManager();
        int userId = eventController.getUserId();
        boolean userVIP = eventController.isUserVIP();

        String[][] data = new String[eventManager.availEvents(userId, userVIP).size()][];

        for (int i = 0; i < eventManager.availEvents(userId, userVIP).size(); i++) {

            Event event = eventManager.availEvents(userId, userVIP).get(i);
            int availableSpace = event.getEventCapacity() - event.getEventAttendees().size();

            String[] eventInfo = {String.valueOf(event.getEventId()), event.getEventName(), event.getEventType(),
                    String.valueOf(event.getEventRoom()), dateFormat.format(event.getEventDate()),
                    timeFormat.format(event.getEventDate()),
                    String.valueOf(event.getEventDuration()), String.valueOf(event.getEventCapacity()),
                    String.valueOf(availableSpace), String.valueOf(event.getVIPStatus()),
                    getSpeakerString(event.getEventId())};
            data[i] = eventInfo;
        }

        return data;
    }


    public String[][] getRooms() {
        RoomManager roomManager = eventController.getRoomManager();

        String[][] data = new String[roomManager.getRoomList().size()][];

        for (int i = 0; i < roomManager.getRoomList().size(); i++) {

            Room room = roomManager.getRoomList().get(i);

            String[] roomInfo = {String.valueOf(room.getRoomNumber()), String.valueOf(room.getRoomCapacity())};
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

        return eventController.createParty(EventName, capacity, date, room, duration, vip);
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

        return eventController.createSingleSpeakerEvent(EventName, capacity, date, room, duration, vip, speaker);
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

        return eventController.createMultiSpeakerEvent(EventName, capacity, date, room, duration, vip, speakers);

    }

    public int cancelAttend(String eventId) {
        int id;
        try {
            id = Integer.parseInt(eventId);
        }
        catch (Exception e) {
            return -2;
        }

        return  eventController.cancelAttend(id);
    }


    public boolean createRoom(String RoomCapacity){
        int capacity;

        try {
            capacity = Integer.parseInt(RoomCapacity);
        }
        catch (Exception e) {
            return false;
        }

        return eventController.createRoom(capacity);
    }

    public int findType(String EventId) {
        int eventId;

        try {
            eventId = Integer.parseInt(EventId);
        }
        catch (Exception e) {
            return -1;
        }

        return eventController.findType(eventId);
    }

    public String[] getPartyInfo(String EventId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        String[] eventInfo = new String[6];
        EventManager eventManager = eventController.getEventManager();
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
        EventManager eventManager = eventController.getEventManager();
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
        EventManager eventManager = eventController.getEventManager();
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
                         String EventDate, String EventTime, String EventRoom, String EventDuration, String EventVIP) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        int eventId = Integer.parseInt(EventId);
        int capacity;
        String day;
        String time;
        Date date;
        int room;
        int duration;
        boolean vip;
        EventManager eventManager = eventController.getEventManager();
        Party event = (Party) eventManager.getEvent(eventId);
        String name;
        if (EventName.equals(""))
            name = event.getEventName();
        else
            name = EventName;

        try {
            if (EventCapacity.equals(""))
                capacity = event.getEventCapacity();
            else
                capacity = Integer.parseInt(EventCapacity);

            if (EventDate.equals("-"))
                day = dateFormat.format(event.getEventDate());
            else
                day = EventDate;

            if (EventDate.equals("-"))
                time = timeFormat.format(event.getEventDate());
            else
                time = EventTime;

            date = sdf.parse(day + "-" + time);

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

        return eventController.editParty(eventId, EventName, capacity, date, room, duration, vip);

    }



    public int editSingleSpeaker(String EventId, String EventName, String EventCapacity,
                                 String EventDate, String EventTime, String EventRoom, String EventDuration, String EventVIP,
                                 String EventSpeaker) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        int eventId = Integer.parseInt(EventId);
        int capacity;
        String day;
        String time;
        Date date;
        int room;
        int duration;
        boolean vip;
        int speaker;
        EventManager eventManager = eventController.getEventManager();
        SingleSpeakerEvent event = (SingleSpeakerEvent) eventManager.getEvent(eventId);
        String name;
        if (EventName.equals(""))
            name = event.getEventName();
        else
            name = EventName;

        try {
            if (EventCapacity.equals(""))
                capacity = event.getEventCapacity();
            else
                capacity = Integer.parseInt(EventCapacity);

            if (EventDate.equals("-"))
                day = dateFormat.format(event.getEventDate());
            else
                day = EventDate;

            if (EventDate.equals("-"))
                time = timeFormat.format(event.getEventDate());
            else
                time = EventTime;

            date = sdf.parse(day + "-" + time);

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

        return eventController.editSingleSpeaker(eventId, EventName, capacity, date, room, duration, vip, speaker);

    }


    public int editMultiSpeaker(String EventId, String EventName, String EventCapacity,
                                String EventDate, String EventTime, String EventRoom, String EventDuration, String EventVIP,
                                String EventSpeakers) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String[] Speakers = EventSpeakers.split(",");
        int eventId = Integer.parseInt(EventId);
        int capacity;
        String day;
        String time;
        Date date;
        int room;
        int duration;
        boolean vip;
        int speaker;
        ArrayList<Integer> speakers = new ArrayList<>();
        EventManager eventManager = eventController.getEventManager();
        MultiSpeakerEvent event = (MultiSpeakerEvent) eventManager.getEvent(eventId);
        String name;
        if (EventName.equals(""))
            name = event.getEventName();
        else
            name = EventName;


        try {
            if (EventCapacity.equals(""))
                capacity = event.getEventCapacity();
            else
                capacity = Integer.parseInt(EventCapacity);

            if (EventDate.equals("-"))
                day = dateFormat.format(event.getEventDate());
            else
                day = EventDate;

            if (EventDate.equals("-"))
                time = timeFormat.format(event.getEventDate());
            else
                time = EventTime;

            date = sdf.parse(day + "-" + time);

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
                for (String num: Speakers) {
                    speaker = Integer.parseInt(num);
                    if (!speakers.contains(speaker))
                        speakers.add(speaker);
                }

            vip = Boolean.parseBoolean(EventVIP);
        }
        catch (Exception e) {
            return -2;
        }

        return eventController.editMultiSpeaker(eventId, name, capacity, date, room, duration, vip, speakers);

    }

    private String getSpeakerString(int eventId) {
        EventManager eventManager = eventController.getEventManager();
        Event event = eventManager.getEvent(eventId);

        if (event.getEventType().equals("Single Speaker"))
            return String.valueOf(((SingleSpeakerEvent) event).getEventSpeaker());
        else if (event.getEventType().equals("Multi-Speaker"))
            return String.valueOf(((MultiSpeakerEvent) event).getEventSpeakers());
        else
            return "None";
    }
}