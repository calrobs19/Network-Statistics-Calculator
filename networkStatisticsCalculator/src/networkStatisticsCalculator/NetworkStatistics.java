package networkStatisticsCalculator;

import java.util.HashMap;
import java.util.List;

public class NetworkStatistics {
public HashMap<Integer,Integer> connectionsPerPerson(List<CsvData> dataList) {
	HashMap<Integer,Integer> userConnections = new HashMap<Integer,Integer>();
    
    for (CsvData data : dataList) {
    	int localIP = data.getLocalIP();
    	int flows = data.getFlows();
    	
    	userConnections.merge(localIP, flows, Integer::sum);
    }
    return userConnections;
}
}

