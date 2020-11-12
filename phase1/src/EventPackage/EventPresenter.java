package EventPackage;

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
        temp.append("-----------------------------\n");

        temp.append("1. See all Events\n");
        temp.append("2. See my Events (Events attending or speaking at)\n");

        if (UserPerm != -1) {
            temp.append("3. See Events I can sign up for\n");
            temp.append("4. Attend a new Event\n");
            temp.append("5. Cancel attending an Event\n");
            if (UserPerm == 0) {
                temp.append("6. Create a new Event\n");
                temp.append("7. Create a new Room\n");
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
            System.out.println("Sorry this option doesn't exist for a speaker.");
        else if (UserPerm == 1)
            System.out.println("Sorry this option doesn't exist for an attendee.");
        else
            System.out.println("Sorry this option doesn't exist.");
    }


    /**
     * Prints the full list of events and all the information related to them
     * @param em The EventManager with all the events
     * @param rm The RoomManager with all the rooms
     */
    public void seeEvents(EventManager em, RoomManager rm) {
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
        ArrayList<Event> eventList;
        if (UserPerm != -1)
            eventList = em.myEvents(UserId);
        else
            eventList = em.speakingAt(UserId);
        eventFormat(eventList, rm);
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
    }
}