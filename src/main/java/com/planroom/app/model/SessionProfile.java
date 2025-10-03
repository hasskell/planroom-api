package com.planroom.app.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SessionProfile {
    @Getter
    private final String displayName;

    public static SessionProfile of(String displayName){
        return new SessionProfile(displayName);
    }
}
