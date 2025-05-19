package com.skala.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skala.stock.domain.Player;
import com.skala.stock.domain.PlayerStock;
import com.skala.stock.domain.Stock;

public interface PlayerStockRepository extends JpaRepository<PlayerStock, Long> {
    List<PlayerStock> findByPlayer(Player player);

    List<PlayerStock> findByStock(Stock stock);
}

