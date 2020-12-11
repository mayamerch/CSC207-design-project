package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAttendeeMenuView extends JFrame{
    private JPanel attendeeCreatePanel;
    private JButton backButton;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton createAttendeeButton;
    private JLabel conditionLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel creationLabel;

    private Presenter presenter;

    /**
     * GUI Menu to create Attendees when not logged in.
     * @param presenter Presenter to be used in this view
     */
    public CreateAttendeeMenuView(Presenter presenter) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(attendeeCreatePanel);
        this.pack();

        this.presenter = presenter;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LoginView loginView = new LoginView(presenter);
                loginView.setVisible(true);
            }
        });
        createAttendeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (createAttendee()){
                    creationLabel.setText("Attendee successfully created");
                }
                else{creationLabel.setText("Need a Unique Username");}
            }
        });
    }
    private boolean createAttendee(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        return presenter.createAccount(username, password, "Attendee");
    }
}
