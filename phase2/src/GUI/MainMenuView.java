package GUI;

import EventPackage.EventGUI.UserMenus.AttendeeEventView;
import EventPackage.EventGUI.UserMenus.OrganizerEventView;
import EventPackage.EventGUI.UserMenus.SpeakerEventView;
import EventPackage.EventOuterLayer.EventPresenter;
import EventPackage.EventOuterLayer.EventProgramPresenter;
import MessagePackage.MessageGUI.AttendeeMessageMenu;
import MessagePackage.MessageGUI.OrganizerMessageMenu;
import MessagePackage.MessageGUI.SpeakerMessageMenu;
import UserPackage.UserController;
import UserPackage.UserType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuView extends JFrame{
    private JPanel mainMenuPanel;
    private JButton logoutButton;
    private JButton usersButton;
    private JButton eventsButton;
    private JButton messagesButton;
    private JLabel loggedInAsLabel;
    private JLabel usernameIDLabel;
    private JButton exitButton;

    private UserController userController;
    private Presenter presenter;
    private EventPresenter eventPresenter;

    /**
     * GUI Menu to access the Events Menu, Friends Menu or Messages Menu after logging in
     * @param presenter Presenter to be used in this view
     */
    public MainMenuView(Presenter presenter) {
        //this code is super incomplete
        //need something that keeps track of decisions made in the menu
        //so that the "super controller" knows where to go next
        // ie a listener, possibly use observer design pattern
        //also, we don't need to pass a user controller, feel free to
        //to remove userController stuff
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainMenuPanel);
        this.pack();

        this.userController = presenter.getUserController();
        this.usernameIDLabel.setText(userController.getUsername() + "<" + userController.getCurrentUserId() + ">");
        this.presenter = presenter;
        this.eventPresenter = presenter.getEventPresenter();

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                presenter.userLogOut();
                setVisible(false);
                LoginView loginView = new LoginView(presenter);
                loginView.setVisible(true);
            }
        });
        eventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserType userType = presenter.getUserType();
                EventProgramPresenter programPresenter = presenter.getProgramPresenter();
                if (userType == UserType.ATTENDEE) {
                    AttendeeEventView attendeeEventView = new AttendeeEventView(eventPresenter, programPresenter);
                    attendeeEventView.setVisible(true);
                }

                else if (userType == UserType.ORGANIZER) {
                    OrganizerEventView organizerEventView = new OrganizerEventView(eventPresenter,programPresenter);
                    organizerEventView.setVisible(true);
                }

                else {
                    SpeakerEventView speakerEventView = new SpeakerEventView(eventPresenter,programPresenter);
                    speakerEventView.setVisible(true);
                }
            }
        });
        usersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkLoggedIn()){
                    setVisible(false);
                    UserMenuView userMenuView = new UserMenuView(presenter);
                    userMenuView.setVisible(true);
                }

            }
        });
        messagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserType userType = presenter.getUserType();
                if(userType == UserType.ATTENDEE){
                    AttendeeMessageMenu attendeeMessageMenu = new AttendeeMessageMenu(presenter);
                    attendeeMessageMenu.setVisible(true);
                }
                else if(userType == UserType.ORGANIZER){
                    OrganizerMessageMenu organizerMessageMenu = new OrganizerMessageMenu(presenter);
                    organizerMessageMenu.setVisible(true);
                }

                else if(userType == UserType.SPEAKER){
                    SpeakerMessageMenu speakerMessageMenu = new SpeakerMessageMenu(presenter);
                    speakerMessageMenu.setVisible(true);
                }

            }
        });
    }
    private boolean checkLoggedIn(){
        return !presenter.checkNotLoggedIn();
    }
}
