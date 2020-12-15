import java.sql.*;
import java.util.Scanner;

public class All_Rooms {

        static Scanner scanner = new Scanner(System.in);
        static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
        static final String url = "jdbc:mysql://localhost:3306/hotel";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "";

        Connection connection = null;

        public void showAll_Rooms() {

            try {
                Class.forName(JDBC_Driver);

                connection = DriverManager.getConnection(url, USERNAME, PASSWORD);

                PreparedStatement ps = connection.prepareStatement("SELECT * FROM rooms left Join category on fk_category_id = category.id;");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    System.out.println("ID: "+rs.getInt("id") + " "+rs.getString("room_number")+" price: "+rs.getFloat("price"));
                    System.out.println("______________________");
                }
                connection.close();


            } catch (SQLException sql) {
                sql.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }


