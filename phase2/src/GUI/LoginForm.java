package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame{

    JFrame f;
    LoginForm(){
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton b1 = new JButton("Login");
        b1.setBounds(150,150,100, 40);
        add(b1);
        JButton b2 = new JButton("Create Account");
        b2.setBounds(150,200,150, 40);
        add(b2);
        JLabel label = new JLabel("Group 0039 FTW");
        label.setBounds(150, 100, 150, 40);
        add(label);

        setLayout(null);
        setVisible(true);

        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                new SignInForm();
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                new SignUpForm();
            }
        });

    }
}
