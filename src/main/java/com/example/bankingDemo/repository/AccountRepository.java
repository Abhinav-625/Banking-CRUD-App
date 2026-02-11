package com.example.bankingDemo.repository;

import com.example.bankingDemo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
