package EventPackage.EventGateways;


import EventPackage.EventUseCases.EventManager;

import java.io.*;

import java.util.ArrayList;


public class EventGateway {
    private File fileDataEvent;
    private EventManager eventManager;

    /**
     * Creates a new EventGateway
     */
    public EventGateway() {
        this.fileDataEvent = new File("src/EventPackage/EventGateways/EventData.ser");
        try  {
            if (fileDataEvent.createNewFile())
                eventManager = new EventManager();
             else
                read();
        } catch (IOException e) {
            System.out.println("File Access Denied in Event Gateway");
        }
    }

    /**
     * Tries to read and return data from a text file.
     * Prints to console on exception
     * @return Returns the the EventManager serialized in file
     */
    public void read() {
        try {
            FileInputStream fileIn = new FileInputStream(this.fileDataEvent);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            this.eventManager = (EventManager) objIn.readObject();
            objIn.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            System.out.println("Check file directory");
            this.eventManager = new EventManager();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Empty Events List (Rebuild .ser file or check permissions)");
            this.eventManager = new EventManager();
        }
    }


    /**
     * Tries to serialize an EventManager to file specified in constructor.
     * Prints to console on exception
     * @param eventManager An EventManager object
     */
    public void write(EventManager eventManager) {
        try {
            this.eventManager = eventManager;
            FileOutputStream fileOut = new FileOutputStream(this.fileDataEvent);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(eventManager);
            objOut.close();
            fileOut.close();
        } catch (IOException i) {
            System.out.println("Check file directory");
        }
    }

    /**
     * Updates current EventManager and returns it
     * @return Returns an instance of an EventManager
     */

    public EventManager getEventManager() {
        return this.eventManager;
    }
}