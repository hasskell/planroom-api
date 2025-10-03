package com.planroom.spi;

import com.planroom.domain.SessionId;
import com.planroom.domain.UserId;

import java.time.Instant;

public class Token {
    public String issueToken(SessionId sid, UserId user, Instant plus) {
        return "";
    }
}
