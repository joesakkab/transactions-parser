package com.progressoft.induction.transactionsparser;

import java.io.File;
import java.util.List;

public class Main {

	// This is the testing method that I used.
	public static void main (String[] args) {
		// Create new CSVTransactionParser Object
		CSVTransactionParser csvParser = new CSVTransactionParser();
		// Create new file.
		File csvFile = new File("src/main/resources/transactions.csv");
		// Assign csvList to return output of parse method.
		List<Transaction> csvList = csvParser.parse(csvFile);
		// Print list for testing.
		csvParser.toString(csvList);
		
		
		// Same as above for XMLTransactionParser
		XMLTransactionParser xmlParser = new XMLTransactionParser();
		File xmlFile = new File("src/main/resources/transactions.xml");
		List<Transaction> xmlList = xmlParser.parse(xmlFile);
		xmlParser.toString(xmlList);
	}
}
