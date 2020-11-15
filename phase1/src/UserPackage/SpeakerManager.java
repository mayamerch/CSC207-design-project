package UserPackage;

import java.util.ArrayList;

public class SpeakerManager {

    SpeakerManager(){
    }
    public ArrayList<Integer> getSpeakerList(Speaker speaker){
        return speaker.getTalksList();
    }
    public boolean getSpeakerEvent(Speaker speaker, int eventID){
        ArrayList<Integer> talksList = speaker.getTalksList();
        for (int eventNum: talksList){
            if (eventNum == eventID){
                return true;
            }
        }
        return false;
    }


    public boolean add_event(Speaker speaker, int eventID){
        if (getSpeakerEvent(speaker, eventID)){ return false;}
        speaker.add_event(eventID);
        return true;
    }
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
