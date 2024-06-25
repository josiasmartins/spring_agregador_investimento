package com.josiasmartins.relacionamentos.ApiRelacionamentos.service;

import com.josiasmartins.relacionamentos.ApiRelacionamentos.controller.DTO.CreateStockDTO;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.entity.Stock;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(CreateStockDTO createStockDTO) {

        // DTO -> ENTITY
        Stock stock = new Stock(
                createStockDTO.stockId(),
                createStockDTO.description()
        );

        stockRepository.save(stock);

    }
}
