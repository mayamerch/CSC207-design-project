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
        try {
            eventProgramExporter.openHTMLFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openMyEventsProgram(){
        eventProgramExporter.exportEventsUserAttendingProgram(currentUserID);
        if (userType == UserType.SPEAKER){
            eventProgramExporter.exportEventsBySpeakerProgram(currentUserID);
        }else{
            eventProgramExporter.exportEventsUserAttendingProgram(currentUserID);
        }

        try {
            eventProgramExporter.openHTMLFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void openEventsToSignupProgram(){
//        eventProgramExporter.exportEventsToSignupProgram(currentUserID);
//        try {
//            eventProgramExporter.openHTMLFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void openPartyEventsProgram(){
        eventProgramExporter.exportPartyEventsProgram();
        try {
            eventProgramExporter.openHTMLFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openMultiSpeakerEventsProgram(){
        eventProgramExporter.exportMultiSpeakerEventsProgram();
        try {
            eventProgramExporter.openHTMLFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openSingleSpeakerEventsProgram(){
        eventProgramExporter.exportSingleSpeakerEventsProgram();
        try {
            eventProgramExporter.openHTMLFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void openEventsBySpeakerProgram(){
//        // only run this if the user signed in is a speaker!
//        eventProgramExporter.exportEventsBySpeakerProgram(currentUserID);
//        try {
//            eventProgramExporter.openHTMLFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
