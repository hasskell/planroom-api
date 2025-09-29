package com.planroom.domain;

import lombok.NonNull;

/**
 * Record that encapsulates user id
 * @param value userid as string
 */
public record UserId(@NonNull String value) {

    public UserId {
        if (value.isBlank() || !value.startsWith("usr_")) {
            throw new IllegalArgumentException("User id cannot be blank and shout start with prefix usr_");
        }
    }

    @Override
    @NonNull
    public String toString() {
        return value;
    }

    /**
     * Returns user id
     * @param value user id as string
     * @return User id object
     */
    public static UserId of(String value) {
        return new UserId(value);
    }
}
