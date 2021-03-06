//Editor: Gabriella
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkInvoice {
    int id;
    LocalDateTime iDate;
    int fk_bookings_id;
    int fk_stuff_id;

    public WorkInvoice(int id, LocalDateTime iDate, int fk_bookings_id, int fk_stuff_id) {
        this.id = id;
        this.iDate = iDate;
        this.fk_bookings_id = fk_bookings_id;
        this.fk_stuff_id = fk_stuff_id;
    }

   /* public static void listAllInvoices(){

    }*/



    public static void createInvoice(){
        MyBooking bookingObj=askBookingId();
        if (bookingObj == null) {
            return;
        }
        // next method controls if an invoice to this booking exist
        int invoiceFoundOption= MyBooking.checkInvoice(bookingObj.id);  //0=not found 1=found 9=missing connection
        if (invoiceFoundOption==1) {
            System.out.println("There is already an Invoice for this Booking!");
            return;
        } else {
            if (invoiceFoundOption == 9) {  //we could not connect the database
                return;
            }else{
                System.out.println("Invoice will be created for following booking:");
                System.out.println("Booking: "+bookingObj.id+"   Guest: "+bookingObj.surName.toUpperCase()+
                        " "+bookingObj.lastName.toUpperCase()+"   Room number: "+bookingObj.roomNumber+ "   Amount: "+bookingObj.total+ " EUR");
                if(!invoiceGenerator(bookingObj)){
                    System.out.println("I could not generate the Invoice, check your Data Base connection!");
                }
            }
        }
        return;
    }


    public static MyBooking askBookingId(){

        Scanner scanner = new Scanner(System.in);
        int selection;
        boolean exit = false;
        MyBooking bookingObj = null;
        while (!exit) {
            System.out.println("Enter booking ID: ");
            try {
                selection = Integer.parseInt(scanner.nextLine());
                if (selection==0){   //user want to get out
                    return null;
                }
                bookingObj = MyBooking.checkBookingId(selection);
                if (bookingObj != null ) {
                    //There was a connection!
                    if(bookingObj.id==0) {
                        System.out.println("Not existing Booking Id, try to enter again, or add 0 to exit");
                        exit=false;
                    }else{
                        exit = true;
                    }

                } else {//no connection
                    // System.out.println("There is no connection to the DataBase! ");
                    return null;
                }

            } catch (Exception e) {
                System.out.println("Please Enter one of the booking Ids, only numbers are allowed!");
            }
        }
        return bookingObj;
    }



    /**
     * generates WorkInvoice   the name of the file is: invoice_X.txt, where X=bookingId
     *
     */
    public static boolean invoiceGenerator(MyBooking bookingObj) {

        String path="C:\\Test";
        String pathAndFileName = path+"\\invoiceErr.txt";
        String filename;
        String pathNew="C:\\Test";

        Scanner scanner = new Scanner(System.in);

        System.out.println("the report will be generated: "+path);

        String choice = askingYesOrNo("Do you want to change this? Y/N");
        boolean exit;
        exit= !choice.equals("Y");
        File file1;

        while(!exit) {
            System.out.println("Old Path: " + path + "   Please enter new Path: ");
            pathNew = scanner.nextLine();

            file1 = new File(pathNew);
            if (file1.exists()) {
                exit = true;
                pathAndFileName=pathNew+"\\invoiceErr.txt";
                path=pathNew;
                System.out.println("Filepath is accepted!" + pathNew);
            } else {
                if(pathNew.equals("")){
                    System.out.println("Empty file path, process finished!");
                    System.out.println();
                    return false;
                }
                exit = false;
                System.out.println("NOT existing filepath!" + pathNew);
            }

        }
        Connection con;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/hotel?useTimezone=true&serverTimezone=UTC";
            con = DriverManager.getConnection(
                    url, "root", "");
            con.setAutoCommit(false);
            con.setReadOnly(false);

            String query1 = "insert into invoice (fk_bookings_id,fk_staff_id) values (?,?)";
            PreparedStatement pst1=con.prepareStatement(query1);
            pst1.setInt(1,bookingObj.id);
            pst1.setInt(2,bookingObj.fk_staff_id);
            pst1.executeUpdate();
            con.commit();
            pst1.close();
            String query2="SELECT  * from invoice where fk_bookings_id=" + bookingObj.id;
            PreparedStatement pst2=con.prepareStatement(query2);
            ResultSet rs = pst2.executeQuery();
            boolean invoiceFound=rs.next();
            int invoiceId=0;
            java.sql.Date invoiceDate=null;
            if(invoiceFound){
                invoiceId = rs.getInt("id");
                invoiceDate = rs.getDate("date");
                System.out.println("Invoice Id: "+invoiceId+"  Invoice Date: "+invoiceDate);
                filename="invoice"+bookingObj.id;
                pathAndFileName=path+"\\"+filename+".txt";
            }else{
                System.out.println("There is a big big technical problem, connect please the system Administrator");
            }
            con.commit();
            pst2.close();
            con.close();
            FileWriter file = new FileWriter(pathAndFileName);
            file.write("\n**************************************************************************" + System.lineSeparator());
            file.write("\n                   Like in Your Dream Hotel "+System.lineSeparator());
            file.write( "\n***************************************************************************" + System.lineSeparator());
            file.write( "\n\n Invoice number: " + invoiceId + " Date: " + invoiceDate + System.lineSeparator());
            file.write( "\n\n Guest: " + bookingObj.surName.toUpperCase() + "  "+bookingObj.lastName.toUpperCase() + System.lineSeparator());
            file.write( "\n Room:       " + bookingObj.roomNumber  + System.lineSeparator());
            file.write( " Booking Id: " + bookingObj.id  + System.lineSeparator());
            file.write( " Arrival:    " + bookingObj.arrival  + System.lineSeparator());
            file.write( " Departure:  " + bookingObj.departure  + System.lineSeparator());

            file.write( "\n\n Total amount to pay: " + bookingObj.total  + System.lineSeparator());
            file.write("\n\n Above amount is inclusive 10% value added tax");
            file.write("\n\n\n\n\n----------------------------------------------------------------------------");
            file.write("\nLike in Your Dream Hotel, 2100, Nassfeld, Wald Straße 22. Id:984398494974339");
            file.close();
            System.out.println("\nSuccessfully wrote report to: "+pathAndFileName);
            System.out.println();

        } catch(IOException e){
            System.out.println("An error occurred during writing into the file.");
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println("The database is not available! Check your environment and start again!");
            e.printStackTrace();
        }
        return true;
    }

    /**
     *
     * @return arrylist with null: not succeed, succeed:arraylist wit two items from and to date
     */
    public static ArrayList<String> askPeriod(){
        ArrayList<String> period=null;
        boolean exit=false;
        String fromDate = null;
        String toDate = null;
        System.out.println("Enter date of arrival and date of departure in format YYYY-MM-dd");
        Scanner sc = new Scanner(System.in);
        while(!exit) {
            System.out.println("From date:\n");
            fromDate = sc.next();
            System.out.println("To date:\n");
            toDate = sc.next();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                java.util.Date DateFrom = format.parse(fromDate);
                java.util.Date DateTo = format.parse(toDate);
                period.add(fromDate);
                period.add(toDate);
                exit=true;
            } catch (ParseException e) {
                System.out.println("Wrong date format! Try it again");
                // e.printStackTrace();
            }
        }
        return period;
    }
    /**
     *
     * @param question this is the text,the question to answer with yes or no
     * @return it gives back Y or N in form of a String object
     */
    public static String askingYesOrNo(String question){
        String choice="";
        Scanner scanner = new Scanner(System.in);
        boolean exit=false;
        while (!exit){
            System.out.println(question);
            choice=scanner.nextLine();
            choice=choice.toUpperCase();
            if (choice.equals("N") || choice.equals("Y")  ){
                exit=true;
            }else{
                System.out.println("Wrong input, can only be Y/N.");
            }
        }
        return choice.toUpperCase();
    }

    public static Connection init(){
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver is not available!");
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/hotel?useTimezone=true&serverTimezone=UTC";
        try {
            con = DriverManager.getConnection(url,"root", "");
            con.setAutoCommit(false);
            con.setReadOnly(false);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    public static void stop(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getiDate() {
        return iDate;
    }

    public void setiDate(LocalDateTime iDate) {
        this.iDate = iDate;
    }

    public int getFk_bookings_id() {
        return fk_bookings_id;
    }

    public void setFk_bookings_id(int fk_bookings_id) {
        this.fk_bookings_id = fk_bookings_id;
    }

    public int getFk_stuff_id() {
        return fk_stuff_id;
    }

    public void setFk_stuff_id(int fk_stuff_id) {
        this.fk_stuff_id = fk_stuff_id;
    }

    @Override
    public String toString() {
        return "WorkInvoice{" +
                "id=" + id +
                ", iDate=" + iDate +
                ", fk_bookings_id=" + fk_bookings_id +
                ", fk_stuff_id=" + fk_stuff_id +
                '}';
    }
}


