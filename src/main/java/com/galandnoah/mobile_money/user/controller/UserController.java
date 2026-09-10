package com.galandnoah.mobile_money.user.controller;


import com.galandnoah.mobile_money.user.dto.CreateUser;
import com.galandnoah.mobile_money.user.dto.UserResponse;
import com.galandnoah.mobile_money.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "User account management API")
public class UserController {
    private final UserService userService;

    /**
     * POST /api/v1/users
     * Create new user account
     * */
    @PostMapping()
    @Operation(description = "Create new user account")
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUser createUser)
    {
        log.info("POST /api/v1/users");

        UserResponse userResponse = userService.create(createUser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResponse);
    }

    /**
     * PATCH /api/v1/users?phone=
     * Deactivate user account
     * */
    @PatchMapping()
    @Operation(description = "Deactivate user account")
    public ResponseEntity<UserResponse> deactivate(@RequestParam("phone")String phone)
    {
        log.info("PATCH /api/v1/users?phone={}", phone);

        UserResponse userResponse = userService.deactivate(phone);

        return ResponseEntity.ok(userResponse);
    }

    /**
     * GET /api/v1/users?phone=
     * Fetch user account by phone number
     * */
    @GetMapping()
    @Operation(description = "Fetch user account by phone")
    public ResponseEntity<UserResponse> getUser(@RequestParam("phone")String phone)
    {
        log.info("GET /api/v1/users?phone={}", phone);

        UserResponse userResponse = userService.findByPhone(phone);

        return ResponseEntity.ok(userResponse);
    }
}
