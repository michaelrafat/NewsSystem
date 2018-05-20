import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author miche
 */

public class PostNewsServer {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
     
    Socket clientSocket = null;
    ServerSocket serverSocket = null;
    NewsAdder news = new NewsAdder();
    ObjectInputStream is = null;
    ObjectInputStream ois;
    ObjectOutputStream oos = null;
    String newsData[] = new String[5];
    
    try{
        serverSocket = new ServerSocket(300); 
        System.out.println("server started....");
        clientSocket = serverSocket.accept();    
        
        oos = new ObjectOutputStream(clientSocket.getOutputStream());

        ois = new ObjectInputStream(clientSocket.getInputStream());
        
        newsData = (String[]) ois.readObject();
        
        System.out.println(newsData[1]);
         
    } catch(Exception e) {
          e.printStackTrace();
    }
    news.postNews(newsData[0], Integer.parseInt(newsData[1]), newsData[2], newsData[3], newsData[4]);        
    oos.writeObject("News Added!");
             
    }   
}