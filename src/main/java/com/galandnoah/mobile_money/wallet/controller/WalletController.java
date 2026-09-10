package com.galandnoah.mobile_money.wallet.controller;

import com.galandnoah.mobile_money.wallet.dto.WalletResponse;
import com.galandnoah.mobile_money.wallet.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/wallets")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Wallet management API")
public class WalletController {
    private final WalletService walletService;

    /**
     * GET /api/v1/wallets/users?phone
     * */
    @GetMapping("/users")
    @Operation(description = "Consult user's wallet balance")
    private ResponseEntity<WalletResponse> getWallet(@RequestParam("phone")String phone)
    {
        log.info("GET /api/v1/wallets/users?phone={}", phone);

        WalletResponse walletResponse = walletService.findWallet(phone);

        return ResponseEntity.ok(walletResponse);
    }
}
