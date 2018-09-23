package p1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginController extends HttpServlet 
{
	//====== only service method..cz not getting connected to dbms =====
	public void service(HttpServletRequest x,HttpServletResponse y)
	{
		try 
		{
			String cusid=x.getParameter("cusid");
			int id=Integer.parseInt(cusid); //converting to int..cz in dbms.. we set type of customer id as number
			String pwd=x.getParameter("pwd");
			Model m=new Model();
			m.setId(id);
			m.setPwd(pwd);
			
			boolean b=m.verify(); //calling verify()..nd storing in b
			
			if(b==true)
			{
				HttpSession hs=x.getSession(true);
				hs.setAttribute("accno",m.getAccno()); //from model class we are getting account no nd putting in "accno", which resides in session..
				hs.setAttribute("name",m.getName()); //from model class we are getting name nd putting in "name", which resides in session..
				y.sendRedirect("/BankApp/LoginSuccess.jsp");
			}
			else
			{
				y.sendRedirect("/BankApp/LoginFailure.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
} 


