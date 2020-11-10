package EventPackage;

public class EventController {

    private EventManager em;
    private RoomManager rm;


    /**
     * Interacts with user and asks for input then performs actions related to events based on that input.
     * @param UserId The id of the user its interacting with
     */
    public void run(int UserId) {

        EventGateway eg = new EventGateway();
        em = new EventManager(eg.parse());

        //some code

        eg.write(em.getEventList());

    }
}