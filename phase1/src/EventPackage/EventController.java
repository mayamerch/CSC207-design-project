package EventPackage;


import java.util.ArrayList;
import java.util.Scanner;

public class EventController {

    private EventManager em;
    private RoomManager rm;
    private EventPresenter ep;


    public EventController() {
        EventRoomGateway EvRoGate = new EventRoomGateway();
        em = new EventManager(EvRoGate.parseEvent());
        rm = new RoomManager(EvRoGate.parseRoom());
        ep = new EventPresenter();
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
     * @param speakerIds A list of the ids of speakers at this conference
    **/
    public void run(int UserId, int UserPerm, ArrayList<Integer> speakerIds) {

        Scanner reader = new Scanner(System.in);

        ep.printMenu(UserPerm);
        int UserInput = reader.nextInt();

        while (UserInput != 0) {

            if (UserInput == 1) {
                ep.seeEvents(em, rm);
            }

            else if (UserInput == 2) {
                ep.seeMyEvents(em, rm, UserId, UserPerm);
            }

            else if (UserInput == 3) {
                if (UserPerm == -1)
                    ep.denyUser(UserPerm);
                else {
                    // show available events
                }
            }

            else if (UserInput == 4) {
                if (UserPerm == -1)
                    ep.denyUser(UserPerm);
                else {
                    // Attend new event
                }
            }

            else if (UserInput == 5) {
                if (UserPerm == -1)
                    ep.denyUser(UserPerm);
                else {
                    // Cancel attending
                }
            }

            else if (UserInput == 6) {
                if (UserPerm == -1 || UserPerm == 1)
                    ep.denyUser(UserPerm);
                else {
                    // Create event
                }
            }

            else if (UserInput == 7) {
                if (UserPerm == -1 || UserPerm == 1)
                    ep.denyUser(UserPerm);
                else {
                    // Create room
                }
            }


            UserInput = reader.nextInt();
        }

        EventRoomGateway EvRoGate = new EventRoomGateway();
        EvRoGate.write(em.getEventList(), rm.getRoomList());

    }

}