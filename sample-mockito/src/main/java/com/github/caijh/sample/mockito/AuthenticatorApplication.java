package com.github.caijh.sample.mockito;

public class AuthenticatorApplication {

    private Authenticator authenticator;

    public AuthenticatorApplication(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    public boolean authenticate(String username, String password) {
        boolean authenticated;
        authenticated = this.authenticator.authenticateUser(username, password);
        return authenticated;
    }

}
