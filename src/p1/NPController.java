package p1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class NPController extends HttpServlet 
{
	
	public void service(HttpServletRequest x,HttpServletResponse y)
	{
		try
		{
			String cnp=x.getParameter("cnp");
			HttpSession hs=x.getSession();
			
			String toEmail=(String) hs.getAttribute("toEmail");
			Model m=new Model();
			m.setCnp(cnp);
			m.setToEmail(toEmail);
			boolean b=m.email();
			
			if(b==true)
			{
				y.sendRedirect("/BankApp/NPSuccess.jsp");
			}
			else
			{
				y.sendRedirect("/BankApp/NPFailure.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
