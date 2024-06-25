package com.josiasmartins.relacionamentos.ApiRelacionamentos.controller;

import com.josiasmartins.relacionamentos.ApiRelacionamentos.controller.DTO.CreateStockDTO;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/stock")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDTO createUserDTO) {

        stockService.createStock(createUserDTO);

        return ResponseEntity.ok().build();

    }

}
