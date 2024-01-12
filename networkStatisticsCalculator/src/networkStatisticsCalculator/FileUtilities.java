package networkStatisticsCalculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class FileUtilities {
	
	

	public void writeToFile(List<CsvData> dataList) {
		//initialise network stats object 
		NetworkStatistics networkStats = new NetworkStatistics(dataList);
		List<Entry<Integer, Integer>> topUserConnections = networkStats.connectionsPerUser();		
		List<Entry<Integer, Integer>> topConnectionsPerASN = networkStats.totalNumberOfconnectionsPerASN();
		List<Entry<String, Integer>> topConnectionsPerWeek = networkStats.totalNumberOfConnectionsPerWeek();
		String topUsers = String.format("top user connections: %s \n",topUserConnections.toString());
		String topConnections = String.format("top Connections per ASN: %s \n",topConnectionsPerASN.toString());
		String topUserConnectionsPerWeek = String.format("top user connections: %s \n",topConnectionsPerWeek.toString());
		try {
			FileWriter writer = new FileWriter("NetStatistics.txt");
			writer.write("");

			writer.write(topUsers);
			writer.write(topConnections);
			writer.write(topUserConnectionsPerWeek);
			writer.close();
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// reads the CSV file, ignores the header and returns a list of CSV data objects
	/**
	 * @param String filename
	 * @return List of CsvData
	 */
	public List<CsvData> readCsvFile(String filename) {
		List<CsvData> dataList = new ArrayList<>();
		BufferedReader reader = null;
		String line = "";

		// using exception handling to catch different exceptions
		try {
			reader = new BufferedReader(new FileReader(filename));
			boolean isHeader = true;

			// created while loop to loop through all the data in the CSV file
			while ((line = reader.readLine()) != null) {
				// ignores the header
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
		} finally {
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
