package com.galandnoah.mobile_money.user.service;

import com.galandnoah.mobile_money.exceptiom.AccountAlreadyExist;
import com.galandnoah.mobile_money.user.dto.CreateUser;
import com.galandnoah.mobile_money.user.dto.UserResponse;
import com.galandnoah.mobile_money.user.entity.User;
import com.galandnoah.mobile_money.user.mapper.UserMapper;
import com.galandnoah.mobile_money.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Create new user account
     * */
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public UserResponse create(CreateUser createUser)
    {
        log.info("Creating new user");

        User user = userMapper.toEntity(createUser);

        if (userRepository.existsByPhone(createUser.phone()))
        {
            log.warn("Phone number already used");
            throw new AccountAlreadyExist();
        }

        user.setActive(true);

        user.setVerified(false);

        user.setRole(createUser.role());

        User created = userRepository.save(user);

        log.info("New user created: {}", createUser.phone());

        return userMapper.toDTO(created);
    }

    /**
     * Get user account by phone number
     * */
    @Cacheable(value = "users", key = "#phone")
    public UserResponse findByPhone(String phone)
    {
        User user = userRepository.findByPhone(phone)
                .orElseThrow(()-> new EntityNotFoundException("Aucun compte n'est associé à ce numero"));

        return userMapper.toDTO(user);
    }

    /**
     * Deactivate a user account
     * */
    @Transactional
    @CachePut(value = "users", key = "#phone")
    public UserResponse deactivate(String phone)
    {
        log.info("Deactivating a user account: {}", phone);

        User user = userRepository.findByPhone(phone)
                .orElseThrow(()-> new EntityNotFoundException("Aucun compte n'est associé à ce numero"));

        user.setActive(false);

        User updated = userRepository.save(user);

        return userMapper.toDTO(updated);
    }



}
