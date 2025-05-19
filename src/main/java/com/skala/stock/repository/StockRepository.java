package com.skala.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skala.stock.domain.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByStockName(String stockName);
}
