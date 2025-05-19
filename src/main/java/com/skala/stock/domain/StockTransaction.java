package com.skala.stock.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "generated_at", nullable = false)
    private LocalDateTime generatedAt;

    @Column(name = "player_id", nullable = false)
    private String playerId;

    @Column(name = "stock_name", nullable = false)
    private String stockName;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column(name = "transaction_quantity", nullable = false)
    private Long transactionQuantity;

    @Column(name = "buy_price", nullable = false)
    private Long buyPrice;

    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    @Builder
    public StockTransaction(String playerId, String stockName, String transactionType, Long transactionQuantity, Long buyPrice, Long totalPrice) {
        this.generatedAt = LocalDateTime.now();
        this.playerId = playerId;
        this.stockName = stockName;
        this.transactionType = transactionType;
        this.transactionQuantity = transactionQuantity;
        this.buyPrice = buyPrice;
        this.totalPrice = totalPrice;
    }
}
