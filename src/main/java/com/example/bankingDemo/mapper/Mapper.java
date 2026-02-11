package com.example.bankingDemo.mapper;

import com.example.bankingDemo.dto.AccountDto;
import com.example.bankingDemo.entity.Account;

public class Mapper {

    public static Account toAccount(AccountDto accountDto) {
        return new Account(
                accountDto.getEmail(),
                accountDto.getName(),
                accountDto.getBalance()
        );
    }

    public static AccountDto toAccountDto(Account account){
        return new AccountDto(
                account.getEmail(),
                account.getName(),
                account.getBalance()
        );
    }
}
