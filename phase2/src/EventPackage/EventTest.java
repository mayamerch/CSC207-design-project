package EventPackage;

import EventPackage.EventGUI.UserMenus.AttendeeEventView;
import EventPackage.EventGUI.UserMenus.OrganizerEventView;
import EventPackage.EventOuterLayer.EventController;

import javax.swing.*;
import java.util.ArrayList;


public class EventTest {


    public static void main(String[] args) {
        /*
        //EventController ec = new EventController();

        ArrayList<Integer> SpeakerIds = new ArrayList<>(); //Contains sample Id of speakers

        // More Statement adding Ids of speakers acn be added
        SpeakerIds.add(1);
        SpeakerIds.add(2);

        //ec.run(0, 'O', SpeakerIds); // User Id (first number) has no impact separately, its what shows up when you attend an event (your UserId)
                                        // Second parameter is permissions, -1 for speaker, 1 for attendee, 0 for organizer

        //Gateway Test
        Date d = new Date();
        Room room = new Room(1, 30);
        Event cbt = new Event(1, "cbt", 1, d, 1, 2 );
        EventGateway eg = new EventGateway();
        EventManager em = new EventManager();
        em.createMultiSpeakerEvent("cbt", 30,  d, 1, 2, false, SpeakerIds);
        eg.write(em);
        EventManager s = eg.getEventManager();
        System.out.println(s.getMultiSpeakerList());

        */

        ArrayList<Integer> SpeakerIds = new ArrayList<>(); //Contains sample Id of speakers

        // More Statement adding Ids of speakers acn be added
        SpeakerIds.add(1);
        SpeakerIds.add(2);

        EventController ec = new EventController(0, true, SpeakerIds);

        OrganizerEventView or = new OrganizerEventView(ec);
        or.setContentPane(or.getMainPanel());
        or.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        or.pack();
        or.setVisible(true);

    }
}
