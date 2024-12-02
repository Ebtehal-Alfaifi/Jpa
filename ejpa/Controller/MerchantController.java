package com.example.ejpa.Controller;

import com.example.ejpa.ApiResponse.Api;
import com.example.ejpa.Model.Merchant;
import com.example.ejpa.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/merchant")
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(merchantService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.addNew(merchant);
        return ResponseEntity.status(200).body(new Api("Merchant added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable Integer id, @RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdated = merchantService.update(id, merchant);
        if (isUpdated) {
            return ResponseEntity.status(200).body("Merchant updated successfully");
        }
        return ResponseEntity.status(400).body("Merchant not found");
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable Integer id) {
        Boolean isDeleted = merchantService.delete(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body("Merchant deleted successfully");
        }
        return ResponseEntity.status(404).body("Merchant not found");
    }




}
