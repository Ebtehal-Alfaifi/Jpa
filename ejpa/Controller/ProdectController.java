package com.example.ejpa.Controller;

import com.example.ejpa.ApiResponse.Api;
import com.example.ejpa.Model.Prodect;
import com.example.ejpa.Service.ProdectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor

public class ProdectController {
    private final ProdectService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProducts() {
        List<Prodect> products = productService.getAllProducts();
        return ResponseEntity.status(200).body(products);

    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Prodect product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        productService.addProduct(product);
        return ResponseEntity.status(200).body(new Api("Product added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody @Valid Prodect product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        Boolean isUpdated = productService.updateProduct(id, product);

        if (!isUpdated) {
            return ResponseEntity.status(400).body(new Api("Prodect not found or update failed"));
        }

        return ResponseEntity.status(200).body(new Api("Prodect updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.status(200).body(new Api("Product deleted successfully"));
        }
        return ResponseEntity.status(400).body(new Api("Product not found"));
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyProduct(@RequestParam Integer userId, @RequestParam Integer productId, @RequestParam Integer merchantId) {
        String result = productService.buyProduct(userId, productId, merchantId);
        if (result.equals("Purchase successful")) {
            return ResponseEntity.status(200).body(result);
        }
        return ResponseEntity.status(400).body(result);
    }




    @PostMapping("/apply-discount")
    public ResponseEntity applyDiscount(@RequestParam Integer merchantId, @RequestParam Integer productId, @RequestParam double discountPercentage) {
        double discountedPrice = productService.applyDiscount(merchantId, productId, discountPercentage);
        if (discountedPrice != -1) {
            return ResponseEntity.status(200).body( new Api("Discount applied successfully. New price: " + discountedPrice));
        }
        return ResponseEntity.status(400).body("Failed to apply discount");
    }

}













