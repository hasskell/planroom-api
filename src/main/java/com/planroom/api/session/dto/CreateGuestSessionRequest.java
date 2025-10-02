package com.planroom.api.session.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateGuestSessionRequest(
        @Size(min = 2, max = 32, message = "displayName must be 2–32 characters")
        @Pattern(
                regexp = "^[A-Za-z0-9 _.-]+$",
                message = "displayName may contain letters, numbers, space, underscore (_), dot (.), and hyphen (-)"
        )
        String displayName
) {
}
