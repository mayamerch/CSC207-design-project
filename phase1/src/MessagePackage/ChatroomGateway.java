package MessagePackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ChatroomGateway {
    private File chatroomDataFile;
    private ArrayList<StringBuilder> chatroomData;


    public ChatroomGateway() {
        try {
            this.chatroomDataFile = new File("phase1/src/EventPackage/chatroomData.txt");
            if (this.chatroomDataFile.createNewFile()) {
                this.chatroomData = new ArrayList<>();
            } else {
                this.chatroomData = reader(chatroomDataFile);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private ArrayList<StringBuilder> reader(File chatroomDataFile) {

    }

    private void stringToChatroom(String s){

    }

    private void fileToChatroomController(){

    }


}
