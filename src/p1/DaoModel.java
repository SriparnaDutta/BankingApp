package p1;

import java.sql.Connection;
import java.sql.DriverManager;

import oracle.jdbc.driver.OracleDriver;

public class DaoModel 
{
	private String url="jdbc:oracle:thin:@//localhost:1521/XE";
	private String un="system";
	private String pass="admin";
	private Connection con;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUn() {
		return un;
	}
	public void setUn(String un) {
		this.un = un;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	
	
	Connection getConnection()
	{
		try
		{
			DriverManager.registerDriver(new OracleDriver());
			con=DriverManager.getConnection(url, un, pass);
			return con; // returning connection to Model constructor of Model class
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null; // if no connection is established
	} 
}
