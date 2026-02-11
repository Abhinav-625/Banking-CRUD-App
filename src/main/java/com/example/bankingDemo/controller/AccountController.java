package com.example.bankingDemo.controller;

import com.example.bankingDemo.dto.AccountDto;
import com.example.bankingDemo.exception.NegativeInput;
import com.example.bankingDemo.exception.ResourceNotFound;
import com.example.bankingDemo.service.serviceImpl.AccountServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AccountController {

    private AccountServiceImpl serviceImpl;
    public AccountController(AccountServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    @GetMapping
    public String home(){
        return "home";
    }

    @GetMapping("/account")
    public String getAccount(Model model, @RequestParam String email){
        try {
            model.addAttribute("account", serviceImpl.getAccount(email));
            return "account";
        }
        catch (ResourceNotFound e){
            model.addAttribute("notFound1", e.getMessage());
            return "home";
        }
    }

    @GetMapping("/all")
    public String getAccounts(Model model){
        List<AccountDto> accountDtos = serviceImpl.getAccounts();
        model.addAttribute("allAccounts", accountDtos);
        return "all-accounts";
    }

    @GetMapping("/register")
    public String register(Model model){
        AccountDto accountDto = new AccountDto();
        model.addAttribute("newAccount",accountDto);
        return "register";
    }

    @PostMapping("/register/new")
    public String createAccount(Model model, @ModelAttribute("newAccount") AccountDto accountDto){
        try {
            serviceImpl.createAccount(accountDto);
            return "new-account";
        }
        catch (NegativeInput e){
            model.addAttribute("negativeInput1", e.getMessage());
            return "register";
        }
        catch (RuntimeException e){
            model.addAttribute("alreadyExists", e.getMessage());
            return "register";
        }
    }

    @PostMapping("/deposit")
    public String deposit(Model model, @RequestParam String email, @RequestParam Double balance){
        try {
            model.addAttribute("updatedAccount", serviceImpl.deposit(email, balance));
            return "deposit";
        }
        catch(ResourceNotFound e){
            model.addAttribute("notFound2", e.getMessage());
            return "home";
        }
        catch(NegativeInput e){
            model.addAttribute("negativeInput2", e.getMessage());
            return "home";
        }
    }

    @PostMapping("/withdraw")
    public String withdraw(Model model, @RequestParam String email, @RequestParam Double balance){
        try {
            model.addAttribute("updatedAccount", serviceImpl.withdraw(email, balance));
            return "withdraw";
        }
        catch (ResourceNotFound e){
            model.addAttribute("notFound3", e.getMessage());
            return "home";
        }
        catch(NegativeInput e){
            model.addAttribute("negativeInput3", e.getMessage());
            return "home";
        }
        catch(RuntimeException e){
            model.addAttribute("insufficient", e.getMessage());
            return "home";
        }
    }

    @PostMapping
    public String deleteAccount(Model model, @RequestParam String email){
        try {
            model.addAttribute("deletedAccount", serviceImpl.deleteAccount(email));
            return "home";
        }
        catch (ResourceNotFound e){
            model.addAttribute("notFound4", e.getMessage());
            return "home";
        }
    }
}
