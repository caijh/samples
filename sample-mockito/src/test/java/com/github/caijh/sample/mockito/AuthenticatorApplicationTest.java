package com.github.caijh.sample.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

class AuthenticatorApplicationTest {

    @Test
    public void testAuthenticate() {
        Authenticator authenticatorMock;
        AuthenticatorApplication authenticator;
        String username = "JavaCodeGeeks";
        String password = "unsafePassword";
        authenticatorMock = Mockito.mock(Authenticator.class);
        authenticator = new AuthenticatorApplication(authenticatorMock);
        when(authenticatorMock.authenticateUser(username, password)).thenReturn(false);

        boolean actual = authenticator.authenticate(username, password);
        assertFalse(actual);
    }

}
