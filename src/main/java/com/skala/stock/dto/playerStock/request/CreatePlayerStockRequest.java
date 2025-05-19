package com.skala.stock.dto.playerStock.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePlayerStockRequest {
    private String playerId;
    private String playerPw;
    private Long stockId;
    private Long quantity;
}
