package com.example.ejpa.Controller;

import com.example.ejpa.ApiResponse.Api;
import com.example.ejpa.Model.Stock;
import com.example.ejpa.Service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor

public class StockController {
    private final StockService stockService;


    @GetMapping("/get")
    public ResponseEntity getAllStocks() {
        List<Stock> stocks=stockService.getAll();
        return ResponseEntity.status(200).body(stocks);
    }

    @PostMapping("/add")
    public ResponseEntity addStock(@RequestBody @Valid Stock stock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        stockService.addStock(stock);
        return ResponseEntity.status(200).body(new Api("Stock added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStock(@PathVariable Integer id, @RequestBody @Valid Stock stock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdated = stockService.update(id, stock);
        if (isUpdated) {
            return ResponseEntity.status(200).body("Stock updated successfully");
        }
        return ResponseEntity.status(400).body("Stock not found");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStock(@PathVariable Integer id) {
        Boolean isDeleted = stockService.delete(id);
        if (!isDeleted) {
            return ResponseEntity.status(400).body("Stock not found");
        }
        return ResponseEntity.status(200).body("Stock deleted successfully");
    }

    @PostMapping("/add-stock/{productId}/{merchantId}/{additionalStock}")
    public ResponseEntity addAdditionalStock(@PathVariable Integer productId, @PathVariable Integer merchantId, @PathVariable int additionalStock) {
        boolean isAdded = stockService.addAdditionalStock(productId, merchantId, additionalStock);
        if (isAdded) {
            return ResponseEntity.status(200).body("Stock updated successfully");
        }
        return ResponseEntity.status(400).body("Product or Merchant not found");
    }



}
