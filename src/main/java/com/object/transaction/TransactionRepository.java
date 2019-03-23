package com.object.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByFrom(String from);
    Transaction findByFromAndTo(String from, String to);
    boolean existsByFromAndTo(String from, String to);
}
