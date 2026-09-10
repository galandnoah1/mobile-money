package com.galandnoah.mobile_money.wallet.service;

import com.galandnoah.mobile_money.user.entity.User;
import com.galandnoah.mobile_money.user.repository.UserRepository;
import com.galandnoah.mobile_money.wallet.dto.IWalletResponse;
import com.galandnoah.mobile_money.wallet.dto.WalletResponse;
import com.galandnoah.mobile_money.wallet.entity.Wallet;
import com.galandnoah.mobile_money.wallet.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;


    /**
     * Create user's wallet
     * */
    @CacheEvict(value = "wallets", allEntries = true)
    @Transactional
    public void createWallet(String phone)
    {
        log.info("Creating user's wallet: {}", phone);
        User user = userRepository.findByPhone(phone)
                .orElseThrow(()-> new EntityNotFoundException("Aucun compte n'est associé à ce numero"));

        Wallet wallet = Wallet.builder()
                .user(user)
                .balance(new BigDecimal("0.0"))
                .currency("XAF")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        wallet.setUser(user);

      walletRepository.save(wallet);

        log.info("Wallet created");
    }


    /**
     * Find wallet by user phone
     * */
    @Cacheable(value = "wallets", key = "#phone")
    public WalletResponse findWallet(String phone)
    {
        log.info("Fetching user wallet");

        if (!userRepository.existsByPhone(phone))
        {
            throw new EntityNotFoundException("Aucun compte n'est associe a ce numero");
        }

        IWalletResponse iWalletResponse = walletRepository.getBalance(phone);


        return new WalletResponse(iWalletResponse.getBalance(), iWalletResponse.getCurrency());
    }
}
