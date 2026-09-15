package com.galandnoah.mobile_money.transaction.controller;

import com.galandnoah.mobile_money.transaction.dto.CreateTransaction;
import com.galandnoah.mobile_money.transaction.dto.TransactionResponse;
import com.galandnoah.mobile_money.transaction.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transferMoney(@RequestBody @Valid CreateTransaction createTransaction, HttpServletRequest httpRequest)
    {
        log.info("POST /api/v1/transactions/transfer");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transactionService.transferMoney(createTransaction, httpRequest.getHeader("idempotencyKey")));
    }
}
