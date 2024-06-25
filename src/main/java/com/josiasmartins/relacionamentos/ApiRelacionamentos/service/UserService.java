package com.josiasmartins.relacionamentos.ApiRelacionamentos.service;

import com.josiasmartins.relacionamentos.ApiRelacionamentos.controller.DTO.AccountResponseDTO;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.controller.DTO.CreateAccountDTO;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.controller.DTO.CreateUserDto;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.controller.DTO.UpdateUserDto;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.entity.Account;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.entity.BillingAddress;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.entity.User;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.repository.AccountRepository;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.repository.BillingAddressRepository;
import com.josiasmartins.relacionamentos.ApiRelacionamentos.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private BillingAddressRepository billingAddressRepository;

    public UserService(
            UserRepository userRepository,
            AccountRepository accountRepository,
            BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {

        // DTO -> ENTITY
        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                null,
                Instant.now(),
                null);

        var userSaved = userRepository.save(entity);

        return userSaved.getId();
    }

    public Optional<User> getUserById(String userId) {

        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUserById(String userId,
                               UpdateUserDto updateUserDto) {

        var id = UUID.fromString(userId);

        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDto.username() != null) {
                user.setUsername(updateUserDto.username());
            }

            if (updateUserDto.password() != null) {
                user.setPassword(updateUserDto.password());
            }

            userRepository.save(user);
        }

    }

    public void deleteById(String userId) {

        var id = UUID.fromString(userId);

        var userExists = userRepository.existsById(id);

        if (userExists) {
            userRepository.deleteById(id);
        }
    }

    public void createAccount(String userId, CreateAccountDTO createAccountDTO) {

        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // DTO -> ENTITY
        Account account = new Account(
                UUID.randomUUID(),
                user,
                null,
                createAccountDTO.description(),
                new ArrayList<>()
        );

        Account accountCreated = accountRepository.save(account);

        BillingAddress billingAddress = new BillingAddress(
                accountCreated.getAccountId(),
                account,
                createAccountDTO.street(),
                createAccountDTO.number()
        );

        billingAddressRepository.save(billingAddress);

    }

    public List<AccountResponseDTO> listAccounts(String userId) {

        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return user.getAccounts()
                .stream()
                .map(ac ->
                        new AccountResponseDTO(ac.getAccountId().toString(), ac.getDescription())
                ).toList();


    }
}