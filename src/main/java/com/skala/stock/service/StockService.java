package com.skala.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skala.stock.domain.Stock;
import com.skala.stock.dto.stock.request.CreateStockRequest;
import com.skala.stock.dto.stock.request.UpdatePriceRequest;
import com.skala.stock.dto.stock.response.CreateStockResponse;
import com.skala.stock.dto.stock.response.StockResponse;
import com.skala.stock.repository.StockRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StockService {

    private final StockRepository stockRepository;

    public CreateStockResponse create(CreateStockRequest request) {
        return new CreateStockResponse(stockRepository.save(request.toEntity()));
    }

    public List<StockResponse> findAll() {
        return stockRepository.findAll()
                .stream().map(StockResponse::new).toList();
    }

    public StockResponse findStockByStockName(String stockName) {
        return new StockResponse(stockRepository.findByStockName(stockName));
    }

    public StockResponse updatePriceByName(String stockName, UpdatePriceRequest request) {
        Stock stock = stockRepository.findByStockName(stockName);
        stock.updatePrice(request.getNewPrice());
        return new StockResponse(stock);
    }

    public void deleteStockByName(String stockName) {
        Stock stock = stockRepository.findByStockName(stockName);
        stockRepository.delete(stock);
    }
}