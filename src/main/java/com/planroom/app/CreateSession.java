package com.planroom.app;

import com.planroom.app.impl.SessionResult;
import com.planroom.domain.SessionId;
import reactor.core.publisher.Mono;

import java.time.Duration;

public interface CreateSession {

    /**
     * Creates session from existing session id, essentially reusing it
     * @param displayName display the name of the user
     * @param existingSessionId existing session id
     * @param sessionTtl session time to live
     * @param tokenTtl token time to live
     * @return session result
     */
    Mono<SessionResult> createSession(String displayName, SessionId existingSessionId, Duration sessionTtl, Duration tokenTtl);

    /**
     * Creates new session
     * @param displayName display the name of the user
     * @param sessionTtl session time to live
     * @param tokenTtl token time to live
     * @return session result
     */
    Mono<SessionResult> createSession(String displayName, Duration sessionTtl, Duration tokenTtl);
}
