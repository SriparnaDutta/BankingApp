package p1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ChangePasswordController extends HttpServlet 
{
	public void service(HttpServletRequest x, HttpServletResponse y)
	{
		try
		{
		String opass=x.getParameter("opass");
		String npass=x.getParameter("npass");
		String cnpass=x.getParameter("cnpass");
		
		if(npass.equals(cnpass)==true)
		{
			HttpSession hs=x.getSession();
			int accno=(int) hs.getAttribute("accno"); // accessing that previously created session to get account number
			
			Model m=new Model();
			m.setNpass(npass);
			m.setAccno(accno);
			boolean b=m.changePassword();
			if(b==true)
			{
				y.sendRedirect("/BankApp/PasswordChangeSuccess.jsp");
			}
			else
			{
				y.sendRedirect("/BankApp/PasswordChangeFailure.jsp");
			}
		}
		else
		{
			y.sendRedirect("/BankApp/PasswordError.jsp");
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
