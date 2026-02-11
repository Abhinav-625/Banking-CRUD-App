package com.example.bankingDemo.service;

import com.example.bankingDemo.dto.AccountDto;

import java.util.List;

public interface AccountService {
    public AccountDto getAccount(String email);
    public List<AccountDto> getAccounts();
    public AccountDto createAccount(AccountDto accountDto);
    public AccountDto deposit(String email, double balance);
    public AccountDto withdraw(String email, double balance);
    public String deleteAccount(String email);
}
