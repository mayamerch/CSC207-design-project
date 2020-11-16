package UserPackage;

public class UserFactory {

    // Use getuser method to get an object of type User
    public User getuser(String usertype, String new_username, String new_password) {

        if (usertype == null) {
            return null;
        }
        if (usertype.equalsIgnoreCase("ORGANISER")) {
            Organiser organiser = new Organiser(new_username, new_password);
            organiser.setType('O');
            return organiser;
        }
        if (usertype.equalsIgnoreCase("SPEAKER")){
            Speaker speaker = new Speaker(new_username, new_password);
            speaker.setType('S');
            return speaker;
        }
        else if (usertype.equalsIgnoreCase("ATTENDEE")) {
            Attendee attendee = new Attendee(new_username, new_password);
            attendee.setType('A');
            return attendee;
        } else {
            System.out.println("A " + usertype.toLowerCase() + " is an undefined user for this program.");
            return null;
        }
    }
}
