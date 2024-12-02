package com.example.ejpa.Repostry;

import com.example.ejpa.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepostry extends JpaRepository<User,Integer> {
}
