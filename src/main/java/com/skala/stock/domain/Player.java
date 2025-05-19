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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) //생성 시간과 수정 시간을 자동으로 관리
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 생성되는 키
    @Column(name = "id")
    private long id;

    @Column(name = "player_id", nullable = false, unique = true, length = 255) //null이 허용되지 않고(nullable = false), 중복 값이 허용되지 않으며(unique = true), 최대 길이가 255
    private String playerId;

    @Column(name = "player_pw", nullable = false, length = 255)
    private String playerPw;

    @Column(name = "player_money")
    private long playerMoney; //long 타입은 큰 범위의 정수를 저장할 수 있다.

    @CreatedDate
    @Column(name = "created_at", updatable = false) //생성 시점 이후 업데이트 불가
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Player(String playerId, String playerPw, Long playerMoney){
        this.playerId = playerId;
        this.playerPw = playerPw;
        this.playerMoney = playerMoney;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void addMoney(Long extraPlayerMoney){
        validateNumber(extraPlayerMoney);
        this.playerMoney += extraPlayerMoney;
    }

    public void payMoney(Long money) {
        validateNumber(money);
        if (playerMoney - money < 0) {
            throw new IllegalArgumentException("More money entered than you have");
        }
        this.playerMoney -= money;
    }

    public void validateNumber(Long num){ //0보다 작은 숫자는 예외를 발생시킨다.
        if (num < 0){
            throw new IllegalArgumentException("validation error in number");
        }
    }

}
