package JavaTask;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {
	public static void main(String[] args) throws IOException {
		FileReader fileReader = new FileReader("C:\\Users\\user\\Desktop\\employeesampledata1.csv");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String line = "";
		String splitBy = ","; // normally .csv file each value separated by commas(,)  
		
		boolean isFirstLine = true;       //to avoid header line of the output
		
		int recordsCount = 0;
		while ((line = bufferedReader.readLine()) != null) {
			String[] employee = line.split(splitBy); // split() method used to split each value commas(,)
			
			if(!isFirstLine) {                   // ! not equal first line cancel the print statement
			System.out.println("Employee [Sno=" + employee[0] + "| EEID=" + employee[1] + "| Full Name=" + employee[2]
					+ "| Job Title=" + employee[3] + "| Department=" + employee[4] + "| BusinessUnit=" + employee[5]
					+ "| Gender=" + employee[6] + "| Ethnicity=" + employee[7] + "| Age=" + employee[8] + "| HireDate="
					+ employee[9] + "| AnnualSalary=" + employee[10] + "| Bonus=" + employee[11] + "| Country="
					+ employee[12] + "| City=" + employee[13] + "] ");

			recordsCount++;
		 }
			else {
				isFirstLine=false;              //first line (content line) true ,,, else part not print statement anything 
			}
		}
		
		bufferedReader.close();
		fileReader.close();

		long fileSizeInBytes = java.nio.file.Files.size(java.nio.file.Paths.get("C:\\Users\\user\\Desktop\\employeesampledata1.csv"));
		double fileSizeInKB = (double) fileSizeInBytes / 1024;
		double fileSizeInMB = fileSizeInKB / 1024;
		System.out.println("File size in bytes: " + fileSizeInBytes);
		System.out.println("File size in kilobytes: " + fileSizeInKB);
		System.out.println("File size in megabytes: " + fileSizeInMB);

		System.out.println("No. of the records in document: "+recordsCount);


        System.out.println("<------------------------------------------------------------->");
		
		        String sourceFilePath = "C:\\Users\\user\\Desktop\\employeesampledata1.csv"; // replace with the path to your source file
		        String destinationFilePath = "C:\\Users\\user\\Desktop\\employeesampledata2.csv"; // replace with the path to your destination file
		        int numCopies = 10000; // number of times to copy the file

		        try (FileInputStream fis = new FileInputStream(sourceFilePath);
		             ByteArrayOutputStream bos = new ByteArrayOutputStream();
		             FileOutputStream fos = new FileOutputStream(destinationFilePath)) {

		            // read the content of the source file into a byte array output stream
		            byte[] buffer = new byte[1024];
		            int bytesRead;
		 
		            while ((bytesRead = fis.read(buffer)) != -1) {
		                bos.write(buffer, 0, bytesRead);                 
		              }
		            
		            //buffer: This is the byte array containing the data to be written to the output stream.
		            //0: This is the index in the buffer array where the write operation should start. In this case, it starts at the beginning of the array.
		            //bytesRead: This is the number of bytes to be written to the output stream. 
		            
		            // write the content of the byte array output stream to the destination file for the desired number of copies
		            byte[] fileContent = bos.toByteArray();
		            for (int i = 0; i < numCopies; i++) {
		                fos.write(fileContent);
		            }

		            System.out.println("File copied successfully.");
		        } catch (IOException e) {
		            System.err.println("Error copying file: " + e.getMessage());
		        }
		        
		        long fileSizeInBytes1 = java.nio.file.Files.size(java.nio.file.Paths.get("C:\\Users\\user\\Desktop\\employeesampledata2.csv"));
				double fileSizeInKB1 = (double) fileSizeInBytes1 / 1024;
				double fileSizeInMB1 = fileSizeInKB1 / 1024;
				System.out.println("File size in bytes: " + fileSizeInBytes1);
				System.out.println("File size in kilobytes: " + fileSizeInKB1);
				System.out.println("File size in megabytes: " + fileSizeInMB1);
				
				int record_count1=recordsCount*numCopies;
				System.out.println("Copied file count of the records is: "+record_count1);
				
		        
   }
}	


