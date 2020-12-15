import java.sql.*;
import java.util.Scanner;

public class  inputUSERNameAndPassword{
    static Scanner scanner = new Scanner(System.in);
    static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/hotel";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    Connection connection = null;



    public void showInputUSERNameAndPassword() {

        try {
            Class.forName(JDBC_Driver);

            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);




            try {
                System.out.println("Enter username");
                Scanner scc = new Scanner(System.in);

                String inputUser= scc.next();
                System.out.println("Enter password");

                String inputPassword= scc.next();
                PreparedStatement passWordDatabase = connection.prepareStatement("SELECT staff.password FROM staff");
                ResultSet rs = passWordDatabase.executeQuery();
                PreparedStatement usernameDatabase = connection.prepareStatement("SELECT staff.username FROM staff");
                ResultSet rs1 = usernameDatabase.executeQuery();

                if(inputUser.equals(usernameDatabase) && inputPassword.equals(passWordDatabase)){
                    while (rs.next()&&rs1.next()) {

                        System.out.println("wellcome");
                    }
                } else {
                    System.out.println("\"Enter Valid username and password");
                }
            }catch (Exception e) {
                System.out.println("Enter numeric value(username and password");
            }
            connection.close();


        } catch (SQLException sql) {
            sql.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        inputUSERNameAndPassword in= new inputUSERNameAndPassword();
        in.showInputUSERNameAndPassword();
    }

}
