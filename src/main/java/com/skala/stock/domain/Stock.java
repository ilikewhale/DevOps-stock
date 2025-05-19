package com.skala.stock.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "stock_name", nullable = false, unique = true, length = 255)
    private String stockName;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "change_rate")
    private Double changeRate;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Stock(String stockName, Long price) {
        this.stockName = stockName;
        this.price = price;
        this.changeRate = 0.0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updatePrice(Long newPrice) {
        this.price = newPrice;
    }

    public void updateChangeRate(Double newChangeRate) {
        this.changeRate = newChangeRate;
    }
}
