package com.planroom.app.impl;

import com.planroom.app.CreateSession;
import com.planroom.app.model.SessionProfile;
import com.planroom.app.service.DisplayNameService;
import com.planroom.app.service.IdService;
import com.planroom.domain.SessionId;
import com.planroom.domain.UserId;
import com.planroom.spi.SessionStore;
import com.planroom.spi.Token;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

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

    public record Result(SessionId sessionId, UserId userId, String jwt, SessionProfile profile, boolean reused) {}

    @Override
    public Mono<SessionResult> createSession(String displayName, SessionId existingSessionId, Duration sessionTtl, Duration tokenTtl) {
        if (existingSessionId != null) {
            return sessionStore.get(existingSessionId)
                    .flatMap(sessionRecord -> {
                        var sid = existingSessionId;
                        var user = sessionRecord.userId();
                        var name = displayNameService.normalizeOrDefault(displayName, safeSuffix(user.value()));
                        var profile = SessionProfile.of(name);

                        return sessionStore.update(sid, sessionTtl)
                                .then(Mono.fromCallable(() -> new SessionResult(sid, user, profile,
                                        token.issueToken(sid, user, Instant.now().plus(tokenTtl)),
                                        true
                                        )));
                    })
                    .switchIfEmpty(createSession(displayName, sessionTtl, tokenTtl));
        }
        return createSession(displayName, sessionTtl, tokenTtl);
    }

    @Override
    public Mono<SessionResult> createSession(String displayName, Duration sessionTtl, Duration tokenTtl) {
        var sid = idService.newSessionId();
        var user = idService.newUserId();
        var name = displayNameService.normalizeOrDefault(displayName, safeSuffix(user.value()));
        var profile = SessionProfile.of(name);

        return sessionStore.update(sid, sessionTtl)
                .then(Mono.fromCallable(() -> new SessionResult(sid, user, profile,
                        token.issueToken(sid, user, Instant.now().plus(tokenTtl)),
                        true
                )));
    }

    private static String safeSuffix(String s) {
        int n = s == null ? 0 : s.length();
        return n <= 4 ? (s == null ? "0000" : s) : s.substring(n - 4);
    }
}
