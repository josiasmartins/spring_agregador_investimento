package com.josiasmartins.relacionamentos.ApiRelacionamentos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_accounts")
@Data
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.UUID) // quando persistir, se o campo não estiver preenchido, ele gerará um valor aleatório.
    private UUID accountId;

    @ManyToOne
    @JoinColumn(name = "user_id") // cria uma coluna user_id como a FOR KEY
    private User user;

    @OneToOne(mappedBy = "account")
    @PrimaryKeyJoinColumn // passa a PRIMARY KEY para BillingAddress
    private BillingAddress billingAddress;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "account")
    private List<AccountStock> accountStocks;

}
