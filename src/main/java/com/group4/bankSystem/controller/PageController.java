package com.group4.bankSystem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // src/main/resources/templates/login.html
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register"; // src/main/resources/templates/register.html
    }

    @GetMapping("/index")
    public String indexPage() {
        return "index"; // src/main/resources/templates/index.html
    }

    @GetMapping("/accounts")
    public String accountsPage() {
        return "account-list"; // src/main/resources/templates/account-list.html
    }

    @GetMapping("/transactions")
    public String transactionsPage() {
        return "transaction-list"; // src/main/resources/templates/transaction-list.html
    }

    @GetMapping("/customers")
    public String customersPage() {
        return "customer-list"; // src/main/resources/templates/customer-list.html
    }

    // Profil sayfasına yönlendirme
    @GetMapping("/profile")
    public String profilePage() {
        return "profile"; // src/main/resources/templates/profile.html
    }

    // İşlem yapma sayfasına yönlendirme
    @GetMapping("/transaction")
    public String showTransactionPage() {
        return "transaction";   // src/main/resources/templates/transaction.html

    }

    @GetMapping("/card-application")
    public String showCartCreatePage() {
        return "card-application";   // src/main/resources/templates/account.html
    }
  }
