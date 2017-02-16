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
 * @author  ���� E-mail: 
 * @date ����ʱ�䣺2017��2��10�� ����9:25:13 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class JavaSendMail {
	
	   public static void main(String [] args)
	   {   
	      // �ռ��˵�������
	      String to = "ruihao.zhai@gmail.com";
	 
	      // �����˵�������
	      String from = "zrhaxy@qq.com";
	 
	      // ָ�������ʼ�������
	      String host = "smtp.qq.com";  //QQ �ʼ�������
	      
	      String usr = "zrhaxy@qq.com";
	      String pwd = "ncffmgnvotjbbefa";
	 
	      // ��ȡϵͳ����
	      Properties properties = System.getProperties();
	 
	      // �����ʼ�������
	      properties.setProperty("mail.smtp.host", host);
	 
	      //�����Ҫ�ṩ�û����������e-mail���������ﵽ�û���֤��Ŀ�ģ������ͨ��������������ɣ�
	      properties.put("mail.smtp.auth", "true");
	      //properties.setProperty("mail.user", usr);
	      //properties.setProperty("mail.password", pwd);
	      
	      // ��ȡĬ��session����
	      // Session session = Session.getDefaultInstance(properties);
	      Session session = Session.getDefaultInstance(properties,new Authenticator(){
	          public PasswordAuthentication getPasswordAuthentication()
	          {
	           return new PasswordAuthentication(usr, pwd); //�������ʼ��û���������
	          }
	         });
	      
	      try{
	         // ����Ĭ�ϵ� MimeMessage ����
	         MimeMessage message = new MimeMessage(session);
	 
	         // Set From: ͷ��ͷ�ֶ�
	         message.setFrom(new InternetAddress(from));
	 
	         // Set To: ͷ��ͷ�ֶ�
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	         
	         // ������뷢��һ��e-mail������ռ��ˣ���ôʹ������ķ�����ָ������ռ���ID��
	         // void addRecipients(Message.RecipientType type, Address[] addresses) throws MessagingException
	         //InternetAddress [] addresses = {new InternetAddress(to), new InternetAddress("123@gmail.com")};
	         // message.addRecipients(Message.RecipientType.TO, addresses);
	         
	         // Set Subject: ͷ��ͷ�ֶ�
	         message.setSubject("This is the Subject Line!");
	         /******************************������Ϣ*********************************/
	         // ������Ϣ��
	         message.setText("This is actual message");
	         
	         /******************************HTML��Ϣ**********************************/
	         // ���� HTML ��Ϣ, ���Բ���html��ǩ
	         // message.setContent("<h1>This is actual message</h1>", "text/html" );
	         /******************************������Ϣ**********************************/
	         // ������Ϣ����
	         BodyPart messageBodyPart = new MimeBodyPart();
	 
	         // ��Ϣ
	         messageBodyPart.setText("This is message body");
	        
	         // ����������Ϣ
	         Multipart multipart = new MimeMultipart();
	 
	         // �����ı���Ϣ����
	         multipart.addBodyPart(messageBodyPart);
	 
	         // ��������
	         messageBodyPart = new MimeBodyPart();
	         String filename = "file.txt";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);
	 
	         // ����������Ϣ
	         message.setContent(multipart );
	         /******************************STOP**********************************/
	         // ������Ϣ
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }

}
