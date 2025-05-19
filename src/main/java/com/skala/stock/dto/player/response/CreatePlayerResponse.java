package com.skala.stock.dto.player.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skala.stock.domain.Player;

import lombok.Getter;

@Getter
public class CreatePlayerResponse {
    
    private final String playerId;
    private final String playerPw;
    private final Long playerMoney;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    public CreatePlayerResponse(Player player) {
        this.playerId = player.getPlayerId();
        this.playerPw = player.getPlayerPw();
        this.playerMoney = player.getPlayerMoney();
        this.createdAt = player.getCreatedAt();
        this.updatedAt = player.getUpdatedAt();
    }
}
