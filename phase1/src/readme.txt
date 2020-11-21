Run main method in Boot.java to test the program.
You should open phase1 as a project in IntelliJ to do this not group_0039.

(1)
If there is some sort of error relating to IOException or FileNotFoundException
(may occur with EventRoomGateway, BroadcastGateway, ChatroomGateway which save and read in data from .txt files)
go look at filepath param in <new File("src/EventPackage/eventData.txt")> constructor in each of those Gateway classes.
Change all filepaths to phase1/src/EventPackage/eventData.txt to see if that works or change all filepaths to
src/EventPackage/eventData.txt...
On my computer I have phase1 opened as the project in IntelliJ so filepaths start with
src/EventPackage/eventData.txt.
If you have the file group_0039 opened as a project the filepath should start with phase1/src/...
If you are running this from the terminal you might have to go in the Gateways and enter path from Root.


(2)
Other errors from EventRoomGateway, BroadcastGateway, ChatroomGateway may be solved by simply deleting everything in
the respective .txt files (so that they are empty). This error may be simply due to some whitespace characters
entered accidentally by someone.


(3)
Some group members have also come across a weird Scanner error in the main method where it doesn't wait for an input.
We currently don't know how to fix this because not everyone has this error. Usually rerunning the program works!


(4)
EventTest is fully functional and you can test the EventPackage from there as well.


(5)
TestChatroomGateway and TestMessagePackage is obsolete and tests an older version of the classes in MessagePackage.
Please Ignore them, thanks!

