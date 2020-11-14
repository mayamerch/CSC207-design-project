package UserPackage;

import EventPackage.EventManager;

import java.util.LinkedList;
import java.util.List;

public class SpeakerController extends UserController {


    public SpeakerController(UserManager user_manager, EventManager event_manager) {
        super(user_manager, event_manager);
    }

    public void speakerBroadcast(){
        int eventID;
        LinkedList<Integer> eventList = new LinkedList<>();
        do{
            System.out.println("Enter ID of Event");
            eventID = scanner.nextInt();
            System.out.println("Enter -1 when you want to stop");
            eventList.add(eventID);
        }
        while (eventID != -1);
        speakerBroadcast(eventList);
    }

    public void speakerBroadcast(List eventList){

    }

}
