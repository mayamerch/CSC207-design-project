package EventPackage;


public class EventController {

    private EventManager em;
    private RoomManager rm;


    public EventController() {
        EventRoomGateway EvRoGate = new EventRoomGateway();
        em = new EventManager(EvRoGate.parseEvent());
        rm = new RoomManager(EvRoGate.parseRoom());
    }


    /**
     * Returns the EventManager in this controller with all the information
     * @return      The EventManager in this controller
     */
    public EventManager getEventManager() {
        return em;
    }


    /**
     * Returns the RoomManager in this controller with all the information
     * @return      The RoomManager in this controller
     */
    public RoomManager getRoomManager() {
        return rm;
    }



    /**
     * Interacts with user and asks for input then performs actions related to events based on that input.
     * @param UserId The id of the user its interacting with
     * @param UserPerm -1 If User is a Speaker
     *                 0 If User is an Organizer
     *                 1 If User is an Attendee
    **/
    public void run(int UserId, int UserPerm) {

        //some code

        EventRoomGateway EvRoGate = new EventRoomGateway();
        EvRoGate.write(em.getEventList(), rm.getRoomList());

    }
}