package UserPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserGateway {
    String path = "C:/Users/edwar/Documents/UofT/CSC207";
    String line = "";
    BufferedReader userReader;

    public ArrayList<String> read() {
        {
            try {
                userReader = new BufferedReader(new FileReader(path));
                ArrayList<String> userData = new ArrayList<String>();
                while ((line = userReader.readLine()) != null) {
                    String[] values = line.split(",");
                    Collections.addAll(userData, values);
                    // not sure if this is right, but we can modify this to only add
                    // usernames, passwords, etc.
                    // we can also take every 4th term for specific lists
                }
                return userData;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;

            }
        }
    }

    public void write(List<List<String>> userdata){
        // each list is the userdata for an individual user (ID, username, password, friends list)
        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter("userdata.csv");
            csvWriter.append("UserID");
            csvWriter.append(",");
            csvWriter.append("Username");
            csvWriter.append(",");
            csvWriter.append("Password");
            csvWriter.append(",");
            csvWriter.append("Friend List");
            csvWriter.append("\n");
            for (List<String> rowData : userdata) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
