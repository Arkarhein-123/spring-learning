package org.example.bankapp.controller;

import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.example.bankapp.dto.AccountDto;
import org.example.bankapp.entity.Account;
import org.example.bankapp.service.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bank")
public class AccountController {
    private final AccountServiceImpl accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok().body(accountDto);
    }

    @PostMapping("/create-account")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        AccountDto createdAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteAccountById(@PathVariable("id") Long id){
        accountService.deleteById(id);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable("id") Long id, @RequestBody Map<String,Double> request){
        AccountDto accountDto = accountService.deposit(id,request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdrawl")
    public ResponseEntity<AccountDto> withdrawl(@PathVariable("id") Long id, @RequestBody Map<String,Double> request){
        AccountDto accountDto = accountService.withdrawl(id,request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping("/all-accounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accountDtos = accountService.getAllAccount();
        return ResponseEntity.ok(accountDtos);
    }
}
