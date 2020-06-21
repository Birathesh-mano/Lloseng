// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import common.*;
import java.io.*;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 
  String loginId;

  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    this.loginId = loginId'
    openConnection();
  }

  
  //Instance methods ************************************************
    
  
  /**
   * Display message to Client when server is shuts down
   */
    public void connectionClosed() {
      clientUI.display("Server Connection Lost, Session Will Now Terminate.");
    }
    
  
  
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
    clientUI.display(msg.toString());
  }

  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(String message)
  {
	  if(message.startsWith("#") && message.length()!= 0) {
			 
		  
		  	if(message.equals("#quit")) {
		  		
		  		quit();
		  		
		  	}
		  
		  	else if(message.equals("#logoff")) {
		  		
		  		try {
					closeConnection();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  		
		  	}
		  
		  	else if(message.equals("#login")) {
		  		try {
					openConnection();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  	}
		  
		  	else if(message.equals("#gethost")) {
		  		clientUI.display(getHost());
		  	}
		  	
		  	
		  	else if(message.equals("#getport")) {
		  		clientUI.display(Integer.toString(getPort()));
		  	}
		  	
		  	else if(message.startsWith("#sethost")) {
		  		
		  		String hostVal = message.substring(9);
		  		int hostNum = Integer.parseInt(hostVal);
		  		
		  		if(!this.isConnected()) {
		  			setHost(hostVal);
		  		}
		  		else {
		  			
		  			clientUI.display("Error: Client is not logged off");
		  		}
		  		
		  		
		  	}
		  	else if(message.startsWith("#setport")) {
		  		
		  		String portVal = message.substring(9);
		  		int portNum = Integer.parseInt(portVal);
		  		
		  		if(!this.isConnected()) {
		  			setPort(portNum);
		  		}
		  		else {
		  			clientUI.display("Error: Client is not logged off");
		  		}
		  	}
	  }
	  
	  else {
		    try
		    {
		      sendToServer(message);
		    }
		    catch(IOException e)
		    {
		      clientUI.display
		        ("Could not send message to server.  Terminating client.");
		      quit();
		    }
	  }
  }
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class
