package cn.com.t8sort.javabasic;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
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
 * @date 创建时间：2017年2月10日 上午10:19:05 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class JavaSendMailUtill {

	private MimeMessage mimeMsg; // MIME邮件对象
	private Session session; // 邮件会话对象
	private Properties props; // 系统属性
	//private boolean needAuth = true; // smtp是否需要认证
	private String username = ""; // smtp认证用户名和密码
	private String password = "";
	private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成//MimeMessage对象
	
	public JavaSendMailUtill(String smtp){
		setSmtpHost(smtp);
		createMimeMessage();
	}
	
	public void setSmtpHost(String hostName)
	{
	System.out.println("设置系统属性：mail.smtp.host = " + hostName);
	if (props == null)
	props = System.getProperties(); // 获得系统属性对象
	props.put("mail.smtp.host", hostName); // 设置SMTP主机
	}

	public boolean createMimeMessage()
	{
	try {
	System.out.println("准备获取邮件会话对象！");
	session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
	}
	catch (Exception e)
	{
	System.err.println("获取邮件会话对象时发生错误！" + e);
	return false;
	}
	System.out.println("准备创建MIME邮件对象！");
	try {
	mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
	mp = new MimeMultipart(); // mp 一个multipart对象
	// Multipart is a container that holds multiple body parts.
	return true;
	}
	catch (Exception e)
	{
	System.err.println("创建MIME邮件对象失败！" + e);
	return false;
	}
	}

	public void setNeedAuth(boolean need) {
	System.out.println("设置smtp身份认证：mail.smtp.auth = " + need);
	if (props == null)
	props = System.getProperties();
	if (need) {
	props.put("mail.smtp.auth", "true");
	} else {
	props.put("mail.smtp.auth", "false");
	}
	}

	public void setNamePass(String name, String pass)
	{
	System.out.println("程序得到用户名与密码");
	System.out.println("用户名："+name);
	System.out.println("密码："+pass);
	username = name;
	password = pass;
	}

	public boolean setSubject(String mailSubject) {
	System.out.println("设置邮件主题！");
	try {
	mimeMsg.setSubject(mailSubject);
	return true;
	}
	catch (Exception e) {
	System.err.println("设置邮件主题发生错误！");
	return false;
	}
	}

	public boolean setBody(String mailBody)
	{
	try{
	System.out.println("设置邮件体格式");
	BodyPart bp = new MimeBodyPart();
	bp.setContent("<meta http-equiv=Content-Type content=text/html; charset=gb2312>"+ mailBody, "text/html;charset=GB2312");mp.addBodyPart(bp);
	return true;
	}
	catch (Exception e)
	{
	System.err.println("设置邮件正文时发生错误！" + e);
	return false;
	}
	}


	public boolean addFileAffix(String filename) {
	System.out.println("增加邮件附件：" + filename);
	try {
	BodyPart bp = new MimeBodyPart();
	FileDataSource fileds = new FileDataSource(filename);
	bp.setDataHandler(new DataHandler(fileds));
	bp.setFileName(fileds.getName());
	mp.addBodyPart(bp);
	return true;
	}
	catch (Exception e) {
	System.err.println("增加邮件附件：" + filename + "发生错误！" + e);
	return false;
	}
	}



	public boolean setFrom(String from) {
	System.out.println("设置发信人！");
	try {
	mimeMsg.setFrom(new InternetAddress(from)); // 设置发信人
	return true;
	}
	catch (Exception e)
	{
	return false;
	}
	}



	public boolean setTo(String to)
	{
	System.out.println("设置收信人");
	if (to == null)
	return false;
	try
	{
	mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	return true;
	}
	catch (Exception e)
	{
	return false;
	}
	}



	public boolean setCopyTo(String copyto)
	{
	System.out.println("发送附件到");
	if (copyto == null)
	return false;
	try {
	mimeMsg.setRecipients(Message.RecipientType.CC,(Address[]) InternetAddress.parse(copyto));
	return true;
	}
	catch (Exception e)
	{
	return false;
	}
	}



	public boolean sendout()
	{
	try{
	mimeMsg.setContent(mp);
	mimeMsg.saveChanges();
	System.out.println("正在发送邮件....");
	
	Session mailSession = Session.getInstance(props, new Authenticator(){
	    //Authenticator中的唯一一个方法，作用是返回用来认证的用户名和密码
	    public PasswordAuthentication getPasswordAuthentication() {
	                    //此处的username,password对应你邮箱的账号名称和密码
	        return new PasswordAuthentication(username, password);
	        }
	});
	
	Transport transport = mailSession.getTransport("smtp"); //
	transport.connect((String) props.get("mail.smtp.host"), username,password);
	transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
	// transport.send(mimeMsg);
	System.out.println("发送邮件成功！");
	transport.close();
	return true;
	}
	catch (Exception e)
	{
	System.out.println("邮件发送失败！" + e);
	e.printStackTrace();
	return false;
	}
	}

	public class auth extends Authenticator{
	    //Authenticator中的唯一一个方法，作用是返回用来认证的用户名和密码
	    public PasswordAuthentication getPasswordAuthentication() {
	                    //此处的username,password对应你163邮箱的账号名称和密码
	        return new PasswordAuthentication(username, password);
	        }
	}


	public static void main(String[] args)
	{
	String mailbody = "http://www.laabc.com 用户邮件注册测试 <font color=red>欢迎光临</font> <a href=\"http://www.laabc.com\">啦ABC</a>";
	JavaSendMailUtill themail = new JavaSendMailUtill("smtp.qq.com");
	themail.setNeedAuth(true);
	if (themail.setSubject("laabc.com邮件测试") == false)
	return;
	//邮件内容 支持html 如 <font color=red>欢迎光临</font> <a href=\"http://www.laabc.com\">啦ABC</a>
	if (themail.setBody(mailbody) == false)
	return;
	//收件人邮箱
	if (themail.setTo("ruihao.zhai@gmail.com") == false)
	return;
	//发件人邮箱
	if (themail.setFrom("zrhaxy@qq.com") == false)
	return;
	//设置附件
	//if (themail.addFileAffix("#######") == false)
	//return; // 附件在本地机子上的绝对路径


	themail.setNamePass("zrhaxy@qq.com", "tuxzvdgxxxzebbdc"); // 用户名与密码
	if (themail.sendout() == false)
	return;
	}
}
