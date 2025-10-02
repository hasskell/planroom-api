package com.planroom.api.session.dto;

import com.planroom.app.impl.CreateGuestSession;

public record CreateGuestSessionResponse(
        String sessionId,
        String userId,
        String jwt,
        SessionProfile profile
) {
    public record SessionProfile(String displayName){}

    public static CreateGuestSessionResponse from(CreateGuestSession.Result result) {
        return new CreateGuestSessionResponse(
                result.sessionId().value(),
                result.userId().value(),
                result.jwt(),
                new SessionProfile(result.profile().getDisplayName())
        );
    }
}
