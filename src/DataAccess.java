import javax.security.auth.Subject;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

public class DataAccess {
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/hotel" + "?useTimezone=true&serverTimezone=UTC";

    public DataAccess() throws SQLException, ClassNotFoundException {
        java.lang.Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Connecting to database...");
        SECRET secret = new SECRET();
        connection = DriverManager.getConnection(url, secret.getUser(), secret.getPassword());
        connection.setAutoCommit(true);
        connection.setReadOnly(false);
    }
    public ArrayList<DisplayGuests> getAllGuests() {
        String sql = "SELECT * FROM `bookings` LEFT JOIN guests on fk_guest_id = guests.id WHERE CURDATE() >= arrival_date AND CURDATE() <= departure_date;";
        ArrayList <DisplayGuests> guestList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String address = rs.getString("adress");
                String zip = rs.getString("zip");
                String country = rs.getString("country");
                String email = rs.getString("email");
                java.sql.Date birth = rs.getDate("birth");
                int phoneNumber = rs.getInt("phone_number");
                String document = rs.getString("document");
                Date arrival = rs.getDate("arrival_date");
                Date depature = rs.getDate("departure_date");

                guestList.add(new DisplayGuests(id, firstName, lastName, address, zip, country, email, birth, phoneNumber, document, arrival, depature));
            }
            rs.close();
            preparedStatement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return guestList;
    }

public void displayAllGuests(){
    ArrayList <DisplayGuests> studentList = getAllGuests();
    System.out.println("First Name: / Last Name: / Address: / Zip-Code: / Country: / Email: / Birth Date: / Phone Number: / Document: / Arrival Date: / Depature Date: ");
    for (int i = 0; i < studentList.size(); i++) {
        System.out.println(studentList.get(i).getFirstName() + " / " + studentList.get(i).getLastName() + " / " + studentList.get(i).getAddress() + " / " + studentList.get(i).getZip() + " / " + studentList.get(i).getCountry() + " / " + studentList.get(i).getEmail() + " / " + studentList.get(i).getBirth() + " / " + studentList.get(i).getPhoneNumber() + " / " + studentList.get(i).getDocument() + " / " +  studentList.get(i).getArrival() + " / " +  studentList.get(i).getDepature());
    }
}
/*
    public ArrayList<DisplayGuests> createGuest(){
        ArrayList <DisplayGuests> guestList = new ArrayList<>();


        System.out.println("type max Users");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();

        System.out.println("fdkajf");
        String firstName = in.nextLine();

        System.out.println("type ticket price");
        String lastName = in.nextLine();

        System.out.println("type price pool");
        String address = in.nextLine();

        System.out.println("type event name");
        String asdf = in.nextLine();  //for some reason it doesnt work without this unused line
        String zip = in.nextLine();

        System.out.println("type number of players");
        String country = in.nextLine();

        System.out.println("type number of players");
        String email = in.nextLine();

        System.out.println("type event date(please enter this format DD/MM/YYYY)");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        String date = in.nextLine();

        System.out.println("type number of players");
        int phoneNumber = in.nextInt();

        System.out.println("asdf");
        String document = in.nextLine();

        guestList.add(new DisplayGuests(id, firstName, lastName, address, zip, country, email, date, phoneNumber, document));

        return guestList;
    }
*/

    public void closeDB() throws SQLException {
        System.out.println("Closing connection...");
        connection.close();
    }
}
