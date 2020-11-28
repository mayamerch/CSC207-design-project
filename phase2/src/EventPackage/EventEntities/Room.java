package EventPackage.EventEntities;

import java.io.Serializable;

public class Room implements Serializable {
    private int roomNumber;
    private int roomCapacity;

    /**
     * Creates a new room with a number and a capacity
     * @param roomNumber: A unique number representing a room
     * @param roomCapacity: The capacity of said room
     */
    public Room(int roomNumber, int roomCapacity)
    {
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
    }

    /**
     * Getter for roomNumber
     * @return Returns the unique room number of this room
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Getter for roomCapacity
     * @return Returns the capacity of this room
     */
    public int getRoomCapacity() {
        return roomCapacity;
    }



    /**
     * Returns String representation of Room
     * @return      String representing room
     */
    @Override
    public String toString() {
        return String.valueOf(roomNumber) + ',' + roomCapacity;
    }

}
