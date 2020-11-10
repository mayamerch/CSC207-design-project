package EventPackage;

public class Room {
    private int roomNumber;
    private final int roomCapacity = 2; // This is final for phase 1 but in phase two it will be changed to variable.
                                        //It is going to be included in some methods as preparation for phase 2

    /**
     * Creates a new room with a number and a capacity
     * @param roomNumber: A unique number representing a room
     * @param roomCapacity: The capacity of said room
     */
    public Room(int roomNumber, int roomCapacity) //This variable is here for phase 2
    {
        this.roomNumber = roomNumber;
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

    public String toString() {
        return (this.roomNumber + ',' + this.roomCapacity);
    }

}
