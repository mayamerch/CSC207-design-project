package EventPackage;


public class EventController {

    private EventManager em;
    private RoomManager rm;


    /**
     * Interacts with user and asks for input then performs actions related to events based on that input.
     * @param UserId The id of the user its interacting with
     * @param UserPerm -1 If User is a Speaker
     *                 0 If User is an Organizer
     *                 1 If User is an Attendee
    **/
    public void run(int UserId, int UserPerm) {

        EventRoomGateway EvRoGate = new EventRoomGateway();
        em = new EventManager(EvRoGate.parseEvent());
        rm = new RoomManager(EvRoGate.parseRoom());

        //some code

        EvRoGate.write(em.getEventList(), rm.getRoomList());

    }
}