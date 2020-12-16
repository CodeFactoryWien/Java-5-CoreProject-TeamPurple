import java.net.Inet4Address;
import java.util.Scanner;

public class Menu {
    private static Scanner scan = new Scanner(System.in);
    public Menu(DataAccess dataAccess){
        System.out.println("+------------------------------------+");
        System.out.println("|                                    |");
        System.out.println("|     Welcome to hotel menu          |");
        System.out.println("|                                    |");
        System.out.println("+------------------------------------+");
        showMenu(dataAccess);
    }
    public void showMenu(DataAccess dataAccess){
        int n = -1;

        do {

            System.out.println("+++++++++++++++++++++++++++++++++++" + "\n");
            System.out.println("1. Display all Rooms");
            System.out.println("2. Display all Available Room");
            System.out.println("3. Display the guests that are booked in the rooms");
            System.out.println("4. Create new Guest");
            System.out.println("5. Create invoice");
            System.out.println("6. Create booking");
            System.out.println("0. Quit");
            System.out.println("++++++++++++++++++++++++++++++++++++" + "\n");
            try {
                Scanner user_input = new Scanner(System.in);
                System.out.println("Enter Your Choice");
                int x = user_input.nextInt();
                if (x >= 0 && x <= 6) {
                    switch (x) {
                        case 1: {
                            All_Rooms me = new All_Rooms();
                            me.showAll_Rooms();
                            break;
                        }
                        case 2: {
                            AllAvailableRoom me = new AllAvailableRoom();
                            me.showAllAvailableRoom();
                            break;
                        }
                        case 3: {
                            DataAccess me = new DataAccess();
                            me.displayAllGuests();
                            break;
                        }
                        case 4: {
                            AddNewGuest me = new AddNewGuest();
                            me.addNewGuest();
                            break;
                        }
                        case 5: {
                            WorkInvoice.createInvoice();
                            break;
                        }
                        case 6: {
                            AddNewBooking.addNewBooking();
                            break;
                        }
                        case 0: {
                            System.out.println("Exit");
                            n=-2;
                            break;
                        }
                    }
                } else {

                    System.out.println("\"Enter Valid number from 0-6 \"");
                }
            } catch (Exception e) {

                System.out.println("Enter numeric value(number from 0-6)");
            }

        } while (n == -1);
        System.out.println("Thanks bye");
    }
}
