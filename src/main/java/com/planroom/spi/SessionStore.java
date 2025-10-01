package com.planroom.spi;

import com.planroom.app.model.SessionProfile;
import com.planroom.domain.SessionId;
import com.planroom.domain.UserId;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

public interface SessionStore {

    record SessionRecord(SessionId sessionId, UserId userId, String displayName, Instant createdAt, Instant accessedAt){

    }

    Mono<SessionRecord> add(SessionId sessionId, UserId userId, SessionProfile sessionProfile, Duration ttl);
    Mono<SessionRecord> get(SessionId sessionId);
    Mono<Void> update(SessionId sessionId, Duration ttl);
}
