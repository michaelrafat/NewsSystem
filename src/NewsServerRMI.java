import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author miche
 */

public class NewsServerRMI extends FetchNews {
    
    News[] newsArr;
    
    public NewsServerRMI() {} 
    
    public static void main(String args[]) { 
        try { 
            FetchNews obj = new FetchNews(); 
            
            NewsRMI stub = (NewsRMI) UnicastRemoteObject.exportObject(obj, 0);  

            Registry registry = LocateRegistry.getRegistry(); 
            
            registry.bind("NewsRMI", stub);  

            System.err.println("Server ready"); 
        } catch (Exception e) { 
            System.err.println("Server exception: " + e.toString()); 
            e.printStackTrace(); 
        } 
    } 
}