package User;

public class UserFactory {

    // Use getuser method to get an object of type User
    public User getuser(String usertype, String new_username, String new_password) {

        if (usertype == null) {
            return null;
        }
        if (usertype.equalsIgnoreCase("ORGANISER")) {
            return new Organiser(new_username, new_password);
        }
        if (usertype.equalsIgnoreCase("SPEAKER")){
            return new Speaker(new_username, new_password);}
        else if (usertype.equalsIgnoreCase("ATTENDEE")) {
            return new Attendee(new_username, new_password);
        } else {
            System.out.println("A " + usertype.toLowerCase() + " is an undefined user for this program.");
            return null;
        }
    }
}
