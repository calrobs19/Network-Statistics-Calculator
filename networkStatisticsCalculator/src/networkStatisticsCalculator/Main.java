package networkStatisticsCalculator;

import java.io.*;
import java.util.HashMap;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class Main {	
	public static void main(String[] args) {
		String file = "data/PCAP Data.csv";
		List<CsvData> dataList = readCsvFile(file);

		NetworkStatistics networkStats = new NetworkStatistics();
		HashMap<Integer, Integer> connectionsPerPerson = networkStats.connectionsPerPerson(dataList);

		for (HashMap.Entry<Integer, Integer> entry : connectionsPerPerson.entrySet()) {
			int localIP = entry.getKey();
			int totalFlows = entry.getValue();
			System.out.println("LocalIP: " + localIP + ", Total Flows " + totalFlows);
		}
	}

	// reads the CSV file, ignores the header and returns a list of CSV data objects
	/**
	 * @param String filename
	 * @return List of CsvData
	 */
	private static List<CsvData> readCsvFile(String filename) {
		List<CsvData> dataList = new ArrayList<>();
		BufferedReader reader = null;
		String line = "";
		
		// using exception handling to catch different exceptions
		try {
			reader = new BufferedReader(new FileReader(filename));
			boolean isHeader = true;
			
            //created while loop to loop through all the data in the CSV file
			while ((line = reader.readLine()) != null) {
				//ignores the header 
				if (isHeader) {
					isHeader = false;
					continue;
				}
				String[] row = line.split(",");
				String date = (row[0]);
				int localIP = Integer.parseInt(row[1]);
				int remoteASN = Integer.parseInt(row[2]);
				int flows = Integer.parseInt(row[3]);
				CsvData csvdata = new CsvData(date, localIP, remoteASN, flows);
				dataList.add(csvdata);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return dataList;
	}
}