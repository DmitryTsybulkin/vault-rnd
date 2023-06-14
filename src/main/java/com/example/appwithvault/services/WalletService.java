package com.example.appwithvault.services;

import com.example.appwithvault.entities.Wallet;
import com.example.appwithvault.repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    @Transactional
    public Wallet createWallet(BigDecimal initBalance) {
        Wallet wallet = new Wallet(initBalance);
        return walletRepository.save(wallet);
    }

    @Transactional
    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id).orElseThrow(() -> new NullPointerException("Wallet by id not found"));
    }

    @Transactional
    public void sendMoney(Long senderId, Long receiverId, BigDecimal amount) {
        Wallet sender = getWalletById(senderId);
        Wallet receiver = getWalletById(receiverId);
        if (sender.getBalance().min(amount).equals(sender.getBalance())) {
            throw new IllegalArgumentException("Not enough money");
        }
        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));
        log.info("{} sent money to {} successfully", senderId, receiverId);
    }

}
