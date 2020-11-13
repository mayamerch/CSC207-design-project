package EventPackage;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EventPresenter {

    /**
     * Responsible for Printing the Main Menu for a Events and Rooms with all the options possible for
     * a specific user
     * @param UserPerm The permission the user has that decides what they can do with events
     */
    public void printMenu(int UserPerm) {

        StringBuilder temp = new StringBuilder();

        temp.append("Event and Room Main Menu \n");
        temp.append("---------------------------------\n");

        temp.append("1. See all Events\n");
        temp.append("2. See my Events (Events attending or speaking at)\n");

        if (UserPerm != -1) {
            temp.append("3. Attend a new Event\n");
            temp.append("4. Cancel Attending an Event");
            if (UserPerm == 0) {
                temp.append("5. Create a new Event\n");
                temp.append("6. Create a new Room\n");
            }
        }

        temp.append("0. Exit this menu.\n \n");

        temp.append("Please choose an option number.\n");
        System.out.print(temp);

    }


    /**
     * Prints a message that shows that the User is denied an option
     * @param UserPerm The permission of this User
     */
    public void denyUser(int UserPerm) {
        if (UserPerm == -1)
            System.out.println("Sorry this option doesn't exist for a speaker. Please try again.\n");
        else if (UserPerm == 1)
            System.out.println("Sorry this option doesn't exist for an attendee. Please try again\n");
        else
            System.out.println("Sorry this option doesn't exist. Please try again\n");
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
        eventFormat(eventList, rm);
    }


    /**
     * Prints the list of events a user is signed up for or speaking at
     * and all the information related to them
     * @param em The EventManager with all the events
     * @param rm The RoomManager with all the rooms
     * @param UserId The Id of the User we want the events for.
     * @param UserPerm The Type of this User
     */
    public void seeMyEvents(EventManager em, RoomManager rm, int UserId, int UserPerm) {
        System.out.println("My Events");
        System.out.println("---------------------------------");
        ArrayList<Event> eventList;
        if (UserPerm != -1)
            eventList = em.myEvents(UserId);
        else
            eventList = em.speakingAt(UserId);
        eventFormat(eventList, rm);
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
        eventFormat(eventList, rm);
        StringBuilder temp = new StringBuilder();
        temp.append("Please choose an option.\n\n");
        temp.append("1. Attend a new Event\n");
        temp.append("0. Go back\n");
        System.out.print(temp);
    }

    /**
     * Prints a list of options a User has when cancelling attending
     */
    public void cancelOptions() {
        StringBuilder temp = new StringBuilder();
        temp.append("Please choose an option.\n\n");
        temp.append("1. Cancel Attending an Event\n");
        temp.append("0. Go back\n");
        System.out.print(temp);
    }


    private void eventFormat(ArrayList<Event> eventList, RoomManager rm) {
        String[][] table = new String[eventList.size() + 1][7];
        table[0] = new String[] {"Event ID", "Event Name", "Event Date", "Event Duration", "Room Number",
                "Spots Left", "Speakers"};
        for (int i = 0; i < eventList.size(); i++) {
            Event currEvent = eventList.get(i);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:");
            String eventDate = sdf.format(currEvent.getEventDate());

            String capacity = String.valueOf(rm.findRoom(currEvent.getEventRoom()).getRoomCapacity()
                    - currEvent.getEventAttendees().size());

            table[i + 1] = new String[] {String.valueOf(currEvent.getEventId()), currEvent.getEventName(),
                    eventDate, String.valueOf(currEvent.getEventDuration()), String.valueOf(currEvent.getEventRoom()),
                    capacity, String.valueOf(currEvent.getEventSpeaker())};

        }

        for (String[] row : table)
            System.out.format("%s%15s%15s%15s%15s%15s%15s\n", row);

        System.out.print("\n");
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
            System.out.println("Sorry this process could not be completed. Please try again.");
        else
            System.out.println("Process completed successfully.");
    }

    /**
     * Tells User that program is going back to previous menu
     */
    public void goBack() {
        System.out.println("Going back to previous menu.\n");
    }

    public void adamorganizer() {

    }
    public void createRoom(RoomManager rm) {
        StringBuilder temp = new StringBuilder();
        temp.append("Please enter a capacity for the room" + System.lineSeparator());
        System.out.print(temp);
    }

    public void displayRoom(int id) {
        System.out.println("Your assigned room number is "+id+System.lineSeparator());
        System.out.println("Please don't forget this number.");
    }
}