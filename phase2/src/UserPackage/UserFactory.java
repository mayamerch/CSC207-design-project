package UserPackage;

public class UserFactory {

    /**
     * @param newUsername the username of the user to be created, String
     * @param newPassword the password of the user to be created, String
     * @param userType the type of the User to be created, char
     * @return the created User.
     */

    public User getUser(String newUsername, String newPassword, UserType userType){
        switch (userType){
            case SPEAKER:
                return new Speaker(newUsername, newPassword, userType);
            default:
                return new User(newUsername, newPassword, userType);
        }
    }
}
