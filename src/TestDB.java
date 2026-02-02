import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/stockdb?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";  
        String pass = "123456789"; 

        try {
            System.out.println("Connecting to MySQL...");
            try (Connection con = DriverManager.getConnection(url, user, pass)) {
                if (con.isValid(2)) {
                    System.out.println("SUCCESS! Connected to MySQL database.");
                } else {
                    System.out.println("Connection is not valid.");
                }
            }
        } 
        catch (SQLException e) {
            System.out.println("FAILED to connect!");
            e.getMessage();  
        }
    }
}
