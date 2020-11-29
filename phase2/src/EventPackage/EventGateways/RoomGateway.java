package EventPackage.EventGateways;

import EventPackage.EventUseCases.EventManager;
import EventPackage.RoomManager;

import java.io.*;
import java.util.ArrayList;

public class RoomGateway {
    private File fileDataRoom;
    private RoomManager roomManager;
    public RoomGateway()   {
        this.fileDataRoom = new File("RoomData.ser");
        try  {
            if (fileDataRoom.createNewFile())
                roomManager = new RoomManager();
            else
                read(this.fileDataRoom);
        } catch (IOException e) {
            System.out.println("File Access Denied in Gateway");
        }
    }

    /**
     * Tries to read data from a .ser file.
     * Prints to console on exception
     * @param fileData Data of file to be read
     * @return Returns the the RoomManager serialized in file
     */
    public void read(File fileData) {
        ArrayList<StringBuilder> data = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(this.fileDataRoom);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            objIn.close();
            fileIn.close();
            this.roomManager = (RoomManager) objIn.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Check file directory");
            this.roomManager =  new RoomManager();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Empty Events List (Rebuild .ser file or check permissions)");
            this.roomManager = new RoomManager();
        }
    }


    /**
     * Tries to serialize an RoomManager to file specified in constructor.
     * Prints to console on exception
     * @param roomManager An EventManager object
     */
    public void write(RoomManager roomManager) {

        try {
            FileOutputStream fileOut = new FileOutputStream(this.fileDataRoom);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(roomManager);
            objOut.close();
            fileOut.close();
        } catch (IOException i) {
            System.out.println("Check file directory");
        }
    }

    /**
     * Updates current RoomManager and returns it
     * @return Returns an instance of an EventManager
     */

    public RoomManager getEventManager() {
        read(this.fileDataRoom);
        return this.roomManager;
    }

}