import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RegistrationServer {
    
    private static Socket clientSocket;
    private static ServerSocket serverSocket;
    private static Register register = new Register();
    private static ObjectInputStream is;
    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;
    private static String registerData[] = new String[4];

 public static void main(String[] args) throws IOException, ClassNotFoundException {
     
    new Thread() {
        @Override
        public void run() {
                
                try{
                    serverSocket = new ServerSocket(4444); 
                    System.out.println("server started....");
                    clientSocket = serverSocket.accept();    

                    oos = new ObjectOutputStream(clientSocket.getOutputStream());

                    ois = new ObjectInputStream(clientSocket.getInputStream());

                    registerData = (String[]) ois.readObject();

                    System.out.println(registerData[1]);

                } catch(Exception e) {
                    e.printStackTrace();
                }
                
                try {
                    if(register.registerServer(registerData[0], registerData[1], registerData[2], registerData[3])){
                        oos.writeObject("registtartion done"); 
                        oos.flush();
                    } else {
                        oos.writeObject("Error"); 
                    } 
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }}.start();              
    }        
}