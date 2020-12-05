package EventPackage.EventUseCases;

import EventPackage.EventEntities.Event;
import UserPackage.UserManager;

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

    }

    private String generateExportForEvents(ArrayList<Event> events){
        StringBuilder eventsFormatted = new StringBuilder();

        // This hashmap is used to group the events that occur on the same day
        // The key is an identifier that represents a day.
        // The value is an array of all events on that day.
        HashMap<String, ArrayList<Event>> eventsForDay = groupEventsByDay(events);

        // Now sort all of the days in the hashmap
        ArrayList<Date> eventDaysInOrder = getEventDaysInOrder(eventsForDay);

        // with this sorted array, we can iterate through the days
        // build Strings representing the data we need for each day and for each event
        for (Date date : eventDaysInOrder){
            String dateIdentifier = getDateIdentifier(date);
            ArrayList<Event> eventsOnDay = eventsForDay.get(dateIdentifier);
            eventsFormatted.append(generateEventsFormattedForDay(eventTemplate, eventsOnDay));
        }

        return eventsFormatted.toString();
    }

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

    private String getDateIdentifier(Date date){
        return dateFormatter.format(date);
    }

    private ArrayList<Date> getEventDaysInOrder(HashMap<String, ArrayList<Event>> eventsForDay){
        ArrayList<Date> eventDatesInOrder = new ArrayList();
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
