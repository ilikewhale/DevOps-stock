package com.skala.stock.dto.playerStock.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateQuantityRequest {
    private Long playerStockId;
    private String playerPw;
    private Long reduceQuantity;
}
