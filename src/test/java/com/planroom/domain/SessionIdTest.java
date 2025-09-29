package com.planroom.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SessionIdTest {

    public static final String SESSION_ID = "ses_1234567890";

    @Test
    public void test_session_id_null(){
        assertThrows(IllegalArgumentException.class, () -> new SessionId(null));
    }

    @Test
    public void test_session_id_empty(){
        assertThrows(IllegalArgumentException.class, () -> new SessionId(""));
    }

    @Test
    public void test_session_id_prefix_missing(){
        assertThrows(IllegalArgumentException.class, () -> new SessionId("1234567890"));
    }

    @Test
    public void test_session_id_valid(){
        SessionId sessionId = SessionId.of(SESSION_ID);
        String actual = sessionId.toString();
        assert actual.equals(SESSION_ID);
    }
}