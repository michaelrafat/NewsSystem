import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author miche
 */

public class FetchNews implements NewsRMI {

    DatabaseUtils database = new DatabaseUtils();

    @Override
    public ArrayList<News> getNews() throws Exception {
        
        ArrayList<News> newsList = new ArrayList<News>();

        Connection conn = null;
        Statement stmt = null;

        Class.forName("com.mysql.jdbc.Driver");

        conn = DriverManager.getConnection(database.URL, database.USERNAME, database.PASSWORD);

        stmt = conn.createStatement();
        String sql = "SELECT * FROM news";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String title = rs.getString(4);
            String category = rs.getString(1);

            String date = rs.getString(3);
            String image = rs.getString(5);

            News news = new News(title, category, date, image);

            newsList.add(news);
        }
        rs.close();
        return newsList;
    }
}