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

import com.skala.stock.dto.stock.request.CreateStockRequest;
import com.skala.stock.dto.stock.request.UpdatePriceRequest;
import com.skala.stock.dto.stock.response.CreateStockResponse;
import com.skala.stock.dto.stock.response.StockResponse;
import com.skala.stock.service.StockService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;

    @Operation(summary = "Create stock", description = "Create stock")
    @PostMapping
    public ResponseEntity<CreateStockResponse> createStock(@RequestBody CreateStockRequest request) {
        CreateStockResponse stockResponse = stockService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(stockResponse);
    }

    @Operation(summary = "Find all stocks", description = "Find all stocks")
    @GetMapping
    public ResponseEntity<List<StockResponse>> findAllStocks() {
        List<StockResponse> stockResponses = stockService.findAll();
        return ResponseEntity.ok()
                .body(stockResponses);
    }

    @Operation(summary = "Find stock by stockName", description = "Find stock by stockName")
    @GetMapping("/{stockName}")
    public ResponseEntity<StockResponse> findStockByStockName(@PathVariable(name = "stockName") String stockName) {
        StockResponse stockResponse = stockService.findStockByStockName(stockName);
        return ResponseEntity.ok()
                .body(stockResponse);
    }

    @Operation(summary = "Update price", description = "Update price")
    @PatchMapping("/{stockName}")
    public ResponseEntity<StockResponse> updatePriceByStockName(@PathVariable(name = "stockName") String stockName, @RequestBody UpdatePriceRequest request) {
        StockResponse stockResponse = stockService.updatePriceByName(stockName, request);
        return ResponseEntity.ok()
                .body(stockResponse);
    }

    @Operation(summary = "Delete stock", description = "Delete stock")
    @DeleteMapping("/{stockName}")
    public ResponseEntity<Void> deleteStockByStockName(@PathVariable(name = "stockName") String stockName) {
        stockService.deleteStockByName(stockName);
        return ResponseEntity.ok()
                .build();
    }
}