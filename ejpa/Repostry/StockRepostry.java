package com.example.ejpa.Repostry;

import com.example.ejpa.Model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepostry extends JpaRepository<Stock,Integer> {
    Optional<Stock> findByProductIdAndMerchantId(Integer productId, Integer merchantId);
}
