package com.galandnoah.mobile_money.transaction.service;

import com.galandnoah.mobile_money.exceptiom.AccountNotFoundException;
import com.galandnoah.mobile_money.exceptiom.DeactivatedAccountException;
import com.galandnoah.mobile_money.exceptiom.InsufficientBalanceException;
import com.galandnoah.mobile_money.transaction.dto.CreateTransaction;
import com.galandnoah.mobile_money.transaction.dto.TransactionResponse;
import com.galandnoah.mobile_money.transaction.entity.Transaction;
import com.galandnoah.mobile_money.transaction.enums.TransactionStatus;
import com.galandnoah.mobile_money.transaction.enums.TransactionType;
import com.galandnoah.mobile_money.transaction.mapper.TransactionMapper;
import com.galandnoah.mobile_money.transaction.pattern.FeeFactory;
import com.galandnoah.mobile_money.transaction.repository.LedgerEntryRepository;
import com.galandnoah.mobile_money.transaction.repository.TransactionRepository;
import com.galandnoah.mobile_money.user.repository.UserRepository;
import com.galandnoah.mobile_money.wallet.entity.Wallet;
import com.galandnoah.mobile_money.wallet.repository.WalletRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final LedgerEntryRepository ledgerEntryRepository;
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final FeeFactory feeFactory;
    private final TransactionMapper transactionMapper;

    /**
     * Transfer money from one account to another
     * */
    @Transactional
    public TransactionResponse transferMoney(CreateTransaction createTransaction, HttpServletRequest httpRequest) throws AccountNotFoundException, DeactivatedAccountException, InsufficientBalanceException
    {
        String idempotencyKey = httpRequest.getHeader("idempotencyKey");

        try {
            log.info("{} want to send money to {}", createTransaction.initiatorPhone(), createTransaction.beneficiaryPhone());

            if (!userRepository.existsByPhone(createTransaction.beneficiaryPhone()))
            {
                log.warn("Beneficiary's account doesn't exist");
                throw new AccountNotFoundException();
            }

            Wallet initiatorWallet = walletRepository.findByPhone(createTransaction.initiatorPhone())
                    .orElseThrow(AccountNotFoundException::new);

            if (!userRepository.isActive(createTransaction.beneficiaryPhone()))
            {
                log.warn("Beneficiary's account is not active");
                throw new DeactivatedAccountException();
            }

            BigDecimal transferFee = feeFactory.getFeeStrategy(createTransaction.type())
                    .getFees(createTransaction.amount());

            BigDecimal amountToDebit = transferFee.add(createTransaction.amount());

            if (amountToDebit.doubleValue() > initiatorWallet.getBalance().doubleValue())
            {
                log.warn("Initiator's wallet balance is insufficient");
                throw new InsufficientBalanceException();
            }

            Transaction transaction = transactionMapper.toEntity(createTransaction);

            transaction.setStatus(TransactionStatus.INITIATED);

            transaction.setReference(generateTransactionRef(transaction.getType()));

            log.info("Transaction initiated - {}", transaction.getReference());
            Transaction initiatedTransaction = transactionRepository.save(transaction);


        }catch (DataIntegrityViolationException e)
        {
            log.warn("The same money transfer was created earlier in the database. Returning it");

            Transaction existingTransaction = transactionRepository.findByIdempotencyKey(idempotencyKey);

            return transactionMapper.toDTO(existingTransaction);
        }




        return null;
    }

    /**
     * Return the number of transactions per date
     * */
    @Cacheable(value = "transactions_number", key = "#date")
    public Integer getNumberOfTransactions(LocalDate date)
    {
        return transactionRepository.findNumberOfTransactions(date);
    }


/**
 * Generate unique transaction reference
 * */
private String generateTransactionRef(TransactionType type)
{
    StringBuilder ref = new StringBuilder();


    Integer numberOfTransactions = getNumberOfTransactions(LocalDate.now());


    switch (type)
    {
        case DEPOSIT -> ref.append("CI");
        case PAYMENT -> ref.append("PAY");
        case TRANSFER -> ref.append("PP");
        case WITHDRAWAL -> ref.append("CO");
        default -> throw new RuntimeException("Invalid transaction type");
    }

    ref.append(LocalDate.now().getYear())
            .append(LocalDate.now().getMonthValue())
            .append(LocalDate.now().getDayOfMonth())
            .append(".");

    int round = 5 - numberOfTransactions.toString().length();

    ref.repeat("0", Math.max(0, round));


    return ref.toString();
}

}

