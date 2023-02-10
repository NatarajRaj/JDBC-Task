package JavaTask;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVfileRead {
	public static void main(String [] args) {
	
		{  
			String line = "";  
			String splitBy = ",";  
			try   
			{  
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\employeee.csv"));  
			while ((line = br.readLine()) != null)   //returns a Boolean value  
			{  
			String[] employee = line.split(splitBy);    // use comma as separator  
			System.out.println("Employee [EEID=" + employee[0] + ", Full Name=" + employee[1] + ", Job Title=" + employee[2]  +"]");  
			}  
			}   
			catch (IOException e)   
			{  
			e.printStackTrace();  
			}  
			}  
			}  
}
