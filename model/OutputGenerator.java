package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputGenerator {

	public void createTxtResultsFromExtractedInfo(ExtractedInfo extractedInfo) throws IOException {
		
		
		
		// TODO insert txt creator thingy
		/** Create File */
		File file = null;
		String fileName = "Results.csv";
		

		try
		{
			file = new File(fileName);
			file.createNewFile();
		}
		catch (IOException iOException)
		{
			System.out.println("error po");
		}
		
		BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
		
		/** Compose info for text */
		String message = "";
		StringBuilder stringBuilder = new StringBuilder(message);
		out.write("Word,Category,Correct?");
		/** print persons number */
//		out.write("Number of persons mentioned: " + extractedInfo.getPersons().size());
		/** print persons */
		for(String person : extractedInfo.getPersons()) {
			out.newLine();
			out.write(person + ",Person");
		}
		/** print place number */
//		out.write("Number of places mentioned: " + extractedInfo.getPlaces().size());
		/** print place */
		for(String place : extractedInfo.getPlaces()) {
			out.newLine();
			out.write(place + ",Place");
		}
		/** print date number */
//		out.write("Number of date mentioned: " + extractedInfo.getDates().size());
		/** print date */
		for(String date : extractedInfo.getDates()) {
			out.newLine();
			out.write(date + ",Date");
		}
		message = stringBuilder.toString();
		
		/** Write to file */
		out.close();

//		try
//		{
//			out = new BufferedWriter(new FileWriter(fileName));
//			out.write(message);
//		}
//		catch (IOException iOException)
//		{
//			
//		}
//		finally
//		{
//			out.close();
//		}
	}
	
	
}
