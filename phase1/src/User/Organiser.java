package User;

public class Organiser extends Attendee{
    /**
     * Constructs a new Organiser object
     * @param username : a name unique to this instance of user, is a String
     * @param password : a password used to log in, is a String
     */
    public Organiser(String username, String password) {
        super(username, password);
    }
    // Do we need an IsOrganiser attribute when we can check types?


}
