package MessagePackage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ConversationGateway {
    private File chatroomDataFile;
    private File broadcastDataFile;
    private ArrayList<StringBuilder> chatroomData;
    private ArrayList<StringBuilder> broadcastData;

    /**
     * create an instance of ConversationGateway
     */
    public ConversationGateway(){
        try {
            File file = new File("phase1/src/MessagePackage/ConversationData.txt");



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Conversation ConversationReader(){
        return null;
    }

    private ArrayList<StringBuilder> ConversationParser(){

    }


}
