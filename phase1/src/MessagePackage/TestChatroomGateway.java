package MessagePackage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TestChatroomGateway {

    public static void main(String[] args) throws IOException {
        ChatroomGateway g = new ChatroomGateway();
        ChatroomController cc = g.makeController();
        System.out.println(cc.returnChatsforUserID(123));
        System.out.println(cc.returnChatsforUserID(1));

        Chatroom chat = cc.returnChatsforUserID(2).get(0);
        chat.sendMessage("Hello my name is Joe", 2);
        chat.sendMessage("Hello Joe how are you", 1);

        g.writeControllerToFile(cc);

        //g.resetChatroomDataFile();

    }
}
