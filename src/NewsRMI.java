import java.rmi.Remote;
import java.util.List;

/**
 *
 * @author miche
 */

public interface NewsRMI extends Remote {  
    
   public List<News> getNews() throws Exception;  
    
}