package com.skala.stock.dto.playerStock.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skala.stock.domain.Player;
import com.skala.stock.domain.PlayerStock;
import com.skala.stock.domain.Stock;

import lombok.Getter;

//이미 존재하는 player가 보유하고 있는 stock정보를 가져온다. -> "조회"" 작업의 결과를 반환
@Getter
public class PlayerStockResponse {

    private final Long playerStockId;
    private final Player player;
    private final Stock stock;
    private final Long quantity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    public PlayerStockResponse(PlayerStock playerStock) {
        this.playerStockId = playerStock.getPlayerStockId();
        this.player = playerStock.getPlayer();
        this.stock = playerStock.getStock();
        this.quantity = playerStock.getQuantity();
        this.createdAt = playerStock.getCreatedAt();
        this.updatedAt = playerStock.getUpdatedAt();
    }

}
