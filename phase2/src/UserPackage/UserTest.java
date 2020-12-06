package UserPackage;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserTest {
    @Test(timeout=50)
    public void testCreateOrganiser(){
        UserManager userManager = new UserManager();
        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
        // assertTrue("User not created", userManager.createAccount("user1", "user1", "Organiser"));
        assertEquals("user not added\n", 1, userManager.getUserMap().size());
        assertEquals("Organiser list not updating\n", 1, userManager.getOrganizerList().size());
    }
//    @Test(timeout=50)
//    public void testAddFriend(){
//        UserManager userManager = new UserManager();
//        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
//        userManager.createAccount("user2", "User2",UserType.ATTENDEE);
//        User user1 = userManager.getUserByID(1);
//        User user2 = userManager.getUserByID(2);
//        userManager.addFriend(1, 2);
//        assertEquals("user not added\n", 1, user1.getFriendsList().size());
//        assertEquals("user not added in friend\n", 1, user2.getFriendsList().size());
//    }
    @Test(timeout=50)
    public void testAddDuplicateUserName(){
        UserManager userManager = new UserManager();
        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
        assertFalse("Duplicate created", userManager.createAccount("user1", "User2",UserType.ATTENDEE));
        assertEquals("Duplicate Username added\n", 1, userManager.getUserMap().size());
    }
    @Test(timeout=50)
    public void testSendAndAcceptFriendRequest(){
        UserManager userManager = new UserManager();
        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
        userManager.createAccount("user2", "User2",UserType.ATTENDEE);
        User user1 = userManager.getUserByID(1);
        User user2 = userManager.getUserByID(2);
        userManager.sendFriendRequest(1, 2);
        assertFalse("Can send friend request twice", userManager.sendFriendRequest(1, 2));
        assertEquals("user not added\n", 1, user2.getFriendRequestList().size());
        userManager.acceptFriendRequest(2, 1);
        // assertEquals("user not removed\n", 0, user2.getFriendRequestList().size());
        assertEquals("user did not add friend\n", 1, user1.getFriendsList().size());
        assertEquals("user not added in friend\n", 1, user2.getFriendsList().size());
        assertFalse("Can send friend request to friend", userManager.sendFriendRequest(1, 2));
    }
    @Test(timeout=50)
    public void testLinkedListUserManagerConstructor(){
        UserManager userManager = new UserManager();
        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
        userManager.createAccount("user2", "User2",UserType.ATTENDEE);
        userManager.createAccount("user3", "User3",UserType.SPEAKER);
        userManager.sendFriendRequest(1, 2);
        userManager.acceptFriendRequest(2, 1);
        Map<Integer, User> userHashMap = userManager.getUserMap();
        // Start with a linked list of users and create a new linked list
        UserManager userManager2 = new UserManager(userHashMap);
        assertEquals("OrganizerList not created properly\n", 1, userManager2.getOrganizerList().size());
        assertEquals("SpeakerList not created properly\n", 1, userManager2.getSpeakerList().size());
        assertEquals("AttendeeList not created properly\n", 1, userManager2.getAttendeeList().size());
        User user1 = userManager2.getUserByID(1);
        assertEquals("Friends List not carried over from list to Usermanager2\n", 1, user1.getFriendsList().size());
        userManager2.createAccount("user4", "User4",UserType.ORGANIZER);
        assertNotNull("Id not assigned based on existing List", userManager2.getUserByID(4));
        // This checks if The new User manager creates new IDs based on the old user list, which should have max ID of 3
    }
    @Test(timeout=50)
    public void testSendAndAcceptFriendRequestUsername(){
        UserManager userManager = new UserManager();
        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
        userManager.createAccount("user2", "User2",UserType.ATTENDEE);
        User user1 = userManager.getUserByID(1);
        User user2 = userManager.getUserByID(2);
        userManager.sendFriendRequest("user1", "user2");
        assertFalse("Can send friend request twice", userManager.sendFriendRequest(1, 2));
        assertEquals("user not added\n", 1, user2.getFriendRequestList().size());
        userManager.acceptFriendRequest("user2", "user1");
        // assertEquals("user not removed\n", 0, user2.getFriendRequestList().size());
        assertEquals("user did not add friend\n", 1, user1.getFriendsList().size());
        assertEquals("user not added in friend\n", 1, user2.getFriendsList().size());
        assertFalse("Can send friend request to friend", userManager.sendFriendRequest(1, 2));
    }
    @Test(timeout = 50)
    public void testSendFriendRequestToFriendRequestList(){
        UserManager userManager = new UserManager();
        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
        userManager.createAccount("user2", "User2",UserType.ATTENDEE);
        User user1 = userManager.getUserByID(1);
        User user2 = userManager.getUserByID(2);
        userManager.sendFriendRequest("user1", "user2");
        assertFalse("Can send friend request to person who has already sent you one",
                userManager.sendFriendRequest("user2", "user1"));
    }

}
