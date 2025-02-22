package com.ty.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ty.model.CurrencyConversionRequest;
import com.ty.service.CurrencyService;
import com.ty.exception.CurrencyNotFoundException;

@RestController
@RequestMapping("/api") 
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/rates")
    public ResponseEntity<Map<String, Object>> getExchangeRates(@RequestParam(defaultValue = "USD") String base) {
        return ResponseEntity.ok(currencyService.getExchangeRates(base));
    }

    @PostMapping("/convert")
    public ResponseEntity<Map<String, Object>> convertCurrency(@RequestBody CurrencyConversionRequest request) {
        return ResponseEntity.ok(currencyService.convertCurrency(request));
    }

    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCurrencyNotFound(CurrencyNotFoundException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }
}
