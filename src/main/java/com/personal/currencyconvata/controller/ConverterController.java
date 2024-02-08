package com.personal.currencyconvata.controller;

import com.personal.currencyconvata.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ConverterController {

    private final ConverterService converterService;

    @Autowired
    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping("/convertCurrency")
    public double convertCurrency(@RequestParam String from,
                                  @RequestParam String to,
                                  @RequestParam double amount) {
        return converterService.convertCurrency(from, to, amount);
    }
}
