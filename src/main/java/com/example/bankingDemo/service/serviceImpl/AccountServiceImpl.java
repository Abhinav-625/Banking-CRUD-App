package com.example.bankingDemo.service.serviceImpl;

import com.example.bankingDemo.dto.AccountDto;
import com.example.bankingDemo.entity.Account;
import com.example.bankingDemo.exception.NegativeInput;
import com.example.bankingDemo.exception.ResourceNotFound;
import com.example.bankingDemo.mapper.Mapper;
import com.example.bankingDemo.repository.AccountRepository;
import com.example.bankingDemo.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto getAccount(String email) {
        Account account = accountRepository.findById(email)
                .orElseThrow(
                        () -> new ResourceNotFound("Account not found with email: " + email)
                );
        return Mapper.toAccountDto(account);
    }

    @Override
    public List<AccountDto> getAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(Mapper::toAccountDto).toList();
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        if(accountDto.getBalance() < 1000) {
            throw new NegativeInput("Initial deposit must be at least 1000");
        }
        if(accountRepository.existsById(accountDto.getEmail())){
            throw new RuntimeException("Account already exists with email: " + accountDto.getEmail());
        }
        Account account = Mapper.toAccount(accountDto);
        return Mapper.toAccountDto(accountRepository.save(account));
    }

    @Override
    public AccountDto deposit(String email, double balance) {
        Account account = accountRepository.findById(email)
                .orElseThrow(
                        () -> new ResourceNotFound("Account not found with email: " + email)
                );
        if(balance < 0){
            throw new NegativeInput("Enter a valid amount to deposit");
        }
        account.setBalance(account.getBalance() + balance);
        return Mapper.toAccountDto(accountRepository.save(account));
    }

    @Override
    public AccountDto withdraw(String email, double balance) {
        Account account = accountRepository.findById(email)
                .orElseThrow(
                        () -> new ResourceNotFound("Account not found with email: " + email)
                );
        if(balance < 0){
            throw new NegativeInput("Enter a valid amount to withdraw");
        }
        if(account.getBalance() < balance) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - balance);
        return Mapper.toAccountDto(accountRepository.save(account));
    }

    @Override
    public String deleteAccount(String email) {
        Account account = accountRepository.findById(email)
                .orElseThrow(
                        () -> new ResourceNotFound("Account not found with email: " + email)
                );
        accountRepository.delete(account);
        return "Account has been deleted";
    }
}
