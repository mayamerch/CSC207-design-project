package EventPackage.EventUseCases;

import EventPackage.EventEntities.Event;
import UserPackage.UserManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

    public void exportAllEvents() {
        //ArrayList<Event> events = eventManager.getEventList();
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
