import java.net.Inet4Address;
import java.util.Scanner;

public class Menu {
    private static Scanner scan = new Scanner(System.in);
    public Menu(DataAccess dataAccess){
        System.out.println("+------------------------------------+");
        System.out.println("|                                    |");
        System.out.println("|     Welcome to the school menu     |");
        System.out.println("|                                    |");
        System.out.println("+------------------------------------+");
        showMenu(dataAccess);
    }
    public void showMenu(DataAccess dataAccess){
        System.out.println("\nSelect from the following:\n" +
                "1) Display all students.\n" +
                "2) Display all teachers.\n" +
                "3) Display all classes.\n" +
                "4) Display courses of a specific teacher.\n" +
                "0) Quit");

        System.out.println("\nEnter your choice: ");
    }
}
