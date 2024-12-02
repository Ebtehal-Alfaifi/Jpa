package com.example.ejpa.Service;

import com.example.ejpa.Model.Prodect;
import com.example.ejpa.Model.Stock;
import com.example.ejpa.Model.User;
import com.example.ejpa.Repostry.ProductRepostry;
import com.example.ejpa.Repostry.StockRepostry;
import com.example.ejpa.Repostry.UserRepostry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdectService {
    private final StockRepostry stockRepository;
    private final UserRepostry userRepository;
    private final ProductRepostry productRepository;

    public List<Prodect> getAllProducts() {
        return productRepository.findAll();

    }

    public void addProduct(Prodect productModel) {
        productRepository.save(productModel);
    }

    public Boolean updateProduct(Integer id, Prodect productModel) {
        Prodect old = productRepository.getById(id);
        if (old == null) {
            return false;
        }
        old.setName(productModel.getName());
        old.setPrice(productModel.getPrice());
        old.setCategoryId(productModel.getCategoryId());

        productRepository.save(old);
        return true;
    }

    public Boolean deleteProduct(Integer id) {
        Prodect old = productRepository.getById(id);
        if (old == null) {
            return false;
        }
        productRepository.delete(old);
        return true;
    }

    public Prodect getProductById(Integer id) {
        for (Prodect product : productRepository.findAll()) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public String buyProduct(Integer userId, Integer prodectId, Integer merchantID) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "User not found";
        }
        Prodect product = getProductById(prodectId);
        if (product == null) {
            return "Product not found";
        }
        // METHODE findByProductIdAndMerchantId عرفتها في الاستوك ريبوزتري
        Stock stockModel = stockRepository.findByProductIdAndMerchantId(prodectId, merchantID).orElse(null);
        if (stockModel == null || stockModel.getStock() <= 0) {
            return "Bad request";
        }
        if (user.getBalance() < product.getPrice()) {
            return "You don't have enough money";
        }
        user.setBalance(user.getBalance() - product.getPrice());
        stockModel.setStock(stockModel.getStock() - 1);
        userRepository.save(user);
        stockRepository.save(stockModel);
        return "Purchase successful";
    }

    public double applyDiscount(Integer merchantID, Integer productID, double discountPercentage) {
        Prodect product = getProductById(productID);
        if (product == null) {
            return -1;
        }
        Stock stock = stockRepository.findByProductIdAndMerchantId(productID, merchantID).orElse(null);
        if (stock == null || !stock.getMerchantId().equals(merchantID)) {
            return -1;
        }
        double originalPrice = product.getPrice();
        double discountedPrice = originalPrice - (originalPrice * discountPercentage / 100);
        product.setPrice(discountedPrice);
        productRepository.save(product);
        return discountedPrice;
    }
}





