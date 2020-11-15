package UserPackage;

import EventPackage.EventManager;
import MessagePackage.MessageQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SpeakerController extends UserController {
    private SpeakerManager speakerManager;


    public SpeakerController(UserManager user_manager, EventManager event_manager) {
        super(user_manager, event_manager);
        speakerManager = new SpeakerManager();

    }
    public Speaker getSpeakerObject(){
        // This should be a speaker automatically if accessing this controller
        return (Speaker) userManager.getUserByID(currentUserId);

    }

    public void speakerBroadcast(){
        int eventID;
        LinkedList<Integer> eventList = new LinkedList<>();
        do{
            Speaker user = getSpeakerObject();
            // This should be a speaker if a speaker is using the controller
            System.out.println("Enter ID of Event");
            eventID = validateEventInput();
            ArrayList<Integer> talksList = speakerManager.getSpeakerList(user);
            if (speakerManager.getSpeakerEvent(user, eventID)){
                eventList.add(eventID);
                System.out.println("Enter -1 when you want to stop");}
            else{System.out.println("You are not speaking in that event");}
        }
        while (eventID != -1);
        speakerBroadcast(eventList);
    }

    public void speakerBroadcast(List eventList){
        Speaker currentSpeaker = getSpeakerObject();
        MessageQueue messageQueue = new MessageQueue();

    }

}
