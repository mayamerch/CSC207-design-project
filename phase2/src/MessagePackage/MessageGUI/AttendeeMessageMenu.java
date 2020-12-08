package MessagePackage.MessageGUI;

import GUI.MainMenuView;
import GUI.Presenter;
import MessagePackage.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendeeMessageMenu extends JFrame {
    private JButton sendMessages;
    private JButton checkMessages;
    private JButton checkBroadcasts;
    private JLabel AttendeeMenu;
    private Presenter presenter;

    public AttendeeMessageMenu(Presenter presenter){
        super();
        this.presenter = presenter;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setContentPane();
        this.pack();

        checkMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });

        sendMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });

        checkBroadcasts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });

    }

    public void showMessages(){

    }
}
