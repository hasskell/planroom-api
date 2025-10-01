package com.planroom.spi.impl;

import com.planroom.app.model.SessionProfile;
import com.planroom.domain.SessionId;
import com.planroom.domain.UserId;
import com.planroom.spi.SessionStore;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Primary
public class InMemorySessionStore implements SessionStore {

    private final Map<String, SessionRecord> sessions;

    public InMemorySessionStore() {
        this.sessions = new ConcurrentHashMap<>();
    }

    @Override
    public Mono<SessionRecord> add(SessionId sessionId, UserId userId, SessionProfile sessionProfile, Duration ttl) {
        Instant now = Instant.now();
        return Mono.fromSupplier(() -> {
            return getSessionRecord(sessionId, userId, sessionProfile, now);
        });
    }

    private SessionRecord getSessionRecord(SessionId sessionId, UserId userId, SessionProfile sessionProfile, Instant now) {
        var existing = sessions.get(sessionId.value());
        var record = (existing == null)
                ? new SessionRecord(sessionId, userId, sessionProfile.getDisplayName(), now, now)
                : new SessionRecord(existing.sessionId(), existing.userId(), sessionProfile.getDisplayName(), existing.accessedAt(), now);
        sessions.put(sessionId.value(), record);
        return record;
    }

    @Override
    public Mono<SessionRecord> get(SessionId sessionId) {
        var record = sessions.get(sessionId.value());
        return record == null ? Mono.empty() : Mono.just(record);
    }

    @Override
    public Mono<Void> update(SessionId sessionId, Duration ttl) {
        var record = sessions.get(sessionId.value());
        if (record != null) {
            sessions.put(sessionId.value(), new SessionRecord(sessionId, record.userId(), record.displayName(), record.createdAt(), record.accessedAt()));
        }
        return Mono.empty();
    }
}
