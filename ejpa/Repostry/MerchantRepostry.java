package com.example.ejpa.Repostry;

import com.example.ejpa.Model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepostry extends JpaRepository<Merchant,Integer> {

}
