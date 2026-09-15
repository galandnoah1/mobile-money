package com.galandnoah.mobile_money.transaction.repository;

import com.galandnoah.mobile_money.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    Transaction findByIdempotencyKey(String idempotencyKey);

    @Query(value = """
                    SELECT COUNT(id) FROM transactions 
                                        WHERE created_at::date = :date
                    """,
    nativeQuery = true)
    Integer findNumberOfTransactions(@Param("date")LocalDate date);
}
