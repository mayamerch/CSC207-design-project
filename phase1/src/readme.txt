Run main method in Boot.java to test the program.

If there is some sort of error relating to IOException or FileNotFoundException
(may occur with EventRoomGateway, BroadcastGateway, ChatroomGateway which save and read in data from .txt files)
please go look at the filepath param in the <new File("src/EventPackage/eventData.txt")> constructor.
Change all filepaths to phase1/src/EventPackage/eventData.txt to see if that works or change all filepaths to
src/EventPackage/eventData.txt... On my computer I have phase1 opened as the project in IntelliJ so filepaths start with
src/EventPackage/eventData.txt. If you have group_0039 opened as a project the filepath should start with phase1/src/...

Some group members have also come across a weird Scanner error in the main method where it doesn't wait for an input.
We currently don't know how to fix this because not everyone has this error. Usually rerunning the program works!

