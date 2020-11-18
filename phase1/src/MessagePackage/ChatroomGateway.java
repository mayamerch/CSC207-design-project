package MessagePackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class ChatroomGateway {
    private File chatroomDataFile;
    //private ArrayList<StringBuilder> chatroomData;


    public ChatroomGateway() {
        try {
            this.chatroomDataFile = new File("phase1/src/MessagePackage/ChatroomDataFile.txt");
            this.chatroomDataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeControllerToFile(ChatroomController controller) throws IOException {
        FileWriter writer = new FileWriter(this.chatroomDataFile);
        writer.write(controller.toString());
    }

    public ChatroomController makeController() throws FileNotFoundException {
        ArrayList<Chatroom> chats = new ArrayList<Chatroom>();

        File file = new File("/Users/jzhang/Desktop/ChatroomDataFile.txt");
        Scanner scan = new Scanner(file);
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
        String myStatusStr = stuff[0].substring(1, stuff[0].length()-1); // removes outer brackets
        ArrayList<Integer> userList = stringToUserList(stuff[1]);
        MessageQueue mq = stringToMessageQueue(stuff[2]);

        return new Chatroom(userList, mq, myStatusStr);
    }

/*    private ChatroomController stringToChatroomController(String s){
        String[] stuff = s.split("\n\n"); // each string in stuff is for one chatroom

        ArrayList<Chatroom> chats = new ArrayList<Chatroom>();
        for (String chatroomStr: stuff){
            chats.add(stringToChatroom(chatroomStr));
        }
        return new ChatroomController(chats);
    }*/

}
