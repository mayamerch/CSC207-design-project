package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VIPMenuView extends JFrame {
    private JTextField textField1;
    private JButton removeVIPButton;
    private JButton makeVIPButton;
    private JLabel usernameIDLabel;
    private JLabel statusLabel;
    private JButton backButton;
    private JPanel vipPanel;

    private Presenter presenter;

    public VIPMenuView(Presenter presenter) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(vipPanel);
        this.pack();

        makeVIPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = usernameIDLabel.getText();
                if (makeVIP(userInput)){
                    statusLabel.setText("User has been made VIP");
                }
                else{statusLabel.setText("User is already VIP");}
            }
        });
        removeVIPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = usernameIDLabel.getText();
                if (removeVIP(userInput)){
                    statusLabel.setText("User has had VIP status removed");
                }
                else{statusLabel.setText("User is not VIP. No change needed");}
            }
            });
    }
    public boolean makeVIP(String userInput){
        return presenter.changeVIP(userInput, true);
        }
    public boolean removeVIP(String userInput){
        return presenter.changeVIP(userInput, false);
    }
}
