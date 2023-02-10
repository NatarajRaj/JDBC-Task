package JavaTask;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class simpleproject1 {

	public static void main(String [] args) throws ClassNotFoundException, SQLException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Banking option service");
		System.out.println("1.Read the customer data details from database");
		System.out.println("2.Enter the new customer details");
		System.out.println("3.Updating the customer details");
		System.out.println("4. Deposit amount:");
		System.out.println("5. Withdraw amount:");
		System.out.println("6.Statement:");
		System.out.println("7.get customer details datewise");
		
		int n=sc.nextInt();
		switch(n) {
		case 1:
				CustomerDetails();
				break;
		case 2:
				NewCustomer();
				break;
		case 3:
				UpdateCustomer();
				break;
		case 4:
				Deposit();
				break;
		case 5:
				Withdraw();
				break;
		case 6:
			
				GetStatement();
				break;
		case 7:
				GetStatementDateWise();
				break;
		default:
				System.out.println("plz enter the correct input value");
				break;
		}
	}
	

	private static void CustomerDetails() {
		try {
			String url="jdbc:mysql://localhost:3306/nataraj";    //database name ex:nataraj
			String username="root";				//mysql username
			String password="Nataraj@123";		//mysql password
			
			String query = "SELECT * FROM BankingData";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			int file_Count=0;
			while (rs.next()) {
				
				 int Sno = rs.getInt("Sno"); 
				 String AccountNo = rs.getString("AccountNo"); 
				 String CustomerName = rs.getString("CustomerName");
				 String City = rs.getString("City");
				 String Deposit = rs.getString("Deposit");
				 String Withdraw = rs.getString("Withdraw");
				 String Balance = rs.getString("Balance");
				 	 
				 System.out.println("Sno: " + Sno + ",| AccountNo: " +AccountNo+ ",| CustomerName: " +CustomerName+ ",| City: " +City+ ",| Deposit: " + Deposit+ ",| Withdraw: " +Withdraw+ ",| Balance: " +Balance+ "");
			  file_Count++;
			}
			
			System.out.println("No of Records in the Customer details: "+file_Count);
			rs.close();
			stmt.close();
			con.close();

		}

		catch (Exception e) {
        e.printStackTrace();
    }

		
	}

	private static void NewCustomer() {
		try {
		String url="jdbc:mysql://localhost:3306/nataraj";    //database name ex:nataraj
		String username="root";				//mysql username
		String password="Nataraj@123";		//mysql password
		
			Class.forName("com.mysql.cj.jdbc.Driver");  //no need declare class driver name for updated version
			Connection con=DriverManager.getConnection(url,username,password);
			
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the customer Serial Number");
		int Sno=sc.nextInt();
		System.out.println("Enter the Account NUmber");
		int AccountNo=sc.nextInt();
		System.out.println("Enter the Customer Name");
		String CustomerName=sc.next();
		System.out.println("Enter the Customer Residing City");
		String City=sc.next();
		 int Deposit=0,Withdraw=0,Balance=0; 
		 Date Date1=null;
		
		String query="insert into BankingData values(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(query);
		pstmt.setInt(1, Sno);
		pstmt.setInt(2, AccountNo);
		pstmt.setString(3, CustomerName);
		pstmt.setString(4, City);
		pstmt.setInt(5, Deposit);
		pstmt.setInt(6, Withdraw);
		pstmt.setInt(7, Balance);
		pstmt.setDate(8, Date1);
		
		pstmt.executeUpdate();
		
		System.out.println("Record Inserted");
	} catch (Exception e) {
		e.printStackTrace();
	}
}		
	private static void UpdateCustomer() {
		  try {
		    String url = "jdbc:mysql://localhost:3306/nataraj"; // database name, ex: nataraj
		    String username = "root"; // mysql username
		    String password = "Nataraj@123"; // mysql password
		    
		    Scanner sc = new Scanner(System.in);
		    System.out.println("Enter the updating customer Serial Number");
		    int no = sc.nextInt();
		    String query = "update BankingData set CustomerName = ? where Sno = ?";
		    
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con = DriverManager.getConnection(url, username, password);
		    PreparedStatement stmt = con.prepareStatement(query);
		    stmt.setString(1, "Raju");
		    stmt.setInt(2, no);   //to get customer serial number on the scanner input 
		    int rows = stmt.executeUpdate();

		    System.out.println("Number of rows affected: " + rows);
		    System.out.println("Records updated");

		    con.close();
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
}
	
	private static void Deposit() {
		  try {
		    String url = "jdbc:mysql://localhost:3306/nataraj"; // database name, ex: nataraj
		    String username = "root"; // mysql username
		    String password = "Nataraj@123"; // mysql password
		    
		    Scanner sc = new Scanner(System.in);
		    System.out.println("Enter the Account number");
		    int no = sc.nextInt();
		    System.out.println("Enter the deposit amount");
		    int deposit = sc.nextInt();

		    // get the current date
		    java.util.Date date = new java.util.Date();
		    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con = DriverManager.getConnection(url, username, password);
		    PreparedStatement stmt = con.prepareStatement("SELECT Balance FROM BankingData WHERE AccountNo = ?");
		    stmt.setInt(1, no);   //to get customer serial number from input method
		    ResultSet rs = stmt.executeQuery();
		    int balance = 0;
		    if (rs.next()) {
		        balance = rs.getInt("balance") + deposit;    //get balance amount from database and add deposit amount,,,to get new acoount balance amount
		    }
		    rs.close();
		    stmt.close();

		    stmt = con.prepareStatement("UPDATE BankingData SET Deposit = deposit + ?, Balance = ?, Date1 = ? WHERE AccountNo = ?");
		    stmt.setInt(1, deposit);
		    stmt.setInt(2, balance);
		    stmt.setDate(3, sqlDate);
		    stmt.setInt(4, no);
		    int rows = stmt.executeUpdate();

		    System.out.println("Number of rows affected: " + rows);
		    System.out.println("Records updated");

		    con.close();
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
		}
	private static void Withdraw() {
		 try {
			    String url = "jdbc:mysql://localhost:3306/nataraj"; // database name, ex: nataraj
			    String username = "root"; // mysql username
			    String password = "Nataraj@123"; // mysql password
			    
			    Scanner sc = new Scanner(System.in);
			    System.out.println("Enter the Account number");
			    int no = sc.nextInt();
			    System.out.println("Enter the withdraw amount");
			    int withdraw = sc.nextInt();
			    
			    // get the current date
			    java.util.Date date = new java.util.Date();
			    java.sql.Date sqlDate = new java.sql.Date(date.getTime());


			    Class.forName("com.mysql.cj.jdbc.Driver");
			    Connection con = DriverManager.getConnection(url, username, password);
			    PreparedStatement stmt = con.prepareStatement("SELECT Balance FROM BankingData WHERE AccountNo = ?");
			    stmt.setInt(1, no);
			    ResultSet rs = stmt.executeQuery();
			    int balance = 0;
			    if (rs.next()) {
			        balance = rs.getInt("balance") - withdraw;
			    }
			    rs.close();
			    stmt.close();
			    
			    if(withdraw>balance) {
			    	System.out.println("Sorry to enough balance to withdraw,,, so, plz check the customer balance");
			    }
			    else {
			    stmt = con.prepareStatement("UPDATE BankingData SET Withdraw = withdraw - ?, Balance = ?,Date1 =? WHERE AccountNo = ?");
			    stmt.setInt(1, withdraw);
			    stmt.setInt(2, balance);
			    stmt.setDate(3,sqlDate);
			    stmt.setInt(4, no);
			    int rows = stmt.executeUpdate();

			    System.out.println("Number of rows affected: " + rows);
			    System.out.println("Records updated");
			    }
			    
			    con.close();
			  } catch (Exception e) {
			    e.printStackTrace();
			  }
	}


	
	private static void GetStatement() {
		  try {
			Scanner sc=new Scanner(System.in);
		    String url = "jdbc:mysql://localhost:3306/nataraj"; // database name, ex: nataraj
		    String username = "root"; // mysql username
		    String password = "Nataraj@123"; // mysql password
		    
//		    // get the current date
//		    java.util.Date date = new java.util.Date();
//		    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		    
		    System.out.println("Enter the current date (yyyy-MM-dd): ");
		    String DateString = sc.nextLine();

		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		    java.util.Date startDate = dateFormat.parse(DateString);  //to conversion date string into the date 
		    
		    java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());

		    System.out.println("Enter on the Account number");
		    int no=sc.nextInt();
		    
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con = DriverManager.getConnection(url, username, password);
		    PreparedStatement stmt = con.prepareStatement("SELECT * FROM BankingData WHERE Date1 = ? and AccountNo = ?");
		    stmt.setDate(1, sqlDate);     //to get date from method input
		    stmt.setInt(2, no);
		    ResultSet rs = stmt.executeQuery();

		    while (rs.next()) {
		       
		    	int Sno = rs.getInt("Sno");
		    	int AccountNo = rs.getInt("AccountNo");
		    	String CustomerName = rs.getString("CustomerName");
		    	int deposit = rs.getInt("Deposit");
		    	int withdraw = rs.getInt("Withdraw");
		        int balance = rs.getInt("Balance");
		        Date depositDate = rs.getDate("Date1");
		        System.out.println("Sno: "+Sno+ " | AccountNo: "+AccountNo+ " | CustomerName: "+CustomerName+"| Deposit: " + deposit +"| Withdraw: " + withdraw + " | Balance: " + balance + " | Date1: " + depositDate);
		        //pipe ("|") seperate the values
		    }

		    rs.close();
		    stmt.close();
		    con.close();
		  } catch (Exception e) {
		    e.printStackTrace();
		  }

	}	
	private static void GetStatementDateWise() {
		  try {
		    String url = "jdbc:mysql://localhost:3306/nataraj";
		    String username = "root";
		    String password = "Nataraj@123";
		    
		    Scanner scan = new Scanner(System.in);
		    System.out.println("Enter the start date (yyyy-MM-dd): ");
		    String startDateString = scan.nextLine();
		    System.out.println("Enter the end date (yyyy-MM-dd): ");
		    String endDateString = scan.nextLine();

		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		    java.util.Date startDate = dateFormat.parse(startDateString);  //to conversion date string into the date 
		    java.util.Date endDate = dateFormat.parse(endDateString);

		    java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		    java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con = DriverManager.getConnection(url, username, password);
		    PreparedStatement stmt = con.prepareStatement("SELECT * FROM BankingData WHERE Date1 BETWEEN ? AND ?");
		    stmt.setDate(1, sqlStartDate);
		    stmt.setDate(2, sqlEndDate);
		    ResultSet rs = stmt.executeQuery();

		    while (rs.next()) {
		        int Sno = rs.getInt("Sno");
		        int AccountNo = rs.getInt("AccountNo");
		        String CustomerName = rs.getString("CustomerName");
		        int deposit = rs.getInt("Deposit");
		        int withdraw = rs.getInt("Withdraw");
		        int balance = rs.getInt("Balance");
		        Date depositDate = rs.getDate("Date1");
		        System.out.println("Sno: "+Sno+ " | AccountNo: "+AccountNo+ " | CustomerName: "+CustomerName+"| Deposit: " + deposit +"| Withdraw: " + withdraw + " | Balance: " + balance + " | Date1: " + depositDate);
		    }

		    rs.close();
		    stmt.close();
		    con.close();
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
		}


}
	

