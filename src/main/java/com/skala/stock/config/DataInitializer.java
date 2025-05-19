package com.skala.stock.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.skala.stock.domain.Stock;
import com.skala.stock.repository.StockRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initStocks(StockRepository stockRepository) {
        return args -> {
            if (stockRepository.count() == 0) {
                stockRepository.save(Stock.builder().stockName("Skala").price(7000L).build());
                stockRepository.save(Stock.builder().stockName("JAVA를 JAVA!").price(5500L).build());
                stockRepository.save(Stock.builder().stockName("Happy6Team").price(8000L).build());
                stockRepository.save(Stock.builder().stockName("dani").price(130L).build());
                stockRepository.save(Stock.builder().stockName("April").price(20L).build());
                stockRepository.save(Stock.builder().stockName("apple").price(920L).build());
                System.out.println("Init data save succeeded");
            }
        };
    }
}

