package com.example.ejpa.Service;

import com.example.ejpa.Model.Stock;
import com.example.ejpa.Repostry.StockRepostry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StockService {
    private final StockRepostry stockRepository;
    public List<Stock> getAll() {
        return stockRepository.findAll();
    }


    public void addStock(Stock stock) {
        stockRepository.save(stock);
    }

    public Boolean update(Integer id, Stock stock) {
        Stock old = stockRepository.getById(id);
        if (old == null) {
            return false;
        }
        old.setProductId(stock.getProductId());
        old.setMerchantId(stock.getMerchantId());
        old.setStock(stock.getStock());
        stockRepository.save(old);
        return true;
    }

    public Boolean delete(Integer id) {
        Stock old = stockRepository.getById(id);
        if (old == null) {
            return false;
        }
        stockRepository.delete(old);
        return true;
    }

    public boolean addAdditionalStock(Integer productId, Integer merchantId, int additionalStock) {
        for (Stock model : stockRepository.findAll()) {
            if (model.getProductId().equals(productId) && model.getMerchantId().equals(merchantId)) {
                model.setStock(model.getStock() + additionalStock);
                stockRepository.save(model);
                return true;
            }
        }
        return false;
    }

    public Stock getStockByProductAndMerchant(Integer merchantID, Integer productid) {
        for (Stock model : stockRepository.findAll()) {
            if (model.getMerchantId().equals(merchantID) && model.getProductId().equals(productid)) {
                return model;
            }
        }
        return null;
    }

}
