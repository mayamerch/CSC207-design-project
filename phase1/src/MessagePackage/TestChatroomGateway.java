package MessagePackage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TestChatroomGateway {

    public static void main(String[] args) throws IOException {
        ChatroomController cc = new ChatroomController();
        ArrayList<Integer> userIDs = new ArrayList<>();


        cc.saveChats();

    }
}
