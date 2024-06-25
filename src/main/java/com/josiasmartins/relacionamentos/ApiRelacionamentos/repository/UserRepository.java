package com.josiasmartins.relacionamentos.ApiRelacionamentos.repository;

import com.josiasmartins.relacionamentos.ApiRelacionamentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
