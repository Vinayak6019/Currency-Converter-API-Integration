package com.ty.service;

import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ty.model.CurrencyConversionRequest;
import com.ty.exception.CurrencyNotFoundException;

@Service
public class CurrencyService {
    private final RestTemplate restTemplate;
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public CurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> getExchangeRates(String base) {
        if (base == null || base.trim().isEmpty() || base.length() != 3 || !base.matches("[A-Z]+")) {
            throw new CurrencyNotFoundException("Invalid base currency: " + base);
        }

        String url = API_URL + base.toUpperCase();

        try {
            return restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            throw new CurrencyNotFoundException("Failed to fetch exchange rates for: " + base);
        }
    }

    public Map<String, Object> convertCurrency(CurrencyConversionRequest request) {
        Map<String, Object> response = getExchangeRates(request.getFrom());

        if (response == null || !response.containsKey("rates")) {
            throw new CurrencyNotFoundException("Invalid base currency: " + request.getFrom());
        }

        Object ratesObject = response.get("rates");
        if (!(ratesObject instanceof Map)) {
            throw new RuntimeException("Invalid API response format");
        }

        @SuppressWarnings("unchecked")
        Map<String, Double> rateMap = (Map<String, Double>) ratesObject;

        if (!rateMap.containsKey(request.getTo())) {
            throw new CurrencyNotFoundException("Invalid target currency: " + request.getTo());
        }

        double convertedAmount = request.getAmount() * rateMap.get(request.getTo());

        return Map.of(
            "from", request.getFrom(),
            "to", request.getTo(),
            "amount", request.getAmount(),
            "convertedAmount", convertedAmount
        );
    }
}