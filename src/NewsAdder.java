
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author miche
 */

public class NewsAdder {
    
    private DatabaseUtils database = new DatabaseUtils();
    
    public void postNews (String category, int categoryID, String date, String body, String image) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(database.URL, database.USERNAME, database.PASSWORD);

            String query = "INSERT INTO `news`(`category`,`category_id`,`date`,`body`, `image`) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, category);
            preparedStmt.setInt(2, categoryID);
            preparedStmt.setString(3, date);
            preparedStmt.setString(4, body);
            preparedStmt.setString(5, image);

            preparedStmt.execute();
            connection.close();
            
            System.out.println("Registration Done");

        } catch (Exception e) {
            e.printStackTrace();
        }   
    }   
}