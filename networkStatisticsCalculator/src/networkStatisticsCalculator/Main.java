package networkStatisticsCalculator;

import java.util.Scanner;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		FileUtilities fileUtilities = new FileUtilities();
		List<CsvData> dataList = null; // Initialise to null

		int choice;
		{
			do {
				// Display menu
				System.out.println("Menu:");
				System.out.println("1. Read Data File");
				System.out.println("2. Calculate Network Statistics");
				System.out.println("3. Exit");
				System.out.print("Enter your choice: ");

				// Get user input
				choice = scanner.nextInt();

				// Perform actions based on user choice
				switch (choice) {
				case 1:
					// Read Data File
					dataList = readDataFile(fileUtilities, scanner);
					break;
				case 2:
					// Calculate Network Statistics
					calculateNetworkStatistics(fileUtilities, dataList);
					break;
				case 3:
					// Exit
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Invalid choice. Please enter a valid option.");
				}

			} while (choice != 3);

			// Close the scanner
			scanner.close();
		}
	}

	private static List<CsvData> readDataFile(FileUtilities fileUtilities, Scanner scanner) {
		return fileUtilities.readCsvFile("data/PCAP Data.csv");
	}

	private static void calculateNetworkStatistics(FileUtilities fileUtilities, List<CsvData> dataList) {
		if (dataList != null && !dataList.isEmpty()) {
			// Calculate Network Statistics
			fileUtilities.writeToFile(dataList);
			System.out.println("Network statistics calculated and saved to file!");
		} else {
			System.out.println("No data loaded. Please read the data file first.");
		}
	}
}
