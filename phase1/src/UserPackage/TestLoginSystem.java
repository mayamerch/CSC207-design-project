//package UserPackage;
//
//import EventPackage.Event;
//import EventPackage.EventManager;
//
//import java.util.Scanner;
//
//public class LoginSystem {
//    Scanner scanner1 = new Scanner(System.in);
//
//   public UserManager createUserManager(){
//       return new UserManager();
//    }
//
//
//    public EventManager createEventManager(){
//       return new EventManager();
//    }
//    public UserController controllerCreator(User currentUser, UserManager userManager, EventManager eventManager, int currentUserID){
//       if (currentUser.getType() == 'A'){
//           AttendeeController attendeeController = new AttendeeController(userManager, eventManager, currentUserID);
//           return attendeeController;
//           }
//       else if (currentUser.getType() == 'O'){
//           OrganiserController organiserController = new OrganiserController(userManager, eventManager, currentUserID);
//           return organiserController;
//       }
//       else {
//           SpeakerController speakerController = new SpeakerController(userManager, eventManager, currentUserID);
//           return speakerController;
//       }
//    }
//
//    public void UserLogin() {
//       System.out.println();
//        System.out.println("Enter Username");
//        String username = scanner1.nextLine();
//        System.out.println("Enter Password");
//        String password = scanner1.nextLine();
//        UserManager userManager = createUserManager();
//        EventManager eventManager = createEventManager();
//        int potentialID = userManager.validateLogin(username, password);
//        if (potentialID >= 0 ) {
//            int currentUserId = potentialID;
//            User currentUser = userManager.getUserByID(currentUserId);
//            System.out.println("Login Successful");
//            controllerCreator(currentUser, userManager, eventManager, currentUserId);
//
//        } else {
//            System.out.println("Invalid email or password");
//        }
//    }
//}

