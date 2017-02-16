package cn.com.t8sort.javabasic;

import java.net.*;
import java.io.*;
/** 
 * @author  ���� E-mail: 
 * @date ����ʱ�䣺2017��2��9�� ����5:22:12 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class JavaServer extends Thread{

	
	 
	   private ServerSocket serverSocket;
	   
	   public JavaServer(int port) throws IOException
	   {
	      serverSocket = new ServerSocket(port);
	      serverSocket.setSoTimeout(90000);
	   }
	 
	   public void run()
	   {
	      while(true)
	      {
	         try
	         {
	            System.out.println("Waiting for client on port " +
	            serverSocket.getLocalPort() + "...");
	            // accept() �������÷�����һֱ�ȴ���ֱ���ͻ������ӵ��������ϸ����Ķ˿ڡ�
	            Socket server = serverSocket.accept();
	            System.out.println("Just connected to "
	                  + server.getRemoteSocketAddress());
	            DataInputStream in =
	                  new DataInputStream(server.getInputStream());
	            System.out.println(in.readUTF());
	            DataOutputStream out =
	                 new DataOutputStream(server.getOutputStream());
	            out.writeUTF("Thank you for connecting to "
	              + server.getLocalSocketAddress() + "\nGoodbye!");
	            server.close();
	         }catch(SocketTimeoutException s)
	         {
	            System.out.println("Socket timed out!");
	            break;
	         }catch(IOException e)
	         {
	            e.printStackTrace();
	            break;
	         }
	      }
	   }
	   public static void main(String [] args)
	   {
	      int port = Integer.parseInt(args[0]);
	      try
	      {
	         Thread t = new JavaServer(port);
	         t.start();
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
	   }
}
