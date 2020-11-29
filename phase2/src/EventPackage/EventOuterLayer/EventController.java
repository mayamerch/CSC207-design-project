package EventPackage.EventOuterLayer;


import EventPackage.EventEntities.Event;
import EventPackage.EventGateways.EventGateway;
import EventPackage.EventGateways.RoomGateway;
import EventPackage.EventUseCases.EventManager;
import EventPackage.EventUseCases.RoomManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class EventController {

    private EventManager em;
    private RoomManager rm;
    private EventPresenter ep;
    private EventGateway eg;
    private RoomGateway rg;


    /**
     * Creates an instance of EventController and loads in all the saved data
     */
    public EventController() {
        this.eg = new EventGateway();
        this.rg = new RoomGateway();
        em = new EventManager(eg.parseEvent());
        rm = new RoomManager(eg.parseRoom());
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
     * @param UserPermChar 'S' If User is a Speaker
     *                      'O' If User is an Organizer
     *                      'A' If User is an Attendee
     * @param speakerIds A list of the ids of speakers at this conference
     **/
    public void run(int UserId, char UserPermChar, ArrayList<Integer> speakerIds) {

        Scanner reader = new Scanner(System.in);
        Scanner reader2 = new Scanner(System.in);

        String UserPerm = Character.toString(UserPermChar);

        ep.printMenu(UserPerm);
        String UserInput = reader2.nextLine();

        while (!UserInput.equals("0")) {

            switch (UserInput) {

                case "1":
                    ep.seeEvents(em, rm);
                    ep.seeAttendees();
                    String choice = reader.nextLine();

                    while (!choice.equals("0")) {
                        if (!checkInput(choice))
                            ep.printStatus(-1);
                        else {
                            int eventId = Integer.parseInt(choice);
                            try {
                                ep.printAttendees(em.getEvent(eventId).getEventAttendees());
                            }
                            catch (Exception e) {
                                ep.printStatus(-1);
                            }
                        }
                        ep.seeEvents(em, rm);
                        ep.seeAttendees();
                        choice = reader.nextLine();
                    }

                    ep.goBack();

                    break;

                case "2":
                    ep.seeMyEvents(em, rm, UserId, UserPerm);
                    ep.pressContinue();
                    reader.nextLine();
                    ep.goBack();
                    break;

                case "3":
                    ep.seeRooms(rm);
                    ep.pressContinue();
                    reader.nextLine();
                    ep.goBack();
                    break;

                case "4":
                    if (UserPerm.equals("S"))
                        ep.denyUser(UserPerm);
                    else {
                        ep.seeAvailEvents(em, rm, UserId);
                        String UserInput2 = reader.nextLine();
                        while (UserInput2.equals("1")) {
                            this.signUp(UserId);

                            ep.seeAvailEvents(em, rm, UserId);
                            UserInput2 = reader.nextLine();
                        }

                        ep.goBack();
                    }
                    break;

                case "5":
                    if (UserPerm.equals("S"))
                        ep.denyUser(UserPerm);
                    else {
                        ep.seeMyEvents(em, rm, UserId, UserPerm);
                        ep.cancelOptions();
                        String UserInput2 = reader.nextLine();
                        while (UserInput2.equals("1")) {
                            this.cancelAttend(UserId);

                            ep.seeMyEvents(em, rm, UserId, UserPerm);
                            ep.cancelOptions();
                            UserInput2 = reader.nextLine();
                        }

                        ep.goBack();
                    }
                    break;

                case "6":
                    if (UserPerm.equals("S") ||UserPerm.equals("A"))
                        ep.denyUser(UserPerm);
                    else {
                        ep.createEvent();
                        String OrganizerInput = reader.next();
                        if (!OrganizerInput.equals("-1")) {
                            int status = this.createEvent(OrganizerInput, speakerIds);
                            if (status != 0)
                                ep.printError(status);
                            else
                                ep.printStatus(0);
                        }

                        ep.goBack();
                    }
                    break;

                case "7":
                    if (UserPerm.equals("S") || UserPerm.equals("A"))
                        ep.denyUser(UserPerm);
                    else {
                        ep.createRoom();
                        String inputString = reader.nextLine();
                        while (!checkInput(inputString)) {
                            inputString = reader.nextLine();
                        }
                        int input =  Integer.parseInt(inputString);
                        if (input != -1)
                            this.createRoom(input);
                        ep.goBack();
                    }
                    break;

                default:
                    ep.denyUser(UserPerm);
                    break;
            }

            eg.write(em.getEventList(), rm.getRoomList());
            ep.printMenu(UserPerm);
            UserInput = reader2.nextLine();
        }

        ep.goBack();

        eg.write(em.getEventList(), rm.getRoomList());

    }


    private boolean checkInput(String UserInput) {
        try {
            Integer.parseInt(UserInput);
        } catch (Exception e) {
            ep.tryAgain();
            return false;
        }
        return true;
    }


    private void signUp(int UserId) {
        Scanner reader = new Scanner(System.in);

        ep.chooseEvent();
        String UserInput = reader.nextLine();
        while (!checkInput(UserInput)) {
            UserInput = reader.nextLine();
        }
        int UserInputInt = Integer.parseInt(UserInput);

        int status = -1;
        for (Event event: em.availEvents(UserId)) {
            if (event.getEventId() == UserInputInt) {
                em.enroll(UserInputInt, UserId);
                status = 0;
            }
        }

        ep.printStatus(status);
        ep.goBack();
    }


    private void cancelAttend(int UserId) {
        Scanner reader = new Scanner(System.in);

        ep.chooseEvent();
        String UserInput = reader.nextLine();
        while (!checkInput(UserInput)) {
            UserInput = reader.nextLine();
        }
        int UserInputInt = Integer.parseInt(UserInput);

        int status = -1;
        for (Event event: em.myEvents(UserId)) {
            if (event.getEventId() == UserInputInt) {
                em.unenroll(UserInputInt, UserId);
                status = 0;
            }
        }

        ep.printStatus(status);
        ep.goBack();
    }


    private int createEvent(String UserInput, ArrayList<Integer> speakerIds) {
        String eventName;
        int eventRoom;
        Date eventDate;
        int eventSpeaker;
        int eventDuration = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        int status;

        String[] splitInput = UserInput.split(";");
        if (splitInput.length != 4)
            return -2;

        eventName = splitInput[0];
        try {
            eventRoom = Integer.parseInt(splitInput[1]);
            eventDate = sdf.parse(splitInput[2]);
            eventSpeaker = Integer.parseInt(splitInput[3]);
        }
        catch (Exception e) {
            return -3;
        }

        if (!(speakerIds.contains(eventSpeaker)) || !(rm.roomExists(eventRoom))) {
            return -4;
        }
        status = em.createEvent(eventName, eventRoom, eventDate, eventSpeaker, eventDuration);


        return status;
    }

    private void createRoom(int input) {
        int id = rm.createRoom(input);
        ep.printStatus(0);
        ep.displayRoom(id);
    }
}
