package com.planroom.app.impl;

import com.planroom.app.model.SessionProfile;
import com.planroom.domain.SessionId;
import com.planroom.domain.UserId;

public record SessionResult(SessionId sessionId, UserId userId, SessionProfile sessionProfile, String jwt, boolean reused) {

}
