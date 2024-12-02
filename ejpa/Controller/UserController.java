package com.example.ejpa.Controller;

import com.example.ejpa.ApiResponse.Api;
import com.example.ejpa.Model.User;
import com.example.ejpa.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new Api("add success"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdated = userService.updateUser(id, user);
        if (!isUpdated) {
            return ResponseEntity.status(400).body(new Api("User not found or update failed"));
        }
        return ResponseEntity.status(200).body(new Api("User updated successfully"));
    }


    @PostMapping("/transfer-balance/{senderId}/{receiverId}/{amount}")
    public ResponseEntity<String> transferBalance(@PathVariable Integer senderId, @PathVariable Integer receiverId, @PathVariable double amount) {
        String result = userService.transferBalance(senderId, receiverId, amount);
        if (result.equals("Transfer successful!")) {
            return ResponseEntity.status(200).body(result);
        }
        return ResponseEntity.status(400).body(result);
    }

    @GetMapping("/wishlist")
    public ResponseEntity getWishlist() {
        List<String> wishlist = userService.getWishlist();
        return ResponseEntity.status(200).body(wishlist);
    }

    @PostMapping("/wishlist/add/{userId}/{productId}")
    public ResponseEntity<String> addProductToWishlist(@PathVariable Integer userId, @PathVariable Integer productId) {
        String result = userService.addProductToWishlist(userId, productId);
        if (result.equals("Product added to wishlist successfully.")) {
            return ResponseEntity.status(200).body(result);
        }
        return ResponseEntity.status(400).body(result);
    }


}
