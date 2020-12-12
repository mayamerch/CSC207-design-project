package MessagePackage;

import EventPackage.EventUseCases.EventManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BroadcastGateway {
    private File broadcastDataFile;
    private ArrayList<Broadcast> broadcasts;

    public BroadcastGateway() {
        this.broadcastDataFile = new File("src/MessagePackage/BroadcastDataFile.ser");
        try  {
            if (broadcastDataFile.createNewFile())
                broadcasts = new ArrayList<Broadcast>();
            else
                readBroadcastsObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveBroadcastsObject(ArrayList<Broadcast> broadcasts) {
        try {
            this.broadcasts = broadcasts;
            FileOutputStream fileOut = new FileOutputStream(this.broadcastDataFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(broadcasts);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in "+this.broadcastDataFile.getPath());
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    public void readBroadcastsObject(){
        try {
            FileInputStream fileIn = new FileInputStream(this.broadcastDataFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            broadcasts = (ArrayList<Broadcast>) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            System.out.println("Check file directory");
            broadcasts = new ArrayList<Broadcast>();
        }
        catch (IOException | ClassNotFoundException e)
        {
            broadcasts = new ArrayList<Broadcast>();
        }
    }

    public ArrayList<Broadcast> getBroadcasts() {
        readBroadcastsObject();
        return broadcasts;
    }

}
