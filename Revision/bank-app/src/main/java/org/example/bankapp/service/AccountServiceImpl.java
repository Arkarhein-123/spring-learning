package org.example.bankapp.service;

import lombok.RequiredArgsConstructor;
import org.example.bankapp.dto.AccountDto;
import org.example.bankapp.entity.Account;
import org.example.bankapp.exception.AccountException;
import org.example.bankapp.exception.InsufficientBalanceException;
import org.example.bankapp.mapper.AccountMapper;
import org.example.bankapp.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

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
        Account account = accountRepository.findById(id).orElseThrow(()-> new AccountException("Account Not Found"));
        return accountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountException("Account Not Found...."));
        double totalAmount = account.getBalance() + amount;
        account.setBalance(totalAmount);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdrawl(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() ->new AccountException("Account Not found"));
        if(amount > account.getBalance()) throw new InsufficientBalanceException("Exceed Amount. Cant' withdrawl..");
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(accountMapper::mapToAccountDto).toList();
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}
