package networkStatisticsCalculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtilities {
	public void createFile() {
// create a file if the file doesn't already exist
// add exception handling
	}

	public void writeToFile() {
// open file created
// clear any data inside file if it already exists
// write data to the file
// add exception handling
	}

	//reads the CSV file, ignores the header and returns a list of CSV data objects
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
