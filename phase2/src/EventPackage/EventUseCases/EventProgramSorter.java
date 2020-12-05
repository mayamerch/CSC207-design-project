package EventPackage.EventUseCases;

import EventPackage.EventEntities.*;
import UserPackage.UserManager;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class EventProgramSorter {
    private EventManager eventManager;
    private UserManager userManager;

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

    private ArrayList<String[]> eventsToString(ArrayList<Event> events){
        ArrayList<Event> sortedEvents = new ArrayList(events);
        Collections.sort(sortedEvents);
        // Call generateEventInfo on sortedEvents
        return new ArrayList<>();
    }

    private String getDateIdentifier(Date date){
        return dateFormatter.format(date);
    }

    private HashMap<String, ArrayList<String[]>> generateEventInfo(ArrayList<Event> events){
        StringBuilder eventsFormatted = new StringBuilder();

        // This hashmap is used to group the events that occur on the same day
        // The key is an identifier that represents a day.
        // The value is an array of all events on that day.
        HashMap<String, ArrayList<Event>> eventsForDay = groupEventsByDay(events);

        // Sort days in the hashmap
        ArrayList<Date> eventDaysInOrder = getEventDaysInOrder(eventsForDay);

        // for return
        // key is dayFormatted string
        // value is ArrayList of String[] to rep data for events
        HashMap<String, ArrayList<String[]>> eventInfo = new HashMap<>();

        // with this sorted array, we can iterate through the days
        // build Strings representing the data we need for each day and for each event
        for (Date date : eventDaysInOrder){
            String dateIdentifier = dayFormatter.format(date); // ex: Friday November 27
            ArrayList<Event> eventsOnDay = eventsForDay.get(dateIdentifier); // eventsOnDay for one day only
            eventInfo.put(dateIdentifier, generateEventInfoForDay(eventsOnDay));
            //eventsFormatted += String.format(eventTemplate, time, eventName, speakerName.toString(), eventRoom);
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

/*    private String generateEventsFormattedForDay(String eventTemplate, ArrayList<Event> events){
        // format the header for this day
        Date day = events.get(0).getEventDate();
        String dateIdentifier = dayFormatter.format(day);
        String eventsHeaderFormatted = String.format(dateHeaderTemplate, dateIdentifier);

        // now format all the events for this day
        String eventsFormatted = "";
        for(int i = 0; i < events.size(); i++){
            Event event = events.get(i);
            String eventName = event.getEventName();
            //int eventID = event.getEventId();
           *//* if (eventManager.isMultiSpeakerEvent(eventID)){
                ArrayList<Integer> speakers = ((MultiSpeakerEvent)event).getEventSpeakers();
            } else if (eventManager.isSingleSpeakerEvent(eventID)) {
                speakerName = "";
            } else { // it's a party
                speakerName = "";
            }*//*

            ArrayList<Integer> speakerIDs = eventManager.getSpeakerIDs(event);
            StringBuilder speakerName = new StringBuilder();
            for (int ID: speakerIDs){
                speakerName.append(userManager.getUserByID(ID).getUsername());
            }
            //= userManager.getUserByID(userID).getUsername();
            Date eventDate = event.getEventDate();
            String time = timeFormatter.format(eventDate);
            String eventRoom = Integer.toString(event.getEventRoom());

            eventsFormatted += String.format(eventTemplate, time, eventName, speakerName.toString(), eventRoom);

            // add a space to separate this event and the next event
            if(i < events.size() - 1){
                // eventsFormatted += "<hr class='hr-slim'>";
                eventsFormatted += "<br>";
            }
        }
        return eventsHeaderFormatted + eventsFormatted;
    }*/


    private HashMap<String, ArrayList<Event>> groupEventsByDay(ArrayList<Event> events){
        HashMap<String, ArrayList<Event>> eventsForDay = new HashMap();
        for(Event event : events){
            String dateIdentifier = getDateIdentifier(event.getEventDate());
            if(!eventsForDay.containsKey(dateIdentifier)){
                // we haven't seen this day before. So make a new array list and insert this day into the hashmap
                ArrayList<Event> newEventsForDay = new ArrayList<>();
                newEventsForDay.add(event);
                eventsForDay.put(dateIdentifier, newEventsForDay);
            }else{
                // we have seen this day before, so append to the arraylist at this day.
                ArrayList<Event> existingEvents = eventsForDay.get(dateIdentifier);
                existingEvents.add(event);
                eventsForDay.put(dateIdentifier, existingEvents);
            }
        }
        return eventsForDay;
    }



    private ArrayList<Date> getEventDaysInOrder(HashMap<String, ArrayList<Event>> eventsForDay){
        ArrayList<Date> eventDatesInOrder = new ArrayList<>();
        // first loop through all the events for each day
        for (HashMap.Entry<String, ArrayList<Event>> eventsOnDay : eventsForDay.entrySet()){
            // then get the date of one event for that day
            Date dateOfEventOnThisDay = eventsOnDay.getValue().get(0).getEventDate();
            // add this date to our arrayList
            eventDatesInOrder.add(dateOfEventOnThisDay);
        }
        // now sort the arrayList to order the eventDays
        Collections.sort(eventDatesInOrder);
        return eventDatesInOrder;
    }

    public void exportAllEvents() {
        ArrayList<Event> events = eventManager.getEventList();

        //exportEvents(events);
    }

    public void exportEventsForUser(int userID) {
        // TODO: export program based on what the user signed up for
        //ArrayList<Event> events = eventManager.myEvents(userID);
        //exportEvents(events);
    }

    public void exportEventsByDay(int day) {
        // TODO: export a program based on Day of the Conference (Day 1, Day 2, Day 3 of the conference)
        //ArrayList<Event> events = eventManager.getEventsForDay();
        //exportEvents(events);
    }

    public void exportEventsBySpeaker(int speakerID){
        // TODO: export a program based on a search for a specific speaker
        //ArrayList<Event> events = eventManager.speakingAt(speakerID);
        //exportEvents(events);
    }

    public void exportEventsByType(char EventType){
        //TODO: export a program based on the type of Event (Party, MultiSpeaker, SingleSpeaker)
        //ArrayList<Party> events = eventManager.getPartyList();
        //ArrayList<Event> events = eventManager.getMultiSpeakerList();
        //ArrayList<Event> events = eventManager.getSingleSpeakerList();
        //exportEvents(events);
    }

    public void exportEventsByRoom(int roomNumber){
        //TODO: export a program based on the room of the Event
    }

    // this class should return ArrayList<String[]> to the EventsProgramExporter
}
