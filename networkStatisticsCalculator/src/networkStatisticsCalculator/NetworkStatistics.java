package networkStatisticsCalculator;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;

/**
 * network statistics class that contains all methods to calculate network
 * statistics
 */
public class NetworkStatistics {
	// adds total number of flows then allocates said flows to each local IP and
	// then a hashmap of the results is returned
	/**
	 * @param List of CsvData
	 * @return HashMap of Integer, Integer
	 */
	public HashMap<Integer, Integer> connectionsPerUser(List<CsvData> dataList) {
		HashMap<Integer, Integer> userConnections = new HashMap<Integer, Integer>();

		for (CsvData data : dataList) {
			int localIP = data.getLocalIP();
			int flows = data.getFlows();

			userConnections.merge(localIP, flows, Integer::sum);
		}
		return userConnections;
	}

	public void totalNumberOfConnectionsPerWeek(List<CsvData> dataList) {
		HashMap<String, Integer> connectionsPerWeek = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		for (CsvData data : dataList) {
			String weekStartDate = getWeekStartDate(data.getDate(), formatter);
			int flows = data.getFlows();

			connectionsPerWeek.merge(weekStartDate, flows, Integer::sum);
		}
		for (HashMap.Entry<String, Integer> entry : connectionsPerWeek.entrySet()) {
			String weekStartDate = entry.getKey();
			int totalFlows = entry.getValue();
			System.out.println("Week starting on " + weekStartDate + ": Total Flows" + totalFlows);
		}
	}

	private String getWeekStartDate(String date, DateTimeFormatter formatter) {
		LocalDate localDate = LocalDate.parse(date, formatter);
		LocalDate weekStartDate = localDate.minusDays(localDate.getDayOfWeek().getValue() - 1);
		return weekStartDate.format(formatter);
	}

	public HashMap<Integer, Integer> totalNumberOfconnectionsPerASN(List<CsvData> dataList) {
		HashMap<Integer, Integer> connectionsPerASN = new HashMap<Integer, Integer>();

		for (CsvData data : dataList) {
			int remoteASN = data.getASN();
			int flows = data.getFlows();

			connectionsPerASN.merge(remoteASN, flows, Integer::sum);
		}
		return connectionsPerASN;
	}

}
