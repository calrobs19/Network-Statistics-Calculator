package networkStatisticsCalculator;

import java.io.*;
import java.util.HashMap;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		String file = "data/PCAP Data.csv";
		FileUtilities fileUtilities = new FileUtilities();
		List<CsvData> dataList = fileUtilities.readCsvFile(file);

		NetworkStatistics networkStats = new NetworkStatistics();
		HashMap<Integer, Integer> connectionsPerUser = networkStats.connectionsPerUser(dataList);

		for (HashMap.Entry<Integer, Integer> entry : connectionsPerUser.entrySet()) {
			int localIP = entry.getKey();
			int totalFlows = entry.getValue();
			System.out.println("LocalIP: " + localIP + ", Total Flows " + totalFlows);
		}
		HashMap<Integer, Integer> totalNumberOfconnectionsPerASN = networkStats
				.totalNumberOfconnectionsPerASN(dataList);

		for (HashMap.Entry<Integer, Integer> entry : totalNumberOfconnectionsPerASN.entrySet()) {
			int remoteASN = entry.getKey();
			int totalFlows = entry.getValue();
			System.out.println("remote ASN: " + remoteASN + ", Total Flows " + totalFlows);
		}
		networkStats.totalNumberOfConnectionsPerWeek(dataList);

	}

	
}