package com.planroom.app.service;

import com.planroom.domain.SessionId;
import com.planroom.domain.UserId;

import java.util.UUID;

public class IdService {

    private IdService() {}

    private static IdService instance;

    public static IdService of(){
        return instance == null ? instance = new IdService() : instance;
    }
    /**
     * @return Returns instance of session id
     */
    public SessionId newSessionId(){
        return SessionId.of("ses_" + UUID.randomUUID());
    }

    /**
     * @return Returns instance of user id
     */
    public UserId newUserId(){
        return UserId.of("usr_" + UUID.randomUUID());
    }
}
