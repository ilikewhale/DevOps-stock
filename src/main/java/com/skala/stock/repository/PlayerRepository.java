package com.skala.stock.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.skala.stock.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findPlayerByPlayerId(String playerId);

    void deleteByPlayerId(String playerId);
}

