package networkStatisticsCalculator;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		// reading CSV file and storing as a list of CSV data objects
		String file = "data/PCAP Data.csv";
		FileUtilities fileUtilities = new FileUtilities();
		List<CsvData> dataList = fileUtilities.readCsvFile(file);

		// create file and write the network stats to file with datalist
		fileUtilities.writeToFile(dataList);
	}
}