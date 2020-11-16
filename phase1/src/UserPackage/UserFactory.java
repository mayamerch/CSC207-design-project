package UserPackage;

public class UserFactory {

    // Use getuser method to get an object of type User
    public User getuser(String usertype, String new_username, String new_password) {

        if (usertype == null) {
            return null;
        }
        if (usertype.equalsIgnoreCase("ORGANISER")) {
            char type = 'O';
            return new Organiser(new_username, new_password, type);
        }
        if (usertype.equalsIgnoreCase("SPEAKER")){
            char type = 'S';
            return new Speaker(new_username, new_password, type);
        }
        else if (usertype.equalsIgnoreCase("ATTENDEE")) {
            char type = 'A';
            return new Attendee(new_username, new_password, type);
        } else {
            System.out.println("A " + usertype.toLowerCase() + " is an undefined user for this program.");
            return null;
        }
    }
}
