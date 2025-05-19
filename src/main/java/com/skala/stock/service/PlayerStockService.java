package com.skala.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skala.stock.domain.Player;
import com.skala.stock.domain.PlayerStock;
import com.skala.stock.domain.Stock;
import com.skala.stock.domain.StockTransaction;
import com.skala.stock.dto.playerStock.request.CreatePlayerStockRequest;
import com.skala.stock.dto.playerStock.request.DeletePlayerStockRequest;
import com.skala.stock.dto.playerStock.request.UpdateQuantityRequest;
import com.skala.stock.dto.playerStock.response.CreatePlayerStockResponse;
import com.skala.stock.dto.playerStock.response.PlayerStockResponse;
import com.skala.stock.repository.PlayerRepository;
import com.skala.stock.repository.PlayerStockRepository;
import com.skala.stock.repository.StockRepository;
import com.skala.stock.repository.StockTransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class PlayerStockService {

    private final PlayerRepository playerRepository;
    private final StockRepository stockRepository;
    private final PlayerStockRepository playerStockRepository;
    private final StockTransactionRepository stockTransactionRepository;

    //주식 구매 로직
    public CreatePlayerStockResponse create(CreatePlayerStockRequest request) {
        //플레이어와 주식 정보 찾기
        Player player = playerRepository.findPlayerByPlayerId(request.getPlayerId());
        Stock stock = stockRepository.findById(request.getStockId())
                .orElseThrow(() -> new IllegalArgumentException("NOT FOUND STOCK " + request.getStockId()));
        //비밀번호 확인
        if (!player.getPlayerPw().equals(request.getPlayerPw())) {
            throw new IllegalArgumentException("Incorrect pw");
        }
        //돈 지불 처리
        player.payMoney(request.getQuantity() * stock.getPrice());
        playerRepository.save(player);
        //기존 주식 보유 확인
        List<PlayerStock> playerStocks = playerStockRepository.findByPlayer(player);
        PlayerStock existingPlayerStock = null; 
        //거래 기록 저장
        stockTransactionRepository.save(
                StockTransaction.builder()
                        .playerId(player.getPlayerId())
                        .stockName(stock.getStockName())
                        .transactionType("매입")
                        .transactionQuantity(request.getQuantity())
                        .buyPrice(stock.getPrice())
                        .totalPrice(stock.getPrice() * request.getQuantity())
                        .build()
        );

        //기존 보유 주식 확인 -> 현재 구매하려는 주식과 동일한 주식을 이미 가지고 있는지 찾고, 찾았다면 existingPlayerStock 변수에 저장
        if (playerStocks != null) {
            for (PlayerStock ps : playerStocks) {
                if (ps.getStock().getStockId().equals(request.getStockId())) {
                    existingPlayerStock = ps;
                    break;
                }
            }
            //플레이어가 이미 해당 주식을 가지고 있다면 → 수량만 추가해서 업데이트 된 정보 DTO에 반환
            if (existingPlayerStock != null) {
                existingPlayerStock.addQuantity(request.getQuantity());
                playerStockRepository.save(existingPlayerStock);
                return new CreatePlayerStockResponse(existingPlayerStock);
            }
        }
        // 플레이어가 이 주식을 처음 구매한다면 새로운 PlayerStock 객체를 생성. 플레이어, 주식, 수량 정보를 담아 DTO에 반환.
        PlayerStock newPlayerStock = PlayerStock.builder()
                .player(player)
                .stock(stock)
                .quantity(request.getQuantity())
                .build();
        return new CreatePlayerStockResponse(playerStockRepository.save(newPlayerStock));
    }
    //보유한 주식을 매도(판매)하는 로직 -> 원하는 주식 전체 판매 ex.delete 메소드: 10주 전체를 판매하고 보유 기록 삭제
    public void delete(DeletePlayerStockRequest request) {
        PlayerStock playerStock = playerStockRepository.findById(request.getPlayerStockId())
                .orElseThrow(() -> new IllegalArgumentException("NOT FOUND player stock " + request.getPlayerStockId()));
        Player player = playerStock.getPlayer();
        Stock stock = playerStock.getStock();

        if (!player.getPlayerPw().equals(request.getPlayerPw())) {
            throw new IllegalArgumentException("Incorrect pw");
        }

        player.addMoney(stock.getPrice() * playerStock.getQuantity());
        playerRepository.save(player);

        stockTransactionRepository.save(
                StockTransaction.builder()
                        .playerId(player.getPlayerId())
                        .stockName(stock.getStockName())
                        .transactionType("매도")
                        .transactionQuantity(playerStock.getQuantity())
                        .buyPrice(stock.getPrice())
                        .totalPrice(stock.getPrice() * playerStock.getQuantity())
                        .build()
        );
        //주식 보유 정보 삭제
        playerStockRepository.delete(playerStock);
    }
    
    // 보유한 주식의 일부 판매 ex. updateQuantity 메소드: 3주만 판매하고 7주는 계속 보유
    public PlayerStockResponse updateQuantity(UpdateQuantityRequest request) {
        PlayerStock playerStock = playerStockRepository.findById(request.getPlayerStockId())
                .orElseThrow(() -> new IllegalArgumentException("NOT FOUND player stock " + request.getPlayerStockId()));
        Player player = playerStock.getPlayer();
        Stock stock = playerStock.getStock();

        if (!player.getPlayerPw().equals(request.getPlayerPw())) {
            throw new IllegalArgumentException("Incorrect pw");
        }

        Long reduceQuantity = request.getReduceQuantity();

        playerStock.reduceQuantity(reduceQuantity);
        playerStockRepository.save(playerStock);

        player.addMoney(stock.getPrice() * reduceQuantity);
        playerRepository.save(player);

        stockTransactionRepository.save(
                StockTransaction.builder()
                        .playerId(player.getPlayerId())
                        .stockName(stock.getStockName())
                        .transactionType("매도")
                        .transactionQuantity(reduceQuantity)
                        .buyPrice(stock.getPrice())
                        .totalPrice(stock.getPrice() * reduceQuantity)
                        .build()
        );

        return new PlayerStockResponse(playerStock);
    }

    //모든 플레이어의 주식 보유 정보를 조회
    public List<PlayerStockResponse> findAll() {
        return playerStockRepository.findAll()
                .stream().map(PlayerStockResponse::new).toList();
    }
    //특정 플레이어가 보유한 모든 주식 정보를 조회
    public List<PlayerStockResponse> findStockByPlayer(String playerId) {
        Player player = playerRepository.findPlayerByPlayerId(playerId);
        return playerStockRepository.findByPlayer(player)
                .stream().map(PlayerStockResponse::new).toList();
    }
    //특정 주식을 보유한 모든 플레이어 정보를 조회
    public List<PlayerStockResponse> findPlayerByStock(String stockName) {
        Stock stock = stockRepository.findByStockName(stockName);
        return playerStockRepository.findByStock(stock)
                .stream().map(PlayerStockResponse::new).toList();
    }
    
}

