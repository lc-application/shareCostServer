package com.userlogin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {
    boolean existsByUsername(String username);
    List<UserLogin> findByUsernameAndPassword(String username, String password);
    void deleteByUsername(String username);
}
