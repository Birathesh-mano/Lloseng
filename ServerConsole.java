import java.io.*;
import common.*;

public class ServerConsole implements ChatIF {

    EchoServer sv;
    int port;

    
/**
 * constructor for server console
 * @param port
 */
    public ServerConsole(int port){

      this.port = port;

        try{
            sv = new EchoServer(port, this);
            sv.listen();
        }
        catch(IOException e){
            System.out.println("Error with server");
        }

    }
/**
 * show message
 * @param message
 */

    public void display(String message){
        System.out.println(message);
    }
    /**
     * take in server input
     */
    public void accept() 
    {
      try
      {
        BufferedReader fromServer = 
          new BufferedReader(new InputStreamReader(System.in));
        String message;
  
        while (true) 
        {
          message = fromServer.readLine();
          
          if(message.startsWith("#") && message.length()!= 0) {
       		 
        	  if(!sv.handleMessageFromServerUI(message)) {
        		  message = "SERVER MSG> " + message;
                  display(message);
                  sv.sendToAllClients(message);
        	  }
          }
          else {
        	  
          message = "SERVER MSG> " + message;
          display(message);
          sv.sendToAllClients(message);
          }
        }
      } 
      catch (Exception ex) 
      {
        System.out.println("Unexpected error while reading from server!");
      }
    }
  

    //Class methods *************************************************
    
    public static void main(String[] args) 
    {
      int DEFAULT_PORT = 5555;
      int port = 0; 

      try
      {
        port = Integer.parseInt(args[0]); 
      }
      catch(Throwable t)
      {
        port = DEFAULT_PORT; 
      }

      ServerConsole chat = new ServerConsole(port);
      chat.accept(); 
    }
}