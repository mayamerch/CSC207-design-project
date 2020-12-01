package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuForm extends JFrame {

    MenuForm(){
        setSize(400,400);

        JButton b1 = new JButton("Events");
        b1.setBounds(150,100,100, 40);
        add(b1);
        JButton b2 = new JButton("Conversation");
        b2.setBounds(150,150,100, 40);
        add(b2);
        JButton b3 = new JButton("Friends");
        b3.setBounds(150,200,100, 40);
        add(b3);

        setLayout(null);
        setVisible(true);

        JButton backButton = new JButton("<- Back");
        backButton.setBounds(10,10,75, 30);
        add(backButton);
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(340,340,50, 30);
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO:check if attendee, organizer, speaker
                new AttendeeEventForm();
            }
        });

    }
}
