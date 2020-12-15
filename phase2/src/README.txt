Both the UserController and the Presenter have constructors that take in
a UserManager as well as a no argument constructor. Both of these were included
for testing purposes, since we did not want to use the no argument constructor for
User Controller since it would read off of UserFile.ser, and we wanted to test under
conditions that we did not want to "save" to UserFile.ser. However, when running
the program for demonstration, we used the no argument constructor for both. RunPresenter
uses the Presenter constructor that takes UserManager as an argument, so we could run independently
of UserFile.ser, and RunPresenter2 uses the no argument constructor for Presenter.


For UserTest, you might find that the program won't run due to a missing import.
To resolve this, hover over [ import static org.junit.Assert.*; ] and press import
class path. I also pushed a new test on December 14.

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
(Note there are currently 30 sample user in userFile.ser that were created during our testing)

Note that if you create an attendee without logging in, to save the new account you have to log in as a user
and then log out through the GUI to save it next time you run the program.

Features Implemented and Design Patterns are found in text under the phase 2 folder.
UML Diagrams are in UML Diagrams folder under phase 2

Error: To change VIP Status of Users Through the GUI, made fixes: added this.presenter = presenter should
be added to the VIPMenuView constructor (new line at around line 26). Then, on line 42 and 32, changed
change String userInput = usernameIDLabel.getText(); to String userInput = textField1.getText(); on December 13.

Error: In Lines 527, 610 in EventPackage.EventOuterLayer.EventPresenter, the variable 'EventName' in these line should be changed to 'name'.
Fixed this on 13-12-2020

Small Change on 13-12-2020: We had a useless interface name SpeakerEvents in EventPackage.Entities.
Deleted this interface and any of its mentions in other classes

Small Change on 13-12-2020: Removed old Boot class from phase 1

Small Change on 13-12-2020: Fixed saving error for Speaker Events