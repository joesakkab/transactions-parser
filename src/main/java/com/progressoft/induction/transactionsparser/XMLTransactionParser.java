package com.progressoft.induction.transactionsparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLTransactionParser implements TransactionParser{

	// This class converts a List of Transactions into a String.
	// The purpose of this method is for testing the parse method in the Main class.
	public static void toString(List<Transaction> list) {
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			result = result + "\n" + (i + 1) + ": " + list.get(i).toString();
		}
		System.out.println(result);
		
	}

	// implementation of TransactionsParser interface
	@Override
	public List<Transaction> parse(File transactionsFile) {
		
		try {
			// Convert the x,l file into a document.
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(transactionsFile);
			return getAllTransactions(doc);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// Iterate through the different transactions in the xml file.
	public static List<Transaction> getAllTransactions(Document doc) {
		NodeList transactionNodes = doc.getElementsByTagName("Transaction");
		List<Transaction> ls = new ArrayList<Transaction>();
		for (int i = 0; i < transactionNodes.getLength(); i++) {
			Node transactionNode = transactionNodes.item(i);
			ls.add(getTransactionNode(transactionNode));
		}
		return ls;
	}
	
	// Go deeper into the nested nodes and retrieve information about transactions.
	public static Transaction getTransactionNode(Node transactionNode) {
		Transaction t = new Transaction();
		if (transactionNode.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) transactionNode;
			t.setDescription(element.getElementsByTagName("Description").item(0).getTextContent());
			t.setDirection(element.getElementsByTagName("Direction").item(0).getTextContent());
			t.setAmount(new BigDecimal(element.getElementsByTagName("Value").item(0).getTextContent()));
			t.setCurrency(element.getElementsByTagName("Currency").item(0).getTextContent());
		}
		return t;
		
	}
	

}
