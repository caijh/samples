package com.github.caijh.sample.mockito;

import com.github.caijh.sample.mockito.exception.EmptyCredentialsException;

public interface Authenticator {

    boolean authenticateUser(String username, String password) throws EmptyCredentialsException;

}
