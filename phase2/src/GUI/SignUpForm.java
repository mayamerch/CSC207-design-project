package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpForm extends JFrame{

    SignUpForm(){
        setSize(400,400);

        JButton b1 = new JButton("Sign Up");
        b1.setBounds(150,225,100, 40);
        add(b1);

        JTextField tf1 = new JTextField();
        tf1.setBounds(200, 100, 150, 30);
        JTextField tf2 = new JTextField();
        tf2.setBounds(200, 150, 150, 30);
        add(tf1);
        add(tf2);

        JLabel l1 = new JLabel("Username");
        l1.setBounds(75, 100, 200, 30);
        JLabel l2 = new JLabel("Password");
        l2.setBounds(75, 150, 200, 30);
        add(l1);
        add(l2);

        setLayout(null);
        setVisible(true);

        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String username = tf1.getText();
                String password = tf2.getText();
                //TODO: createAccount for user here
                //UserController.createUser
            }
        });
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

    }
}
