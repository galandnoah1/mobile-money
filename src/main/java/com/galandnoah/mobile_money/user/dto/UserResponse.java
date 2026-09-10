package com.galandnoah.mobile_money.user.dto;

import java.time.Instant;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String phone,
        String role,
        Boolean active,
        Boolean verified,
        Instant createdAt
) {
}
