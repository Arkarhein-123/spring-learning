package org.example.bankapp.mapper;

import org.example.bankapp.dto.AccountDto;
import org.example.bankapp.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account mapToAccount(AccountDto accountDto);
    AccountDto mapToAccountDto(Account account);
}
