// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port) 
  {
    super(port);
  }

  
  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {
	  String clientMsg = msg.toString();

		 
		 if(clientMsg.startsWith("#login <")) {
			 if(client.getInfo("loginId")==null){
				 String clientLoginId = clientMsg.substring(8);
				 client.setInfo("loginId", clientLoginId);
			 }
			 else {
				 System.out.println("Error: client is already logged in.");
			 }
		 }
		// else {
		  
	    System.out.println("Message received: " + msg + " from " + client +" - " + client.getInfo("loginId"));
	    this.sendToAllClients("Client Login ID - "+ client.getInfo("loginId") +" : " + msg);
		// }
  }
    
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }
  
  
  
  public void clientConnected(ConnectionToClient client) {
	    System.out.println("A client has connected");
	  }


  
  public void clientDisconnected(ConnectionToClient client) {
		      System.out.println("A client has disconnected");
		    }
  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of 
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555 
   *          if no argument is entered.
   */
  public boolean handleMessageFromServerUI(String message)
  {
	boolean command = true;
	  	if(message.equals("#quit")) {
		  		
		  		System.exit(0);
		  		
		  	} 
		  	
		  	else if(message.equals("#stop")) {
		  		
		  			stopListening();	
		  	}
		  
		  	else if(message.equals("#close")) {
		  		
		  			try {
						close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		  	}
		  
		  	else if(message.equals("#start")) {
		  			try {
						listen();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		  	}
		  	else if(message.equals("#getport")) {
		  	 System.out.println("Port: " + getPort());
		  	}
		  	else if(message.startsWith("#setport")) {
		  		String portVal = message.substring(9);
		  		int portNum = Integer.parseInt(portVal);
		  		
		  		if(!this.isListening()) {
		  			this.setPort(portNum);
		  		}
		  		else {
		  			System.out.println("Error: close the server first");
		  		}
		  		
		  		
		  	}
		  	else {
		  		command = false;
		  	}
		  
	  	return command;
  }
//  public static void main(String[] args) 
//  {
//    int port = 0; //Port to listen on
//
//    try
//    {
//      port = Integer.parseInt(args[0]); //Get port from command line
//    }
//    catch(Throwable t)
//    {
//      port = DEFAULT_PORT; //Set port to 5555
//    }
//	
//    EchoServer sv = new EchoServer(port);
//    
//    try 
//    {
//      sv.listen(); //Start listening for connections
//    } 
//    catch (Exception ex) 
//    {
//      System.out.println("ERROR - Could not listen for clients!");
//    }
//  }
}
//End of EchoServer class
