package EventPackage.EventOuterLayer;

import EventPackage.EventEntities.Event;
import EventPackage.EventUseCases.EventProgramSorter;
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


    public EventProgramExporter(EventProgramSorter sorter){
        this.sorter = sorter;

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

    //this one is for all Events!!
    public void exportAllEventsProgram(){
        HashMap<Date, ArrayList<String[]>> eventInfo = sorter.getAllEventsForProgram();
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
            ArrayList<String[]> eventsOnDayInfo = eventInfo.get(date);
            eventsFormatted.append(generateEventsFormattedForDay(eventTemplate, dayString, eventsOnDayInfo));
        }

        return eventsFormatted.toString();
    }

    private String generateEventsFormattedForDay(String eventTemplate, String dayString, ArrayList<String[]> eventsOnDayInfo){
        // format the header for this day
        String eventsHeaderFormatted = String.format(dateHeaderTemplate, dayString);

        //format events for this day
        StringBuilder eventsFormatted = new StringBuilder();
        for(String[] info : eventsOnDayInfo){
            String time = info[0];
            String eventName = info[1];
            String speakerName = info[2];
            String eventRoom = info[3];
            eventsFormatted.append(String.format(eventTemplate, time, eventName, speakerName, eventRoom));

            // add a space to separate this event and the next event
            // eventsFormatted += "<hr class='hr-slim'>";
            eventsFormatted.append("<br>");
        }
        return eventsHeaderFormatted + eventsFormatted.toString();
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
