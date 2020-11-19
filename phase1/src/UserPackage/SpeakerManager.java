package UserPackage;

import java.util.ArrayList;

public class SpeakerManager {
    /**
     * Creates a Speaker manager
     */
    SpeakerManager(){}
    public ArrayList<Integer> getSpeakerList(Speaker speaker){
        return speaker.getTalksList();
    }
    /**
     * Takes in a event ID and returns true or false based on whether the speaker has the event id
     * in their events list
     * @param speaker: The Speaker object
     * @param eventID : The ID of the event
     */
    public boolean getSpeakerEvent(Speaker speaker, int eventID){
        ArrayList<Integer> talksList = speaker.getTalksList();
        for (int eventNum: talksList){
            if (eventNum == eventID){
                return true;
            }
        }
        return false;
    }

    /**
     * Takes in a event ID and returns true or false based on whether the event ID was successfully added to
     * the list of events the speaker is speaking at
     * @param speaker: The Speaker object
     * @param eventID : The ID of the event
     */
    public boolean add_event(Speaker speaker, int eventID){
        if (getSpeakerEvent(speaker, eventID)){ return false;}
        speaker.add_event(eventID);
        return true;
    }
    /**
     * Takes in a event ID and returns true or false based on whether the event ID was successfully removed
     * from the list of events the speaker is speaking at
     * @param speaker: The Speaker object
     * @param eventID : The ID of the event
     */
    public boolean remove_event(Speaker speaker, int eventID){
        ArrayList<Integer> talksList = speaker.getTalksList();
        for (int eventNum: talksList){
            if (eventNum == eventID){
            speaker.remove_event(eventID);
            return true;}
        }
        return false;
    }

}
