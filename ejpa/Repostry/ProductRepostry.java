package com.example.ejpa.Repostry;

import com.example.ejpa.Model.Prodect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepostry extends JpaRepository<Prodect,Integer> {
}
