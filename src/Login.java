import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author miche
 */
public class Login {
    
    private DatabaseUtils database = new DatabaseUtils();
    private boolean user;
    
    public boolean login(String email, String password) {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(database.URL, database.USERNAME, database.PASSWORD);

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM `user` WHERE email = '" + email + "' AND password = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            user = resultSet.next();

            connection.close();
            
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        if (user) {
                System.out.println("Login Successful");
                return true;
            } else {
                System.out.println("User Not Found");
                return false;
            }
    }   
}