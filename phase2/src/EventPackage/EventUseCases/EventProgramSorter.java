package EventPackage.EventUseCases;

import EventPackage.EventEntities.*;
import UserPackage.UserManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class EventProgramSorter {
    private final EventManager eventManager;
    private final UserManager userManager;

    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat dayFormatter;
    private SimpleDateFormat timeFormatter;


    public EventProgramSorter(EventManager em, UserManager um){
        this.eventManager = em;
        this.userManager = um;

        // ex: 10/08/2020
        dateFormatter = new SimpleDateFormat("dd/M/yyyy");
        // ex: Friday November 27
        dayFormatter = new SimpleDateFormat("EEEE MMMM dd");
        // ex: 10:00am - 10:30am
        timeFormatter = new SimpleDateFormat("hh:mma - hh:mma");

    }

    public HashMap<Date, ArrayList<String[]>> getAllEventsForProgram() {
        ArrayList<Event> events = eventManager.getEventList();
        return this.generateEventInfo(events);
    }

    public HashMap<Date, ArrayList<String[]>> getEventsUserAttendingForProgram(int myUserID) {
        ArrayList<Event> events = eventManager.myEvents(myUserID);
        return this.generateEventInfo(events);
    }

    public HashMap<Date, ArrayList<String[]>> getEventsUserSignupForProgram(int myUserID){
        boolean statusVIP = userManager.getUserByID(myUserID).getVIP();
        ArrayList<Event> events = eventManager.availEvents(myUserID, statusVIP);
        return this.generateEventInfo(events);
    }

    public HashMap<Date, ArrayList<String[]>> getEventsByTypeForProgram(EventType type){
        ArrayList<Event> events = null;
        if (type == EventType.PARTY){
            events = new ArrayList<>(eventManager.getPartyList());
        } else if (type == EventType.MULTISPEAKER){
            events = new ArrayList<>(eventManager.getMultiSpeakerList());
        } else if (type == EventType.SINGLESPEAKER){
            events = new ArrayList<>(eventManager.getSingleSpeakerList());
        }
        return this.generateEventInfo(events);
    }

    public HashMap<Date, ArrayList<String[]>> getEventsBySpeakerForProgram(int speakerID){
        ArrayList<Event> events = eventManager.speakingAt(speakerID);
        return this.generateEventInfo(events);
    }

    private HashMap<Date, ArrayList<String[]>> generateEventInfo(ArrayList<Event> events){

        //copy constructor for shallow copy
        ArrayList<Event> sortedEvents = new ArrayList<>(events);
        //sort shallow copy by date
        Collections.sort(sortedEvents);

        StringBuilder eventsFormatted = new StringBuilder();

        // Hashmap to group the events that occur on the same day
        HashMap<Date, ArrayList<Event>> eventsForDay = groupEventsByDay(sortedEvents);

        // Sort days in the hashmap
        ArrayList<Date> eventDaysInOrder = getEventDaysInOrder(eventsForDay);

        // for return
        // key is dayFormatted string
        // value is ArrayList of String[] to rep data for events
        HashMap<Date, ArrayList<String[]>> eventInfo = new HashMap<>();

        // iterate through the days, get all events on date...
        // build Strings representing the data we need for eventsOnDay
        for (Date date : eventDaysInOrder){
            ArrayList<Event> eventsOnDay = eventsForDay.get(date);
            eventInfo.put(date, generateEventInfoForDay(eventsOnDay));
        }

        return eventInfo;
        // returns Hashmap of key: DAY,  value: ArrayList of String[] that rep data for a single event
        // each String[] has [time, eventName, speakerName, eventRoom]
    }

    private ArrayList<String[]> generateEventInfoForDay(ArrayList<Event> events){
        ArrayList<String[]> info = new ArrayList<>();
        for (Event event : events){
            info.add(generateEventInfoForEvent(event));
        }
        return info;
    }

    private String[] generateEventInfoForEvent(Event event){
        Date eventDate = event.getEventDate();
        String time = timeFormatter.format(eventDate);
        String eventName = event.getEventName();
        String speakerName = getSpeakerString(event);
        String eventRoom = Integer.toString(event.getEventRoom());
        return new String[]{time, eventName, speakerName, eventRoom};
    }

    private String getSpeakerString(Event event){
        int eventID = event.getEventId();
        String speakerName;
        if (eventManager.isMultiSpeakerEvent(eventID)){
            ArrayList<Integer> speakers = ((MultiSpeakerEvent)event).getEventSpeakers();
            StringBuilder namesBuilder = new StringBuilder();
            for (int speakerId : speakers){
                String name = userManager.getUserByID(speakerId).getUsername();
                namesBuilder.append(name);
                namesBuilder.append(", ");
            }
            speakerName = namesBuilder.substring(0, namesBuilder.length()-2);
        } else if (eventManager.isSingleSpeakerEvent(eventID)) {
            int speakerId = ((SingleSpeakerEvent)event).getEventSpeaker();
            speakerName = userManager.getUserByID(speakerId).getUsername();
        } else { // it's a party
            speakerName = "";
        }
        return speakerName;
    }

    private HashMap<Date, ArrayList<Event>> groupEventsByDay(ArrayList<Event> events){
        HashMap<Date, ArrayList<Event>> eventsForDay = new HashMap<>();
        for(Event event : events){
            Date date = event.getEventDate();
            if(!eventsForDay.containsKey(date)){
                // we haven't seen this day before. So make a new array list and insert this day into the hashmap
                ArrayList<Event> newEventsForDay = new ArrayList<>();
                newEventsForDay.add(event);
                eventsForDay.put(date, newEventsForDay);
            }else{
                // we have seen this day before, so append to the arraylist at this day.
                ArrayList<Event> existingEvents = eventsForDay.get(date);
                existingEvents.add(event);
                eventsForDay.put(date, existingEvents);
            }
        }
        return eventsForDay;
    }



    private ArrayList<Date> getEventDaysInOrder(HashMap<Date, ArrayList<Event>> eventsForDay){
//        for (HashMap.Entry<String, ArrayList<Event>> eventsOnDay : eventsForDay.entrySet()){
//            Date dateOfEventOnThisDay = eventsOnDay.getValue().get(0).getEventDate();
//            eventDatesInOrder.add(dateOfEventOnThisDay);
//        }
        ArrayList<Date> eventDatesInOrder = new ArrayList<>(eventsForDay.keySet());
        Collections.sort(eventDatesInOrder);
        return eventDatesInOrder;
    }

    // want to return a hashmap where the key is Date not String
}
