package p1;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FPController extends HttpServlet 
{
	public void service(HttpServletRequest x,HttpServletResponse y)
	{
		try
		{
		final String fromEmail="dutta.sree10@gmail.com";
		final String password="santjoygurudev";
		final String toEmail=x.getParameter("toEmail"); // toEmail is user's email id, who has forgotten her password
		
		//accessing previous session to put toEmail id..
		HttpSession hs=x.getSession();
		hs.setAttribute("toEmail",toEmail);
		
		//we have to insert values to property file..
		Properties prop=new Properties();
		
		prop.put("mail.smtp.host","smtp.gmail.com"); //first one is key, second one is value
		prop.put("mail.smtp.port",587); //port number of gmail is 587 always fixed
		prop.put("mail.smtp.auth","true"); //authentication is set enabled..
		prop.put("mail.smtp.starttls.enable","true"); //encryption is set enabled
		
		Session session=Session.getDefaultInstance(prop, new javax.mail.Authenticator() //Authenticator is interface..which is doing authentication. present in javax.mail package.. Session is a class..
		//get ...getPasswordAuthentication() method is present in authenticator interface
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(fromEmail,password);
			}
		}); // similar to comparator
		
		
		MimeMessage mesg=new MimeMessage(session);
		mesg.setFrom(new InternetAddress(fromEmail));
		mesg.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
		mesg.setSubject("DO NOT REPLY TO THIS EMAIL");
		mesg.setText("http://localhost:9090/BankApp/NewPassword.jsp");
		Transport.send(mesg);
		y.sendRedirect("/BankApp/SuccessLink.jsp");
		System.out.println("Message Sent");
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
