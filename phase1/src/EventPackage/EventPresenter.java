package EventPackage;

public class EventPresenter {


    public void printMenu(int UserPerm) {

        StringBuilder temp = new StringBuilder();

        temp.append("Event and Room Main Menu \n");
        temp.append("-----------------------------\n");

        temp.append("1. See all Events\n");
        temp.append("2. See my Events (Events attending or speaking at)\n");

        if (UserPerm != -1) {
            temp.append("3. See Events I can sign up for\n");
            temp.append("4. Attend a new Event\n");
            temp.append("5. Cancel attending an Event\n");
            if (UserPerm == 0) {
                temp.append("6. Create a new Event\n");
                temp.append("7. Create a new Room\n \n");
            }
        }

        temp.append("Please choose an option number.\n");
        System.out.println(temp);

    }
}