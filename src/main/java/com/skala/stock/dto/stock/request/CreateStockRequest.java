package com.skala.stock.dto.stock.request;

import com.skala.stock.domain.Stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateStockRequest {
    private String stockName;
    private Long price;

    public Stock toEntity() {
        return Stock.builder()
                .stockName(stockName)
                .price(price)
                .build();
    }
}
