package EventPackage;

import UserPackage.User;
import UserPackage.UserManager;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventsProgramExporter {
    private final String eventExportFileName = "events.html";

    private EventManager eventManager;
    private UserManager userManager;

    private String mainHtmlTemplate;
    private String dateHeaderTemplate;
    private String eventTemplate;

    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat dayFormatter;
    private SimpleDateFormat timeFormatter;

    public EventsProgramExporter(EventManager em, UserManager um){
        eventManager = em;
        userManager = um;
        String exportTemplatePath = "src/EventPackage/templates/exportTemplate.txt";
        mainHtmlTemplate = readTemplate(exportTemplatePath);
        String dateHeaderTemplatePath = "src/EventPackage/templates/dateHeaderTemplate.txt";
        dateHeaderTemplate = readTemplate(dateHeaderTemplatePath);
        String eventTemplatePath = "src/EventPackage/templates/eventTemplate.txt";
        eventTemplate = readTemplate(eventTemplatePath);

        // ex: 10/08/2020
        dateFormatter = new SimpleDateFormat("dd/M/yyyy");
        // ex: Friday November 27
        dayFormatter = new SimpleDateFormat("EEEE MMMM dd");
        // ex: 10:00am - 10:30am
        timeFormatter = new SimpleDateFormat("hh:mma - hh:mma");
    }

    public void exportAllEvents() {
        ArrayList<Event> events = eventManager.getEventList();
        exportEvents(events);
    }

    public void exportEventsForUser(int userID) {
        // TODO: export program based on what the user signed up for
        //ArrayList<Event> events = eventManager.myEvents(userID)
        //exportEvents(events);
    }

    public void exportEventsByDay(int day) {
        // TODO: export a program based on Day of the Conference (Day 1, Day 2, Day 3 of the conference)
        //ArrayList<Event> events = eventManager.getEventsForDay();
        //exportEvents(events);
    }

    public void exportEventsBySpeaker(int speakerID){
        // TODO: export a program based on a search for a specific speaker
        //ArrayList<Event> events = eventManager.getEventsForSpeaker();
        //exportEvents(events);
    }

    public void exportEventsByType(char EventType){
        //TODO: export a program based on the type of Event (Party, MultiSpeaker, SingleSpeaker)
        //ArrayList<Event> events = eventManager.getAllParties();
        //ArrayList<Event> events = eventManager.getAllMultiSpeakerEvents();
        //ArrayList<Event> events = eventManager.getAllSingleSpeakerEvents();
        //exportEvents(events);
    }

    public void exportEventsByRoom(int roomNumber){
        //TODO: export a program based on the room of the Event
    }

    public void exportEvents(ArrayList<Event> events){
        // sort the events by startDate
        // TODO: ask Adam to sort by startDate???
        // if I sort the events param does it sort the ArrayList within EventManager and mess it up
        ArrayList<Event> sortedEvents = new ArrayList(events);
        Collections.sort(sortedEvents);
        createExportFileIfExists();
        String contents = generateFileContents(sortedEvents);
        writeFileContents(contents);
    }

    private String generateFileContents(ArrayList<Event> events){
        String eventContent = generateExportForEvents(events);
        // Note: The only string substitution we perform for the main HTML template are the events
        return String.format(mainHtmlTemplate, eventContent);
    }

    private String generateExportForEvents(ArrayList<Event> events){
        StringBuilder eventsFormatted = new StringBuilder();

        // This hashmap is used to group the events that occur on the same day
        // The key is an identifier that represents a day.
        // The value is an array of all events on that day.
        HashMap<String, ArrayList<Event>> eventsForDay = groupEventsByDay(events);

        // Now sort all of the days in the hashmap
        ArrayList<Date> eventDaysInOrder = getEventDaysInOrder(eventsForDay);

        // with this sorted array, we can iterate through the days in order to print in our itinerary
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

    // returns an identifier that represents the date of the date object
    private String getDateIdentifier(Date date){
        return dateFormatter.format(date);
    }


    // This function returns a sorted ArrayList of date objects where each date object is on a separate day
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

    private String generateEventsFormattedForDay(String eventTemplate, ArrayList<Event> events){
        // format the header for this day
        Date day = events.get(0).getEventDate();
        String dateIdentifier = dayFormatter.format(day);
        String eventsHeaderFormatted = String.format(dateHeaderTemplate, dateIdentifier);

        // now format all the events for this day
        // TODO: is calling a method from Event bad? Should I write a method in EventManager that exports strings instead?
        String eventsFormatted = "";
        for(int i = 0; i < events.size(); i++){
            Event event = events.get(i);
            String eventName = event.getEventName();
            int userID = event.getEventSpeaker(); //TODO: this does not work anymore now that we have PARTY as an event
            String speakerName = userManager.getUserByID(userID).getUsername();
            Date eventDate = event.getEventDate();
            String time = timeFormatter.format(eventDate);
            String eventRoom = Integer.toString(event.getEventRoom());

            eventsFormatted += String.format(eventTemplate, time, eventName, speakerName, eventRoom);

            // add a space to separate this event and the next event
            if(i < events.size() - 1){
                // eventsFormatted += "<hr class='hr-slim'>";
                eventsFormatted += "<br>";
            }
        }
        return eventsHeaderFormatted + eventsFormatted;
    }

    private String readTemplate(String path){
        try{
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, StandardCharsets.UTF_8);
        }catch (IOException e){
            System.out.printf("Error: could not find template %s", path);
            e.printStackTrace();
            return "";
        }
    }

    private void createExportFileIfExists(){
        File exportFile = new File(eventExportFileName);
        if (!exportFile.exists()){
            try {
                exportFile.createNewFile();
            } catch (IOException e){
                System.out.printf("Error: could not create %s", eventExportFileName);
                e.printStackTrace();
            }
            System.out.println("File created: " + exportFile.getName());
        }
    }


    private void writeFileContents(String contents){
        FileWriter fw;
        try{
            // Set the second parameter to false to overwrite existing text in the file
            fw = new FileWriter(eventExportFileName,false);
            fw.write(contents);
            System.out.printf("Your events have been exported into %s", eventExportFileName);
            fw.close();
        }catch (IOException e){
            System.out.printf("Error: could not write to %s \n", eventExportFileName);
            e.printStackTrace();
        }
    }
}
