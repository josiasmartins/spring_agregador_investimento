package com.josiasmartins.relacionamentos.ApiRelacionamentos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

//@Entity
//@Table(name = "tb_account_stock_id")
@Embeddable
@Data
public class AccountStockId {

    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "stock_id")
    private String stockId;

}
