package com.skala.stock.dto.player.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skala.stock.domain.Player;

import lombok.Getter;

@Getter
public class UpdateMoneyResponse {
    private final String playerId;
    private final Long playerMoney;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    public UpdateMoneyResponse(Player player) {
        this.playerId = player.getPlayerId();
        this.playerMoney = player.getPlayerMoney();
        this.updatedAt = player.getUpdatedAt();
    }
}
