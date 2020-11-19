package UserPackage;

public class UserFactory {

    /**
     * @param new_username the username of the user to be created, String
     * @param new_password the password of the user to be created, String
     * @param usertype the type of the User to be created, char
     * @return the created User.
     */
    public User getuser(String new_username, String new_password, String usertype) {

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
