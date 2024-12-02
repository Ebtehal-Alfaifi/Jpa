package com.example.ejpa.Service;

import com.example.ejpa.Model.Prodect;
import com.example.ejpa.Model.User;
import com.example.ejpa.Repostry.ProductRepostry;
import com.example.ejpa.Repostry.StockRepostry;
import com.example.ejpa.Repostry.UserRepostry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService {
    private final ProductRepostry productRepository;
    private final UserRepostry userRepository;
    private final StockRepostry stockRepository;
    private final Map<String, Integer> productRatings = new HashMap<>();

    public List<User> getAllUsers() {
        return userRepository.findAll();}

        public void addUser (User user){
            userRepository.save(user);
        }

        public Boolean updateUser (Integer id, User user){
            User oldUser = userRepository.findById(id).orElse(null);
            if (oldUser == null) {
                return false;
            }
            oldUser.setUsername(user.getUsername());
            oldUser.setBalance(user.getBalance());
            oldUser.setRole(user.getRole());
            user.setPassword(user.getPassword());
            user.setEmail(user.getEmail());
            userRepository.save(oldUser);
            return true;

        }

    public String transferBalance(Integer senderId, Integer receiverId, double amount) {
        User sender = userRepository.findById(senderId).orElse(null);
        if (sender == null) {
            return "Sender not found";
        }

        User receiver = userRepository.findById(receiverId).orElse(null);
        if (receiver == null) {
            return "Receiver not found";
        }

        if (sender.getBalance() < amount) {
            return "Insufficient balance";
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        userRepository.save(sender);
        userRepository.save(receiver);

        return "Transfer successful! New balance: " + sender.getBalance();
    }

    private final List<String> favoriteList = new ArrayList<>();

    public List<String> getWishlist() {
        return favoriteList;
    }

    public String addProductToWishlist(Integer userId, Integer productId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "User with ID: " + userId + " not found.";
        }

        Prodect product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return "Product with ID: " + productId + " not found.";
        }

        if (favoriteList.contains(productId.toString())) {
            return "Product is already in the wishlist.";
        }
        favoriteList.add(productId.toString());
        return "Product added to wishlist successfully.";
    }













    }