package EventPackage.EventUseCases;

import EventPackage.EventEntities.Room;

import java.io.Serializable;
import java.util.ArrayList;

public class RoomManager implements Serializable {

    private ArrayList<Room> roomList;
    private int nextNumber;


    /**
     * Creates an instance RoomManager that contains all the rooms in the conference
     * @param roomList The list of rooms this instance of eventManager should store
     */
    public RoomManager(ArrayList<Room> roomList) {
        this.roomList = roomList;
        if (roomList.size() == 0)
            this.nextNumber = 1;
        else
            this.nextNumber = roomList.get(roomList.size() - 1).getRoomNumber() + 1;
    }

    /**
     * Creates an instance of RoomManager with no rooms
     */
    public RoomManager() {
        this.roomList = new ArrayList<>();
        this.nextNumber = 1;
    }


    /**
     * Creates a room with a specific capacity and a room number then adds it to the list of rooms. Returns
     * number of room created
     * @param roomCapacity Capacity of new room
     * @return             Returns the number of the room created
     */
    public int createRoom(int roomCapacity) {
        Room newRoom = new Room(nextNumber, roomCapacity);
        nextNumber += 1;
        roomList.add(newRoom);
        return nextNumber - 1;
    }


    /**
     * Returns list of rooms in RoomManager
     * @return      List of Rooms
     */
    public ArrayList<Room> getRoomList() {
        return roomList;
    }


    /**
     * Returns a Room corresponding to a room number
     * @param RoomNumber The number of the Room
     * @return          The Room with the correct Room Number.
     */
    public Room findRoom(int RoomNumber) {
        for (Room room: roomList) {
            if (room.getRoomNumber() == RoomNumber)
                return room;
        }

        throw new ArrayIndexOutOfBoundsException("Room not found.");
    }


    /**
     * Finds whether a room exists or not
     * @param roomNumber id of the room
     * @return           true if room exists false if not
     */
    public boolean roomExists(int roomNumber) {
        for (Room room: roomList) {
            if (room.getRoomNumber() == roomNumber)
                return true;
        }

        return false;
    }
}