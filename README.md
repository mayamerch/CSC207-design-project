# design-project

My term project for CSC207: Software Design at the University of Toronto, Fall 2020. Completed in collaboration with six other UofT undergraduate students.
My primary focus was the message package (phase2/src/MessagePackage), including functionality of sending/receiving a message, and the specificity of the different types of messages. I also worked on the message GUI, including design and functionality. 

To use the program, run RunPresenter2 (included in GUI package - phase2/src/GUI)
Login information for testing: (username,password)
To login as an Organizer: (user1, user1)
To login as a Speaker: (user2,user2)
To login as an Attendee: (user4, user4)
You can also create a new user as the Organizer.
(Note there are currently 30 sample user in userFile.ser that were created during our testing)




Notes:
You may ignore the phase1 folder and code, as it was optimized into phase2. 

For UserTest, you might find that the program won't run due to a missing import.
To resolve this, hover over [import static org.junit.Assert.*;] and press import
class path. I also pushed a new test on December 14.

For the gateways, if you are running into a file not found exception upon login
or logging out, you may need to insert "phase2/" before "src" or remove "phase2",
from the file path, depending on whether you have "group_0039" or "phase2" open.

The UserMap is designed to be saved automatically only when logging out in the GUI.

To save all changes made to users (creating accounts, changing VIPS, etc.) you need to log out. Closing the program
without saving doesn't save changes to users. (Events and messages still save)
Note that if you create an attendee without logging in, to save the new account you have to log in as a user
and then log out through the GUI to save it next time you run the program.

Features Implemented and Design Patterns are found in text files under the phase 2 folder.
UML Diagrams are in UML Diagrams folder under phase 2
