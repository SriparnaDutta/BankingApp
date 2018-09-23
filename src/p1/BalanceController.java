package p1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BalanceController extends HttpServlet 
{
	HttpSession hs;
	
	public void service(HttpServletRequest x,HttpServletResponse y)
	{
		try
		{
			hs=x.getSession(); //accessing previously created session (by login controller)
			int accno=(int) hs.getAttribute("accno");
			Model m=new Model();
			m.setAccno(accno);
			boolean b=m.checkBalance();
			
			if(b==true)
			{
				hs.setAttribute("balance",m.getBalance());
				y.sendRedirect("/BankApp/BalanceSuccess.jsp");
			}
			else
			{
				y.sendRedirect("/BankApp/BalanceFailure.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
