package com.planroom.app.impl;

import com.planroom.app.CreateSession;
import com.planroom.app.service.DisplayNameService;
import com.planroom.app.service.IdService;
import com.planroom.domain.SessionId;
import com.planroom.spi.SessionStore;
import com.planroom.spi.Token;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RequiredArgsConstructor
public class CreateGuestSession implements CreateSession {

    @NonNull
    private SessionStore sessionStore;
    @NonNull
    private Token token;
    @NonNull
    private IdService idService;
    @NonNull
    private DisplayNameService displayNameService;

    @Override
    public Mono<SessionResult> createSession(String displayName, SessionId existingSessionId, Duration sessionTtl, Duration tokenTtl) {
        return null;
    }

    @Override
    public Mono<SessionResult> createSession(String displayName, Duration sessionTtl, Duration tokenTtl) {
        return null;
    }
}
