package EventPackage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EventPresenter {

    /**
     * Responsible for Printing the Main Menu for a Events and Rooms with all the options possible for
     * a specific user
     * @param UserPerm The permission the user has that decides what they can do with events
     */
    public void printMenu(String UserPerm) {

        StringBuilder temp = new StringBuilder();

        temp.append("Event and Room Main Menu" + System.lineSeparator());
        temp.append("---------------------------------" + System.lineSeparator());

        temp.append("1. See all Events" + System.lineSeparator());
        temp.append("2. See my Events (Events attending or speaking at)" + System.lineSeparator());
        temp.append("3. See Rooms" + System.lineSeparator());

        if (!UserPerm.equals("S")) {
            temp.append("4. Attend a new Event" + System.lineSeparator());
            temp.append("5. Cancel Attending an Event" + System.lineSeparator());
            if (UserPerm.equals("O")) {
                temp.append("6. Create a new Event" + System.lineSeparator());
                temp.append("7. Create a new Room" + System.lineSeparator());
            }
        }

        temp.append("0. Exit this menu." + System.lineSeparator() + System.lineSeparator());

        temp.append("Please choose an option number." + System.lineSeparator());
        System.out.print(temp);

    }


    /**
     * Prints a message that shows that the User is denied an option
     * @param UserPerm The permission of this User
     */
    public void denyUser(String UserPerm) {
        if (UserPerm.equals("S"))
            System.out.println("Sorry this option doesn't exist for a speaker. Please try again."
                    + System.lineSeparator());
        else if (UserPerm.equals("A"))
            System.out.println("Sorry this option doesn't exist for an attendee. Please try again."
                    + System.lineSeparator());
        else
            System.out.println("Sorry this option doesn't exist. Please try again." + System.lineSeparator());
    }


    /**
     * Prints the full list of events and all the information related to them
     * @param em The EventManager with all the events
     * @param rm The RoomManager with all the rooms
     */
    public void seeEvents(EventManager em, RoomManager rm) {
        System.out.println("All Events");
        System.out.println("---------------------------------");
        ArrayList<Event> eventList = em.getEventList();
        eventFormat(em, rm);
    }


    /**
     * Prints the list of events a user is signed up for or speaking at
     * and all the information related to them
     * @param em The EventManager with all the events
     * @param rm The RoomManager with all the rooms
     * @param UserId The Id of the User we want the events for.
     * @param UserPerm The Type of this User
     */
    public void seeMyEvents(EventManager em, RoomManager rm, int UserId, String UserPerm) {
        System.out.println("My Events");
        System.out.println("---------------------------------");
        ArrayList<Event> eventList;
        if (!UserPerm.equals("S"))
            eventList = em.myEvents(UserId);
        else
            eventList = em.speakingAt(UserId);
        eventFormat(em, rm);
    }


    /**
     * Prints the list of events the user can sign up for and give them the option to sign up.
     * @param em The EventManager with the list of Events
     * @param rm The RoomManager with the list of Rooms
     * @param UserId The id of the User
     */
    public void seeAvailEvents(EventManager em, RoomManager rm, int UserId) {
        System.out.println("Available Events");
        System.out.println("---------------------------------");
        ArrayList<Event> eventList = em.availEvents(UserId);
        eventFormat(em, rm);
        StringBuilder temp = new StringBuilder();
        temp.append("Please choose an option. Anything other than a 1 will make you go back." +
                System.lineSeparator() + System.lineSeparator());
        temp.append("1. Attend a new Event" + System.lineSeparator());
        temp.append("0. Go back" + System.lineSeparator());
        System.out.print(temp);
    }

    /**
     * Prints a list of options a User has when cancelling attending
     */
    public void cancelOptions() {
        StringBuilder temp = new StringBuilder();
        temp.append("Please choose an option. Anything other than a 1 will make you go back"
                + System.lineSeparator() + System.lineSeparator());
        temp.append("1. Cancel Attending an Event" + System.lineSeparator());
        temp.append("0. Go back" + System.lineSeparator());
        System.out.print(temp);
    }



    /**
     * Print the list of rooms with their information
     * @param rm RoomManger with all the rooms
     */
    public void seeRooms(RoomManager rm) {
        System.out.println("Rooms");
        System.out.println("---------------------------------");
        ArrayList<Room> roomList = rm.getRoomList();
        String[][] table = new String[roomList.size() + 1][2];
        table[0] = new String[] {"Room Number", "Room Capacity"};
        for (int i = 0; i < roomList.size(); i++) {
            Room currRoom = roomList.get(i);

            table[i + 1] = new String[]{String.valueOf(currRoom.getRoomNumber()),
                    String.valueOf(currRoom.getRoomCapacity())};
        }
        for (String[] row : table)
            System.out.format("%s%15s" + System.lineSeparator(), row);

        System.out.print(System.lineSeparator());
    }


    /**
     * Tells user he has the option of seeing attendees of an event.
     */
    public void seeAttendees() {
        System.out.println("To see the attendees of a specific event please type the Id of the event. " +
                "To exit type 0.");
    }


    /**
     * prints list of integers representing attendees
     * @param attendees The ids of the attendees
     */
    public void printAttendees(ArrayList<Integer> attendees) {
        System.out.println("The list of attendees (by their id) is," + System.lineSeparator() + attendees +
                System.lineSeparator());
    }


    private void eventFormat(EventManager eventManger, RoomManager rm) {
        ArrayList<Event> eventList = eventManger.getEventList();
        String[][] table = new String[eventList.size() + 1][7];
        table[0] = new String[] {"Event ID", "Event Name", "Event Date", "Event Duration", "Room Number",
                "Spots Left", "Speakers"};
        for (int i = 0; i < eventList.size(); i++) {
            Event currEvent = eventList.get(i);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String eventDate = sdf.format(currEvent.getEventDate());

            String capacity = String.valueOf(rm.findRoom(currEvent.getEventRoom()).getRoomCapacity()
                    - currEvent.getEventAttendees().size());

            table[i + 1] = new String[] {String.valueOf(currEvent.getEventId()), currEvent.getEventName(),
                    eventDate, String.valueOf(currEvent.getEventDuration()), String.valueOf(currEvent.getEventRoom()),
                    capacity, String.valueOf(currEvent.getEventSpeaker())};

        }

        System.out.format("%s%15s%15s%20s%15s%15s%15s" + System.lineSeparator(), table[0]);

        for (int i = 1; i < table.length; i++)
            System.out.format("%4s%13s%24s%10s%16s%16s%16s" + System.lineSeparator(), table[i]);

        System.out.print(System.lineSeparator());
    }

    /**
     * Asks User to choose an event.
     */
    public void chooseEvent() {
        System.out.println("Choose the number of an Event.");
    }


    /**
     * Prints the result of a process
     * @param status -1 If process is not completed
     *               0 If process is completed
     */
    public void printStatus(int status) {
        if (status == -1)
            System.out.println("Sorry this process could not be completed. Please try again." + System.lineSeparator());
        else
            System.out.println("Process completed successfully." + System.lineSeparator());
    }

    /**
     * Tells User that program is going back to previous menu
     */
    public void goBack() {
        System.out.println("Going back to previous menu." + System.lineSeparator());
    }


    /**
     * Prints information user needs to know in order to create an event.
     */
    public void createEvent() {
        System.out.println("Please enter information about the new event in this format exactly." +
                System.lineSeparator() + "The format: Event Name;Event Room;Event Date;Event Speaker" +
                System.lineSeparator() + System.lineSeparator() + "Please don't use semi-colons in the Event Name " +
                "and for Event Room and Event Speaker enter the Ids " +
                "corresponding to them." + System.lineSeparator() +
                "For Event Date enter it in the format: yyyy-MM-dd-HH:mm" + System.lineSeparator() +
                "If you want to cancel this process then type -1.");
    }


    /**
     * Print different error messages according to type of error.
     * @param error type of error
     */
    public void printError(int error) {
        if (error == -3) {
            System.out.println("The format of the input was wrong, please try again next time."
                    + System.lineSeparator());
        }

        else if (error == -2) {
            System.out.println("The format of the input was wrong and an accidental \";\"" +
                    " might have been used.Please try again next time." + System.lineSeparator());
        }

        else if (error == -1) {
            System.out.println("The speaker is already booked at that time or an event is booked" +
                    " in the same room on that time.Please check and try again next time." + System.lineSeparator());
        }

        else if (error == -4) {
            System.out.println("The Speaker or Room chosen don't exist." +
                    "Please try again next time." + System.lineSeparator());
        }
    }


    /**
     * Prints information that a user needs to create a room.
     */
    public void createRoom() {
        StringBuilder temp = new StringBuilder();
        temp.append("Please enter a capacity for the room or -1 to go back." + System.lineSeparator());
        System.out.print(temp);
    }


    /**
     * Displays information about a created room
     * @param id Id of the room created
     */
    public void displayRoom(int id) {
        System.out.println("Your assigned room number is "+id);
        System.out.println("Please don't forget this number."+System.lineSeparator());
    }

    /**
     * Asks User to type another integer and gives the option to exit.
     */
    public void tryAgain() {
        System.out.println("Sorry There was a problem. Please try again. To exit type -1." + System.lineSeparator());
    }


    /**
     * Prompts the user to go back.
     */
    public void pressContinue() {
        System.out.println("Type anything to go back.");
    }

}