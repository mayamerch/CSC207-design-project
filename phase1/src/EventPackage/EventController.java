package EventPackage;


import java.util.ArrayList;
import java.util.Scanner;

public class EventController {

    private EventManager em;
    private RoomManager rm;
    private EventPresenter ep;
    private EventRoomGateway EvRoGate;


    public EventController() {
        this.EvRoGate = new EventRoomGateway();
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
                    ep.seeAvailEvents(em, rm, UserId);
                    int UserInput2 = reader.nextInt();
                    while (UserInput2 != 0) {
                        this.signUp(UserId);

                        ep.seeAvailEvents(em, rm, UserId);
                        UserInput2 = reader.nextInt();
                    }

                    ep.goBack();
                }
            }

            else if (UserInput == 4) {
                if (UserPerm == -1)
                    ep.denyUser(UserPerm);
                else {
                    ep.seeMyEvents(em, rm, UserId, UserPerm);
                    ep.cancelOptions();
                    int UserInput2 = reader.nextInt();
                    while (UserInput2 != 0) {
                        this.cancelAttend(UserId);

                        ep.seeMyEvents(em, rm, UserId, UserPerm);
                        ep.cancelOptions();
                        UserInput2 = reader.nextInt();
                    }

                    ep.goBack();
                }
            }

            else if (UserInput == 5) {
                if (UserPerm == -1 || UserPerm == 1)
                    ep.denyUser(UserPerm);
                else {
                    // Create event
                }
            }

            else if (UserInput == 6) {
                if (UserPerm == -1 || UserPerm == 1)
                    ep.denyUser(UserPerm);
                else {
                    // Create event
                }
            }


            ep.printMenu(UserPerm);
            UserInput = reader.nextInt();
        }

        ep.goBack();

        EvRoGate.write(em.getEventList(), rm.getRoomList());

    }


    private void signUp(int UserId) {
        Scanner reader = new Scanner(System.in);

        ep.chooseEvent();
        int UserInput = reader.nextInt();

        int status = -1;
        for (Event event: em.availEvents(UserId)) {
            if (event.getEventId() == UserInput) {
                em.enroll(UserInput, UserId);
                status = 0;
            }
        }

        ep.printStatus(status);
        ep.goBack();
    }


    private void cancelAttend(int UserId) {
        Scanner reader = new Scanner(System.in);

        ep.chooseEvent();
        int UserInput = reader.nextInt();

        int status = -1;
        for (Event event: em.myEvents(UserId)) {
            if (event.getEventId() == UserInput) {
                em.unenroll(UserInput, UserId);
                status = 0;
            }
        }

        ep.printStatus(status);
        ep.goBack();
    }
}