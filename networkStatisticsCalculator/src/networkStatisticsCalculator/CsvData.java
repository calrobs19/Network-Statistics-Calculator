package networkStatisticsCalculator;

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
	
	@Override    
	public String toString() {
		return "CsvData{" +
			"date='" + date + '\'' +
			", localIP=" + localIP +
			", remoteASN=" + remoteASN +
			", flows=" + flows +'}';    
		
	

}
	public int getLocalIP() {
		return localIP;
	}
	public int getFlows() {
		return flows;
	}
}