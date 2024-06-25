package com.josiasmartins.relacionamentos.ApiRelacionamentos.controller;

import com.josiasmartins.relacionamentos.ApiRelacionamentos.controller.DTO.AccountStockResponseDTO;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.controller.DTO.AssociateAccountStockDTO;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.controller.DTO.CreateAccountDTO;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStock(
            @PathVariable("accountId") String accountId,
            @RequestBody AssociateAccountStockDTO dto) {

//        userService.createAccount(userId, createAccountDTO);

        accountService.associateStock(accountId, dto);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDTO>> listaAssociateStock(
            @PathVariable("accountId") String accountId) {

//        userService.createAccount(userId, createAccountDTO);

        var stocks = accountService.listStocks(accountId);

        return ResponseEntity.ok(stocks);

    }

}
