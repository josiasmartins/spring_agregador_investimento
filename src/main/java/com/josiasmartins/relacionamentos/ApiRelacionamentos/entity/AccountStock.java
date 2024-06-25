package com.josiasmartins.relacionamentos.ApiRelacionamentos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_accounts_stocks")
@Data
public class AccountStock {

    @EmbeddedId
    private AccountStockId id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "quantity")
    private Integer quantity;

}
