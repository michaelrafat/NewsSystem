import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author miche
 */

public class LoginServer {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
     
    Socket clientSocket = null;
    ServerSocket serverSocket = null;
    Login login = new Login();
    ObjectInputStream is = null;
    ObjectInputStream ois;
    ObjectOutputStream oos = null;
    String loginData[] = new String[2];
    
    try{
        serverSocket = new ServerSocket(100); 
        System.out.println("server started....");
        clientSocket = serverSocket.accept();    
        
        oos = new ObjectOutputStream(clientSocket.getOutputStream());

        ois = new ObjectInputStream(clientSocket.getInputStream());
        
        loginData = (String[]) ois.readObject();
        
        System.out.println(loginData[0]);
         
    } catch(Exception e) {
          e.printStackTrace();
    }
      if(login.login(loginData[0], loginData[1])){
        
          oos.writeObject(true);
        System.out.println("Login Successful"); 
        
        oos.flush();
      } else {
          System.out.println("User Not Found!"); 
          oos.writeObject(false);
      }            
    }
    
}
