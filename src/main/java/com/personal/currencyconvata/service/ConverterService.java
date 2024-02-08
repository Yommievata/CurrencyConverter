package com.personal.currencyconvata.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConverterService {

    @Value("${currency.api.key}")
    private String apiKey;

    @Value("${currency.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public ConverterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double convertCurrency(String from, String to, double amount) {
        String query = String.format("%s_%s", from, to);
        String url = String.format("%s?q=%s&compact=ultra&apiKey=%s", apiUrl, query, apiKey);

        ConversionResponse response = restTemplate.getForObject(url, ConversionResponse.class);

        if (response != null && response.containsKey(query)) {
            return response.get(query) * amount;
        } else {
            throw new RuntimeException("Unable to retrieve exchange rate for the specified currencies.");
        }
    }
}
