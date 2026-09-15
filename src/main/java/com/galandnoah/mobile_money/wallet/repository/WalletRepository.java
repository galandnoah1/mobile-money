package com.galandnoah.mobile_money.wallet.repository;

import com.galandnoah.mobile_money.wallet.dto.IWalletResponse;
import com.galandnoah.mobile_money.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    @Query(
            value = """
                    SELECT w.balance, w.currency 
                    FROM wallets w
                    JOIN users u
                    ON w.user_id = u.id
                    WHERE u.phone = :phone                                        
                    """,
            nativeQuery = true
    )
    IWalletResponse getBalance(@Param("phone")String phone);

    @Query(
            value = """
                    SELECT  w.id as id, w.user_id as user_id, w.balance, w.currency, w.created_at, w.updated_at
                                            FROM wallets w
                    JOIN users u ON w.user_id = u.id
                     WHERE u.phone = :phone
                                        """,
            nativeQuery = true
    )
    Optional<Wallet> findByPhone(@Param("phone")String phone);
}
