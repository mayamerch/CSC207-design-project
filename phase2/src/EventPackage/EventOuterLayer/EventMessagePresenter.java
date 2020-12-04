package EventPackage.EventOuterLayer;

import javax.swing.*;

public class EventMessagePresenter {

    public void CreateRoomMessage(boolean status) {
        if (!status)
            JOptionPane.showMessageDialog(null,
                    "Sorry please check the format of the information inputted as it caused an error.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else
            JOptionPane.showMessageDialog(null,
                    "Your Room was created successfully.",
                    "Process was successful",
                    JOptionPane.PLAIN_MESSAGE);
    }


    public void MultiSpeakerCreatorMessage(int status) {
        if (status == -2)
            JOptionPane.showMessageDialog(null,
                    "Sorry please check the format of the information inputted as it caused an error.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == -1)
            JOptionPane.showMessageDialog(null,
                    "Sorry this event couldn't be created" +
                            " due to overlap with another event.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == -3)
            JOptionPane.showMessageDialog(null,
                    "Sorry this event couldn't be created" +
                            " due to its capacity exceeding the capacity of its room.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == -4)
            JOptionPane.showMessageDialog(null,
                    "Sorry this event couldn't be created" +
                            " as at least one of the speakers or the room don't exist.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else
            JOptionPane.showMessageDialog(null,
                    "Your Event was created successfully.",
                    "Process was successful",
                    JOptionPane.PLAIN_MESSAGE);
    }

    public void PartyCreatorMessage(int status) {
        if (status == -2)
            JOptionPane.showMessageDialog(null,
                    "Sorry please check the format of the information inputted as it caused an error.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == -1)
            JOptionPane.showMessageDialog(null,
                    "Sorry this event couldn't be created" +
                            " due to overlap with another event.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == -3)
            JOptionPane.showMessageDialog(null,
                    "Sorry this event couldn't be created" +
                            " due to its capacity exceeding the capacity of its room.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == -4)
            JOptionPane.showMessageDialog(null,
                    "Sorry this event couldn't be created" +
                            " as the room doesn't exist.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else
            JOptionPane.showMessageDialog(null,
                    "Your Event was created successfully.",
                    "Process was successful",
                    JOptionPane.PLAIN_MESSAGE);
    }

    public void SingleSpeakerCreatorMessage(int status) {
        if (status == -2)
            JOptionPane.showMessageDialog(null,
                    "Sorry please check the format of the information inputted as it caused an error.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == -1)
            JOptionPane.showMessageDialog(null,
                    "Sorry this event couldn't be created" +
                            " due to overlap with another event.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == -3)
            JOptionPane.showMessageDialog(null,
                    "Sorry this event couldn't be created" +
                            " due to its capacity exceeding the capacity of its room.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == -4)
            JOptionPane.showMessageDialog(null,
                    "Sorry this event couldn't be created" +
                            " as either the speaker or room don't exist.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else
            JOptionPane.showMessageDialog(null,
                    "Your Event was created successfully.",
                    "Process was successful",
                    JOptionPane.PLAIN_MESSAGE);
    }

    public void editMultiMessage(int status) {
        if (status == -2)
            JOptionPane.showMessageDialog(null,
                    "Sorry please check the format of the information inputted as it caused an error.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == -1)
            JOptionPane.showMessageDialog(null,
                    "Sorry this event couldn't be edited" +
                            " due to new information overlapping with another event.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == -4)
            JOptionPane.showMessageDialog(null,
                    "Sorry this event couldn't be created" +
                            " as the room or one of the speakers don't exist.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);


        else
            JOptionPane.showMessageDialog(null,
                    "Your Event was edited successfully.",
                    "Process was successful",
                    JOptionPane.PLAIN_MESSAGE);
    }

    public void editSingleMessage(int status) {
        if (status == -2)
            JOptionPane.showMessageDialog(null,
                    "Sorry please check the format of the information inputted as it caused an error.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == -1)
            JOptionPane.showMessageDialog(null,
                    "Sorry this event couldn't be edited" +
                            " due to new information overlapping with another event.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == -4)
            JOptionPane.showMessageDialog(null,
                    "Sorry this event couldn't be created" +
                            " as the room or speaker don't exist.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);


        else
            JOptionPane.showMessageDialog(null,
                    "Your Event was edited successfully.",
                    "Process was successful",
                    JOptionPane.PLAIN_MESSAGE);
    }

    public void editPartyMessage(int status) {
        if (status == -2)
            JOptionPane.showMessageDialog(null,
                    "Sorry please check the format of the information inputted as it caused an error.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == -1)
            JOptionPane.showMessageDialog(null,
                    "Sorry this event couldn't be edited" +
                            " due to new information overlapping with another event.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == -4)
            JOptionPane.showMessageDialog(null,
                    "Sorry this event couldn't be created" +
                            " as the room doesn't exist.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);


        else
            JOptionPane.showMessageDialog(null,
                    "Your Event was edited successfully.",
                    "Process was successful",
                    JOptionPane.PLAIN_MESSAGE);
    }

    public void rescheduleEventMessage(int status) {
        if (status == -1)
            JOptionPane.showMessageDialog(null,
                    "Sorry please check the format of the information inputted as it caused an error.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == 0)
            JOptionPane.showMessageDialog(null,
                    "The Event you have entered is not available", "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);
    }

    public void AttendEventMessage(boolean status) {
        if (status)
            JOptionPane.showMessageDialog(null,
                    "You have successfully been enrolled in the event", "Process was successful",
                    JOptionPane.PLAIN_MESSAGE);
        else
            JOptionPane.showMessageDialog(null,
                    "The Event you have entered is not available", "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);
    }


    public void CancelEventMessage(int status) {
        if (status == -2)
            JOptionPane.showMessageDialog(null,
                    "Sorry please check the format of the information inputted as it caused an error.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);
        else if (status == -1)
            JOptionPane.showMessageDialog(null,
                    "Sorry the event you entered doesn't exist.",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else if (status == 0)
            JOptionPane.showMessageDialog(null,
                    "You aren't enrolled in the event entered",
                    "Process was unsuccessful",
                    JOptionPane.ERROR_MESSAGE);

        else
            JOptionPane.showMessageDialog(null,
                    "You are no longer enrolling in the Event",
                    "Process was unsuccessful",
                    JOptionPane.PLAIN_MESSAGE);
    }

}