package com.github.caijh.sample.mockito;

import com.github.caijh.sample.mockito.exception.EmptyCredentialsException;

public class AuthenticatorApplication {

    private Authenticator authenticator;

    public AuthenticatorApplication(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    public boolean authenticate(String username, String password) throws EmptyCredentialsException {
        boolean authenticated;
        authenticated = this.authenticator.authenticateUser(username, password);
        return authenticated;
    }

}
