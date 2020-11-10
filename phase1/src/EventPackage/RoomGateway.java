package EventPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class RoomGateway {
    private File fileData;
    private ArrayList<StringBuilder> roomData;


    /**
     * Creates a new RoomGateway
     */
    public RoomGateway() {
        try {
            this.fileData = new File("phase1/src/EventPackage/roomData.txt");
            if (this.fileData.createNewFile()) {
                this.roomData = new ArrayList<>();
            } else {
                this.roomData = read();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    /**
     * Tries to read and return room data from a text file.
     * Prints to console on exception
     * @return Returns a list of Rooms in string form
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



    /**
     * temporary
     * @return
     */
    public ArrayList<Event> parse() {
        ArrayList <Event> objectData = new ArrayList<>();
        for (StringBuilder stringEvent : this.eventData) {
            String[] fieldArr = stringEvent.toString().split(",", 7);
            try {
                Date newDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(fieldArr[3]);
                Event newEvent = new Event(Integer.parseInt(fieldArr[0]),
                        fieldArr[1],
                        Integer.parseInt(fieldArr[2]),
                        newDate,
                        Integer.parseInt(fieldArr[4]),
                        Integer.parseInt(fieldArr[5]));
                String[] attendeesID = fieldArr[6].substring(1, fieldArr[6].length() - 1).split(", ");
                for (int i = 0; i < attendeesID.length; i++) {
                    newEvent.addAttendee(Integer.parseInt(attendeesID[i]));
                }
                objectData.add(newEvent);
            } catch (ParseException e) {
                continue;
            }
        }
        return objectData;
    }
}