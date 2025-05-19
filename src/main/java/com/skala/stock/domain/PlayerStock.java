package com.skala.stock.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PlayerStock {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_stock_id")
    private Long playerStockId;

    @ManyToOne
    @JoinColumn(name = "id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public PlayerStock(Player player, Stock stock, Long quantity){  // 생성자 이름 수정
        this.player = player;
        this.stock = stock;
        this.quantity = quantity;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void addQuantity(Long quantity){
        if (quantity < 0 ){
            throw new IllegalArgumentException("Incorrect quantity");
        }
        this.quantity = quantity;
    }

    public void reduceQuantity(Long enteredQuantity){
        if (enteredQuantity < 0){
            throw new IllegalArgumentException("Incorrect quantity");
        }

        if (quantity < enteredQuantity){
            throw new IllegalArgumentException("More quantity entered than you have");
        }

        if(quantity.equals(enteredQuantity)){
            throw new IllegalArgumentException("Proceed with all selling");
        }
        this.quantity -= enteredQuantity;
    }
}
