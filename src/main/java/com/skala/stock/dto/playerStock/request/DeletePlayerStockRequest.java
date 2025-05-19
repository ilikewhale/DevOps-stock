package com.skala.stock.dto.playerStock.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DeletePlayerStockRequest {
    private Long playerStockId;
    private String playerPw;
}
