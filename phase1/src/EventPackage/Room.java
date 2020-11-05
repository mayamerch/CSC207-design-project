package EventPackage;

public class Room {
    private int roomNumber;
    private int roomCapacity;
    private int currEvent = -1;

    /**
     * Creates a new room with a number and a capacity
     * @param roomNumber: A unique number representing a room
     * @param roomCapacity: The capacity of said room
     */
    public Room(int roomNumber, int roomCapacity) {
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
    }


}
