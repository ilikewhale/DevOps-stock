package com.skala.stock.dto.player.request;

import com.skala.stock.domain.Player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePlayerRequest {
    
    private String playerId;
    private String playerPw;
    private Long playerMoney;

    public Player toEntity(){
        return Player.builder()
        .playerId(playerId)
        .playerPw(playerPw)
        .playerMoney(playerMoney)
        .build();
    }
}
