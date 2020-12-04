package EventPackage.EventGateways;

import EventPackage.EventUseCases.RoomManager;

import java.io.*;
import java.util.ArrayList;

public class RoomGateway {
    private File fileDataRoom;
    private RoomManager roomManager;


    /**
     * Create a RoomGateway and loads in the RoomManager
     */
    public RoomGateway()   {
        this.fileDataRoom = new File("RoomData.ser");
        try  {
            if (fileDataRoom.createNewFile())
                roomManager = new RoomManager();
            else
                read();
        } catch (IOException e) {
            System.out.println("File Access Denied in Room Gateway");
        }
    }

    /**
     * Reads data from a .ser and creates a RoomManager from it and stores it
     */
    public void read() {
        try {
            FileInputStream fileIn = new FileInputStream(this.fileDataRoom);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            this.roomManager = (RoomManager) objIn.readObject();
            objIn.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            System.out.println("Check file directory");
            this.roomManager =  new RoomManager();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Empty Rooms List (Rebuild .ser file or check permissions)");
            this.roomManager = new RoomManager();
        }
    }


    /**
     * Tries to serialize an RoomManager to file specified in constructor.
     * Prints to console on exception
     * @param roomManager A RoomManager object
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
     * @return Returns an instance of an RoomManager
     */

    public RoomManager getRoomManager() {
        return this.roomManager;
    }

}