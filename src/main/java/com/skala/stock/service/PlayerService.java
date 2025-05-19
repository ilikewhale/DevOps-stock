package com.skala.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skala.stock.domain.Player;
import com.skala.stock.dto.player.request.AddMoneyRequest;
import com.skala.stock.dto.player.request.CreatePlayerRequest;
import com.skala.stock.dto.player.request.LoginRequest;
import com.skala.stock.dto.player.response.CreatePlayerResponse;
import com.skala.stock.dto.player.response.PlayerResponse;
import com.skala.stock.dto.player.response.UpdateMoneyResponse;
import com.skala.stock.repository.PlayerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public CreatePlayerResponse create(CreatePlayerRequest request){
        return new CreatePlayerResponse(playerRepository.save(request.toEntity()));
    }

    public PlayerResponse findByPlayerId(String playerId){
        return new PlayerResponse(playerRepository.findPlayerByPlayerId(playerId));
    }
    
    public List<PlayerResponse> findAll() {
    List<Player> players = playerRepository.findAll();
    return players.stream().map(PlayerResponse::new).toList();
    }

    public void deleteByPlayerId(String playerId) {
        playerRepository.deleteByPlayerId(playerId);
    }

    public UpdateMoneyResponse addMoneyByPw(String playerId, AddMoneyRequest request) {
        Player player = playerRepository.findPlayerByPlayerId(playerId);
        if (player.getPlayerPw().equals(request.getPlayerPassword())) {
            player.addMoney(request.getExtraPlayerMoney());
            return new UpdateMoneyResponse(player);
        }
        throw new IllegalArgumentException("Incorrect pw");
    }

    public Boolean loginByPw(String playerId, LoginRequest request) {
    Player player = playerRepository.findPlayerByPlayerId(playerId);
    return player.getPlayerPw().equals(request.getPlayerPassword());
    }
    
}
