package com.example.ejpa.Repostry;

import com.example.ejpa.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepostry extends JpaRepository<Category,Integer> {
}
