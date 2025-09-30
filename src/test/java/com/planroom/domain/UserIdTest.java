package com.planroom.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserIdTest {
    private static final String USER_ID = "usr_123456789";

    @Test
    public void test_userId_is_null() {
        assertThrows(NullPointerException.class, () -> new UserId(null));
    }

    @Test
    public void test_userId_is_empty() {
        assertThrows(IllegalArgumentException.class, () -> new UserId(""));
    }

    @Test
    public void test_userId_is_prefix_missing() {
        assertThrows(IllegalArgumentException.class, () -> new UserId("123456789"));
    }

    @Test
    public void test_userId_is_valid() {
        UserId userId = UserId.of(USER_ID);
        String actual = userId.toString();
        assert actual.equals(USER_ID);
    }
}