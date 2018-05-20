import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author miche
 */

class Register {

    private DatabaseUtils database = new DatabaseUtils();
    private boolean user;
    
    public boolean registerServer (String name, String email, String password, String mobile){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection =DriverManager.getConnection(database.URL, database.USERNAME, database.PASSWORD);

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM user WHERE email = '" + email + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            user = resultSet.next();

            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (!user) {
            registrationTask(name, email, password, mobile);
            return true;
        } else {
            System.out.println("user Exists!");
            return false;
        }
    }

    public void registrationTask (String name, String email, String password, String mobile) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(database.URL, database.USERNAME, database.PASSWORD);

            String query = "INSERT INTO `user`(`name`, `email`, `password`,`type`, `mobile`) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, email);
            preparedStmt.setString(3, password);
            preparedStmt.setInt(4, 0);
            preparedStmt.setString(5, mobile);

            preparedStmt.execute();
            connection.close();
            
            System.out.println("Registration Done");

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}