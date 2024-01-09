package networkStatisticsCalculator;

/**
 * created a CSV data object that has the properties : date, localIP, remoteASN
 * and flows. This is to contain data from CSV file
 */
public class CsvData {
	private String date;
	private int localIP;
	private int remoteASN;
	private int flows;

	public CsvData(String date, int localIP, int remoteASN, int flows) {
		this.date = date;
		this.localIP = localIP;
		this.remoteASN = remoteASN;
		this.flows = flows;
	}

	//overrode the toString method to view contents of the object
	@Override
	public String toString() {
		return "CsvData{" + "date='" + date + '\'' + ", localIP=" + localIP + ", remoteASN=" + remoteASN + ", flows="
				+ flows + '}';
	}
	
    //to access the localIP property
	public int getLocalIP() {
		return localIP;
	}
    
	//to access the flows property
	public int getFlows() {
		return flows;
	}
}