package MessagePackage;

import EventPackage.EventUseCases.EventManager;
import UserPackage.UserManager;

import java.io.IOException;
import java.util.ArrayList;

// ********************************************
// Gateway Tester (can be ignored for Phase 2)
// ********************************************


public class TestChatroomGateway {

    public static void main(String[] args) throws IOException {
        EventManager em = new EventManager();
        UserManager um = new UserManager();
        ChatroomController cc = new ChatroomController(em, um);
        ArrayList<Integer> userIDs = new ArrayList<>();


        cc.saveChats();

    }
}
