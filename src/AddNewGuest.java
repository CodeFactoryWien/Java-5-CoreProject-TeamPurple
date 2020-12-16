import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class AddNewGuest {

    static Scanner scanner = new Scanner(System.in);
    static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/hotel";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    Connection connection = null;

    public void addNewGuest()  {

        try {
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);

            System.out.println("Enter new Guest");

            System.out.println("Enter FirstName");
            String firstName = scanner.next();

            System.out.println("Enter lastname");
            String lastName = scanner.next();

            System.out.println("Enter address");
            String address = scanner.next();

            System.out.println("Enter Zip");
            String ZIP = scanner.next();

            System.out.println("Enter country code");
            String country = scanner.next();

            System.out.println("Enter email");
            String email = scanner.next();

            System.out.println("Enter birthdate in format yyyy-MM-dd");
            String birthDate = scanner.next();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date birth = format.parse(birthDate);
            java.sql.Date sqlBirth = new java.sql.Date(birth.getTime());

            System.out.println("Enter telephone");
            long telephone = scanner.nextLong();

            System.out.println("Enter document");
            String document = scanner.next();


            PreparedStatement ps = connection.prepareStatement("insert into guests (`first_name`,`last_name`,`adress`,`zip`,`country`,`email`,`birth`,`phone_number`,`document`)\n" +
                    "SELECT ?,?,?,?,?,?,?,?,? \n" +
                    "where ? not in (SELECT document from guests)");

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, address);
            ps.setString(4, ZIP);
            ps.setString(5, country);
            ps.setString(6, email);
            ps.setDate(7, sqlBirth);
            ps.setLong(8, telephone);
            ps.setString(9, document);
            ps.setString(10, document);

            int success = ps.executeUpdate();

            if (success >0) {
                System.out.println("Inserted in database !!!");

            } else {
                System.out.println("Guest with that document exists in database");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}