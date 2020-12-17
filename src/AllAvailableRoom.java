import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
public class AllAvailableRoom {
    static Scanner scanner = new Scanner(System.in);
    static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/hotel";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    Connection connection = null;
    public void showAllAvailableRoom() {
        try {
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            String fromDate = null;
            String toDate = null;
            System.out.println("Enter date of arrival and date of departure in format YYYY-MM-dd");
            Scanner sc = new Scanner(System.in);
            fromDate = sc.next();
            toDate = sc.next();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date DateFrom = format.parse(fromDate);
            java.sql.Date sqlDateFrom = new java.sql.Date(DateFrom.getTime());
            java.util.Date DateTo = format.parse(toDate);
            java.sql.Date sqlDateTo = new java.sql.Date(DateTo.getTime());
            PreparedStatement ps = connection.prepareStatement("select rr.id, cc.name, cc.capacity, cc.price, cc.roomsize from rooms as rr \n" +
                    "inner join category as cc on cc.id=rr.fk_category_id\n" +
                    "where rr.id not in(SELECT bb.fk_room_id from bookings as bb where bb.departure_date>=? and bb.arrival_date<=?)");
            ps.setDate(1, sqlDateFrom);
            ps.setDate(2, sqlDateTo);
            ResultSet rs = ps.executeQuery();
            System.out.println("-----available rooms -----");
            if (rs.next())
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") + " " + rs.getString("name") + " price: " + rs.getFloat("price"));
                }
            else System.out.println("Sorry, there are no available rooms.");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}