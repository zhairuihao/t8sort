package cn.com.t8sort.javabasic;

import java.net.*;
import java.io.*;
 
/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2017年2月9日 下午5:29:14 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class JavaClient {

	   public static void main(String [] args)
	   {
	      String serverName = args[0];
	      int port = Integer.parseInt(args[1]);
	      try
	      {
	         System.out.println("Connecting to " + serverName
	                             + " on port " + port);
	         Socket client = new Socket(serverName, port);
	         System.out.println("Just connected to "
	                      + client.getRemoteSocketAddress());
	         OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out =
	                       new DataOutputStream(outToServer);
	 
	         out.writeUTF("Hello from "
	                      + client.getLocalSocketAddress());
	         InputStream inFromServer = client.getInputStream();
	         DataInputStream in =
	                        new DataInputStream(inFromServer);
	         System.out.println("Server says " + in.readUTF());
	         client.close();
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
	   }
	}
	
