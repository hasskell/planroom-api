package com.planroom.domain;

import lombok.NonNull;

/**
 * Record that encapsulates session id
 * @param value sessions id as string
 */
public record SessionId(@NonNull String value) {
    public SessionId {
        if (value.isBlank() || !value.startsWith("ses_")) {
            throw new IllegalArgumentException("Session id cannot be blank and shout start with prefix ses_");
        }
    }

    @Override
    @NonNull
    public String toString() {
        return value;
    }

    /**
     * Returns session id
     * @param value sessions id as string
     * @return Session id object
     */
    public static SessionId of(String value) {
        return new SessionId(value);
    }
}
