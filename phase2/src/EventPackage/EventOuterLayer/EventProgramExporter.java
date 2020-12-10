package EventPackage.EventOuterLayer;

import EventPackage.EventEntities.EventType;
import EventPackage.EventUseCases.EventManager;
import EventPackage.EventUseCases.EventProgramSorter;
import UserPackage.UserManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.net.URI;
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

    public void openHTMLFile() throws IOException {
        File file = new File(eventExportFileName);
        Desktop.getDesktop().open(file);
    }

    public void exportAllEventsProgram(){
        HashMap<Date, ArrayList<String[]>> eventInfo = sorter.getAllEventsForProgram();
        createExportFileIfExists();
        // write contents of html based on import from sorter
        String contents = generateFileContents(eventInfo);
        writeFileContents(contents);
    }

    public void exportEventsUserAttendingProgram(int userID){
        HashMap<Date, ArrayList<String[]>> eventInfo = sorter.getEventsUserAttendingForProgram(userID);
        createExportFileIfExists();
        // write contents of html based on import from sorter
        String contents = generateFileContents(eventInfo);
        writeFileContents(contents);
    }

    public void exportEventsToSignupProgram(int userID){
        HashMap<Date, ArrayList<String[]>> eventInfo = sorter.getEventsUserSignupForProgram(userID);
        createExportFileIfExists();
        // write contents of html based on import from sorter
        String contents = generateFileContents(eventInfo);
        writeFileContents(contents);
    }

    public void exportPartyEventsProgram(){
        HashMap<Date, ArrayList<String[]>> eventInfo = sorter.getEventsByTypeForProgram(EventType.PARTY);
        createExportFileIfExists();
        // write contents of html based on import from sorter
        String contents = generateFileContents(eventInfo);
        writeFileContents(contents);
    }

    public void exportMultiSpeakerEventsProgram(){
        HashMap<Date, ArrayList<String[]>> eventInfo = sorter.getEventsByTypeForProgram(EventType.MULTISPEAKER);
        createExportFileIfExists();
        // write contents of html based on import from sorter
        String contents = generateFileContents(eventInfo);
        writeFileContents(contents);
    }

    public void exportSingleSpeakerEventsProgram(){
        HashMap<Date, ArrayList<String[]>> eventInfo = sorter.getEventsByTypeForProgram(EventType.SINGLESPEAKER);
        createExportFileIfExists();
        // write contents of html based on import from sorter
        String contents = generateFileContents(eventInfo);
        writeFileContents(contents);

    }

    public void exportEventsBySpeakerProgram(int speakerID){
        HashMap<Date, ArrayList<String[]>> eventInfo = sorter.getEventsBySpeakerForProgram(speakerID);
        createExportFileIfExists();
        // write contents of html based on import from sorter
        String contents = generateFileContents(eventInfo);
        writeFileContents(contents);
    }

    private String generateFileContents(HashMap<Date, ArrayList<String[]>> eventInfo){
        String eventContent = generateExportForEvents(eventInfo);
        // Note: The only string substitution we perform for the main HTML template are the events
        return String.format(mainHtmlTemplate, eventContent);
    }

    private String generateExportForEvents(HashMap<Date, ArrayList<String[]>> eventInfo){
        StringBuilder eventsFormatted = new StringBuilder();
        ArrayList<Date> eventDaysInOrder = new ArrayList<>(eventInfo.keySet());
        Collections.sort(eventDaysInOrder);
        // iterate by sorted dates to print itinerary
        for (Date date : eventDaysInOrder){
            String dayString = dayFormatter.format(date); // ex: Friday November 27
            eventInfo.get(date);
            //TODO: see if changes WORK!
            ArrayList<String[]> eventsOnDayInfo = eventInfo.get(date);
            String eventsHeaderFormatted = String.format(dateHeaderTemplate, dayString);
            eventsFormatted.append(eventsHeaderFormatted);
            String eventsFormattedForDay = generateEventsFormattedForDay(eventTemplate, eventsOnDayInfo);
            eventsFormatted.append(eventsFormattedForDay);
        }

        return eventsFormatted.toString();
    }

    //TODO: see if changes work!!
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
