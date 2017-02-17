import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class script {

	public static void main(String[] args) {

		String csvFile = "meteo.csv";
		String line = "";
		String cvsSplitBy = ",";
		DecimalFormat df = new DecimalFormat("#.##");
		PrintWriter writer = null;

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			writer = new PrintWriter("newdatatrue.csv", "UTF-8");
			writer.println("date"+ "," +"value");
			br.readLine(); 
			int countdays=0;
			int sumPerMonth=0;
			String previousMonth = "01";
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] data = line.split(cvsSplitBy);
				//year, day, month, temperature
				System.out.println("year " + data[0] + " , month=" + data[1] + "day "+ data[2]+ "tempreture "+data[3]);
				String year = data[0];
				String month = data[1];
				int tempreture = Integer.parseInt(data[3]);


				if(month.equals(previousMonth)){
					countdays++;
					sumPerMonth += tempreture;
					System.out.println(countdays + "====="+ previousMonth   );

				}else{
					System.out.println("Sum "+ sumPerMonth);
					System.out.println("Days "+ countdays);

					double  average= sumPerMonth/ (double)countdays;
					System.out.println("The Average is "+ df.format(average));
					writer.println(year+"-"+month+","+df.format(average));
					countdays=1;
					sumPerMonth=tempreture;
				}

				previousMonth = month;   
			}


			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


} 