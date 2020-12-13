Both the UserController and the Presenter have constructors that take in
a UserManager as well as a no argument constructor. Both of these were included
for testing purposes, since we did not want to use the no argument constructor for
User Controller since it would read off of UserFile.ser, and we wanted to test under
conditions that we did not want to "save" to UserFile.ser. However, when running
the program for demonstration, we used the no argument constructor for both.

For UserTest, you might find that the program won't run due to a missing import.
To resolve this, hover over [ import static org.junit.Assert.*; ] and press import
class path.

For the gateways, If you are running into a file not found exception upon login
or logging out, you may need to insert "phase2/" before "src" or remove "phase2",
from the file path, depending on whether you have "group_0039" or "phase2" open.

For VIP users, We interpreted the instructions as adding a VIP attribute that is either
true or false for Users of type Speaker, Organiser or Attendee. The UserType itself is
also an attribute of User.

The UserMap is designed to be saved automatically only when logging out in the GUI.

To use the program, run RunPresenter2 (included in GUI package)
Login information for testing: (username,password)
To login as an Organizer: (user1, user1)
To login as a Speaker: (user2,user2)
To login as an Attendee: (user4, user4)
You can also create a new user as the Organizer.
