package UserPackage;
import GUI.Presenter;
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
    @Test(timeout = 50)
    public void testValidateLogin(){
        // already know this works by testing GUI
        UserManager userManager = new UserManager();
        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
        int userID = userManager.getUserIDByUsername("user1");
        int returnedID = userManager.validateLogin("user1", "user1");
        assertEquals("does not return the same ID", userID, returnedID);
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
    public void testAddUserNameWithDigit(){
        UserManager userManager = new UserManager();
        boolean creation = userManager.createAccount("12user1", "user1", UserType.ORGANIZER);
        assertFalse("Can create User with digit in front of username", creation);
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
        userManager.sendFriendRequest("user1", "user2");
        assertFalse("Can send friend request to person who has already sent you one",
                userManager.sendFriendRequest("user2", "user1"));
    }
    @Test(timeout = 50)
    public void testSetVIP(){
        UserManager userManager = new UserManager();
        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
        int userID = userManager.getUserIDByUsername("user1");
        User user1 = userManager.getUserByID(userID);
        userManager.changeVIP(userID,true);
        boolean VIPStatus = user1.getVIP();
        assertTrue("User not made VIP", VIPStatus);
        userManager.changeVIP(userID,true);
        boolean VIPStatus3 = userManager.changeVIP(userID, true);
        assertFalse("Does not return false properly", VIPStatus3);
        userManager.changeVIP(userID,false);
        boolean VIPStatus2 = user1.getVIP();
        assertFalse("user VIP not removed", VIPStatus2);
        userManager.changeVIP(userID,false);
        boolean VIPStatus4 = userManager.changeVIP(userID, false);
        assertFalse("Does not return false properly", VIPStatus4);
    }

    // ======================= Controller Tests ===========================
    // Probably dont need to test the get friends list or friend requests list, tested in GUI

    @Test(timeout = 50)
    public void testControllerLogin() {
        // already know this works with GUI testing
        UserManager userManager = new UserManager();
        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
        UserController userController1 = new UserController(userManager);
        boolean correctLogin = userController1.userLogin("user1", "user1");
        assertTrue("doesn't log in with correct credentials", correctLogin);
        boolean incorrectLogin = userController1.userLogin("user1", "user2");
        assertFalse("Logs in with incorrect details", incorrectLogin);
    }
    @Test(timeout = 50)
    public void testLogOut(){
        UserManager userManager = new UserManager();
        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
        UserController userController1 = new UserController(userManager);
        boolean correctLogin = userController1.userLogin("user1", "user1");
        assertTrue("doesn't log in with correct credentials", correctLogin);
        userController1.logOut();
        int currentUser = userController1.getCurrentUserId();
        assertEquals("Not logged out properly", -1, currentUser);
    }
    @Test(timeout = 50)
    public void testCreateUserLoggedIn(){
        UserManager userManager = new UserManager();
        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
        UserController userController1 = new UserController(userManager);
        boolean correctLogin = userController1.userLogin("user1", "user1");
        assertTrue("doesn't log in with correct credentials", correctLogin);
        userController1.createUser("user2", "user2", UserType.ORGANIZER);
        int organiserListSize = userController1.getUserManager().getOrganizerList().size();
        assertEquals("doesn't add organiser properly", 2, organiserListSize);
        userController1.createUser("user3", "user3", UserType.SPEAKER);
        int speakerListSize = userController1.getUserManager().getSpeakerList().size();
        assertEquals("doesn't add speaker properly", 1, speakerListSize);
        userController1.createUser("user4", "user4", UserType.ATTENDEE);
        int attendeeListSize = userController1.getUserManager().getAttendeeList().size();
        assertEquals("doesn't add attendee properly", 1, attendeeListSize);
        boolean digitUsername = userController1.createUser("3user3", "user3", UserType.ORGANIZER);
        assertFalse("Organiser created with digit in name", digitUsername);
        int organiserListSize2 = userController1.getUserManager().getOrganizerList().size();
        assertEquals("add to list when account not created", 2, organiserListSize2);
        boolean repeatUsername = userController1.createUser("user3", "user3", UserType.ORGANIZER);
        assertFalse("Duplicate username added", repeatUsername);
        int organiserListSize3 = userController1.getUserManager().getOrganizerList().size();
        assertEquals("add to list when account not created", 2, organiserListSize3);
        int userMapSize = userController1.getUserManager().getUserMap().size();
        assertEquals("doesn't add users properly", 4, userMapSize);
    }
    @Test(timeout = 50)
    // remember to change this test if creating account permissions are changed
    public void testCreateUserNotLoggedIn(){
        UserManager userManager = new UserManager();
        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
        UserController userController1 = new UserController(userManager);
        userController1.createUser("user2", "user2", UserType.ATTENDEE);
        int attendeeListSize = userController1.getUserManager().getAttendeeList().size();
        assertEquals("doesn't add attendee properly", 1, attendeeListSize);
        int userMapSize = userController1.getUserManager().getUserMap().size();
        assertEquals("doesn't add attendee properly", 2, userMapSize);
        boolean accountCreated = userController1.createUser("user3", "user3", UserType.ORGANIZER);
        assertFalse("Creates Organiser when not Logged in", accountCreated);
        boolean accountCreated2 = userController1.createUser("user3", "user3", UserType.SPEAKER);
        assertFalse("Creates Speaker when not Logged in", accountCreated2);
    }
    @Test(timeout = 50)
    // remember to change this test if creating account permissions are changed
    public void testCreateUserLoggedInAsAttendee(){
        UserManager userManager = new UserManager();
        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
        UserController userController1 = new UserController(userManager);
        userController1.createUser("user2", "user2", UserType.ATTENDEE);
        int attendeeListSize = userController1.getUserManager().getAttendeeList().size();
        assertEquals("doesn't add attendee properly", 1, attendeeListSize);
        int userMapSize = userController1.getUserManager().getUserMap().size();
        assertEquals("doesn't add attendee properly", 2, userMapSize);
        userController1.userLogin("user2", "user2");
        boolean accountCreated = userController1.createUser("user3", "user3", UserType.ORGANIZER);
        assertFalse("Creates Organiser as Attendee", accountCreated);
        boolean accountCreated2 = userController1.createUser("user3", "user3", UserType.SPEAKER);
        assertFalse("Creates Speaker as Attendee", accountCreated2);
    }
    // ====================== Presenter Tests =====================
    @Test(timeout = 50)
    public void testPresenterSendAndAcceptFriendRequest(){
        UserManager userManager = new UserManager();
        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
        userManager.createAccount("user2", "user2", UserType.ATTENDEE);
        userManager.createAccount("user3", "user3", UserType.ATTENDEE);
        User user1 = userManager.getUserByID(1);
        User user2 = userManager.getUserByID(2);
        User user3 = userManager.getUserByID(3);
        // Presenter presenter = new Presenter(); causes a weird error
        Presenter presenter = new Presenter(userManager);
        presenter.userLogin("user1", "user1");
        // send friend request by username
        presenter.sendFriendRequest("user2");
        // send friend request by ID
        presenter.sendFriendRequest("3");
        presenter.userLogOut();
        presenter.userLogin("user2", "user2");
        // accept friend request by username
        presenter.acceptFriendRequest("user1");
        int size1 = user1.getFriendsList().size();
        int size2 = user2.getFriendsList().size();
        assertEquals("Friend not added properly in user1", 1, size1);
        assertEquals("Friend not added properly in user2", 1, size2);
        presenter.userLogOut();
        presenter.userLogin("user3", "user3");
        // Accept friend request by ID
        presenter.acceptFriendRequest("1");
        int size3 = user1.getFriendsList().size();
        int size4 = user3.getFriendsList().size();
        assertEquals("Friend not added properly in user1", 2, size3);
        assertEquals("Friend not added properly in user3", 1, size4);
        boolean addRepeatUser = presenter.acceptFriendRequest("user1");
        assertFalse("can accept request from someone on list", addRepeatUser);
    }
    @Test(timeout = 50)
    public void testSendAndAcceptFriendRequestLargeIDs(){
        // already know this works by GUI test
        UserManager userManager = new UserManager();
        userManager.createAccount("user1", "user1", UserType.ATTENDEE);
        for (int i=2; i< 30; i++){
            userManager.createAccount("user" + i, "user"+i, UserType.ATTENDEE);
            // send friend requests to user1
            userManager.sendFriendRequest(i, 1);
        }
        User user1 = userManager.getUserByID(1); // should be user1
        int size1 = userManager.getUserMap().size();
        assertEquals("users not made successfully", 29, size1);
        Presenter presenter = new Presenter(userManager);
        assertTrue("Login unsuccessful", presenter.userLogin("user1", "user1"));
        presenter.acceptFriendRequest("29");
        presenter.acceptFriendRequest("20");
        presenter.acceptFriendRequest("14");
        int size2 = user1.getFriendsList().size();
        assertEquals("Friends not added properly by ID", 3, size2);
        presenter.acceptFriendRequest("user13");
        presenter.acceptFriendRequest("user12");
        presenter.acceptFriendRequest("user11");
        int size3 = user1.getFriendsList().size();
        assertEquals("Friends not added properly by name", 6, size3);
        presenter.acceptFriendRequest("29");
        presenter.acceptFriendRequest("user12");
        int size4 = user1.getFriendsList().size();
        assertEquals("repeat friends added", 6, size4);
    }



}
