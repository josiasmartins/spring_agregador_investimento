package com.josiasmartins.relacionamentos.ApiRelacionamentos.service;

import com.josiasmartins.relacionamentos.ApiRelacionamentos.controller.DTO.AccountStockResponseDTO;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.controller.DTO.AssociateAccountStockDTO;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.entity.AccountStock;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.entity.AccountStockId;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.repository.AccountRepository;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.repository.AccountStockRepository;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.repository.StockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private StockRepository stockRepository;
    private AccountStockRepository accountStockRepository;

    public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
    }


    public void associateStock(String accountId, AssociateAccountStockDTO dto) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var stock = stockRepository.findById(dto.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // DTO -> ENTITY
        var id = new AccountStockId(account.getAccountId(), stock.getStockId());
        var entity = new AccountStock(
                id,
                account,
                stock,
                dto.quantity()
        );

        accountStockRepository.save(entity);



    }

    public List<AccountStockResponseDTO> listStocks(String accountId) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        return account.getAccountStocks()
                .stream()
                .map(as ->
                        new AccountStockResponseDTO(as.getStock().getStockId(), as.getQuantity(), 0.0)
                ).toList();
    }
}