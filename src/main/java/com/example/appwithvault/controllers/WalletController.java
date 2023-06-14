package com.example.appwithvault.controllers;

import com.example.appwithvault.entities.Wallet;
import com.example.appwithvault.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public Wallet createWallet(@RequestParam("balance") BigDecimal balance) {
        return walletService.createWallet(balance);
    }

    @GetMapping(path = "/{id}")
    public Wallet getWallet(@PathVariable("id") Long id) {
        return walletService.getWalletById(id);
    }

    @PostMapping("/send")
    public void sendMoney(@RequestParam("senderId") Long senderId,
                          @RequestParam("receiverId") Long receiverId,
                          @RequestParam("amount") BigDecimal amount) {
        walletService.sendMoney(senderId, receiverId, amount);
    }

}
