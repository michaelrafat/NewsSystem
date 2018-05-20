import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

/**
 * @author miche
 */

public class NewsClient {
    
    private static List<News> newsList = new ArrayList<>();
    
    private static Socket clientSocket;
    private static ServerSocket serverSocket;
    private static ObjectInputStream is;
    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;

    private NewsClient() {
    }

    public static void main(String[] args) throws Exception {

        try {

            Registry registry = LocateRegistry.getRegistry();

            NewsRMI stub = (NewsRMI) registry.lookup("NewsRMI");

            newsList = (List) stub.getNews();

            for (News news : newsList) {

                System.out.println("Title: " + news.getTitle());
                System.out.println("Category: " + news.getCategory());
                System.out.println("Date: " + news.getDare());
                //System.out.println("Image: " + news.getImage());

            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
        
        try{
                FileOutputStream fos= new FileOutputStream("NewsSerialization.txt");
                ObjectOutputStream oos= new ObjectOutputStream(fos);
                
                for (int i = 0; i < newsList.size(); i++){
                oos.writeObject(newsList.get(i));
                }
                oos.close();
                fos.close();
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        
        
    
        try{
            serverSocket = new ServerSocket(500); 
            System.out.println("server started....");
            clientSocket = serverSocket.accept();    
            
            ByteArrayOutputStream bao = new ByteArrayOutputStream(clientSocket.getLocalPort());

            oos = new ObjectOutputStream(bao);

            ois = new ObjectInputStream(clientSocket.getInputStream());
            
            oos.reset();

            oos.writeObject("News");
            oos.flush();
                        
            oos.close();
            ois.close();
            clientSocket.close();
        } catch(Exception e) {
            e.printStackTrace();
        }          
    }
}