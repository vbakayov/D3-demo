package src;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;


public class script {

	public static void main(String[] args) {

		String csvFile = "meteo.csv";
		String csvOutputFile = "newdata.csv";
		String line = "";
		String cvsSplitBy = ",";
		DecimalFormat df = new DecimalFormat("#.##");

		PrintWriter writer = null;
		double  average= 0;
		//open the file for reading 
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			//open an output file
			writer = new PrintWriter(csvOutputFile, "UTF-8");
			writer.println("date"+ "," +"temperature");
			//skip the header line 
			br.readLine(); 

			int countdays=0;
			int sumPerMonth=0;
			String previousMonth = "01";
			String previousyear = null;

			//while they are more lines to read 
			while ((line = br.readLine()) != null) {

				// use comma as separator and split the data 
				String[] data = line.split(cvsSplitBy);
				//year, day, month, temperature
				// System.out.println("year " + data[0] + " , month=" + data[1] + "day "+ data[2]+ "tempreture "+data[3]);
				String year = data[0];
				String month = data[1];
				int tempreture = Integer.parseInt(data[3]);

				//agregate if the next line of info is for the current month 
				if(month.equals(previousMonth)){
					countdays++;
					sumPerMonth += tempreture;

				}else{
					//take average and output to file 
					average= sumPerMonth/ (double)countdays;
					//System.out.println("The Average is "+ df.format(average));
					writer.println(previousyear+"-"+previousMonth+","+df.format(average));
					countdays=1;
					sumPerMonth=tempreture;
				}

				//update 
				previousyear= year;
				previousMonth = month;   
			}

			//handle the last month
			average= sumPerMonth/ (double)countdays;
			writer.println(previousyear+"-"+previousMonth+","+df.format(average));
			//close the stream
			writer.close();
			System.out.println("Script Successfull");
		} catch (IOException e) {
			System.out.println("OOps someting went wrong ");
			e.printStackTrace();
		}

	}


} 