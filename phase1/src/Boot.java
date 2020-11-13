import java.util.Scanner;

public class Boot {

    Scanner scanner = new Scanner(System.in);

    public Integer firstMenu(int input){
        if(input == 1){
            System.out.println("all events");
            return 1;
        }
        else if (input == 2){
            System.out.println("registered events");
            return 2;
        }
        else if (input == 3){
            System.out.println("messages");
            return 3;
        }
        else if (input == 4){
            System.out.println("broadcasts");
            return 4;
        }
        else{
            System.out.println("try again");
            return -1;
        }

    }

    public static void main(String[] args){
        Boot boot = new Boot();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Login");
        System.out.println("Enter your goddamn Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        // TODO: check if credentials are valid

        //Assuming password is correct
        System.out.println("What would you like to do?\n" +
                "1. View All Events\n" +
                "2. View Registered Events\n" +
                "3. Check Messages\n" +
                "4. Check Broadcasts\n" +
                "Please input a number: ");
        int i;
        do {
            int input = scanner.nextInt();
            i = boot.firstMenu(input);
        }
        while (i == -1);
        System.out.println("the menu chosen is "+ i);
        // take it to appropriate controller from here
    }
}
