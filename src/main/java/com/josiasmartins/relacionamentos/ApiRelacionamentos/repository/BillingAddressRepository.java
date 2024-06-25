package com.josiasmartins.relacionamentos.ApiRelacionamentos.repository;

import com.josiasmartins.relacionamentos.ApiRelacionamentos.entity.AccountStock;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.entity.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, UUID> {
}
