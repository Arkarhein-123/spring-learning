package org.example.bankapp.service;

import org.example.bankapp.dto.AccountDto;

public interface AccountService {
    public AccountDto createAccount(AccountDto accountDto);
    public AccountDto getAccountById(Long id);
    public AccountDto deposit(Long id, double amount);
}
