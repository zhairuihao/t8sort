package cn.com.t8sort.javabasic;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2017年2月10日 上午9:25:13 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class JavaSendMail {
	
	   public static void main(String [] args)
	   {   
	      // 收件人电子邮箱
	      String to = "ruihao.zhai@gmail.com";
	 
	      // 发件人电子邮箱
	      String from = "zrhaxy@qq.com";
	 
	      // 指定发送邮件的主机
	      String host = "smtp.qq.com";  //QQ 邮件服务器
	      
	      String usr = "zrhaxy@qq.com";
	      String pwd = "ncffmgnvotjbbefa";
	 
	      // 获取系统属性
	      Properties properties = System.getProperties();
	 
	      // 设置邮件服务器
	      properties.setProperty("mail.smtp.host", host);
	 
	      //如果需要提供用户名和密码给e-mail服务器来达到用户认证的目的，你可以通过如下设置来完成：
	      properties.put("mail.smtp.auth", "true");
	      //properties.setProperty("mail.user", usr);
	      //properties.setProperty("mail.password", pwd);
	      
	      // 获取默认session对象
	      // Session session = Session.getDefaultInstance(properties);
	      Session session = Session.getDefaultInstance(properties,new Authenticator(){
	          public PasswordAuthentication getPasswordAuthentication()
	          {
	           return new PasswordAuthentication(usr, pwd); //发件人邮件用户名、密码
	          }
	         });
	      
	      try{
	         // 创建默认的 MimeMessage 对象
	         MimeMessage message = new MimeMessage(session);
	 
	         // Set From: 头部头字段
	         message.setFrom(new InternetAddress(from));
	 
	         // Set To: 头部头字段
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	         
	         // 如果你想发送一封e-mail给多个收件人，那么使用下面的方法来指定多个收件人ID：
	         // void addRecipients(Message.RecipientType type, Address[] addresses) throws MessagingException
	         //InternetAddress [] addresses = {new InternetAddress(to), new InternetAddress("123@gmail.com")};
	         // message.addRecipients(Message.RecipientType.TO, addresses);
	         
	         // Set Subject: 头部头字段
	         message.setSubject("This is the Subject Line!");
	         /******************************文字消息*********************************/
	         // 设置消息体
	         message.setText("This is actual message");
	         
	         /******************************HTML消息**********************************/
	         // 发送 HTML 消息, 可以插入html标签
	         // message.setContent("<h1>This is actual message</h1>", "text/html" );
	         /******************************附件消息**********************************/
	         // 创建消息部分
	         BodyPart messageBodyPart = new MimeBodyPart();
	 
	         // 消息
	         messageBodyPart.setText("This is message body");
	        
	         // 创建多重消息
	         Multipart multipart = new MimeMultipart();
	 
	         // 设置文本消息部分
	         multipart.addBodyPart(messageBodyPart);
	 
	         // 附件部分
	         messageBodyPart = new MimeBodyPart();
	         String filename = "file.txt";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);
	 
	         // 发送完整消息
	         message.setContent(multipart );
	         /******************************STOP**********************************/
	         // 发送消息
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }

}
