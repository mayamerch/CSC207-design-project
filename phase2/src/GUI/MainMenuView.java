package GUI;

import EventPackage.EventGUI.CancelAttendView;
import EventPackage.EventGUI.UserMenus.AttendeeEventView;
import EventPackage.EventGUI.UserMenus.OrganizerEventView;
import EventPackage.EventGUI.UserMenus.SpeakerEventView;
import EventPackage.EventOuterLayer.EventController;
import EventPackage.EventOuterLayer.EventPresenter;
import UserPackage.User;
import UserPackage.UserController;
import UserPackage.UserType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenuView extends JFrame{
    private JPanel mainMenuPanel;
    private JButton logoutButton;
    private JButton somethingSomethingButton;
    private JButton eventsButton;
    private JButton messagesButton;
    private JLabel loggedInAsLabel;
    private JLabel usernameIDLabel;
    private JButton exitButton;

    private UserController userController;

    public MainMenuView(UserController userController) {
        //this code is super incomplete
        //need something that keeps track of decisions made in the menu
        //so that the "super controller" knows where to go next
        // ie a listener, possibly use observer design pattern
        //also, we don't need to pass a user controller, feel free to
        //to remove userController stuff
        super();
        this.userController = userController;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainMenuPanel);
        this.pack();

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userController.logOut();
            }
        });
        eventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userid = userController.getCurrentUserId();
                boolean userVip = userController.getUserManager().getUserByID(userid).getVIP();
                ArrayList<Integer> speakerIds = new ArrayList<>();
                for (User user: userController.getSpeakerList()) {
                    speakerIds.add(user.getUserID());
                }
                UserType userType = userController.getUserType();
                EventController eventController = new EventController(userid, userVip, speakerIds);
                EventPresenter eventPresenter = new EventPresenter(eventController);

                if (userType == UserType.ATTENDEE) {
                    AttendeeEventView attendeeEventView = new AttendeeEventView(eventPresenter);
                    attendeeEventView.setContentPane(attendeeEventView.getMainPanel());
                    attendeeEventView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    attendeeEventView.pack();
                    attendeeEventView.setVisible(true);
                }

                else if (userType == UserType.ORGANIZER) {
                    OrganizerEventView organizerEventView = new OrganizerEventView(eventPresenter);
                    organizerEventView.setContentPane(organizerEventView.getMainPanel());
                    organizerEventView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    organizerEventView.pack();
                    organizerEventView.setVisible(true);
                }

                else {
                    SpeakerEventView speakerEventView = new SpeakerEventView(eventPresenter);
                    speakerEventView.setContentPane(speakerEventView.getMainPanel());
                    speakerEventView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    speakerEventView.pack();
                    speakerEventView.setVisible(true);
                }
            }
        });
    }
}
