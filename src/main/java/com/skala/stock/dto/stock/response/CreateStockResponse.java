package com.skala.stock.dto.stock.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skala.stock.domain.Stock;

import lombok.Getter;

@Getter
public class CreateStockResponse {

    private final Long stockId;
    private final String stockName;
    private final Long price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    public CreateStockResponse(Stock stock) {
        this.stockId = stock.getStockId();
        this.stockName = stock.getStockName();
        this.price = stock.getPrice();
        this.createdAt = stock.getCreatedAt();
        this.updatedAt = stock.getUpdatedAt();
    }
}
