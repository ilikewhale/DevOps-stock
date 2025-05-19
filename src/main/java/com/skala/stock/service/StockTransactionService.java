package com.skala.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skala.stock.domain.StockTransaction;
import com.skala.stock.dto.stockTransaction.StockTransactionResponse;
import com.skala.stock.repository.StockTransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class StockTransactionService {
    private final StockTransactionRepository transactionRepository;

    public List<StockTransactionResponse> findAll() {
        return transactionRepository.findAll()
                .stream().map(StockTransactionResponse::new).toList();
    }

    public List<StockTransactionResponse> findByPlayerId(String playerId) {
        return transactionRepository.findByPlayerIdOrderByGeneratedAtDesc(playerId)
                .stream().map(StockTransactionResponse::new).toList();
    }

    public List<StockTransactionResponse> findBuyByPlayerId(String playerId) {
        return transactionRepository.findByPlayerIdOrderByGeneratedAtDesc(playerId)
                .stream().filter(this::isBuy).map(StockTransactionResponse::new).toList();
    }

    public List<StockTransactionResponse> findSellByPlayerId(String playerId) {
        return transactionRepository.findByPlayerIdOrderByGeneratedAtDesc(playerId)
                .stream().filter(this::isSell).map(StockTransactionResponse::new).toList();
    }

    public List<StockTransactionResponse> findByStockName(String stockName) {
        return transactionRepository.findByStockNameOrderByGeneratedAtDesc(stockName)
                .stream().map(StockTransactionResponse::new).toList();
    }

    public List<StockTransactionResponse> findBuyByStockName(String stockName) {
        return transactionRepository.findByStockNameOrderByGeneratedAtDesc(stockName)
                .stream().filter(this::isBuy).map(StockTransactionResponse::new).toList();
    }

    public List<StockTransactionResponse> findSellByStockName(String stockName) {
        return transactionRepository.findByStockNameOrderByGeneratedAtDesc(stockName)
                .stream().filter(this::isSell).map(StockTransactionResponse::new).toList();
    }

    private boolean isBuy(StockTransaction transaction) {
        return transaction.getTransactionType().equals("매입");
    }

    private boolean isSell(StockTransaction transaction) {
        return transaction.getTransactionType().equals("매도");
    }
}
