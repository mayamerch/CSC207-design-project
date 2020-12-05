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
    private EventProgramSorter sorter;

    private String mainHtmlTemplate;
    private String dateHeaderTemplate;
    private String eventTemplate;

    public EventProgramExporter(EventProgramSorter sorter){
        this.sorter = sorter;
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
    public void exportEventProgram(){
        //eventInfo = sorter.sortedAllEvents();
        createExportFileIfExists();
        // write contents of html based on import from sorter
        //String contents = generateContents(eventInfo);
        //writeFileContents(contents);
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
