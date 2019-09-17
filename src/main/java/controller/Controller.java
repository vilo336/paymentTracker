package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import model.Payment;

public class Controller {

	private Map<String, Integer> currencyMap = new HashMap<String, Integer>();

	private String fileName = "payments.txt";

	public Controller() {
		currencyMap.putAll(loadDataFromFile());
	}

	public void putPaymentToTheMap(Payment payment) {
		currencyMap.put(payment.getCurrency(), currencyMap.get(payment.getCurrency()) == null ? payment.getAmount() : currencyMap.get(payment.getCurrency()) + payment.getAmount());
	}

	public Payment parseInput(String input) {
		String[] inputs = input.split(" ");
		if (inputs == null || inputs.length != 2 || inputs[0].length() != 3 || !isInteger(inputs[1])) {
			return null;
		}
		return new Payment(inputs[0].toUpperCase(), Integer.parseInt(inputs[1]));

	}

	private boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException ex) {
			System.out.println("wrong format of amount");
			return false;
		}
		return true;
	}

	public Map<String, Integer> getCurrency() {
		return currencyMap;
	}

	public void saveDataToFile(final Map<String, Integer> currencyMap) {
		Properties properties = new Properties();

		for (Map.Entry<String, Integer> entry : currencyMap.entrySet()) {
			properties.put(entry.getKey(), String.valueOf(entry.getValue()));
		}

		try {
			properties.store(new FileOutputStream(fileName), null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Integer> loadDataFromFile() {
		Map<String, Integer> currencyMap = new HashMap<String, Integer>();
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("file " + fileName + "  not found");
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (String key : properties.stringPropertyNames()) {
			currencyMap.put(key, Integer.valueOf(properties.get(key).toString()));
		}

		return currencyMap;
	}
}
