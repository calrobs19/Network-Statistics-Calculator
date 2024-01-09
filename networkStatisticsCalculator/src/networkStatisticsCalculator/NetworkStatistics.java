package networkStatisticsCalculator;

import java.util.HashMap;
import java.util.List;

/**
 * network statistics class that contains all methods to calculate network
 * statistics
 */
public class NetworkStatistics {
	// adds total number of flows then allocates said flows to each local IP and then a hashmap of the results is returned
	/**
	 * @param List of CsvData
	 * @return HashMap of Integer, Integer
	 */
	public HashMap<Integer, Integer> connectionsPerPerson(List<CsvData> dataList) {
		HashMap<Integer, Integer> userConnections = new HashMap<Integer, Integer>();

		for (CsvData data : dataList) {
			int localIP = data.getLocalIP();
			int flows = data.getFlows();

			userConnections.merge(localIP, flows, Integer::sum);
		}
		return userConnections;
	}
}
