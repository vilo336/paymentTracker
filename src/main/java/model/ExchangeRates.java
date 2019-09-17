package model;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRates {

	private Map<String, Double> exchangeRatesMap;

	public ExchangeRates() {
		this.exchangeRatesMap = new HashMap<String, Double>();
		fillMapWithExchanges(exchangeRatesMap);
	}
	
	private void fillMapWithExchanges(Map<String, Double> exchangeRatesMap){
		exchangeRatesMap.put("EUR", 0.907);
		exchangeRatesMap.put("GBP", 0.805);
		exchangeRatesMap.put("CAD", 1.325);
		exchangeRatesMap.put("HUF", 303.2);
		exchangeRatesMap.put("JPY", 108.1);
	}

	public Map<String, Double> getExchangeRatesMap() {
		return exchangeRatesMap;
	}
	
}
