package com.skala.stock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skala.stock.dto.player.request.AddMoneyRequest;
import com.skala.stock.dto.player.request.CreatePlayerRequest;
import com.skala.stock.dto.player.request.LoginRequest;
import com.skala.stock.dto.player.response.CreatePlayerResponse;
import com.skala.stock.dto.player.response.PlayerResponse;
import com.skala.stock.dto.player.response.UpdateMoneyResponse;
import com.skala.stock.service.PlayerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //생성자 주입을 위한 Lombok 어노테이션
@RestController //JSON 응답을 반환하는 컨트롤러 표시
@RequestMapping("/api/player") //기본 URL 경로 지정
public class PlayerController {
    
    private final PlayerService playerService;

    @Operation(summary = "Create player", description = "Create player")
    
    @PostMapping
    public ResponseEntity<CreatePlayerResponse> createPlayer(@RequestBody CreatePlayerRequest request){
        CreatePlayerResponse playerResponse = playerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(playerResponse);
    }
    @Operation(summary = "Find player by playerId", description = "Find player by playerId")
    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerResponse> findPlayerByPlayerId(@PathVariable(name = "playerId") String playerId) {
        PlayerResponse playerResponse = playerService.findByPlayerId(playerId);
        return ResponseEntity.ok()
                .body(playerResponse);
    }
    @Operation(summary = "Find all players", description = "Find all players")
    @GetMapping
    public ResponseEntity<List<PlayerResponse>> findAllPlayer() {
        List<PlayerResponse> playerResponses = playerService.findAll();
        return ResponseEntity.ok()
                .body(playerResponses);
    }
    @Operation(summary = "Delete player by playerId", description = "Delete player by playerId")
    @DeleteMapping("/{playerId}")
    public ResponseEntity<Void> deletePlayerByPlayerId(@PathVariable(name = "playerId") String playerId) {
        playerService.deleteByPlayerId(playerId);
        return ResponseEntity.ok()
                .build();
    }
    @Operation(summary = "Add Money by password", description = "Add Money by password")
    @PatchMapping("/{playerId}/money/add")
    public ResponseEntity<UpdateMoneyResponse> addMoneyByPassword(@PathVariable(name = "playerId") String playerId, @RequestBody AddMoneyRequest request) {
        UpdateMoneyResponse updateMoneyResponse = playerService.addMoneyByPw(playerId, request);
        return ResponseEntity.ok()
                .body(updateMoneyResponse);
    }
    @Operation(summary = "Login", description = "Login with player ID and password")
    @ApiResponse(responseCode = "200", description = "Login successful")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "404", description = "Player not found")
    @PostMapping("/{playerId}/login")
    public ResponseEntity<Boolean> login(@PathVariable(name = "playerId") String playerId, @RequestBody LoginRequest request) {
        Boolean login = playerService.loginByPw(playerId, request);
        return ResponseEntity.ok()
                .body(login);
    }
}
