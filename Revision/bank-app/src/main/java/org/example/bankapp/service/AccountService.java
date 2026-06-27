package org.example.bankapp.service;

import org.example.bankapp.dto.AccountDto;

import java.util.List;

public interface AccountService {
    public AccountDto createAccount(AccountDto accountDto);
    public AccountDto getAccountById(Long id);
    public AccountDto deposit(Long id, double amount);
    public AccountDto withdrawl(Long id, double amount);
    public List<AccountDto> getAllAccount();
    public void deleteById(Long id);
}
