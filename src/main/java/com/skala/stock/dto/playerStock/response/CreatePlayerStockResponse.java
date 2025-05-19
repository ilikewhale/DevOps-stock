package com.skala.stock.dto.playerStock.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skala.stock.domain.Player;
import com.skala.stock.domain.PlayerStock;
import com.skala.stock.domain.Stock;

import lombok.Getter;

// player가 처음 stock을 구매했을때 반환되는 응답 DTO -> "생성" 작업의 결과를 반환
@Getter
public class CreatePlayerStockResponse {
    private final Player player;
    private final Stock stock;
    private final Long quantity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    
    public CreatePlayerStockResponse(PlayerStock playerStock) {
        this.player = playerStock.getPlayer();
        this.stock = playerStock.getStock();
        this.quantity = playerStock.getQuantity();
        this.createdAt = playerStock.getCreatedAt();
        this.updatedAt = playerStock.getUpdatedAt();
    }

}
