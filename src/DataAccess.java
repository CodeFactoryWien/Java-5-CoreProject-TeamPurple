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
    ArrayList <DisplayGuests> guestList = getAllGuests();
    System.out.println("First Name: / Last Name: / Address: / Zip-Code: / Country: / Email: / Birth Date: / Phone Number: / Document: / Arrival Date: / Depature Date: ");
    for (int i = 0; i < guestList.size(); i++) {
        System.out.println(guestList.get(i).getFirstName() + " / " + guestList.get(i).getLastName() + " / " + guestList.get(i).getAddress() + " / " + guestList.get(i).getZip() + " / " + guestList.get(i).getCountry() + " / " + guestList.get(i).getEmail() + " / " + guestList.get(i).getBirth() + " / " + guestList.get(i).getPhoneNumber() + " / " + guestList.get(i).getDocument() + " / " +  guestList.get(i).getArrival() + " / " +  guestList.get(i).getDepature());
    }
}

    public void closeDB() throws SQLException {
        System.out.println("Closing connection...");
        connection.close();
    }
}
