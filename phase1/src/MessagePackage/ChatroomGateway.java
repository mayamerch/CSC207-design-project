package MessagePackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class ChatroomGateway {
    private File chatroomDataFile;

    public ChatroomGateway() {
        this.chatroomDataFile = new File("src/MessagePackage/ChatroomDataFile.txt");
    }

    public void writeControllerToFile(ChatroomController controller) throws IOException {
        FileWriter writer = new FileWriter(this.chatroomDataFile);
        writer.write(controller.toString());
        writer.close();
    }

    public void resetChatroomDataFile() throws IOException {
        FileWriter writer = new FileWriter(this.chatroomDataFile);
        writer.write("");
        writer.close();
    }

    public ChatroomController makeController() throws FileNotFoundException {
        ArrayList<Chatroom> chats = new ArrayList<>();
        Scanner scan = new Scanner(this.chatroomDataFile);
        scan.useDelimiter("\n\n");
        while (scan.hasNext()) {
            Chatroom c = stringToChatroom(scan.next());
            chats.add(c);
        }
        return new ChatroomController(chats);
    }

    private Message stringToMessage(String s){
        String[] stuff = s.split("~");
        String content = stuff[1];
        Integer sender = Integer.parseInt(stuff[0]);
        return new Message(content, sender);
    }

    private MessageQueue stringToMessageQueue(String s){
        //String[] stuff = s.split("\t");
        MessageQueue mq = new MessageQueue();
        if (!s.equals("[]")) {
            String[] stuff = s.substring(1, s.length() - 1).split("\t");
            for (String messageStr : stuff){
                mq.pushMessage(stringToMessage(messageStr));
            }
        }
        // empty mq returns if there s == "[]"
        return mq;
    }

    private ArrayList<Integer> stringToUserList(String s){
        ArrayList<Integer> userList = new ArrayList<Integer>();
        if (!s.equals("[]")) {
            String[] stuff = s.substring(1, s.length() - 1).split(", ");
            for (String userIDStr : stuff){
                userList.add(Integer.parseInt(userIDStr));
            }
        }
        return userList;
    }

    private Chatroom stringToChatroom(String s){
        String[] stuff = s.split("\n");
        ArrayList<Integer> userList = stringToUserList(stuff[0]);
        MessageQueue mq = stringToMessageQueue(stuff[1]);

        return new Chatroom(userList, mq);
    }


}
