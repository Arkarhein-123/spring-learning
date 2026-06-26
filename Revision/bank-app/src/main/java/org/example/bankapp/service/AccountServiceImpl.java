package org.example.bankapp.service;

import lombok.RequiredArgsConstructor;
import org.example.bankapp.dto.AccountDto;
import org.example.bankapp.entity.Account;
import org.example.bankapp.mapper.AccountMapper;
import org.example.bankapp.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = accountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Not Found"));
        return accountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found...."));
        double totalAmount = account.getBalance() + amount;
        account.setBalance(totalAmount);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.mapToAccountDto(savedAccount);
    }
}
