package com.skala.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skala.stock.domain.StockTransaction;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {
    List<StockTransaction> findByPlayerIdOrderByGeneratedAtDesc(String playerId);

    List<StockTransaction> findByStockNameOrderByGeneratedAtDesc(String stockName);
}
