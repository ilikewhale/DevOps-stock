package com.skala.stock.dto.player.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddMoneyRequest {
    private String playerPassword;
    private Long extraPlayerMoney;
}
