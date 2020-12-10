package EventPackage.EventOuterLayer;

import EventPackage.EventEntities.EventType;
import EventPackage.EventUseCases.EventManager;
import EventPackage.EventUseCases.EventProgramSorter;
import UserPackage.UserManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventProgramExporter {
    private final String eventExportFileName = "events.html";
    private final EventProgramSorter sorter;

    private final SimpleDateFormat dayFormatter;

    private String mainHtmlTemplate;
    private String dateHeaderTemplate;
    private String eventTemplate;

    /**
     * create an instance of EventProgramExporter which writes the html file for the conference program
     * @param userManager UserManager required for instantiating EventProgramSorter
     * @param eventManager EventManager required for instantiating EventProgramSorter
     */
    public EventProgramExporter(UserManager userManager, EventManager eventManager){
        this.sorter = new EventProgramSorter(eventManager, userManager);

        // ex: Friday November 27
        dayFormatter = new SimpleDateFormat("EEEE MMMM dd");

        String exportTemplatePath = "src/EventPackage/templates/exportTemplate.txt";
        mainHtmlTemplate = readTemplate(exportTemplatePath);
        String dateHeaderTemplatePath = "src/EventPackage/templates/dateHeaderTemplate.txt";
        dateHeaderTemplate = readTemplate(dateHeaderTemplatePath);
        String eventTemplatePath = "src/EventPackage/templates/eventTemplate.txt";
        eventTemplate = readTemplate(eventTemplatePath);
    }

    /**
     * read in a .txt file as a String
     * @param path path to .txt file
     * @return String of contents of .txt file
     */
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

    /**
     * open events.html in preferred Desktop browser
     */
    public void openHTMLFile(){
        File file = new File(eventExportFileName);
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            System.out.println("Error: could not locate events.html");
            e.printStackTrace();
        }
    }

    /**
     * write events.html to include all Events in the conference
     */
    public void exportAllEventsProgram(){
        HashMap<Date, ArrayList<String[]>> eventInfo = sorter.getAllEventsForProgram();
        createExportFileIfExists();
        String contents = generateFileContents(eventInfo);
        writeFileContents(contents);
    }

    /**
     * write events.html to include all Events that the given user is attending in the conference
     * @param userID userID of the current user
     */
    public void exportEventsUserAttendingProgram(int userID){
        HashMap<Date, ArrayList<String[]>> eventInfo = sorter.getEventsUserAttendingForProgram(userID);
        createExportFileIfExists();
        String contents = generateFileContents(eventInfo);
        writeFileContents(contents);
    }

    /**
     * write events.html to include all Party Events in the conference
     */
    public void exportPartyEventsProgram(){
        HashMap<Date, ArrayList<String[]>> eventInfo = sorter.getEventsByTypeForProgram(EventType.PARTY);
        createExportFileIfExists();
        String contents = generateFileContents(eventInfo);
        writeFileContents(contents);
    }

    /**
     * write events.html to include all MultiSpeaker Events in the conference
     */
    public void exportMultiSpeakerEventsProgram(){
        HashMap<Date, ArrayList<String[]>> eventInfo = sorter.getEventsByTypeForProgram(EventType.MULTISPEAKER);
        createExportFileIfExists();
        String contents = generateFileContents(eventInfo);
        writeFileContents(contents);
    }

    /**
     * write events.html to include all SingleSpeaker Events in the conference
     */
    public void exportSingleSpeakerEventsProgram(){
        HashMap<Date, ArrayList<String[]>> eventInfo = sorter.getEventsByTypeForProgram(EventType.SINGLESPEAKER);
        createExportFileIfExists();
        String contents = generateFileContents(eventInfo);
        writeFileContents(contents);

    }

    /**
     * write events.html to include all Events in the conference that the given Speaker is speaking at
     * @param speakerID userID of the current Speaker user who is logged in
     */
    public void exportEventsBySpeakerProgram(int speakerID){
        HashMap<Date, ArrayList<String[]>> eventInfo = sorter.getEventsBySpeakerForProgram(speakerID);
        createExportFileIfExists();
        String contents = generateFileContents(eventInfo);
        writeFileContents(contents);
    }

    /**
     * creates a String that includes the mainHtmlTemplate and the rest of the events html export
     * @param eventInfo Hashmap of event data organized by date
     * @return string for the entire html file to write
     */
    private String generateFileContents(HashMap<Date, ArrayList<String[]>> eventInfo){
        String eventContent = generateExportForEvents(eventInfo);
        // Note: The only string substitution we perform for the main HTML template are the events
        return String.format(mainHtmlTemplate, eventContent);
    }

    /**
     * creates a String for html file that includes all the events and event date headers
     * @param eventInfo Hashmap of event data organized by date
     * @return string for the html file that includes just events and event date headers
     */
    private String generateExportForEvents(HashMap<Date, ArrayList<String[]>> eventInfo){
        StringBuilder eventsFormatted = new StringBuilder();
        ArrayList<Date> eventDaysInOrder = new ArrayList<>(eventInfo.keySet());
        Collections.sort(eventDaysInOrder);
        // iterate by sorted dates to print itinerary
        for (Date date : eventDaysInOrder){
            String dayString = dayFormatter.format(date); // ex: Friday November 27
            eventInfo.get(date);
            ArrayList<String[]> eventsOnDayInfo = eventInfo.get(date);
            String eventsHeaderFormatted = String.format(dateHeaderTemplate, dayString);
            eventsFormatted.append(eventsHeaderFormatted);
            String eventsFormattedForDay = generateEventsFormattedForDay(eventTemplate, eventsOnDayInfo);
            eventsFormatted.append(eventsFormattedForDay);
        }

        return eventsFormatted.toString();
    }

    /**
     * create string of html for the events on a day with the date header and the event info
     * @param eventTemplate html template for an event, requires string substitution
     * @param eventsOnDayInfo ArrayList of String[] that represents event information for string substitution
     * @return String of html for an event with a date header
     */
    private String generateEventsFormattedForDay(String eventTemplate, ArrayList<String[]> eventsOnDayInfo){
        // format the header for this day
        //String eventsHeaderFormatted = String.format(dateHeaderTemplate, dayString);

        //format events for this day
        StringBuilder eventsFormatted = new StringBuilder();
        for(String[] info : eventsOnDayInfo){
            String startTime = info[0];
            String endTime = info[1];
            String eventName = info[2];
            String speakerName = info[3];
            String eventRoom = info[4];
            eventsFormatted.append(String.format(eventTemplate, startTime, endTime, eventName, speakerName, eventRoom));

            // add a space to separate this event and the next event
            // eventsFormatted += "<hr class='hr-slim'>";
            eventsFormatted.append("<br>");
        }
        //return eventsHeaderFormatted + eventsFormatted.toString();
        return eventsFormatted.toString();
    }

    /**
     * create a html file for export
     */
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

    /**
     * opens events.html to overwrite it with new content for the current requested export
     * @param contents String to be written to events.html
     */
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
