package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountMenuView {
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton createSpeakerButton;
    private JButton backButton;
    private JButton createOrganizerButton;
    private JButton createAttendeeButton;
    private JLabel accountCreationLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel conditionLabel;

    private Presenter presenter;

    public CreateAccountMenuView(Presenter presenter) {
        this.presenter = presenter;
        createAttendeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (createAccount("Attendee")){
                    accountCreationLabel.setText("Attendee successfully created");
                }
                else{accountCreationLabel.setText("Need a unique Username");}
            }
        });
        createOrganizerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(createAccount("Organizer")){
                    accountCreationLabel.setText("Organizer successfully created");
                }
                else{accountCreationLabel.setText("Need a Unique Username or Need to be an Organizer");}
            }
        });
        createSpeakerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(createAccount("Speaker")){
                    accountCreationLabel.setText("Speaker successfully created");
                }
                else{accountCreationLabel.setText("Need a Unique Username or Need to be an Organizer");}
            }
        });
    }
    public boolean createAccount(String userType){
        String username = usernameField.getText();
        String password = passwordField.getText();
        return presenter.createAccount(username, password, userType);
    }
}