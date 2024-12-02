package com.example.ejpa.Service;

import com.example.ejpa.Model.Merchant;
import com.example.ejpa.Repostry.MerchantRepostry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantRepostry merchantRepository;

    public List<Merchant> getAll() {
        return merchantRepository.findAll();
    }

    public void addNew(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    public Boolean update(Integer id, Merchant merchant) {
        Merchant old = merchantRepository.getById(id);
        if (old == null) {
            return false;
        }
        old.setName(merchant.getName());

        merchantRepository.save(old);
        return true;
    }


    public Boolean delete(Integer id) {
        Merchant old = merchantRepository.getById(id);
        if (old == null) {
            return false;
        }
        merchantRepository.delete(old);
        return true;
    }
}


