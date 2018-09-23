package p1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Model 
{
	private int id;
	private String pwd;
	private Connection con;
	private ResultSet res;
	private PreparedStatement pstmt;
	private int accno;
	private String name;
	private int balance;
	private int tpaccnum;
	private int transamt;
	private  String npass;
	private int amount;
	private String cnp;
	private String toEmail;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	public ResultSet getRes() {
		return res;
	}
	public void setRes(ResultSet res) {
		this.res = res;
	}
	public PreparedStatement getPstmt() {
		return pstmt;
	}
	public void setPstmt(PreparedStatement pstmt) {
		this.pstmt = pstmt;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public int getTpaccnum() {
		return tpaccnum;
	}
	public void setTpaccnum(int tpaccnum) {
		this.tpaccnum = tpaccnum;
	}
	public int getTransamt() {
		return transamt;
	}
	public void setTransamt(int transamt) {
		this.transamt = transamt;
	}
	public String getNpass() {
		return npass;
	}
	public void setNpass(String npass) {
		this.npass = npass;
	}
	
	
	public Model()
	{
		DaoModel dm=new DaoModel();
		con=dm.getConnection();
	}
	
	boolean verify()
	{
		try
		{
			pstmt=con.prepareStatement("Select * from BANK where CUSTOMERID=? and PASSWORD=?");
			pstmt.setInt(1,id);
			pstmt.setString(2,pwd);
			res=pstmt.executeQuery();
			while(res.next()==true)
			{
				accno=res.getInt(1);  // fetching customer name from BANK table
				name=res.getString(5); // fetching customer name from BANK table
				return true;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	boolean checkBalance()
	{
		try
		{
			pstmt=con.prepareStatement("Select * from BANK where ACCNO=?");
			pstmt.setInt(1,accno);
			res=pstmt.executeQuery();
			
			while(res.next()==true)
			{
				balance=res.getInt(9);
				return true;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		return false;
	}
	
	boolean transfer()
	{
		try
		{
			//updating sender's account balance
			pstmt=con.prepareStatement("update BANK set BALANCE=BALANCE-? where ACCNO=?");
			pstmt.setInt(1, transamt);
			pstmt.setInt(2, accno);
			pstmt.executeUpdate();
			
			//updating receiver's account balance
			pstmt=con.prepareStatement("update BANK set BALANCE=BALANCE+? where ACCNO=?");
			pstmt.setInt(1, transamt);
			pstmt.setInt(2, tpaccnum);
			pstmt.executeUpdate();
			
			//updating Statement table..
			pstmt=con.prepareStatement("Insert into STATEMENT values(?,?)");
			pstmt.setInt(1, accno); //sender's account number 
			pstmt.setInt(2, transamt);
			pstmt.executeUpdate();
			
			
			return true; // return true after updation of both account balance & updating STATEMENT table
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	boolean changePassword()
	{
		try
		{
			pstmt=con.prepareStatement("update BANK set PASSWORD=? where ACCNO=?");
			pstmt.setString(1, npass);
			pstmt.setInt(2, accno);
			pstmt.executeUpdate();
			
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	
	ArrayList statement()
	{
		try
		{
			pstmt=con.prepareStatement("Select * from STATEMENT where ACCNO=?");
			pstmt.setInt(1,accno);
			res=pstmt.executeQuery();
			
			ArrayList al=new ArrayList();
			
			while(res.next()==true)
			{
				al.add(res.getInt(2)); // collecting all the amount/values to arraylist al
			}
			return al;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	
	
	
	boolean email()
	{
		try
		{
			pstmt=con.prepareStatement("Update BANK set PASSWORD=? where EMAIL=?"); //setting new password to registered email
			pstmt.setString(1,cnp);
			pstmt.setString(2,toEmail);
			pstmt.executeUpdate();
			
			return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	
}
