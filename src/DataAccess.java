import javax.security.auth.Subject;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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



    public void closeDB() throws SQLException {
        System.out.println("Closing connection...");
        connection.close();
    }
}
