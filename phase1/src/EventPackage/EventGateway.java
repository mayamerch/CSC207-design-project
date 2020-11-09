package EventPackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class EventGateway {
    private File fileData;
    private ArrayList <StringBuilder> eventData;


    /**
     * Creates a new EventGateway
     */
    public EventGateway() {
        try {
            this.fileData = new File("phase1/src/EventPackage/eventData.txt");
            if (this.fileData.createNewFile()) {
                this.eventData = new ArrayList<>();
            } else {
                this.eventData = read();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    /**
     * Tries to read and return event data from a text file.
     * Prints to console on exception
     * @return Returns a list of Events in string form
     */
    public ArrayList<StringBuilder> read() {
        ArrayList<StringBuilder> data = new ArrayList<StringBuilder>();
        try {
            Scanner fileReader = new Scanner(this.fileData);
            while (fileReader.hasNextLine()) {
                StringBuilder e = new StringBuilder(fileReader.nextLine());
                data.add(e);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }


    /**
     * Tries to write a list of events to file specified in constructor.
     * Prints to console on exception
     * @param eventList A list of Event objects
     */
    public void write(ArrayList<Event> eventList) {
        try {
            FileWriter eventWriter = new FileWriter(this.fileData, true);
            for (Event e : eventList) {
                eventWriter.write(e.toString() + System.lineSeparator());
                eventWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
