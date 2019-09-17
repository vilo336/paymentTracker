package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import model.ExchangeRates;

import org.springframework.stereotype.Component;

import controller.Controller;

@Component
public class ConsoleUi {

	private Controller controller = new Controller();
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	//user input
	public void inputData() {
		System.out.println("Type input data and press Enter");

		while (true) {
			String inputLine = readLine();
			if (inputLine.equals("quit")) {
				controller.saveDataToFile(controller.getCurrency());
				break;
			}

			if (controller.parseInput(inputLine) == null) {
				System.out.println("Wrong input format");
			} else {
				controller.putPaymentToTheMap(controller.parseInput(inputLine));
			}
		}
		System.exit(0);

	}

	public void listAllPayments() {
		ExchangeRates rates = new ExchangeRates();
		for (Map.Entry<String, Integer> entry : controller.getCurrency().entrySet()) {
			if (entry.getValue() > 0) {
				System.out.print(entry.getKey() + " " + entry.getValue());
				listUsdValues(rates, entry);
			}
		}
	}

	private void listUsdValues(ExchangeRates rates, Map.Entry<String, Integer> entry) {
		if (rates.getExchangeRatesMap().containsKey(entry.getKey())) {
			System.out.println(" (USD " + entry.getValue() * (rates.getExchangeRatesMap().get(entry.getKey())) + ")");
		} else {
			System.out.println("");
		}
	}

	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

}
