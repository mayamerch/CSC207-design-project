package EventPackage.EventOuterLayer;

import EventPackage.EventUseCases.EventManager;
import UserPackage.UserManager;
import UserPackage.UserType;

import java.io.IOException;

public class EventProgramPresenter {
    private EventProgramExporter eventProgramExporter;
    private int currentUserID;
    private UserType userType;

    public EventProgramPresenter(UserManager userManager, EventManager eventManager, int userID, UserType userType){
        this.eventProgramExporter = new EventProgramExporter(userManager, eventManager);
        this.currentUserID = userID;
        this.userType = userType;
    }

    public void openAllEventsProgram(){
        eventProgramExporter.exportAllEventsProgram();
        eventProgramExporter.openHTMLFile();
    }

    public void openMyEventsProgram(){
        eventProgramExporter.exportEventsUserAttendingProgram(currentUserID);
        if (userType == UserType.SPEAKER){
            eventProgramExporter.exportEventsBySpeakerProgram(currentUserID);
        }else{
            eventProgramExporter.exportEventsUserAttendingProgram(currentUserID);
        }

        eventProgramExporter.openHTMLFile();
    }

    public void openPartyEventsProgram(){
        eventProgramExporter.exportPartyEventsProgram();
        eventProgramExporter.openHTMLFile();
    }

    public void openMultiSpeakerEventsProgram(){
        eventProgramExporter.exportMultiSpeakerEventsProgram();
        eventProgramExporter.openHTMLFile();
    }

    public void openSingleSpeakerEventsProgram(){
        eventProgramExporter.exportSingleSpeakerEventsProgram();
        eventProgramExporter.openHTMLFile();
    }


}
