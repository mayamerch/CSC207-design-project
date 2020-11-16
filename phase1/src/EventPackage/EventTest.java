package EventPackage;

import java.util.ArrayList;

public class EventTest {
    public static void main(String[] args) {
        EventController ec = new EventController();

        ArrayList<Integer> SpeakerIds = new ArrayList<>(); //Contains sample Id of speakers
        SpeakerIds.add(1);
        SpeakerIds.add(2);

        ec.run(0, 0, SpeakerIds); // User Id (first number) has no impact its what shows up when you attend an event
        // Second parameter is permissions, -1 for speaker, 1 for attendee, 0 for organizer
    }
}