package com.skala.stock.dto.player.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skala.stock.domain.Player;

import lombok.Getter;

@Getter
public class PlayerResponse {
    private final String playerId;
    private final Long playerMoney;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    public PlayerResponse(Player player) {
        this.playerId = player.getPlayerId();
        this.playerMoney = player.getPlayerMoney();
        this.createdAt = player.getCreatedAt();
        this.updatedAt = player.getUpdatedAt();
    }
}