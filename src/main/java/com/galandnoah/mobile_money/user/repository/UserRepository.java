package com.galandnoah.mobile_money.user.repository;

import com.galandnoah.mobile_money.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByPhone(String phone);

    @Query(value = """
                    SELECT active FROM users 
                    WHERE phone = :phone
                    """,
    nativeQuery = true)
    Boolean isActive(@Param("phone") String phone);

    Optional<User> findByPhone(String phone);
}
