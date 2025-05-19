package com.skala.stock.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skala.stock.dto.stockTransaction.StockTransactionResponse;
import com.skala.stock.service.StockTransactionService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/transaction")
public class StockTransactionController {

    private final StockTransactionService transactionService;

    @Operation(summary = "Find all transaction", description = "Find all transaction")
    @GetMapping
    public ResponseEntity<List<StockTransactionResponse>> findAll() {
        List<StockTransactionResponse> transactionResponses = transactionService.findAll();
        return ResponseEntity.ok()
                .body(transactionResponses);
    }

    @Operation(summary = "Find player transaction", description = "Find player transaction")
    @GetMapping("/player/{playerId}/all")
    public ResponseEntity<List<StockTransactionResponse>> findAllByPlayerId(@PathVariable(name = "playerId") String playerId) {
        List<StockTransactionResponse> transactionResponses = transactionService.findByPlayerId(playerId);
        return ResponseEntity.ok()
                .body(transactionResponses);
    }

    @Operation(summary = "Find player buy transaction", description = "Find player buy transaction")
    @GetMapping("/player/{playerId}/buy")
    public ResponseEntity<List<StockTransactionResponse>> findBuyByPlayerId(@PathVariable(name = "playerId") String playerId) {
        List<StockTransactionResponse> transactionResponses = transactionService.findBuyByPlayerId(playerId);
        return ResponseEntity.ok()
                .body(transactionResponses);
    }

    @Operation(summary = "Find player sell transaction", description = "Find player sell transaction")
    @GetMapping("/player/{playerId}/sell")
    public ResponseEntity<List<StockTransactionResponse>> findSellByPlayerId(@PathVariable(name = "playerId") String playerId) {
        List<StockTransactionResponse> transactionResponses = transactionService.findSellByPlayerId(playerId);
        return ResponseEntity.ok()
                .body(transactionResponses);
    }

    @Operation(summary = "Find transaction by stock", description = "Find transaction by stock")
    @GetMapping("/stock/{stockName}/all")
    public ResponseEntity<List<StockTransactionResponse>> findAllByStockId(@PathVariable(name = "stockName") String stockName) {
        List<StockTransactionResponse> transactionResponses = transactionService.findByStockName(stockName);
        return ResponseEntity.ok()
                .body(transactionResponses);
    }

    @Operation(summary = "Find buy transaction by stock", description = "Find buy transaction by stock")
    @GetMapping("/stock/{stockName}/buy")
    public ResponseEntity<List<StockTransactionResponse>> findBuyByStockName(@PathVariable(name = "stockName") String stockName) {
        List<StockTransactionResponse> transactionResponses = transactionService.findBuyByStockName(stockName);
        return ResponseEntity.ok()
                .body(transactionResponses);
    }

    @Operation(summary = "Find sell transaction by stock", description = "Find sell transaction by stock")
    @GetMapping("/stock/{stockName}/sell")
    public ResponseEntity<List<StockTransactionResponse>> findSellByStockName(@PathVariable(name = "stockName") String stockName) {
        List<StockTransactionResponse> transactionResponses = transactionService.findSellByStockName(stockName);
        return ResponseEntity.ok()
                .body(transactionResponses);
    }
}
