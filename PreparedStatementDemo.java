package JavaTask;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;


public class PreparedStatementDemo{
	public static void main(String[] args)throws Exception {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Select the CRUD operations: ");
		System.out.println("1.Insert the data");
		System.out.println("2.Delete the data");
		System.out.println("3.Update the data");
		System.out.println("4.Read the data");
		System.out.print("Enter the input: ");
		int n=sc.nextInt();
		
		switch(n) {
		case 1:
			insertRecords();
			break;
		case 2:
			deleteRecords();
			break;
		case 3:
			updateRecords();
			break;
		case 4:
			readRecords();
			break;
		default:
			System.out.println("plz enter the correct input value");
			
		}
		
}

	public static void insertRecords() throws Exception
{
		String url="jdbc:mysql://localhost:3306/nataraj";    //database name ex:nataraj
		String username="root";				//mysql username
		String password="Nataraj@123";		//mysql password
		
				
				
			Class.forName("com.mysql.cj.jdbc.Driver");  //no need declare class driver name for updated version
			Connection con=DriverManager.getConnection(url,username,password);
			String query = "INSERT INTO EmployeeData (Sno, EEID, FullName, JobTitle, Department, BusinessUnit, Gender, Ethnicity, Age, HireDate, AnnualSalary, Bonus, Country, City) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement stmt = con.prepareStatement(query);

			try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\employeesampledata1.csv"))) {
			    String line;
			    int file_Count=0;
			    while ((line = br.readLine()) != null) {
			        String[] values = line.split(",");
			      
			        if (values.length != 15) {					//to intialially give a condition length of a each coloumn line values is 14 
			            System.out.println("Line has " + values.length + " values, but expected 15. Skipping this line.");
			            continue;
			        }
			        for (int i = 0; i < values.length-1; i++) {
			            stmt.setString(i + 1, values[i]);
			        }
			        stmt.addBatch();
			        file_Count++;
			    }
			    
			    stmt.executeBatch();	    //to execute the bulk records
			    System.out.println("Number of rows affected: " + file_Count);
				System.out.println("Record Inserted");
				
				
			con.setAutoCommit(true);	// to confirm updatation	
			con.close();				// to close the opened connection
			}
		}
	
	
	private static void deleteRecords() {    //to delete the entire records
		try {
			String url="jdbc:mysql://localhost:3306/nataraj";    //database name ex:nataraj
			String username="root";				//mysql username
			String password="Nataraj@123";		//mysql password
			
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con = DriverManager.getConnection(url, username, password);
		    String query = "DELETE FROM EmployeeData";

		    PreparedStatement stmt = con.prepareStatement(query);
		    int rows = stmt.executeUpdate();

		    System.out.println("Number of rows affected: " + rows);
		    System.out.println("Records deleted");

		    con.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
	}
	
	private static void updateRecords() { 
      		try {
			String url="jdbc:mysql://localhost:3306/nataraj";    //database name ex:nataraj
			String username="root";				//mysql username
			String password="Nataraj@123";		//mysql password
			
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con = DriverManager.getConnection(url, username, password);
		    
		   // int Sno=1;
		   //String query = "UPDATE EmployeeData SET City =city, WHERE condition Sno=1";
		    String query = "UPDATE EmployeeData SET City = 'CHINA' WHERE City='China'";  
		    
		    PreparedStatement stmt = con.prepareStatement(query);
		    int rows = stmt.executeUpdate();

		    System.out.println("Number of rows affected: " + rows);
		    System.out.println("Records updated");

		    con.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	private static void readRecords() {
		try {
			String url="jdbc:mysql://localhost:3306/nataraj";    //database name ex:nataraj
			String username="root";				//mysql username
			String password="Nataraj@123";		//mysql password
			
			String query = "SELECT * FROM EmployeeData";
			//String query="SELECT City FROM EmployeeData";
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			//int n=1;
			while (rs.next()) {
				
				 int Sno = rs.getInt("Sno"); 
				 String EEID = rs.getString("EEID"); 
				 String Fullname = rs.getString("Fullname");
				 String JobTitle = rs.getString("JobTitle");
				 String Department = rs.getString("Department");
				 String BusinessUnit = rs.getString("BusinessUnit");
				 String Gender = rs.getString("Gender");
				 String Ethnicity = rs.getString("Ethnicity");
				 String Age = rs.getString("Age");
				 String HireDate = rs.getString("HireDate");
				 String AnnualSalary = rs.getString("AnnualSalary");
				 String Bonus = rs.getString("Bonus");
				 String Country = rs.getString("Country");
				 String City = rs.getString("City");
				 
				 System.out.println("Sno: " + Sno + ",| EEID: " + EEID + ",| Fullname: " + Fullname+ ",| JobTitle: " + JobTitle+ ",| Department: " + Department+ ",| BusinessUnit: " + BusinessUnit+ ",| Gender: " + Gender+ ",| Ethnicity: " + Ethnicity+ ",| Age: " + Age+ ",| HireDate: " + HireDate+ ",| AnnualSalary: " + AnnualSalary+ ",| Bonus: " + Bonus+ ",| Country: " + Country+ ",| City: " + City);
				  
	
			  //String City=rs.getString("City");
			  //System.out.println("no: "+n+" City: " + City);
			  //n++;
			}
			
			
			rs.close();
			stmt.close();
			con.close();

		}

		catch (Exception e) {
        e.printStackTrace();
    }
}

}



