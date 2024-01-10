package networkStatisticsCalculator;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
	public List<Map.Entry<Integer, Integer>> connectionsPerUser(List<CsvData> dataList) {
		HashMap<Integer, Integer> userConnections = new HashMap<Integer, Integer>();

		for (CsvData data : dataList) {
			int localIP = data.getLocalIP();
			int flows = data.getFlows();

			userConnections.merge(localIP, flows, Integer::sum);
		}
		// sorted all flows in descending order and selecting the first 3 values 
		List<Map.Entry<Integer, Integer>> topFlowsPerIP = userConnections.entrySet().stream()
				.sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()).limit(3)
				.collect(Collectors.toList());
		return topFlowsPerIP;
	}
   
	public List<Map.Entry<String, Integer>> totalNumberOfConnectionsPerWeek(List<CsvData> dataList) {
		HashMap<String, Integer> connectionsPerWeek = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		for (CsvData data : dataList) {
			String weekStartDate = getWeekStartDate(data.getDate(), formatter);
			int flows = data.getFlows();
			connectionsPerWeek.merge(weekStartDate, flows, Integer::sum);			
		}
		// sorted all flows in descending order alongside the week and selecting the 3 greatest values 
		List<Map.Entry<String, Integer>> topFlowsPerWeek = connectionsPerWeek.entrySet().stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(3)
				.collect(Collectors.toList());
		return topFlowsPerWeek;
		
	}

	private String getWeekStartDate(String date, DateTimeFormatter formatter) {
		LocalDate localDate = LocalDate.parse(date, formatter);
		LocalDate weekStartDate = localDate.minusDays(localDate.getDayOfWeek().getValue() - 1);
		return weekStartDate.format(formatter);
	}

	public List<Map.Entry<Integer, Integer>> totalNumberOfconnectionsPerASN(List<CsvData> dataList) {
		HashMap<Integer, Integer> connectionsPerASN = new HashMap<Integer, Integer>();

		for (CsvData data : dataList) {
			int remoteASN = data.getASN();
			int flows = data.getFlows();

			connectionsPerASN.merge(remoteASN, flows, Integer::sum);
		}
		// sorted all flows in descending order and selecting the first 3 values 
		List<Map.Entry<Integer, Integer>> topFlowsPerASN = connectionsPerASN.entrySet().stream()
				.sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()).limit(3)
				.collect(Collectors.toList());
		return topFlowsPerASN;
	}
}
