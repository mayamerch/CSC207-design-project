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

