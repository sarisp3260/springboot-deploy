package com.misiontic.account_ms.controllers;

import com.misiontic.account_ms.exceptions.AccountNotFoundException;
import com.misiontic.account_ms.models.Account;
import com.misiontic.account_ms.repositories.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;


    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

        //Seed
        Account newAccount01 = new Account("001", 500000, new Date());
        Account newAccount02 = new Account("002", 100000, new Date());

        this.accountRepository.save(newAccount01);
        this.accountRepository.save(newAccount02);
    }

    @GetMapping("/accounts/{userId}")
    Account getAccount(@PathVariable String userId){
        return accountRepository.findById(userId)
                .orElseThrow(() -> new AccountNotFoundException("No se encontro una cuenta con el userId: " + userId));
    }
}
